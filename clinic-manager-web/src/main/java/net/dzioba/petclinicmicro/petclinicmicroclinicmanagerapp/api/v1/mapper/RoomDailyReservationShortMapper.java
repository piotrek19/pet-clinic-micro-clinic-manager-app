package net.dzioba.petclinicmicro.petclinicmicroclinicmanagerapp.api.v1.mapper;

import net.dzioba.petclinicmicro.common.model.RoomDailyReservationShortDTO;
import net.dzioba.petclinicmicro.petclinicmicroclinicmanagerapp.domain.RoomDailyReservation;
import org.mapstruct.Mapper;

@Mapper
public interface RoomDailyReservationShortMapper {

    RoomDailyReservationShortDTO roomDailyReservationToRoomDailyReservationShortDTO(RoomDailyReservation roomDailyReservation);
}
