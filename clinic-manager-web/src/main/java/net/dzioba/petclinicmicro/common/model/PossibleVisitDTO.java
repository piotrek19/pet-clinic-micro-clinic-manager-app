package net.dzioba.petclinicmicro.common.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PossibleVisitDTO {

    private LocalDateTime dateTime;
    private VetShortDTO vet;
    private RoomShortDTO room;

}
