package net.dzioba.petclinicmicro.petclinicmicroclinicmanagerapp.api.v1.mapper;


import net.dzioba.petclinicmicro.common.model.OwnerShortDTO;
import net.dzioba.petclinicmicro.petclinicmicroclinicmanagerapp.domain.Owner;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

@Mapper
@DecoratedWith(OwnerShortMapperDecorator.class)
public interface OwnerShortMapper {

    OwnerShortDTO ownerToOwnerShortDTO(Owner owner);

}
