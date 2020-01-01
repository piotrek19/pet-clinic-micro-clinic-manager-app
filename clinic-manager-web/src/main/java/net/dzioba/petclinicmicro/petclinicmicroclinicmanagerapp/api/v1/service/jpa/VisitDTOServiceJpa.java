package net.dzioba.petclinicmicro.petclinicmicroclinicmanagerapp.api.v1.service.jpa;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.dzioba.petclinicmicro.common.model.PossibleVisitListDTO;
import net.dzioba.petclinicmicro.common.model.RoomReservationShortDTO;
import net.dzioba.petclinicmicro.common.model.VisitShortDTO;
import net.dzioba.petclinicmicro.petclinicmicroclinicmanagerapp.api.v1.mapper.RoomDailyReservationShortMapper;
import net.dzioba.petclinicmicro.petclinicmicroclinicmanagerapp.api.v1.mapper.VetShortMapper;
import net.dzioba.petclinicmicro.petclinicmicroclinicmanagerapp.api.v1.service.VisitDTOService;
import net.dzioba.petclinicmicro.petclinicmicroclinicmanagerapp.domain.RoomReservation;
import net.dzioba.petclinicmicro.petclinicmicroclinicmanagerapp.domain.RoomReservationStart;
import net.dzioba.petclinicmicro.petclinicmicroclinicmanagerapp.service.RoomDailyReservationService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import static java.util.Objects.requireNonNull;

@Service
@Slf4j
@RequiredArgsConstructor
public class VisitDTOServiceJpa implements VisitDTOService {

    private final RoomDailyReservationService roomDailyReservationService;

    private final VetShortMapper vetShortMapper;
    private final RoomDailyReservationShortMapper roomDailyReservationShortMapper;
    private final String className = VisitDTOServiceJpa.class.getName();


    @Override
    public PossibleVisitListDTO findPossibleVisitsForDate(LocalDate visitDate) {
        log.debug(className + "- findPossibleVisitsForDate for date: " + visitDate);
        requireNonNull(visitDate);
        List<VisitShortDTO> visitShortDTOList = new ArrayList<>();

        // there is at least one visit planed for this date, so use the same room/vet (roomDailyReservation) for propositions:
        roomDailyReservationService.findByDate(visitDate).forEach((roomDailyReservation)->{
            log.debug(className + "- findPossibleVisitsForDate - using existing roomDailyReservation for processing" );
            EnumSet<RoomReservationStart> possibleReservationStarts = EnumSet.allOf(RoomReservationStart.class);

            List<RoomReservation> roomReservations = roomDailyReservation.getRoomReservations();
            roomReservations.forEach((roomReservation -> {
                possibleReservationStarts.remove(roomReservation.getReservationStart());
            }));


            possibleReservationStarts.forEach((possibleReservationStart) ->{
                VisitShortDTO visitShortDTO = VisitShortDTO.builder()
                        .dateTime(LocalDateTime.of(visitDate, possibleReservationStart.getTime()))
                        .roomReservation(RoomReservationShortDTO.builder()
                                .roomDailyReservation(roomDailyReservationShortMapper.roomDailyReservationToRoomDailyReservationShortDTO(roomDailyReservation))
                                .build())
                        .vet(vetShortMapper.vetToVetShortDTO(roomDailyReservation.getVet()))
                        .build();

                visitShortDTOList.add(visitShortDTO);
            });

        });

        // there is no visits planed for this date, so take first accessible room/vet and propose related terms:
        if (visitShortDTOList.isEmpty()){
            log.debug(className + "- findPossibleVisitsForDate - creating roomDailyReservation for processing" );
            //todo: implementation needed
        }

        return PossibleVisitListDTO.builder()
                .possibleVisits(visitShortDTOList)
                .build();
    }
}
