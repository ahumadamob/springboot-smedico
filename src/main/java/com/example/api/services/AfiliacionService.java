
package com.example.api.services;

import com.example.api.entity.Afiliacion;
import java.util.List;
import java.util.Optional;



public interface AfiliacionService {
    List<Afiliacion> findAll();
    Optional<Afiliacion> findById(Long id);
    Afiliacion save(Afiliacion afiliacion);
    Afiliacion update(Long id, Afiliacion afiliacion);
    void deleteById(Long id);
}