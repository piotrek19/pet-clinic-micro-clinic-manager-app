package net.dzioba.petclinicmicro.petclinicmicroclinicmanagerapp.service.jpa;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.dzioba.petclinicmicro.petclinicmicroclinicmanagerapp.domain.Speciality;
import net.dzioba.petclinicmicro.petclinicmicroclinicmanagerapp.repository.SpecialityRepository;
import net.dzioba.petclinicmicro.petclinicmicroclinicmanagerapp.service.SpecialityService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.Objects.requireNonNull;

@Service
@Slf4j
@RequiredArgsConstructor
public class SpecialityServiceJpa implements SpecialityService {

    private final SpecialityRepository specialityRepository;
    private final String className = SpecialityServiceJpa.class.getName();

    @Override
    public List<Speciality> findAll() {
        log.debug(className + "- findAll - retrieving all records");

        return specialityRepository.findAll();
    }

    @Override
    public Optional<Speciality> findById(Long id) {
        log.debug(className + "- findById for id: " + id);
        requireNonNull(id);

        return specialityRepository.findById(id);
    }

    @Override
    public Speciality save(Speciality speciality) {
        log.debug(className + " - save for object: " + speciality);
        requireNonNull(speciality);
        requireNonNull(speciality.getDescription());

        return specialityRepository.save(speciality);
    }

    @Override
    public void deleteById(Long id) {
        log.debug(className + " - deleteById for id: "+ id);
        requireNonNull(id);

        specialityRepository.deleteById(id);
    }

    @Override
    public void delete(Speciality speciality) {
        log.debug(className + " - delete for object: "+ speciality);
        requireNonNull(speciality);

        specialityRepository.delete(speciality);
    }
}
