package com.agileillutions.gesdos.modelo;
// Generated Nov 5, 2016 11:06:58 PM by Hibernate Tools 4.0.0



/**
 * Ubicacion generated by hbm2java
 */
public class Ubicacion  implements java.io.Serializable {


     /**
	 * 
	 */
	private static final long serialVersionUID = -8613145838231057002L;
	private String ubiCod;
     private String ubiDes;

    public Ubicacion() {
    }

	
    public Ubicacion(String ubiCod) {
        this.ubiCod = ubiCod;
    }
    public Ubicacion(String ubiCod, String ubiDes) {
       this.ubiCod = ubiCod;
       this.ubiDes = ubiDes;
    }
   
    public String getUbiCod() {
        return this.ubiCod;
    }
    
    public void setUbiCod(String ubiCod) {
        this.ubiCod = ubiCod;
    }
    public String getUbiDes() {
        return this.ubiDes;
    }
    
    public void setUbiDes(String ubiDes) {
        this.ubiDes = ubiDes;
    }




}

