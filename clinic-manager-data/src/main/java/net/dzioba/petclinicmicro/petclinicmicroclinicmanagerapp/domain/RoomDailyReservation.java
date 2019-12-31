package net.dzioba.petclinicmicro.petclinicmicroclinicmanagerapp.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "room_daily_reservation")
public class RoomDailyReservation extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    @Column(name = "date")
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "vet_id")
    private Vet vet;

    @OneToMany(mappedBy = "roomDailyReservation")
    private List<RoomReservation> roomReservations = new ArrayList<>();
}
