package com.imb2025.smedico.dto.response;

public class HorarioAtencionResponseDto {

    private Long id;
    private String diaSemana;
    private String horaInicio;
    private String horaFin;
    private Integer version;

<<<<<<<< HEAD:src/main/java/com/imb2025/smedico/dto/response/HorarioAtencionResponseDto.java
    public HorarioAtencionResponseDto() {}
|||||||| c70e0a5:src/main/java/com/imb2025/smedico/dto/HorarioAtencionResponseDTO.java
    public HorarioAtencionResponseDTO() {
    }
========
    public HorarioAtencionResponseDto() {
    }
>>>>>>>> d695422753dddaa24acccca2f95a1b8ab4e4662c:src/main/java/com/imb2025/smedico/dto/HorarioAtencionResponseDto.java

<<<<<<<< HEAD:src/main/java/com/imb2025/smedico/dto/response/HorarioAtencionResponseDto.java
    public HorarioAtencionResponseDto(Long id, String diaSemana, String horaInicio, String horaFin, Integer version) {
|||||||| c70e0a5:src/main/java/com/imb2025/smedico/dto/HorarioAtencionResponseDTO.java
    public HorarioAtencionResponseDTO(Long id, Long medicoId, String diaSemana, String horaInicio, String horaFin) {
========
    public HorarioAtencionResponseDto(Long id, Long medicoId, String diaSemana, String horaInicio, String horaFin) {
>>>>>>>> d695422753dddaa24acccca2f95a1b8ab4e4662c:src/main/java/com/imb2025/smedico/dto/HorarioAtencionResponseDto.java
        this.id = id;
        this.diaSemana = diaSemana;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.version = version;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(String diaSemana) {
        this.diaSemana = diaSemana;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}