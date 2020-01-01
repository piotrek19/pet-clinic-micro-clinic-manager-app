package net.dzioba.petclinicmicro.petclinicmicroclinicmanagerapp.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "room_reservation")
public class RoomReservation extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "room_daily_reservation")
    private RoomDailyReservation roomDailyReservation;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    @Column(name = "reservation_start")
    private RoomReservationStart reservationStart;

    @OneToOne
    @JoinColumn(name = "visit_id")
    private Visit visit;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RoomReservation)) return false;
        RoomReservation that = (RoomReservation) o;
        return Objects.equals(roomDailyReservation, that.roomDailyReservation) &&
                Objects.equals(room, that.room) &&
                Objects.equals(reservationStart, that.reservationStart) &&
                Objects.equals(visit, that.visit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomDailyReservation, room, reservationStart, visit);
    }

    @Override
    public String toString() {
        return "RoomReservation{" +
                "id=" + getId() +
                ", reservationStart=" + reservationStart +
                ", roomDailyReservation=" + roomDailyReservation +
                ", visit=" + visit +
                '}';
    }
}
