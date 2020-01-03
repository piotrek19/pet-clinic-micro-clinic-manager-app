package net.dzioba.petclinicmicro.common.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PetDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @NotBlank
    @Size(max=255)
    private String name;

    @Past
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

    @NotNull
    private PetTypeDTO petType;

    private OwnerShortDTO owner;

    private Set<VisitDTO> visits = new HashSet<>();
}
