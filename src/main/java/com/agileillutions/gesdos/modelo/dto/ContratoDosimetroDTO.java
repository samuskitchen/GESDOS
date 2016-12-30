package com.agileillutions.gesdos.modelo.dto;

import java.io.Serializable;


/**
*
* @author Zathura Code Generator http://code.google.com/p/zathura
* www.zathuracode.org
*
*/
public class ContratoDosimetroDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long conDosi;
    private Long conNro;
    private Long dosCod;

    public Long getConDosi() {
        return conDosi;
    }

    public void setConDosi(Long conDosi) {
        this.conDosi = conDosi;
    }

    public Long getConNro() {
        return conNro;
    }

    public void setConNro(Long conNro) {
        this.conNro = conNro;
    }

    public Long getDosCod() {
        return dosCod;
    }

    public void setDosCod(Long dosCod) {
        this.dosCod = dosCod;
    }
}
