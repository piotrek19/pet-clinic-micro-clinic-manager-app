package net.dzioba.petclinicmicro.petclinicmicroclinicmanagerapp.api.v1.mapper;

import net.dzioba.petclinicmicro.common.model.VisitDTO;
import net.dzioba.petclinicmicro.petclinicmicroclinicmanagerapp.domain.Visit;
import org.mapstruct.Mapper;

@Mapper
public interface VisitMapper {

    VisitDTO visitToVisitDTO(Visit visit);

}
