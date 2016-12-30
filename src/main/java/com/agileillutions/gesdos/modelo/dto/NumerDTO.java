package com.agileillutions.gesdos.modelo.dto;

import java.io.Serializable;


/**
*
* @author Zathura Code Generator http://code.google.com/p/zathura
* www.zathuracode.org
*
*/
public class NumerDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long numCod;
    private String numDes;
    private Long numUlt;

    public Long getNumCod() {
        return numCod;
    }

    public void setNumCod(Long numCod) {
        this.numCod = numCod;
    }

    public String getNumDes() {
        return numDes;
    }

    public void setNumDes(String numDes) {
        this.numDes = numDes;
    }

    public Long getNumUlt() {
        return numUlt;
    }

    public void setNumUlt(Long numUlt) {
        this.numUlt = numUlt;
    }
}
