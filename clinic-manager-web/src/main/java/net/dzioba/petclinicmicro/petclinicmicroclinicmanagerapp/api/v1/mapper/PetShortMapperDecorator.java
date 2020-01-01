package net.dzioba.petclinicmicro.petclinicmicroclinicmanagerapp.api.v1.mapper;

import net.dzioba.petclinicmicro.common.model.PetShortDTO;
import net.dzioba.petclinicmicro.petclinicmicroclinicmanagerapp.api.v1.controller.OwnerDTOController;
import net.dzioba.petclinicmicro.petclinicmicroclinicmanagerapp.domain.Pet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;


public class PetShortMapperDecorator implements PetShortMapper {

    private PetShortMapper petShortMapper;

    @Autowired
    @Qualifier("delegate")
    public void setPetShortMapper(PetShortMapper petShortMapper) {
        this.petShortMapper = petShortMapper;
    }

    public PetShortMapper getPetShortMapper() {
        return petShortMapper;
    }

    @Override
    public PetShortDTO petToPetShortDTO(Pet pet) {
        PetShortDTO petShortDTO = petShortMapper.petToPetShortDTO(pet);
        petShortDTO.setDetailsUrl(OwnerDTOController.BASE_URL + "/" + pet.getOwner().getId() + "/pets/" + pet.getId());
        return petShortDTO;
    }
}
