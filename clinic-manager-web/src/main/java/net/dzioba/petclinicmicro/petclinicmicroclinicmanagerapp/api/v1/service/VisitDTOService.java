package net.dzioba.petclinicmicro.petclinicmicroclinicmanagerapp.api.v1.service;

import net.dzioba.petclinicmicro.common.model.PossibleVisitListDTO;

import java.time.LocalDate;

public interface VisitDTOService {

    PossibleVisitListDTO findPossibleVisitsForDate(LocalDate visitDate);

}
