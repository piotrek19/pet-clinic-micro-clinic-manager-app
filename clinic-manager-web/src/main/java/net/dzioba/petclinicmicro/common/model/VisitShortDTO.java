package net.dzioba.petclinicmicro.common.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VisitShortDTO {

    private LocalDateTime dateTime;
    private VetShortDTO vet;
    private RoomReservationShortDTO roomReservation;

}
