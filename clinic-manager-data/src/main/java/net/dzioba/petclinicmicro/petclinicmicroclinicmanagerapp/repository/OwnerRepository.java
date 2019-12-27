package net.dzioba.petclinicmicro.petclinicmicroclinicmanagerapp.repository;

import net.dzioba.petclinicmicro.petclinicmicroclinicmanagerapp.domain.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnerRepository extends JpaRepository<Owner, Long> {

}
