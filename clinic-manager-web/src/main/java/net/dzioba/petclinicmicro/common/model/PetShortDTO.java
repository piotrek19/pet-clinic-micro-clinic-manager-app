package net.dzioba.petclinicmicro.common.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PetShortDTO {

    private Long id;

    @NotBlank
    @Size(max=255)
    private String name;

    @URL
    private String detailsUrl;

}
