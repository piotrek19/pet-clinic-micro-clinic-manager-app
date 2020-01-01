package net.dzioba.petclinicmicro.petclinicmicroclinicmanagerapp.api.v1.mapper;

import net.dzioba.petclinicmicro.common.model.RoomShortDTO;
import net.dzioba.petclinicmicro.petclinicmicroclinicmanagerapp.domain.Room;
import org.mapstruct.Mapper;

@Mapper
public interface RoomShortMapper {

    RoomShortDTO roomToRoomShortDTO(Room room);
}
