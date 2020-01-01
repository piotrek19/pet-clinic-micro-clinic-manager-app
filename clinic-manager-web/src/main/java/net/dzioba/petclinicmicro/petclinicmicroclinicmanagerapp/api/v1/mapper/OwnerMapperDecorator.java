package net.dzioba.petclinicmicro.petclinicmicroclinicmanagerapp.api.v1.mapper;

import net.dzioba.petclinicmicro.common.model.OwnerDTO;
import net.dzioba.petclinicmicro.petclinicmicroclinicmanagerapp.domain.Owner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.stream.Collectors;


public class OwnerMapperDecorator implements OwnerMapper {

    private OwnerMapper ownerMapper;
    private PetShortMapper petShortMapper;

    @Autowired
    @Qualifier("delegate")
    public void setOwnerMapper(OwnerMapper ownerMapper) {
        this.ownerMapper = ownerMapper;
    }

    @Autowired
    public void setPetShortMapper(PetShortMapper petShortMapper) {
        this.petShortMapper = petShortMapper;
    }

    public OwnerMapper getOwnerMapper() {
        return ownerMapper;
    }

    public PetShortMapper getPetShortMapper() {
        return petShortMapper;
    }

    @Override
    public OwnerDTO ownerToOwnerDTO(Owner owner) {
        OwnerDTO ownerDTO = ownerMapper.ownerToOwnerDTO(owner);
        ownerDTO.setPets(owner.getPets().stream()
                        .map(petShortMapper::petToPetShortDTO)
                        .collect(Collectors.toSet())
        );
        return ownerDTO;
    }
}
