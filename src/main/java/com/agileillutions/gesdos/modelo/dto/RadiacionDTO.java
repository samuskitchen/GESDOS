package com.agileillutions.gesdos.modelo.dto;

import java.io.Serializable;


/**
*
* @author Zathura Code Generator http://code.google.com/p/zathura
* www.zathuracode.org
*
*/
public class RadiacionDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String radCod;
    private String radDes;

    public String getRadCod() {
        return radCod;
    }

    public void setRadCod(String radCod) {
        this.radCod = radCod;
    }

    public String getRadDes() {
        return radDes;
    }

    public void setRadDes(String radDes) {
        this.radDes = radDes;
    }
}
