package com.agileillutions.gesdos.modelo.dto;

import java.io.Serializable;


/**
*
* @author Zathura Code Generator http://code.google.com/p/zathura
* www.zathuracode.org
*
*/
public class GeominasDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String geoCod;
    private String geoDes;

    public String getGeoCod() {
        return geoCod;
    }

    public void setGeoCod(String geoCod) {
        this.geoCod = geoCod;
    }

    public String getGeoDes() {
        return geoDes;
    }

    public void setGeoDes(String geoDes) {
        this.geoDes = geoDes;
    }
}
