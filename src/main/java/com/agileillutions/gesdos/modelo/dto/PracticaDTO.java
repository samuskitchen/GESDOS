package com.agileillutions.gesdos.modelo.dto;

import java.io.Serializable;


/**
*
* @author Zathura Code Generator http://code.google.com/p/zathura
* www.zathuracode.org
*
*/
public class PracticaDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String praCod;
    private String praDes;

    public String getPraCod() {
        return praCod;
    }

    public void setPraCod(String praCod) {
        this.praCod = praCod;
    }

    public String getPraDes() {
        return praDes;
    }

    public void setPraDes(String praDes) {
        this.praDes = praDes;
    }
}
