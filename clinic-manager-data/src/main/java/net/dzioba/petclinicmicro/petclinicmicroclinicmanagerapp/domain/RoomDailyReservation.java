package net.dzioba.petclinicmicro.petclinicmicroclinicmanagerapp.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "room_daily_reservation")
public class RoomDailyReservation extends BaseEntity {

    @NotNull
    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    @NotNull
    @Column(name = "date")
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "vet_id")
    private Vet vet;

    @OneToMany(mappedBy = "roomDailyReservation")
    private List<RoomReservation> roomReservations = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RoomDailyReservation)) return false;
        RoomDailyReservation that = (RoomDailyReservation) o;
        return Objects.equals(room, that.room) &&
                Objects.equals(date, that.date) &&
                Objects.equals(vet, that.vet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(room, date, vet);
    }

    @Override
    public String toString() {
        return "RoomDailyReservation{" +
                "id=" + getId() +
                ", room=" + room +
                ", date=" + date +
                ", vet=" + vet +
                '}';
    }
}
