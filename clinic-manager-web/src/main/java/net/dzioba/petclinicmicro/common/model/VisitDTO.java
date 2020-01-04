package net.dzioba.petclinicmicro.common.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VisitDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateTime;

    @Size(max=255)
    private String description;

    @NotNull
    private OwnerShorterDTO owner;

    @NotNull
    private PetShorterDTO pet;

    @NotNull
    private VetShortDTO vet;

    @NotNull
    private RoomReservationShortDTO roomReservation;

}
