package com.agileillutions.gesdos.modelo.dto;

import java.io.Serializable;


/**
*
* @author Zathura Code Generator http://code.google.com/p/zathura
* www.zathuracode.org
*
*/
public class GrupdetDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String grupCodigo;
    private String usuaCodigo;

    public String getGrupCodigo() {
        return grupCodigo;
    }

    public void setGrupCodigo(String grupCodigo) {
        this.grupCodigo = grupCodigo;
    }

    public String getUsuaCodigo() {
        return usuaCodigo;
    }

    public void setUsuaCodigo(String usuaCodigo) {
        this.usuaCodigo = usuaCodigo;
    }
}
