package net.dzioba.petclinicmicro.petclinicmicroclinicmanagerapp.service.jpa;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.dzioba.petclinicmicro.petclinicmicroclinicmanagerapp.domain.RoomReservation;
import net.dzioba.petclinicmicro.petclinicmicroclinicmanagerapp.service.RoomReservationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class RoomReservationServiceJpa implements RoomReservationService {

    @Override
    public List<RoomReservation> findAll() {
        return null;
    }

    @Override
    public Optional<RoomReservation> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public RoomReservation save(RoomReservation object) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public void delete(RoomReservation object) {

    }
}
