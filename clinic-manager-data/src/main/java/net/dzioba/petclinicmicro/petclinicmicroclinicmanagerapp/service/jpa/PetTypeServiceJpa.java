package net.dzioba.petclinicmicro.petclinicmicroclinicmanagerapp.service.jpa;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.dzioba.petclinicmicro.petclinicmicroclinicmanagerapp.domain.PetType;
import net.dzioba.petclinicmicro.petclinicmicroclinicmanagerapp.repository.PetTypeRepository;
import net.dzioba.petclinicmicro.petclinicmicroclinicmanagerapp.service.PetTypeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.Objects.requireNonNull;

@Service
@Slf4j
@RequiredArgsConstructor
public class PetTypeServiceJpa implements PetTypeService {

    private final PetTypeRepository petTypeRepository;
    private final String className = PetTypeServiceJpa.class.getName();

    @Override
    public List<PetType> findAll() {
        log.debug(className + "- findAll - retrieving all records");

        return petTypeRepository.findAll();
    }

    @Override
    public Optional<PetType> findById(Long id) {
        log.debug(className + "- findById for id: " + id);
        requireNonNull(id);

        return petTypeRepository.findById(id);
    }

    @Override
    public PetType save(PetType petType) {
        log.debug(className + " - save for object: " + petType);
        requireNonNull(petType);
        requireNonNull(petType.getName());

        return petTypeRepository.save(petType);
    }

    @Override
    public void deleteById(Long id) {
        log.debug(className + " - deleteById for id: "+ id);
        requireNonNull(id);

        petTypeRepository.deleteById(id);
    }

    @Override
    public void delete(PetType petType) {
        log.debug(className + " - delete for object: "+ petType);
        requireNonNull(petType);

        petTypeRepository.delete(petType);
    }
}
