package com.agileillutions.gesdos.modelo.dto;

import java.io.Serializable;


/**
*
* @author Zathura Code Generator http://code.google.com/p/zathura
* www.zathuracode.org
*
*/
public class ProgramasDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String progCodigo;
    private String progDescri;

    public String getProgCodigo() {
        return progCodigo;
    }

    public void setProgCodigo(String progCodigo) {
        this.progCodigo = progCodigo;
    }

    public String getProgDescri() {
        return progDescri;
    }

    public void setProgDescri(String progDescri) {
        this.progDescri = progDescri;
    }
}
