package net.dzioba.petclinicmicro.petclinicmicroclinicmanagerapp.repository;

import net.dzioba.petclinicmicro.petclinicmicroclinicmanagerapp.domain.PetType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetTypeRepository extends JpaRepository<PetType, Long> {

}
