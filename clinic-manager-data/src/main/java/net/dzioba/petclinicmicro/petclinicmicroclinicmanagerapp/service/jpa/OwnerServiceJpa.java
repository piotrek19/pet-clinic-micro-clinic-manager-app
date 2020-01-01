package net.dzioba.petclinicmicro.petclinicmicroclinicmanagerapp.service.jpa;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.dzioba.petclinicmicro.petclinicmicroclinicmanagerapp.domain.Owner;
import net.dzioba.petclinicmicro.petclinicmicroclinicmanagerapp.repository.OwnerRepository;
import net.dzioba.petclinicmicro.petclinicmicroclinicmanagerapp.service.OwnerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.Objects.requireNonNull;

@Service
@Slf4j
@RequiredArgsConstructor
public class OwnerServiceJpa implements OwnerService {

    private final OwnerRepository ownerRepository;
    private final String className = OwnerServiceJpa.class.getName();


    @Override
    public List<Owner> findAll() {
        log.debug(className + "- findAll - retrieving all records");

        return ownerRepository.findAll();
    }

    @Override
    public Optional<Owner> findById(Long id) {
        log.debug(className + "- findById for id: " + id);
        requireNonNull(id);

        return ownerRepository.findById(id);
    }

    @Override
    public List<Owner> findByLastNameLike(String lastName) {
        log.debug(className + " - find by last name: "+ lastName);
        requireNonNull(lastName);

        return ownerRepository.findByLastNameLike(lastName);
    }

    @Override
    public Owner save(Owner owner) {
        log.debug(className + " - save for object: " + owner);
        requireNonNull(owner);
        requireNonNull(owner.getLastName());
        requireNonNull(owner.getAddress());

        return ownerRepository.save(owner);
    }


    @Override
    public void deleteById(Long id) {
        log.debug(className + " - deleteById for id: "+ id);
        requireNonNull(id);

        ownerRepository.deleteById(id);
    }

    @Override
    public void delete(Owner owner) {
        log.debug(className + " - delete for object: "+ owner);
        requireNonNull(owner);

        ownerRepository.delete(owner);
    }
}
