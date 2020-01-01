package net.dzioba.petclinicmicro.petclinicmicroclinicmanagerapp.api.v1.controller;


import net.dzioba.petclinicmicro.common.model.OwnerDTO;
import net.dzioba.petclinicmicro.common.model.OwnerListDTO;
import net.dzioba.petclinicmicro.petclinicmicroclinicmanagerapp.api.v1.service.OwnerDTOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping (OwnerDTOController.BASE_URL)
public class OwnerDTOController {

    public static final String BASE_URL = "/api/v1/owners";

    private final OwnerDTOService ownerDTOService;

    @Autowired
    public OwnerDTOController(OwnerDTOService ownerDTOService) {
        this.ownerDTOService = ownerDTOService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public OwnerListDTO listOwners(){

        return new OwnerListDTO(ownerDTOService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OwnerDTO> getOwnerById(@PathVariable Long id){

        OwnerDTO ownerDTO = ownerDTOService.findById(id);

        return ownerDTO == null ?
                ResponseEntity.notFound().build() : ResponseEntity.ok(ownerDTO);
    }

    @GetMapping("/lastname/{lastName}")
    @ResponseStatus(HttpStatus.OK)
    public OwnerListDTO getOwnerByLastName(@PathVariable String lastName){

        return new OwnerListDTO(ownerDTOService.findByLastName("%" + lastName + "%"));
    }


}
