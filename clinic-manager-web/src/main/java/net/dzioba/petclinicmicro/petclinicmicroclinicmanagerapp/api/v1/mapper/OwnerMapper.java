package net.dzioba.petclinicmicro.petclinicmicroclinicmanagerapp.api.v1.mapper;

import net.dzioba.petclinicmicro.common.model.OwnerDTO;
import net.dzioba.petclinicmicro.petclinicmicroclinicmanagerapp.domain.Owner;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
@DecoratedWith(OwnerMapperDecorator.class)
public interface OwnerMapper {

    @Mapping(target = "pets", ignore = true)
    OwnerDTO ownerToOwnerDTO(Owner owner);
}
