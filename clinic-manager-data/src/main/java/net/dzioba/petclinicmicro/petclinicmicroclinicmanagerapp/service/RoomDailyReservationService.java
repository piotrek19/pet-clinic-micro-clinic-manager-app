package net.dzioba.petclinicmicro.petclinicmicroclinicmanagerapp.service;

import net.dzioba.petclinicmicro.petclinicmicroclinicmanagerapp.domain.Room;
import net.dzioba.petclinicmicro.petclinicmicroclinicmanagerapp.domain.RoomDailyReservation;

import java.time.LocalDate;
import java.util.List;

public interface RoomDailyReservationService extends CrudService<RoomDailyReservation> {

    List<RoomDailyReservation> findByDate(LocalDate date);

    List<RoomDailyReservation> findByRoomAndDate(Room room, LocalDate date);

}
