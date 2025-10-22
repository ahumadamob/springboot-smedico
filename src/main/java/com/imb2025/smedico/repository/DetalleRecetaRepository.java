package com.imb2025.smedico.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.imb2025.smedico.entity.DetalleReceta;
import java.util.List;

@Repository
public interface DetalleRecetaRepository extends JpaRepository<DetalleReceta, Long> {

 
    List<DetalleReceta> findByRecetaId(Long recetaId);


    Long countByMedicamentoId(Long medicamentoId);

   
    List<DetalleReceta> findByControladoTrue();
    List<DetalleReceta> findByControladoFalse();
}
