package com.agileillutions.gesdos.modelo.dto;

import java.io.Serializable;


/**
*
* @author Zathura Code Generator http://code.google.com/p/zathura
* www.zathuracode.org
*
*/
public class DeparDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long depCod;
    private String depNom;

    public Long getDepCod() {
        return depCod;
    }

    public void setDepCod(Long depCod) {
        this.depCod = depCod;
    }

    public String getDepNom() {
        return depNom;
    }

    public void setDepNom(String depNom) {
        this.depNom = depNom;
    }
}
