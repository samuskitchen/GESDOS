package com.agileillutions.gesdos.modelo.dto;

import java.io.Serializable;


/**
*
* @author Zathura Code Generator http://code.google.com/p/zathura
* www.zathuracode.org
*
*/
public class UbicacionDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String ubiCod;
    private String ubiDes;

    public String getUbiCod() {
        return ubiCod;
    }

    public void setUbiCod(String ubiCod) {
        this.ubiCod = ubiCod;
    }

    public String getUbiDes() {
        return ubiDes;
    }

    public void setUbiDes(String ubiDes) {
        this.ubiDes = ubiDes;
    }
}
