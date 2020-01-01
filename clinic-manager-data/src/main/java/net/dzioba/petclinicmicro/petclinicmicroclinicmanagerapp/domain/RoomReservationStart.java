package net.dzioba.petclinicmicro.petclinicmicroclinicmanagerapp.domain;

import java.time.LocalTime;

public enum RoomReservationStart {

    _0900(LocalTime.of(9, 0)),
    _0930(LocalTime.of(9, 30)),
    _1000(LocalTime.of(10, 0)),
    _1030(LocalTime.of(10, 30)),
    _1100(LocalTime.of(11, 0)),
    _1130(LocalTime.of(11, 30)),
    _1200(LocalTime.of(12, 0)),
    _1230(LocalTime.of(12, 30));

    final LocalTime time;

    RoomReservationStart(LocalTime time){
        this.time = time;
    }
}
