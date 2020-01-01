package net.dzioba.petclinicmicro.petclinicmicroclinicmanagerapp.api.v1.service;

import net.dzioba.petclinicmicro.common.model.PetDTO;
import net.dzioba.petclinicmicro.petclinicmicroclinicmanagerapp.api.v1.mapper.PetMapper;
import net.dzioba.petclinicmicro.petclinicmicroclinicmanagerapp.domain.Owner;
import net.dzioba.petclinicmicro.petclinicmicroclinicmanagerapp.domain.Pet;
import net.dzioba.petclinicmicro.petclinicmicroclinicmanagerapp.domain.PetType;
import net.dzioba.petclinicmicro.petclinicmicroclinicmanagerapp.service.OwnerService;
import net.dzioba.petclinicmicro.petclinicmicroclinicmanagerapp.service.PetService;
import net.dzioba.petclinicmicro.petclinicmicroclinicmanagerapp.service.PetTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class PetDTOService {

    private PetService petService;
    private PetMapper petMapper;

    private OwnerService ownerService;
    private PetTypeService petTypeService;

    @Autowired
    public PetDTOService(PetService petService, PetMapper petMapper, OwnerService ownerService, PetTypeService petTypeService) {
        this.petService = petService;
        this.petMapper = petMapper;
        this.ownerService = ownerService;
        this.petTypeService = petTypeService;
    }

    public List<PetDTO> findPetsOfGivenOwner(Long ownerId) {
        return petService.findAll().stream()
                .filter(p -> p.getOwner().getId() == ownerId )  // todo: repository level filtering instead of retrieving all
                .map(petMapper::petToPetDTO)
                .collect(Collectors.toList());
    }

    public PetDTO findById(Long id){
        PetDTO petDTO = null;

        Pet retrievedPet = petService.findById(id).orElse(null);
        if (retrievedPet != null){
            petDTO = petMapper.petToPetDTO(retrievedPet);
        }
        return petDTO;
    }

    @Transactional
    public PetDTO save(PetDTO petDTO, Long ownerId){
        Pet pet = petMapper.petDTOToPet(petDTO);

        // retrieve owner and make object references:
        Owner retrievedOwner = ownerService.findById(ownerId).orElse(null);
        Objects.requireNonNull(retrievedOwner);

        retrievedOwner.addPet(pet);

        // retrieve petType and make object references:
        Long petTypeId = pet.getPetType().getId();
        Objects.requireNonNull(petTypeId);
        PetType retrievedPetType = petTypeService.findById(petTypeId).orElse(null);
        Objects.requireNonNull(retrievedPetType);

        pet.setPetType(retrievedPetType);

        // save pet:
        Pet savedPet = petService.save(pet);
        return petMapper.petToPetDTO(savedPet);
    }

    public void deleteById(Long id) {

        petService.deleteById(id);
    }
}
