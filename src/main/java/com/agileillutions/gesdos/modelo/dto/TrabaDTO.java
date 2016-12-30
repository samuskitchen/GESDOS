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
    
    private Long codEmp;
    private String empRazSoc;
    private Long codDosi;

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

	/** 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava Suarez</a> 
	 * @date 27/11/2016 
	 * @return the codEmp 
	 */
	public Long getCodEmp() {
		return codEmp;
	}

	/** 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava Suarez</a> 
	 * @date 27/11/2016 
	 * @param codEmp the codEmp to set 
	 */
	public void setCodEmp(Long codEmp) {
		this.codEmp = codEmp;
	}

	/** 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava Suarez</a> 
	 * @date 27/11/2016 
	 * @return the empRazSoc 
	 */
	public String getEmpRazSoc() {
		return empRazSoc;
	}

	/** 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava Suarez</a> 
	 * @date 27/11/2016 
	 * @param empRazSoc the empRazSoc to set 
	 */
	public void setEmpRazSoc(String empRazSoc) {
		this.empRazSoc = empRazSoc;
	}

	/** 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava Suarez</a> 
	 * @date 27/11/2016 
	 * @return the codDosi 
	 */
	public Long getCodDosi() {
		return codDosi;
	}

	/** 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava Suarez</a> 
	 * @date 27/11/2016 
	 * @param codDosi the codDosi to set 
	 */
	public void setCodDosi(Long codDosi) {
		this.codDosi = codDosi;
	}
}
