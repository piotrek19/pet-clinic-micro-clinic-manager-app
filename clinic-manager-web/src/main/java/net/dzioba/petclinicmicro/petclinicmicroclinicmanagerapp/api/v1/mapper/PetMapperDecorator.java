package net.dzioba.petclinicmicro.petclinicmicroclinicmanagerapp.api.v1.mapper;

import net.dzioba.petclinicmicro.common.model.PetDTO;
import net.dzioba.petclinicmicro.petclinicmicroclinicmanagerapp.domain.Pet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class PetMapperDecorator implements PetMapper {

    private PetMapper petMapper;
    private OwnerShortMapper ownerShortMapper;

    @Autowired
    @Qualifier("delegate")
    public void setPetMapper(PetMapper petMapper) {
        this.petMapper = petMapper;
    }

    @Autowired
    public void setOwnerShortMapper(OwnerShortMapper ownerShortMapper) {
        this.ownerShortMapper = ownerShortMapper;
    }

    public PetMapper getPetMapper() {
        return petMapper;
    }

    public OwnerShortMapper getOwnerShortMapper() {
        return ownerShortMapper;
    }

    @Override
    public PetDTO petToPetDTO(Pet pet) {
        PetDTO petDTO = petMapper.petToPetDTO(pet);
        petDTO.setOwner(ownerShortMapper.ownerToOwnerShortDTO(pet.getOwner()));
        return petDTO;
    }

    @Override
    public Pet petDTOToPet(PetDTO petDTO) {
        return petMapper.petDTOToPet(petDTO);
    }
}
