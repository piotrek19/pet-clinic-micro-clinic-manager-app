package net.dzioba.petclinicmicro.petclinicmicroclinicmanagerapp.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "room")
public class Room extends BaseEntity {

    @NotBlank
    @Size(max=255)
    @Column(name = "name")
    private String name;

    @Size(max=255)
    @Column(name = "description")
    private String description;

    @OneToOne
    @JoinColumn(name = "main_vet_id")
    private Vet mainVet;

    @Builder
    public Room(Long id, String name, String description, Vet mainVet) {
        super(id);
        this.name = name;
        this.description = description;
        this.mainVet = mainVet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Room)) return false;
        Room room = (Room) o;
        return Objects.equals(name, room.name) &&
                Objects.equals(description, room.description) &&
                Objects.equals(mainVet, room.mainVet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, mainVet);
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + getId() +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
