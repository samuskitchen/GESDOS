package com.agileillutions.gesdos.modelo.dto;

import java.io.Serializable;


/**
*
* @author Zathura Code Generator http://code.google.com/p/zathura
* www.zathuracode.org
*
*/
public class PermisosDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String grupCodigo;
    private String progCodigo;
    private String evenCodigo;

    public String getGrupCodigo() {
        return grupCodigo;
    }

    public void setGrupCodigo(String grupCodigo) {
        this.grupCodigo = grupCodigo;
    }

    public String getProgCodigo() {
        return progCodigo;
    }

    public void setProgCodigo(String progCodigo) {
        this.progCodigo = progCodigo;
    }

    public String getEvenCodigo() {
        return evenCodigo;
    }

    public void setEvenCodigo(String evenCodigo) {
        this.evenCodigo = evenCodigo;
    }
}
