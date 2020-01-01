package net.dzioba.petclinicmicro.petclinicmicroclinicmanagerapp.api.v1.service;


import net.dzioba.petclinicmicro.common.model.OwnerDTO;
import net.dzioba.petclinicmicro.petclinicmicroclinicmanagerapp.api.v1.mapper.OwnerMapper;
import net.dzioba.petclinicmicro.petclinicmicroclinicmanagerapp.domain.Owner;
import net.dzioba.petclinicmicro.petclinicmicroclinicmanagerapp.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OwnerDTOService {

    private OwnerService ownerService;
    private OwnerMapper ownerMapper;

    @Autowired
    public OwnerDTOService(OwnerService ownerService, OwnerMapper ownerMapper) {
        this.ownerService = ownerService;
        this.ownerMapper = ownerMapper;
    }

    public List<OwnerDTO> findAll(){
        return ownerService.findAll().stream()
                .map(ownerMapper::ownerToOwnerDTO)
                .collect(Collectors.toList());
    }

    public List<OwnerDTO> findByLastName(String lastName){
        return ownerService.findByLastNameLike(lastName).stream()
                .map(ownerMapper::ownerToOwnerDTO)
                .collect(Collectors.toList());
    }

    public OwnerDTO findById(Long id){
        OwnerDTO ownerDTO = null;

        Owner retrievedOwner = ownerService.findById(id).orElse(null);
        if (retrievedOwner != null){
            ownerDTO = ownerMapper.ownerToOwnerDTO(retrievedOwner);
        }

        return ownerDTO;
    }
}
