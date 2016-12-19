package com.agileillutions.gesdos.modelo.dto;

import java.io.Serializable;
import java.util.Date;


/**
*
* @author Zathura Code Generator http://code.google.com/p/zathura
* www.zathuracode.org
*
*/
public class DosimetroDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String carCod;
    private Long dosConNro;
    private String dosEst;
    private Long dosPerRec;
    private String dosTipo;
    private String geoCod;
    private Long traCed;
    private Long dosCod;
    private Long empCod;
    private String praCod;
    private String radCod;
    private String ubiCod;
    
    private String nombreTrabajador;
    
    private Date fechaInicioContrato;
    private Date fechaFacturacionContrato;
    
    private String razonSocial;
    
    private Long anioEstudio;
    private Long mesEstudio;
    
    private Long codContrato;

    public String getCarCod() {
        return carCod;
    }

    public void setCarCod(String carCod) {
        this.carCod = carCod;
    }

    public Long getDosConNro() {
        return dosConNro;
    }

    public void setDosConNro(Long dosConNro) {
        this.dosConNro = dosConNro;
    }

    public String getDosEst() {
        return dosEst;
    }

    public void setDosEst(String dosEst) {
        this.dosEst = dosEst;
    }

    public Long getDosPerRec() {
        return dosPerRec;
    }

    public void setDosPerRec(Long dosPerRec) {
        this.dosPerRec = dosPerRec;
    }

    public String getDosTipo() {
        return dosTipo;
    }

    public void setDosTipo(String dosTipo) {
        this.dosTipo = dosTipo;
    }

    public String getGeoCod() {
        return geoCod;
    }

    public void setGeoCod(String geoCod) {
        this.geoCod = geoCod;
    }

    public Long getTraCed() {
        return traCed;
    }

    public void setTraCed(Long traCed) {
        this.traCed = traCed;
    }

    public Long getDosCod() {
        return dosCod;
    }

    public void setDosCod(Long dosCod) {
        this.dosCod = dosCod;
    }

    public Long getEmpCod() {
        return empCod;
    }

    public void setEmpCod(Long empCod) {
        this.empCod = empCod;
    }

    public String getPraCod() {
        return praCod;
    }

    public void setPraCod(String praCod) {
        this.praCod = praCod;
    }

    public String getRadCod() {
        return radCod;
    }

    public void setRadCod(String radCod) {
        this.radCod = radCod;
    }

    public String getUbiCod() {
        return ubiCod;
    }

    public void setUbiCod(String ubiCod) {
        this.ubiCod = ubiCod;
    }

	/** 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava Suarez</a> 
	 * @date 14/11/2016 
	 * @return the nombreTrabajador 
	 */
	public String getNombreTrabajador() {
		return nombreTrabajador;
	}

	/** 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava Suarez</a> 
	 * @date 14/11/2016 
	 * @param nombreTrabajador the nombreTrabajador to set 
	 */
	public void setNombreTrabajador(String nombreTrabajador) {
		this.nombreTrabajador = nombreTrabajador;
	}

	/** 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava Suarez</a> 
	 * @date 14/11/2016 
	 * @return the fechaInicioContrato 
	 */
	public Date getFechaInicioContrato() {
		return fechaInicioContrato;
	}

	/** 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava Suarez</a> 
	 * @date 14/11/2016 
	 * @param fechaInicioContrato the fechaInicioContrato to set 
	 */
	public void setFechaInicioContrato(Date fechaInicioContrato) {
		this.fechaInicioContrato = fechaInicioContrato;
	}

	/** 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava Suarez</a> 
	 * @date 14/11/2016 
	 * @return the fechaFacturacionContrato 
	 */
	public Date getFechaFacturacionContrato() {
		return fechaFacturacionContrato;
	}

	/** 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava Suarez</a> 
	 * @date 14/11/2016 
	 * @param fechaFacturacionContrato the fechaFacturacionContrato to set 
	 */
	public void setFechaFacturacionContrato(Date fechaFacturacionContrato) {
		this.fechaFacturacionContrato = fechaFacturacionContrato;
	}

	/** 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava Suarez</a> 
	 * @date 16/12/2016 
	 * @return the razonSocial 
	 */
	public String getRazonSocial() {
		return razonSocial;
	}

	/** 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava Suarez</a> 
	 * @date 16/12/2016 
	 * @param razonSocial the razonSocial to set 
	 */
	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	/** 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava Suarez</a> 
	 * @date 17/12/2016 
	 * @return the anioEstudio 
	 */
	public Long getAnioEstudio() {
		return anioEstudio;
	}

	/** 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava Suarez</a> 
	 * @date 17/12/2016 
	 * @param anioEstudio the anioEstudio to set 
	 */
	public void setAnioEstudio(Long anioEstudio) {
		this.anioEstudio = anioEstudio;
	}

	/** 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava Suarez</a> 
	 * @date 17/12/2016 
	 * @return the mesEstudio 
	 */
	public Long getMesEstudio() {
		return mesEstudio;
	}

	/** 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava Suarez</a> 
	 * @date 17/12/2016 
	 * @param mesEstudio the mesEstudio to set 
	 */
	public void setMesEstudio(Long mesEstudio) {
		this.mesEstudio = mesEstudio;
	}

	/** 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava Suarez</a> 
	 * @date 18/12/2016 
	 * @return the codContrato 
	 */
	public Long getCodContrato() {
		return codContrato;
	}

	/** 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava Suarez</a> 
	 * @date 18/12/2016 
	 * @param codContrato the codContrato to set 
	 */
	public void setCodContrato(Long codContrato) {
		this.codContrato = codContrato;
	}
}
