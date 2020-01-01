package net.dzioba.petclinicmicro.petclinicmicroclinicmanagerapp.api.v1.mapper;

import net.dzioba.petclinicmicro.common.model.PetShortDTO;
import net.dzioba.petclinicmicro.petclinicmicroclinicmanagerapp.domain.Pet;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

@Mapper
@DecoratedWith(PetShortMapperDecorator.class)
public interface PetShortMapper {

    PetShortDTO petToPetShortDTO(Pet pet);
}
