package com.agileillutions.gesdos.modelo.dto;

import java.io.Serializable;
import java.util.Date;


/**
*
* @author Zathura Code Generator http://code.google.com/p/zathura
* www.zathuracode.org
*
*/
public class FacturapagoDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Date facPagFec;
    private Double facPagRet;
    private Double facPagVal;
    private Long conNro;
    private Long empCod;
    private Long facNro;
    private Long facPagNro;

    public Date getFacPagFec() {
        return facPagFec;
    }

    public void setFacPagFec(Date facPagFec) {
        this.facPagFec = facPagFec;
    }

    public Double getFacPagRet() {
        return facPagRet;
    }

    public void setFacPagRet(Double facPagRet) {
        this.facPagRet = facPagRet;
    }

    public Double getFacPagVal() {
        return facPagVal;
    }

    public void setFacPagVal(Double facPagVal) {
        this.facPagVal = facPagVal;
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

    public Long getFacPagNro() {
        return facPagNro;
    }

    public void setFacPagNro(Long facPagNro) {
        this.facPagNro = facPagNro;
    }
}
