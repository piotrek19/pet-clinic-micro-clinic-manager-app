package net.dzioba.petclinicmicro.petclinicmicroclinicmanagerapp.service.jpa;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.dzioba.petclinicmicro.petclinicmicroclinicmanagerapp.domain.Room;
import net.dzioba.petclinicmicro.petclinicmicroclinicmanagerapp.repository.RoomRepository;
import net.dzioba.petclinicmicro.petclinicmicroclinicmanagerapp.service.RoomService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.Objects.requireNonNull;

@Service
@Slf4j
@RequiredArgsConstructor
public class RoomServiceJpa implements RoomService {

    private final RoomRepository roomRepository;
    private final String className = RoomServiceJpa.class.getName();

    @Override
    public List<Room> findAll() {
        log.debug(className + " - findAll - retrieving all records");

        return roomRepository.findAll();
    }

    @Override
    public Optional<Room> findById(Long id) {
        log.debug(className + " - findById for id: " + id);
        requireNonNull(id);

        return roomRepository.findById(id);
    }

    @Override
    public Room save(Room room) {
        log.debug(className + " - save for object: " + room);
        requireNonNull(room);
        requireNonNull(room.getName());

        return roomRepository.save(room);
    }

    @Override
    public void deleteById(Long id) {
        log.debug(className + " - deleteById for id: " + id);
        requireNonNull(id);

        roomRepository.deleteById(id);
    }

    @Override
    public void delete(Room room) {
        log.debug(className + " - delete for object: " + room);
        requireNonNull(room);

        roomRepository.delete(room);
    }
}
