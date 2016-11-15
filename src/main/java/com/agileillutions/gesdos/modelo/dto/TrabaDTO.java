package com.agileillutions.gesdos.modelo.dto;

import java.io.Serializable;
import java.util.Date;


/**
*
* @author Zathura Code Generator http://code.google.com/p/zathura
* www.zathuracode.org
*
*/
public class TrabaDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String traApe1;
    private String traApe2;
    private Long traCed;
    private String traEst;
    private Date traFecIni;
    private String traNom;
    private String traObs;
    private String traSex;

    public String getTraApe1() {
        return traApe1;
    }

    public void setTraApe1(String traApe1) {
        this.traApe1 = traApe1;
    }

    public String getTraApe2() {
        return traApe2;
    }

    public void setTraApe2(String traApe2) {
        this.traApe2 = traApe2;
    }

    public Long getTraCed() {
        return traCed;
    }

    public void setTraCed(Long traCed) {
        this.traCed = traCed;
    }

    public String getTraEst() {
        return traEst;
    }

    public void setTraEst(String traEst) {
        this.traEst = traEst;
    }

    public Date getTraFecIni() {
        return traFecIni;
    }

    public void setTraFecIni(Date traFecIni) {
        this.traFecIni = traFecIni;
    }

    public String getTraNom() {
        return traNom;
    }

    public void setTraNom(String traNom) {
        this.traNom = traNom;
    }

    public String getTraObs() {
        return traObs;
    }

    public void setTraObs(String traObs) {
        this.traObs = traObs;
    }

    public String getTraSex() {
        return traSex;
    }

    public void setTraSex(String traSex) {
        this.traSex = traSex;
    }
}
