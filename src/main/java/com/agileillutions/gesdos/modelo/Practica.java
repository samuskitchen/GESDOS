package com.agileillutions.gesdos.modelo;
// Generated Nov 5, 2016 11:06:58 PM by Hibernate Tools 4.0.0



/**
 * Practica generated by hbm2java
 */
public class Practica  implements java.io.Serializable {


     /**
	 * 
	 */
	private static final long serialVersionUID = 1685073529781536290L;
	private String praCod;
     private String praDes;

    public Practica() {
    }

    public Practica(String praCod, String praDes) {
       this.praCod = praCod;
       this.praDes = praDes;
    }
   
    public String getPraCod() {
        return this.praCod;
    }
    
    public void setPraCod(String praCod) {
        this.praCod = praCod;
    }
    public String getPraDes() {
        return this.praDes;
    }
    
    public void setPraDes(String praDes) {
        this.praDes = praDes;
    }




}


