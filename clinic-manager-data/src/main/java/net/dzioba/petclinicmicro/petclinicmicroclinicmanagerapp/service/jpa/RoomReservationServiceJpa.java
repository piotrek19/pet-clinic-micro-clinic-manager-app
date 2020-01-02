package net.dzioba.petclinicmicro.petclinicmicroclinicmanagerapp.service.jpa;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.dzioba.petclinicmicro.petclinicmicroclinicmanagerapp.domain.RoomReservation;
import net.dzioba.petclinicmicro.petclinicmicroclinicmanagerapp.repository.RoomReservationRepository;
import net.dzioba.petclinicmicro.petclinicmicroclinicmanagerapp.service.RoomReservationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.Objects.requireNonNull;

@Service
@Slf4j
@RequiredArgsConstructor
public class RoomReservationServiceJpa implements RoomReservationService {

    private final RoomReservationRepository roomReservationRepository;
    private final String className = RoomReservationServiceJpa.class.getName();


    @Override
    public List<RoomReservation> findAll() {
        log.debug(className + " - findAll - retrieving all records");

        return roomReservationRepository.findAll();
    }

    @Override
    public Optional<RoomReservation> findById(Long id) {
        log.debug(className + " - findById for id: " + id);
        requireNonNull(id);

        return roomReservationRepository.findById(id);
    }

    @Override
    public RoomReservation save(RoomReservation roomReservation) {
        log.debug(className + " - save for object: " + roomReservation);
        requireNonNull(roomReservation);
        requireNonNull(roomReservation.getRoomDailyReservation());
        requireNonNull(roomReservation.getRoom());

        return roomReservationRepository.save(roomReservation);
    }

    @Override
    public void deleteById(Long id) {
        log.debug(className + " - deleteById for id: " + id);
        requireNonNull(id);

        roomReservationRepository.deleteById(id);
    }

    @Override
    public void delete(RoomReservation roomReservation) {
        log.debug(className + " - delete for object: " + roomReservation);
        requireNonNull(roomReservation);

        roomReservationRepository.delete(roomReservation);
    }
}
