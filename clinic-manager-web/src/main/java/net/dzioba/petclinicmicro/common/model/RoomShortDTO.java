package net.dzioba.petclinicmicro.common.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoomShortDTO {

    private Long id;

    @Size(max = 255)
    private String name;

}
