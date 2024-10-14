package com.denuncias.dto;

import lombok.Data;

@Data
public class ComentarioAdminDTO {
    private Long denunciaId;
    private String texto;
    private String autor;

}
