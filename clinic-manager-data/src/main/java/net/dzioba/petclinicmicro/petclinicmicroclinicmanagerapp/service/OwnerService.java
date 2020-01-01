package net.dzioba.petclinicmicro.petclinicmicroclinicmanagerapp.service;

import net.dzioba.petclinicmicro.petclinicmicroclinicmanagerapp.domain.Owner;
import net.dzioba.petclinicmicro.petclinicmicroclinicmanagerapp.service.common.CrudService;

import java.util.List;

public interface OwnerService extends CrudService<Owner> {

    List<Owner> findByLastNameLike(String lastName);

}