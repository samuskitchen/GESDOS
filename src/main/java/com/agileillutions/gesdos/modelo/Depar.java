package com.agileillutions.gesdos.modelo;
// Generated Nov 5, 2016 11:06:58 PM by Hibernate Tools 4.0.0



/**
 * Depar generated by hbm2java
 */
public class Depar  implements java.io.Serializable {


     /**
	 * 
	 */
	private static final long serialVersionUID = -8765884284242129563L;
	private Long depCod;
     private String depNom;

    public Depar() {
    }

    public Depar(Long depCod, String depNom) {
       this.depCod = depCod;
       this.depNom = depNom;
    }
   
    public Long getDepCod() {
        return this.depCod;
    }
    
    public void setDepCod(Long depCod) {
        this.depCod = depCod;
    }
    public String getDepNom() {
        return this.depNom;
    }
    
    public void setDepNom(String depNom) {
        this.depNom = depNom;
    }




}

