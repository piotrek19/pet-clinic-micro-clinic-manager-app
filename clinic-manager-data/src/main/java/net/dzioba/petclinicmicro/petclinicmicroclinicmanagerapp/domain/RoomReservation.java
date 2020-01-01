package net.dzioba.petclinicmicro.petclinicmicroclinicmanagerapp.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RoomReservation)) return false;
        RoomReservation that = (RoomReservation) o;
        return Objects.equals(dateTime, that.dateTime) &&
                Objects.equals(roomDailyReservation, that.roomDailyReservation) &&
                Objects.equals(visit, that.visit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateTime, roomDailyReservation, visit);
    }

    @Override
    public String toString() {
        return "RoomReservation{" +
                "id=" + getId() +
                ", dateTime=" + dateTime +
                ", roomDailyReservation=" + roomDailyReservation +
                ", visit=" + visit +
                '}';
    }
}
