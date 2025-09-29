package com.imb2025.smedico.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

@MappedSuperclass
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(updatable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
    
    private Long version;

    // Getters and Setters
    public Long getId() {
        return id;
    }    
    
	
    public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}



	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}



	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setId(Long id) {
		this.id = id;
	}

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        version = (long) 1;
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
        version = version + 1;
    }
}
