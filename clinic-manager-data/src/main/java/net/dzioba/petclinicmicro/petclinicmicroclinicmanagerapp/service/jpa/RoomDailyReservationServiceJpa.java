package net.dzioba.petclinicmicro.petclinicmicroclinicmanagerapp.service.jpa;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.dzioba.petclinicmicro.petclinicmicroclinicmanagerapp.domain.RoomDailyReservation;
import net.dzioba.petclinicmicro.petclinicmicroclinicmanagerapp.repository.RoomDailyReservationRepository;
import net.dzioba.petclinicmicro.petclinicmicroclinicmanagerapp.service.RoomDailyReservationService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static java.util.Objects.requireNonNull;

@Service
@Slf4j
@RequiredArgsConstructor
public class RoomDailyReservationServiceJpa implements RoomDailyReservationService {

    private final RoomDailyReservationRepository roomDailyReservationRepository;
    private final String className = RoomDailyReservationServiceJpa.class.getName();


    @Override
    public List<RoomDailyReservation> findAll() {
        log.debug(className + " - findAll - retrieving all records intentionally NOT implemented");

        return null;
    }

    @Override
    public Optional<RoomDailyReservation> findById(Long id) {
        log.debug(className + " - findById for id: " + id);
        requireNonNull(id);

        return roomDailyReservationRepository.findById(id);
    }

    @Override
    public RoomDailyReservation save(RoomDailyReservation roomDailyReservation) {
        log.debug(className + " - save for object: " + roomDailyReservation);
        requireNonNull(roomDailyReservation);
        requireNonNull(roomDailyReservation.getRoom());
        requireNonNull(roomDailyReservation.getDate());

        return roomDailyReservationRepository.save(roomDailyReservation);
    }

    @Override
    public void deleteById(Long id) {
        log.debug(className + " - deleteById for id: " + id);
        requireNonNull(id);

        roomDailyReservationRepository.deleteById(id);
    }

    @Override
    public void delete(RoomDailyReservation roomDailyReservation) {
        log.debug(className + " - delete for object: " + roomDailyReservation);
        requireNonNull(roomDailyReservation);

        roomDailyReservationRepository.delete(roomDailyReservation);
    }

    @Override
    public List<RoomDailyReservation> findByDate(LocalDate date) {
        log.debug(className + "- findByDate for date: " + date);
        requireNonNull(date);

        return roomDailyReservationRepository.findByDate(date);
    }
}
