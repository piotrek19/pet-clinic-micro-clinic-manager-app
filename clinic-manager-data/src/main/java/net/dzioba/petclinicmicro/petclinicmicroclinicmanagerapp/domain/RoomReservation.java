package net.dzioba.petclinicmicro.petclinicmicroclinicmanagerapp.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "room_reservation")
public class RoomReservation extends BaseEntity {

    @Column(name = "dateTime")
    private LocalDateTime dateTime;

    @ManyToOne
    @JoinColumn(name = "room_daily_reservation")
    private RoomDailyReservation roomDailyReservation;

    @OneToOne
    @JoinColumn(name = "visit_id")
    private Visit visit;

}
