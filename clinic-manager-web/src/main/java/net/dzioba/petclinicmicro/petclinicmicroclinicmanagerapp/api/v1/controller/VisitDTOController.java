package net.dzioba.petclinicmicro.petclinicmicroclinicmanagerapp.api.v1.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.dzioba.petclinicmicro.common.model.PossibleVisitListDTO;
import net.dzioba.petclinicmicro.petclinicmicroclinicmanagerapp.api.v1.service.VisitDTOService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping(VisitDTOController.BASE_URL)
@Slf4j
@RequiredArgsConstructor
public class VisitDTOController {

    private final VisitDTOService visitDTOService;

    public static final String BASE_URL = "/api/v1/visits";
    private final String className = VisitDTOController.class.getName();

    @GetMapping
    public ResponseEntity<PossibleVisitListDTO> findPossibleVisitsForDate(@RequestParam(required=true) String visitDate){
        log.debug(className + " - findPossibleVisitsForDate for date: " + visitDate);

        LocalDate date = LocalDate.parse(visitDate, DateTimeFormatter.ISO_LOCAL_DATE);
        PossibleVisitListDTO possibleVisitListDTO = visitDTOService.findPossibleVisitsForDate(date);

        return ResponseEntity.ok(possibleVisitListDTO);
    }

}
