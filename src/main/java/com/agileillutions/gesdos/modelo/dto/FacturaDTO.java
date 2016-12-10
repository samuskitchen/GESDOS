package com.agileillutions.gesdos.modelo.dto;

import java.io.Serializable;
import java.util.Date;


/**
*
* @author Zathura Code Generator http://code.google.com/p/zathura
* www.zathuracode.org
*
*/
public class FacturaDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Date facFech;
    private Double facVal;
    private Long conNro;
    private Long empCod;
    private Long facNro;

    public Date getFacFech() {
        return facFech;
    }

    public void setFacFech(Date facFech) {
        this.facFech = facFech;
    }

    public Double getFacVal() {
        return facVal;
    }

    public void setFacVal(Double facVal) {
        this.facVal = facVal;
    }

    public Long getConNro() {
        return conNro;
    }

    public void setConNro(Long conNro) {
        this.conNro = conNro;
    }

    public Long getEmpCod() {
        return empCod;
    }

    public void setEmpCod(Long empCod) {
        this.empCod = empCod;
    }

    public Long getFacNro() {
        return facNro;
    }

    public void setFacNro(Long facNro) {
        this.facNro = facNro;
    }
}
