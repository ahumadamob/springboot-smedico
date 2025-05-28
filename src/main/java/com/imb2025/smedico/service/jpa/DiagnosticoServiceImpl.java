package com.imb2025.smedico.service.jpa;
import com.imb2025.smedico.service.IDiagnosticoService;
import com.imb2025.smedico.service.jpa.DiagnosticoServiceImpl;
import com.imb2025.smedico.repository.ConsultaRepository;
import com.imb2025.smedico.entity.Consulta;
import com.imb2025.smedico.entity.Diagnostico;
import com.imb2025.smedico.dto.DiagnosticoRequestDTO;
import com.imb2025.smedico.repository.DiagnosticoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DiagnosticoServiceImpl implements IDiagnosticoService {

    @Autowired
    private DiagnosticoRepository repo;

    @Autowired
    private ConsultaRepository consultaRepository;

    @Override
    public List<Diagnostico> findAll() {
        return repo.findAll();
    }

    @Override
    public Diagnostico findById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Diagnostico save(Diagnostico diagnostico) {
        return repo.save(diagnostico);
    }

    @Override
    public void deleteById(Long id) {
        repo.deleteById(id);
    }

    // ✅ Método para verificar existencia (para el controller)
    @Override
    public boolean existsById(Long id) {
        return repo.existsById(id);
    }

 
    
    @Override
    public Diagnostico fromDto(DiagnosticoRequestDTO dto) {
        try {
            Consulta consulta = consultaRepository.findById(dto.getConsultaId())
                .orElseThrow(() -> new RuntimeException("Consulta no encontrada con ID: " + dto.getConsultaId()));

            Diagnostico diagnostico = new Diagnostico();
            diagnostico.setConsulta(consulta);
            diagnostico.setDescripcion(dto.getDescripcion());
            diagnostico.setFechaDiagnostico(dto.getFechaDiagnostico());

            return diagnostico;

        } catch (RuntimeException e) {
            System.out.println("❌ Error al crear diagnóstico: " + e.getMessage());
            return null;
        }
    }

    @Override
    public Diagnostico actualizar(Long id, DiagnosticoRequestDTO dto) {
        Diagnostico diagnostico = repo.findById(id)
            .orElseThrow(() -> new RuntimeException("Diagnóstico no encontrado"));

        diagnostico.setDescripcion(dto.getDescripcion());
        diagnostico.setFechaDiagnostico(dto.getFechaDiagnostico());

        // Si necesitás asociar consultaId:
        Consulta consulta = consultaRepository.findById(dto.getConsultaId())
            .orElseThrow(() -> new RuntimeException("Consulta no encontrada"));
        diagnostico.setConsulta(consulta);

        return repo.save(diagnostico);
    }




    }
