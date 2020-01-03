package net.dzioba.petclinicmicro.petclinicmicroclinicmanagerapp.domain;

import lombok.Getter;

import java.time.LocalTime;
import java.util.Arrays;

@Getter
public enum RoomReservationStart {

    _0900(LocalTime.of(9, 0)),
    _0930(LocalTime.of(9, 30)),
    _1000(LocalTime.of(10, 0)),
    _1030(LocalTime.of(10, 30)),
    _1100(LocalTime.of(11, 0)),
    _1130(LocalTime.of(11, 30)),
    _1200(LocalTime.of(12, 0)),
    _1230(LocalTime.of(12, 30));

    private final LocalTime time;

    RoomReservationStart(LocalTime time){
        this.time = time;
    }

    public static RoomReservationStart getFromLocalTime(LocalTime localTime){
        return Arrays.stream(RoomReservationStart.values())
                .filter(roomReservationStart -> roomReservationStart.time.equals(localTime)).findAny().orElse(null);
    }
}
