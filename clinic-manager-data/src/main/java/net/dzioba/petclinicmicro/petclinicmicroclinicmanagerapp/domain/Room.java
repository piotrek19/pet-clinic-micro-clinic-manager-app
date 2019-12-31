package net.dzioba.petclinicmicro.petclinicmicroclinicmanagerapp.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "room")
public class Room extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Builder
    public Room(Long id, String name, String description) {
        super(id);
        this.name = name;
        this.description = description;
    }

}
