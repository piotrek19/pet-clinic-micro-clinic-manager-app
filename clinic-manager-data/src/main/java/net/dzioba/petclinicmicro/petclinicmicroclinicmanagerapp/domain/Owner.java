package net.dzioba.petclinicmicro.petclinicmicroclinicmanagerapp.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "owner")
public class Owner extends Person {

    @NotBlank
    @Size(max = 255)
    @Column(name = "address")
    private String address;

    @Size(max = 50)
    @Column(name = "telephone")
    private String telephone;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    private Set<Pet> pets = new HashSet<>();

    @Builder
    public Owner(Long id, String firstName, String lastName, String address, String telephone, Set<Pet> pets) {
        super(id, firstName, lastName);
        this.address = address;
        this.telephone = telephone;
        if (pets != null){
            this.pets = pets;
        }
    }

    public Pet addPet(Pet pet){
        Objects.requireNonNull(pet);
        pet.setOwner(this);
        pets.add(pet);
        return pet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Owner)) return false;
        if (!super.equals(o)) return false;
        Owner owner = (Owner) o;
        return Objects.equals(address, owner.address) &&
                Objects.equals(telephone, owner.telephone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), address, telephone);
    }

    @Override
    public String toString() {
        return "Owner{" +
                "id=" + getId() +
                ", firstName=" + getFirstName() +
                ", lastName=" + getLastName() +
                ", address='" + address + '\'' +
                ", telephone='" + telephone + '\'' +
                '}';
    }
}
