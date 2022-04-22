package com.marcelo.foro.models;

import lombok.*;
import org.springframework.lang.Nullable;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder
public class DtoDatos {
    private Integer idP;
    @Nullable
    private Integer idR;
    private String usuario;
    private String pregunta;
    private List<DtoDatos> lstRespuestas = new ArrayList<>();
}
