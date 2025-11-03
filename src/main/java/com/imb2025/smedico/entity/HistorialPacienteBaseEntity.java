package com.imb2025.smedico.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@MappedSuperclass
public abstract class HistorialPacienteBaseEntity {

	   @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @Column(name = "fecha_creacion", updatable = false)
	    private LocalDateTime fechaCreacion;

	    @Column(name = "ultima_actualizacion")
	    private LocalDateTime ultimaActualizacion;

	    @Column(name = "version")
	    private Long version;

	    @PrePersist
	    public void prePersist() {
	        this.fechaCreacion = LocalDateTime.now();
	        this.ultimaActualizacion = LocalDateTime.now();
	        this.version = 1L;
	    }

	    @PreUpdate
	    public void preUpdate() {
	        this.ultimaActualizacion = LocalDateTime.now();
	        if (version != null) {
	            this.version += 1;
	        } else {
	            this.version = 1L;
	        }
	    }

	    // Getters y setters explícitos
	    public Long getId() {
	        return id;
	    }

	    public void setId(Long id) {
	        this.id = id;
	    }

	    public Long getVersion() {
	        return version;
	    }

	    public void setVersion(Long version) {
	        this.version = version;
	 }
}
