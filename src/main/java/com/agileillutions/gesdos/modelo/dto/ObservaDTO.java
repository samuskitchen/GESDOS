package com.agileillutions.gesdos.modelo.dto;

import java.io.Serializable;


/**
*
* @author Zathura Code Generator http://code.google.com/p/zathura
* www.zathuracode.org
*
*/
public class ObservaDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String obsCod;
    private String obsDes;

    public String getObsCod() {
        return obsCod;
    }

    public void setObsCod(String obsCod) {
        this.obsCod = obsCod;
    }

    public String getObsDes() {
        return obsDes;
    }

    public void setObsDes(String obsDes) {
        this.obsDes = obsDes;
    }
}
