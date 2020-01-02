package net.dzioba.petclinicmicro.petclinicmicroclinicmanagerapp.repository;

import net.dzioba.petclinicmicro.petclinicmicroclinicmanagerapp.domain.RoomDailyReservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface RoomDailyReservationRepository extends JpaRepository<RoomDailyReservation, Long> {

    List<RoomDailyReservation> findByDate(LocalDate date);

}
