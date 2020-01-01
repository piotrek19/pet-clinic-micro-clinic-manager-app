package net.dzioba.petclinicmicro.petclinicmicroclinicmanagerapp.api.v1.mapper;

import net.dzioba.petclinicmicro.common.model.OwnerShortDTO;
import net.dzioba.petclinicmicro.petclinicmicroclinicmanagerapp.api.v1.controller.OwnerDTOController;
import net.dzioba.petclinicmicro.petclinicmicroclinicmanagerapp.domain.Owner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class OwnerShortMapperDecorator implements OwnerShortMapper {

    private OwnerShortMapper ownerShortMapper;

    @Autowired
    @Qualifier("delegate")
    public void setOwnerShortMapper(OwnerShortMapper ownerShortMapper) {
        this.ownerShortMapper = ownerShortMapper;
    }

    public OwnerShortMapper getOwnerShortMapper() {
        return ownerShortMapper;
    }

    @Override
    public OwnerShortDTO ownerToOwnerShortDTO(Owner owner) {
        OwnerShortDTO ownerShortDTO = ownerShortMapper.ownerToOwnerShortDTO(owner);
        ownerShortDTO.setDetailsUrl(OwnerDTOController.BASE_URL+ "/" + owner.getId());
        return ownerShortDTO;
    }
}
