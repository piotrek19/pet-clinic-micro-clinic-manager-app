package net.dzioba.petclinicmicro.petclinicmicroclinicmanagerapp.api.v1.service;

import net.dzioba.petclinicmicro.common.model.PetDTO;

import java.util.List;

public interface PetDTOService {

    List<PetDTO> findPetsOfGivenOwner(Long ownerId);

    PetDTO findById(Long id);

    PetDTO save(PetDTO petDTO, Long ownerId);

    void deleteById(Long id);
}
