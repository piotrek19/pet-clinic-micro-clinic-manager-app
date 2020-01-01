package net.dzioba.petclinicmicro.petclinicmicroclinicmanagerapp.service.jpa;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.dzioba.petclinicmicro.petclinicmicroclinicmanagerapp.domain.Visit;
import net.dzioba.petclinicmicro.petclinicmicroclinicmanagerapp.repository.VisitRepository;
import net.dzioba.petclinicmicro.petclinicmicroclinicmanagerapp.service.VisitService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.Objects.requireNonNull;

@Service
@Slf4j
@RequiredArgsConstructor
public class VisitServiceJpa implements VisitService {

    private final VisitRepository visitRepository;
    private final String className = VisitServiceJpa.class.getName();

    @Override
    public List<Visit> findAll() {
        log.debug(className + "- findAll - retrieving all records");

        return visitRepository.findAll();
    }

    @Override
    public Optional<Visit> findById(Long id) {
        log.debug(className + "- findById for id: " + id);
        requireNonNull(id);

        return visitRepository.findById(id);
    }

    @Override
    public Visit save(Visit visit) {
        log.debug(className + " - save for object: " + visit);
        requireNonNull(visit);
        requireNonNull(visit.getDateTime());
        requireNonNull(visit.getPet());
        requireNonNull(visit.getPet().getId());
        requireNonNull(visit.getPet().getOwner());
        requireNonNull(visit.getPet().getOwner().getId());
        visit.setId(null);

        return visitRepository.save(visit);
    }

    @Override
    public void deleteById(Long id) {
        log.debug(className + " - deleteById for id: "+ id);
        requireNonNull(id);

        visitRepository.deleteById(id);

    }

    @Override
    public void delete(Visit visit) {
        log.debug(className + " - delete for object: "+ visit);
        requireNonNull(visit);

        visitRepository.delete(visit);

    }
}
