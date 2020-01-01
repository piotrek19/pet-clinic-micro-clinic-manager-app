package net.dzioba.petclinicmicro.petclinicmicroclinicmanagerapp.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "visit")
public class Visit extends BaseEntity {

    @NotNull
    @Column(name = "date")
    private LocalDateTime date;

    @Size(max = 255)
    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;

    @ManyToOne
    @JoinColumn(name = "pet_id")
    private Pet pet;

    @ManyToOne
    @JoinColumn(name = "vet_id")
    private Vet vet;

    @OneToOne(mappedBy = "visit")
    private RoomReservation roomReservation;

    @Builder
    public Visit(Long id, LocalDateTime date, String description, Owner owner, Pet pet) {
        super(id);
        this.date = date;
        this.description = description;
        this.owner = owner;
        this.pet = pet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Visit)) return false;
        Visit visit = (Visit) o;
        return Objects.equals(date, visit.date) &&
                Objects.equals(description, visit.description) &&
                Objects.equals(owner, visit.owner) &&
                Objects.equals(pet, visit.pet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, description, owner, pet);
    }

    @Override
    public String toString() {
        return "Visit{" +
                "id=" + getId() +
                ", date=" + date +
                ", description='" + description + '\'' +
                ", owner=" + owner +
                ", pet=" + pet +
                ", vet=" + vet +
                '}';
    }
}
