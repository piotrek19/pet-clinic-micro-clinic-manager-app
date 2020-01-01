package net.dzioba.petclinicmicro.common.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomReservationShortDTO {

    private Long id;
    private RoomDailyReservationShortDTO roomDailyReservation;

}
