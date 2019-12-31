package net.dzioba.petclinicmicro.petclinicmicroclinicmanagerapp.repository;

import net.dzioba.petclinicmicro.petclinicmicroclinicmanagerapp.domain.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
}
