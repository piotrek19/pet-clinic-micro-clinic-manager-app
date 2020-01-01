package net.dzioba.petclinicmicro.petclinicmicroclinicmanagerapp.api.v1.mapper;

import net.dzioba.petclinicmicro.common.model.PetDTO;
import net.dzioba.petclinicmicro.petclinicmicroclinicmanagerapp.domain.Pet;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper
@DecoratedWith(PetMapperDecorator.class)
public interface PetMapper {

    @Mapping(target = "owner", ignore = true)
    PetDTO petToPetDTO(Pet pet);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "visits", ignore = true)
    })
    Pet petDTOToPet(PetDTO petDTO);
}
