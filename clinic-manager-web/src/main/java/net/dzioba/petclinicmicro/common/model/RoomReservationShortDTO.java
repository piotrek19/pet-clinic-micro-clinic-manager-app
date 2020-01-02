package net.dzioba.petclinicmicro.common.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoomReservationShortDTO {

    private RoomDailyReservationShortDTO roomDailyReservation;

}
