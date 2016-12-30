package com.agileillutions.gesdos.modelo.dto;

import java.io.Serializable;
import java.util.Date;


/**
*
* @author Zathura Code Generator http://code.google.com/p/zathura
* www.zathuracode.org
*
*/
public class ContratosDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long conDosi;
    private Long conDura;
    private String conEst;
    private Date conFec;
    private Date conFecFac;
    private Long conNroFac;
    private String conObser;
    private String conTipo;
    private Long conUsu;
    private Long conVal;
    private Long detConCod;
    private Long conNro;
    private Long empCod;
    private String socCod;

    public Long getConDosi() {
        return conDosi;
    }

    public void setConDosi(Long conDosi) {
        this.conDosi = conDosi;
    }

    public Long getConDura() {
        return conDura;
    }

    public void setConDura(Long conDura) {
        this.conDura = conDura;
    }

    public String getConEst() {
        return conEst;
    }

    public void setConEst(String conEst) {
        this.conEst = conEst;
    }

    public Date getConFec() {
        return conFec;
    }

    public void setConFec(Date conFec) {
        this.conFec = conFec;
    }

    public Date getConFecFac() {
        return conFecFac;
    }

    public void setConFecFac(Date conFecFac) {
        this.conFecFac = conFecFac;
    }

    public Long getConNroFac() {
        return conNroFac;
    }

    public void setConNroFac(Long conNroFac) {
        this.conNroFac = conNroFac;
    }

    public String getConObser() {
        return conObser;
    }

    public void setConObser(String conObser) {
        this.conObser = conObser;
    }

    public String getConTipo() {
        return conTipo;
    }

    public void setConTipo(String conTipo) {
        this.conTipo = conTipo;
    }

    public Long getConUsu() {
        return conUsu;
    }

    public void setConUsu(Long conUsu) {
        this.conUsu = conUsu;
    }

    public Long getConVal() {
        return conVal;
    }

    public void setConVal(Long conVal) {
        this.conVal = conVal;
    }

    public Long getDetConCod() {
        return detConCod;
    }

    public void setDetConCod(Long detConCod) {
        this.detConCod = detConCod;
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

    public String getSocCod() {
        return socCod;
    }

    public void setSocCod(String socCod) {
        this.socCod = socCod;
    }
}
