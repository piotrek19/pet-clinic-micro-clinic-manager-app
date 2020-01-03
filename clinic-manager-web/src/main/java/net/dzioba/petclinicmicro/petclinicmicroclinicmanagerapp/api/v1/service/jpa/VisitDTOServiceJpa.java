package net.dzioba.petclinicmicro.petclinicmicroclinicmanagerapp.api.v1.service.jpa;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.dzioba.petclinicmicro.common.model.PossibleVisitListDTO;
import net.dzioba.petclinicmicro.common.model.RoomReservationShortDTO;
import net.dzioba.petclinicmicro.common.model.VisitDTO;
import net.dzioba.petclinicmicro.common.model.VisitShortDTO;
import net.dzioba.petclinicmicro.petclinicmicroclinicmanagerapp.api.v1.mapper.RoomDailyReservationShortMapper;
import net.dzioba.petclinicmicro.petclinicmicroclinicmanagerapp.api.v1.mapper.RoomShortMapper;
import net.dzioba.petclinicmicro.petclinicmicroclinicmanagerapp.api.v1.mapper.VetShortMapper;
import net.dzioba.petclinicmicro.petclinicmicroclinicmanagerapp.api.v1.mapper.VisitMapper;
import net.dzioba.petclinicmicro.petclinicmicroclinicmanagerapp.api.v1.service.VisitDTOService;
import net.dzioba.petclinicmicro.petclinicmicroclinicmanagerapp.domain.*;
import net.dzioba.petclinicmicro.petclinicmicroclinicmanagerapp.service.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNull;

@Service
@Slf4j
@RequiredArgsConstructor
public class VisitDTOServiceJpa implements VisitDTOService {

    private final RoomDailyReservationService roomDailyReservationService;
    private final RoomReservationService roomReservationService;
    private final RoomService roomService;
    private final VetService vetService;
    private final OwnerService ownerService;
    private final PetService petService;
    private final VisitService visitService;

    private final RoomDailyReservationShortMapper roomDailyReservationShortMapper;
    private final RoomShortMapper roomShortMapper;
    private final VetShortMapper vetShortMapper;
    private final VisitMapper visitMapper;
    private final String className = VisitDTOServiceJpa.class.getName();


    @Override
    @Transactional
    public PossibleVisitListDTO findPossibleVisitsForDate(LocalDate visitDate) {
        log.debug(className + " - findPossibleVisitsForDate for date: " + visitDate);
        requireNonNull(visitDate);

        List<VisitShortDTO> visitShortDTOList = new ArrayList<>();

        List<RoomDailyReservation> roomDailyReservations = roomDailyReservationService.findByDate(visitDate);
        List<Room> roomsUsed = roomDailyReservations.stream().map((RoomDailyReservation::getRoom)).collect(Collectors.toList());
        List<Room> roomsUnused = roomService.findAll();
        roomsUnused.removeAll(roomsUsed);

        // there is at least one visit planed for concrete room/vet, so using the same room/vet (roomDailyReservation) propose related terms:
        roomDailyReservations.forEach((roomDailyReservation)->{
            log.debug(className + " - findPossibleVisitsForDate - using existing roomDailyReservation for processing" );
            EnumSet<RoomReservationStart> possibleReservationStarts = EnumSet.allOf(RoomReservationStart.class);

            roomDailyReservation.getRoomReservations().forEach((roomReservation -> {
                possibleReservationStarts.remove(roomReservation.getReservationStart());
            }));

            possibleReservationStarts.forEach((possibleReservationStart) ->{
                VisitShortDTO visitShortDTO = VisitShortDTO.builder()
                        .dateTime(LocalDateTime.of(visitDate, possibleReservationStart.getTime()))
                        .roomReservation(RoomReservationShortDTO.builder()
                                .room(roomShortMapper.roomToRoomShortDTO(roomDailyReservation.getRoom()))
                                .build())
                        .vet(vetShortMapper.vetToVetShortDTO(roomDailyReservation.getVet()))
                        .build();

                visitShortDTOList.add(visitShortDTO);
            });

        });

        // there is at least one room/vet with no visits planed so far, so take it and propose related terms:
        if (! roomsUnused.isEmpty()){
            log.debug(className + " - findPossibleVisitsForDate - using new roomDailyReservation for processing" );

            roomsUnused.forEach((room -> {

                EnumSet<RoomReservationStart> possibleReservationStarts = EnumSet.allOf(RoomReservationStart.class);
                possibleReservationStarts.forEach((possibleReservationStart) ->{
                    VisitShortDTO visitShortDTO = VisitShortDTO.builder()
                            .dateTime(LocalDateTime.of(visitDate, possibleReservationStart.getTime()))
                            .roomReservation(RoomReservationShortDTO.builder()
                                    .room(roomShortMapper.roomToRoomShortDTO(room))
                                    .build())
                            .vet(vetShortMapper.vetToVetShortDTO(room.getMainVet()))
                            .build();

                    visitShortDTOList.add(visitShortDTO);
                });

            }));

        }

        log.debug(className + " - findPossibleVisitsForDate - returning list of possible visits - list has size: " + visitShortDTOList.size());
        return PossibleVisitListDTO.builder().possibleVisits(visitShortDTOList).build();
    }

