package com.agileillutions.gesdos.modelo.dto;

import java.io.Serializable;
import java.util.Date;


/**
*
* @author Zathura Code Generator http://code.google.com/p/zathura
* www.zathuracode.org
*
*/
public class EstudiosDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Double estDos;
    private Date estFecRec;
    private Long estMesAct;
    private String estRec;
    private Long traCed;
    private Long empCod;
    private Long dosCod;
    private Long estAni;
    private Long estMes;
    private String obsCod;
    private Long revNro;
    
    private String nombreTrabajador;

    public Double getEstDos() {
        return estDos;
    }

    public void setEstDos(Double estDos) {
        this.estDos = estDos;
    }

    public Date getEstFecRec() {
        return estFecRec;
    }

    public void setEstFecRec(Date estFecRec) {
        this.estFecRec = estFecRec;
    }

    public Long getEstMesAct() {
        return estMesAct;
    }

    public void setEstMesAct(Long estMesAct) {
        this.estMesAct = estMesAct;
    }

    public String getEstRec() {
        return estRec;
    }

    public void setEstRec(String estRec) {
        this.estRec = estRec;
    }

    public Long getTraCed() {
        return traCed;
    }

    public void setTraCed(Long traCed) {
        this.traCed = traCed;
    }

    public Long getEmpCod() {
        return empCod;
    }

    public void setEmpCod(Long empCod) {
        this.empCod = empCod;
    }

    public Long getDosCod() {
        return dosCod;
    }

    public void setDosCod(Long dosCod) {
        this.dosCod = dosCod;
    }

    public Long getEstAni() {
        return estAni;
    }

    public void setEstAni(Long estAni) {
        this.estAni = estAni;
    }

    public Long getEstMes() {
        return estMes;
    }

    public void setEstMes(Long estMes) {
        this.estMes = estMes;
    }

    public String getObsCod() {
        return obsCod;
    }

    public void setObsCod(String obsCod) {
        this.obsCod = obsCod;
    }

    public Long getRevNro() {
        return revNro;
    }

    public void setRevNro(Long revNro) {
        this.revNro = revNro;
    }

	/** 
	 * @author <a href="mailto:daniel@takum.co">Daniel De La Pava Suarez</a> 
	 * @date 9/12/2016 
	 * @return the nombreTrabajador 
	 */
	public String getNombreTrabajador() {
		return nombreTrabajador;
	}

	/** 
	 * @author <a href="mailto:daniel@takum.co">Daniel De La Pava Suarez</a> 
	 * @date 9/12/2016 
	 * @param nombreTrabajador the nombreTrabajador to set 
	 */
	public void setNombreTrabajador(String nombreTrabajador) {
		this.nombreTrabajador = nombreTrabajador;
	}
}
