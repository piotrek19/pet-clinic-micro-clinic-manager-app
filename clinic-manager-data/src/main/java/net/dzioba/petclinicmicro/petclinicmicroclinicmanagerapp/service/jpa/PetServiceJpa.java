package net.dzioba.petclinicmicro.petclinicmicroclinicmanagerapp.service.jpa;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.dzioba.petclinicmicro.petclinicmicroclinicmanagerapp.domain.Pet;
import net.dzioba.petclinicmicro.petclinicmicroclinicmanagerapp.repository.PetRepository;
import net.dzioba.petclinicmicro.petclinicmicroclinicmanagerapp.service.PetService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.Objects.requireNonNull;

@Service
@Slf4j
@RequiredArgsConstructor
public class PetServiceJpa implements PetService {

    private final PetRepository petRepository;
    private final String className = PetServiceJpa.class.getName();

    @Override
    public List<Pet> findAll() {
        log.debug(className + " - findAll - retrieving all records");

        return petRepository.findAll();
    }

    @Override
    public Optional<Pet> findById(Long id) {
        log.debug(className + " - findById for id: " + id);
        requireNonNull(id);

        return petRepository.findById(id);
    }

    @Override
    public Pet save(Pet pet) {
        log.debug(className + " - save for object: " + pet);
        requireNonNull(pet);
        requireNonNull(pet.getPetType());
        requireNonNull(pet.getName());

        return petRepository.save(pet);
    }

    @Override
    public void deleteById(Long id) {
        log.debug(className + " - deleteById for id: "+ id);
        requireNonNull(id);

        petRepository.deleteById(id);
    }

    @Override
    public void delete(Pet pet) {
        log.debug(className + " - delete for object: "+ pet);
        requireNonNull(pet);

        petRepository.delete(pet);
    }
}
