package net.dzioba.petclinicmicro.petclinicmicroclinicmanagerapp.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "room_reservation")
public class RoomReservation extends BaseEntity {

    @NotNull
    @ManyToOne
    @JoinColumn(name = "room_daily_reservation")
    private RoomDailyReservation roomDailyReservation;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    @Enumerated(EnumType.STRING)
    @Column(name = "reservation_start")
    private RoomReservationStart reservationStart;

    @OneToOne
    @JoinColumn(name = "visit_id")
    private Visit visit;

    @Builder
    public RoomReservation(Long id, RoomDailyReservation roomDailyReservation, Room room, RoomReservationStart reservationStart, Visit visit) {
        super(id);
        this.roomDailyReservation = roomDailyReservation;
        this.room = room;
        this.reservationStart = reservationStart;
        this.visit = visit;
    }

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
