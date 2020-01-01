package net.dzioba.petclinicmicro.common.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PetTypeDTO {

    private Long id;

    @NotBlank
    @Size(max = 255)
    private String name;

}
