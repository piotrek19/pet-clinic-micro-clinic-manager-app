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
import net.dzioba.petclinicmicro.petclinicmicroclinicmanagerapp.api.v1.service.VisitDTOService;
import net.dzioba.petclinicmicro.petclinicmicroclinicmanagerapp.domain.Room;
import net.dzioba.petclinicmicro.petclinicmicroclinicmanagerapp.domain.RoomDailyReservation;
import net.dzioba.petclinicmicro.petclinicmicroclinicmanagerapp.domain.RoomReservationStart;
import net.dzioba.petclinicmicro.petclinicmicroclinicmanagerapp.service.RoomDailyReservationService;
import net.dzioba.petclinicmicro.petclinicmicroclinicmanagerapp.service.RoomService;
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
    private final RoomService roomService;

    private final VetShortMapper vetShortMapper;
    private final RoomDailyReservationShortMapper roomDailyReservationShortMapper;
    private final RoomShortMapper roomShortMapper;
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

        // roomDailyReservation exist in db
        roomDailyReservations.stream().filter(roomDailyReservation -> roomDailyReservation.getRoom().getId()
                .equals(visitDTO.getRoomReservation().getRoom().getId())
        ).findAny().ifPresent(roomDailyReservation -> {

            // createRoomReservation() invocation

            // createVisit() invocation
            // and return
        });

        // roomDailyReservation doesn't exist in db - create one:
        // createRoomDailyReservation() invocation

        // createRoomReservation() invocation

        // createVisit() invocation

        // and return

        return null;
    }


}
