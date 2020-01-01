package net.dzioba.petclinicmicro.petclinicmicroclinicmanagerapp.api.v1.service;

import net.dzioba.petclinicmicro.common.model.OwnerDTO;

import java.util.List;

public interface OwnerDTOService {

    List<OwnerDTO> findAll();

    List<OwnerDTO> findByLastName(String lastName);

    OwnerDTO findById(Long id);
}
