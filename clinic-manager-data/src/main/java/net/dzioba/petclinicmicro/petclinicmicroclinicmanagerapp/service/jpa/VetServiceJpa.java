package net.dzioba.petclinicmicro.petclinicmicroclinicmanagerapp.service.jpa;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.dzioba.petclinicmicro.petclinicmicroclinicmanagerapp.domain.Vet;
import net.dzioba.petclinicmicro.petclinicmicroclinicmanagerapp.repository.SpecialityRepository;
import net.dzioba.petclinicmicro.petclinicmicroclinicmanagerapp.repository.VetRepository;
import net.dzioba.petclinicmicro.petclinicmicroclinicmanagerapp.service.VetService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.Objects.isNull;
import static java.util.Objects.requireNonNull;

@Service
@Slf4j
@RequiredArgsConstructor
public class VetServiceJpa implements VetService {

    private final VetRepository vetRepository;
    private final SpecialityRepository specialityRepository;
    private final String className = VetServiceJpa.class.getName();


    @Override
    public List<Vet> findAll() {
        log.debug(className + "- findAll - retrieving all records");

        return vetRepository.findAll();
    }

    @Override
    public Optional<Vet> findById(Long id) {
        log.debug(className + "- findById for id: " + id);
        requireNonNull(id);

        return vetRepository.findById(id);
    }

    @Override
    public Vet save(Vet vet) {
        log.debug(className + " - save for object: " + vet);
        requireNonNull(vet);
        requireNonNull(vet.getLastName());
        saveUnsavedSpecialitiesOf(vet);

        return vetRepository.save(vet);
    }

    private void saveUnsavedSpecialitiesOf(Vet vet) {
        vet.getSpecialities().forEach(speciality -> {
            if (isNull(speciality.getId())){
                speciality.setId(specialityRepository.save(speciality).getId());
            }
        });
    }

    @Override
    public void deleteById(Long id) {
        log.debug(className + " - deleteById for id: "+ id);
        requireNonNull(id);

        vetRepository.deleteById(id);
    }

    @Override
    public void delete(Vet vet) {
        log.debug(className + " - delete for object: "+ vet);
        requireNonNull(vet);

        vetRepository.delete(vet);
    }
}
