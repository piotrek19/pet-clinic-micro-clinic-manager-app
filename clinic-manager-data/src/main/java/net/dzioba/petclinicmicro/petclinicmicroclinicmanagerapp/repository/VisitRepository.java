package net.dzioba.petclinicmicro.petclinicmicroclinicmanagerapp.repository;

import net.dzioba.petclinicmicro.petclinicmicroclinicmanagerapp.domain.Visit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VisitRepository extends JpaRepository<Visit, Long> {

}
