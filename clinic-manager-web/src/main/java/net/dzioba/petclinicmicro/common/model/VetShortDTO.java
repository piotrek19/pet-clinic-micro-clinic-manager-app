package net.dzioba.petclinicmicro.common.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VetShortDTO {

    private Long id;
    private String firstName;
    private String lastName;

}