    @Transactional
    @Override
    public VisitDTO scheduleThisVisit(VisitDTO visitDTO) {
        log.debug(className + " - scheduleThisVisit for object: " + visitDTO);
        requireNonNull(visitDTO);
        requireNonNull(visitDTO.getDateTime());
        requireNonNull(visitDTO.getOwner());
        requireNonNull(visitDTO.getPet());
        requireNonNull(visitDTO.getVet());
        requireNonNull(visitDTO.getRoomReservation());

        LocalDateTime dateTime = visitDTO.getDateTime();
        List<RoomDailyReservation> roomDailyReservations = roomDailyReservationService.findByDate(dateTime.toLocalDate());

        RoomDailyReservation roomDailyReservation = roomDailyReservations.stream().filter(rDR -> rDR.getRoom().getId()
                .equals(visitDTO.getRoomReservation().getRoom().getId()))
                 .findAny().orElse(null);

        if (roomDailyReservation != null) {
            // roomDailyReservation exist in db case:
            RoomReservation roomReservation  = createRoomReservation(roomDailyReservation, visitDTO);

            Visit visit = createVisit(roomReservation, visitDTO);
            return visitMapper.visitToVisitDTO(visit);
        }

        // roomDailyReservation doesn't exist in db case:
        roomDailyReservation = saveRoomDailyReservation(visitDTO);

        RoomReservation roomReservation  = createRoomReservation(roomDailyReservation, visitDTO);

        Visit visit = createVisit(roomReservation, visitDTO);
        return visitMapper.visitToVisitDTO(visit);
    }

    @Transactional
    public Visit createVisit(RoomReservation roomReservation, VisitDTO visitDTO) {
        requireNonNull(roomReservation);
        requireNonNull(visitDTO);

        Visit newVisit = Visit
                .builder()
                .dateTime(visitDTO.getDateTime())
                .owner(ownerService.findById(visitDTO.getOwner().getId()).orElseThrow(IllegalArgumentException::new))
                .pet(petService.findById(visitDTO.getPet().getId()).orElseThrow(IllegalArgumentException::new))
                .vet(vetService.findById(visitDTO.getVet().getId()).orElseThrow(IllegalArgumentException::new))
                .roomReservation(roomReservationService.findById(roomReservation.getId()).orElseThrow(IllegalArgumentException::new))
                .description(visitDTO.getDescription())
                .build();

        Visit savedVisit = visitService.save(newVisit);

        RoomReservation savedRoomReservation =  savedVisit.getRoomReservation();
        savedRoomReservation.setVisit(savedVisit);
        roomReservationService.save(savedRoomReservation);

        return savedVisit;
    }

    @Transactional
    public RoomReservation createRoomReservation(RoomDailyReservation roomDailyReservation, VisitDTO visitDTO) {
        requireNonNull(roomDailyReservation);
        requireNonNull(visitDTO);

        RoomReservation newRoomReservation = RoomReservation
                .builder()
                .visit(new Visit()) //todo: tbc
                .reservationStart(RoomReservationStart.getFromLocalTime(visitDTO.getDateTime().toLocalTime()))
                .roomDailyReservation(roomDailyReservation)
                .room(roomDailyReservation.getRoom())
                .build();

        return roomReservationService.save(newRoomReservation);
    }

    private RoomDailyReservation saveRoomDailyReservation(VisitDTO visitDTO) {
        requireNonNull(visitDTO);

        RoomDailyReservation newRoomDailyReservation = RoomDailyReservation
                .builder()
                .date(visitDTO.getDateTime().toLocalDate())
                .room(roomService.findById(visitDTO.getRoomReservation().getRoom().getId()).orElseThrow(IllegalArgumentException::new))
                .vet(vetService.findById(visitDTO.getVet().getId()).orElseThrow(IllegalArgumentException::new))
                .build();

        RoomDailyReservation savedRoomDailyReservation = roomDailyReservationService.save(newRoomDailyReservation);
        return savedRoomDailyReservation;
    }


}
