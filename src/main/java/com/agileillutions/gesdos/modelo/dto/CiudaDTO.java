package com.agileillutions.gesdos.modelo.dto;

import java.io.Serializable;


/**
*
* @author Zathura Code Generator http://code.google.com/p/zathura
* www.zathuracode.org
*
*/
public class CiudaDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String ciuNom;
    private Long depCod;
    private Long ciuCod;

    public String getCiuNom() {
        return ciuNom;
    }

    public void setCiuNom(String ciuNom) {
        this.ciuNom = ciuNom;
    }

    public Long getDepCod() {
        return depCod;
    }

    public void setDepCod(Long depCod) {
        this.depCod = depCod;
    }

    public Long getCiuCod() {
        return ciuCod;
    }

    public void setCiuCod(Long ciuCod) {
        this.ciuCod = ciuCod;
    }
}
