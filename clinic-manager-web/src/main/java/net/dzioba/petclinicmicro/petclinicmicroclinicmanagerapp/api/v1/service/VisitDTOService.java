package net.dzioba.petclinicmicro.petclinicmicroclinicmanagerapp.api.v1.service;

import net.dzioba.petclinicmicro.common.model.PossibleVisitListDTO;
import net.dzioba.petclinicmicro.common.model.VisitDTO;

import java.time.LocalDate;

public interface VisitDTOService {

    PossibleVisitListDTO findPossibleVisitsForDate(LocalDate visitDate);

    VisitDTO scheduleThisVisit(VisitDTO visitDTO);

}
