package com.imb2025.smedico.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.imb2025.smedico.entity.DetalleReceta;
import java.util.List;

@Repository
public interface DetalleRecetaRepository extends JpaRepository<DetalleReceta, Long> {

    // 1️⃣ Método mágico para filtrar detalles por receta
    List<DetalleReceta> findByRecetaId(Long recetaId);

    // 2️⃣ Método mágico para contar detalles por medicamento
    Long countByMedicamentoId(Long medicamentoId);
}
