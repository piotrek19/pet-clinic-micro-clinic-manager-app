package net.dzioba.petclinicmicro.petclinicmicroclinicmanagerapp.service.jpa;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.dzioba.petclinicmicro.petclinicmicroclinicmanagerapp.domain.Room;
import net.dzioba.petclinicmicro.petclinicmicroclinicmanagerapp.domain.RoomDailyReservation;
import net.dzioba.petclinicmicro.petclinicmicroclinicmanagerapp.service.RoomDailyReservationService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class RoomDailyReservationServiceJpa implements RoomDailyReservationService {

    @Override
    public List<RoomDailyReservation> findAll() {
        return null;
    }

    @Override
    public Optional<RoomDailyReservation> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public RoomDailyReservation save(RoomDailyReservation object) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public void delete(RoomDailyReservation object) {

    }

    @Override
    public RoomDailyReservation findByRoomAndDate(Room room, LocalDate date) {
        return null;
    }
}
