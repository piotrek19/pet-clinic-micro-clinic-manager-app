package net.dzioba.petclinicmicro.petclinicmicroclinicmanagerapp.service;

import net.dzioba.petclinicmicro.petclinicmicroclinicmanagerapp.domain.BaseEntity;

import java.util.List;
import java.util.Optional;

public interface CrudService<T extends BaseEntity> {

    List<T> findAll();

    Optional<T> findById(Long id);

    T save(T object);

    void deleteById(Long id);

    void delete(T object);
}
