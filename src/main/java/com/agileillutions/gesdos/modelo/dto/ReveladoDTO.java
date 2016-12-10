package com.agileillutions.gesdos.modelo.dto;

import java.io.Serializable;
import java.util.Date;


/**
*
* @author Zathura Code Generator http://code.google.com/p/zathura
* www.zathuracode.org
*
*/
public class ReveladoDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String revDes;
    private Date revFec;
    private Long revNro;

    public String getRevDes() {
        return revDes;
    }

    public void setRevDes(String revDes) {
        this.revDes = revDes;
    }

    public Date getRevFec() {
        return revFec;
    }

    public void setRevFec(Date revFec) {
        this.revFec = revFec;
    }

    public Long getRevNro() {
        return revNro;
    }

    public void setRevNro(Long revNro) {
        this.revNro = revNro;
    }
}
