package net.dzioba.petclinicmicro.petclinicmicroclinicmanagerapp.service;

import net.dzioba.petclinicmicro.petclinicmicroclinicmanagerapp.domain.Room;
import net.dzioba.petclinicmicro.petclinicmicroclinicmanagerapp.domain.RoomDailyReservation;
import net.dzioba.petclinicmicro.petclinicmicroclinicmanagerapp.service.common.CrudService;

import java.time.LocalDate;
import java.util.List;

public interface RoomDailyReservationService extends CrudService<RoomDailyReservation> {

    RoomDailyReservation findByRoomAndDate(Room room, LocalDate date);

    List<RoomDailyReservation> findByDate(LocalDate date);

}
