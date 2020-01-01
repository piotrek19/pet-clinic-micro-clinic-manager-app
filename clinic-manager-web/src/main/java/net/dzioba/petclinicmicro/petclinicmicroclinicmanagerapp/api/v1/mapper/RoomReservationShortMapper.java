package net.dzioba.petclinicmicro.petclinicmicroclinicmanagerapp.api.v1.mapper;

import net.dzioba.petclinicmicro.common.model.RoomReservationShortDTO;
import net.dzioba.petclinicmicro.petclinicmicroclinicmanagerapp.domain.RoomReservation;
import org.mapstruct.Mapper;

@Mapper
public interface RoomReservationShortMapper {

    RoomReservationShortDTO roomReservationToRoomReservationShortDTO(RoomReservation roomReservation);
}
