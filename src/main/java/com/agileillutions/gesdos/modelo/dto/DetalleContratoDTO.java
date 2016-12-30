package com.agileillutions.gesdos.modelo.dto;

import java.io.Serializable;


/**
*
* @author Zathura Code Generator http://code.google.com/p/zathura
* www.zathuracode.org
*
*/
public class DetalleContratoDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String detEst;
    private Long detNumUsu;
    private String detObs;
    private Long detConCod;
    private Long conNro;

    public String getDetEst() {
        return detEst;
    }

    public void setDetEst(String detEst) {
        this.detEst = detEst;
    }

    public Long getDetNumUsu() {
        return detNumUsu;
    }

    public void setDetNumUsu(Long detNumUsu) {
        this.detNumUsu = detNumUsu;
    }

    public String getDetObs() {
        return detObs;
    }

    public void setDetObs(String detObs) {
        this.detObs = detObs;
    }

    public Long getDetConCod() {
        return detConCod;
    }

    public void setDetConCod(Long detConCod) {
        this.detConCod = detConCod;
    }

    public Long getConNro() {
        return conNro;
    }

    public void setConNro(Long conNro) {
        this.conNro = conNro;
    }
}
