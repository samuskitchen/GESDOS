package com.agileillutions.gesdos.modelo.dto;

import java.io.Serializable;


/**
*
* @author Zathura Code Generator http://code.google.com/p/zathura
* www.zathuracode.org
*
*/
public class GruposDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String grupCodigo;
    private String grupNombre;

    public String getGrupCodigo() {
        return grupCodigo;
    }

    public void setGrupCodigo(String grupCodigo) {
        this.grupCodigo = grupCodigo;
    }

    public String getGrupNombre() {
        return grupNombre;
    }

    public void setGrupNombre(String grupNombre) {
        this.grupNombre = grupNombre;
    }
}
