package net.dzioba.petclinicmicro.petclinicmicroclinicmanagerapp.api.v1.mapper;

import net.dzioba.petclinicmicro.common.model.VetShortDTO;
import net.dzioba.petclinicmicro.petclinicmicroclinicmanagerapp.domain.Vet;
import org.mapstruct.Mapper;

@Mapper
public interface VetShortMapper {

    VetShortDTO vetToVetShortDTO(Vet vet);
}
