package com.agileillutions.gesdos.modelo.dto;

import java.io.Serializable;


/**
*
* @author Zathura Code Generator http://code.google.com/p/zathura
* www.zathuracode.org
*
*/
public class Programa1DTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String evenDescri;
    private String progCodigo;
    private String evenCodigo;

    public String getEvenDescri() {
        return evenDescri;
    }

    public void setEvenDescri(String evenDescri) {
        this.evenDescri = evenDescri;
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
