package com.agileillutions.gesdos.modelo;
// Generated Nov 5, 2016 11:06:58 PM by Hibernate Tools 4.0.0



/**
 * Grupos generated by hbm2java
 */
public class Grupos  implements java.io.Serializable {


     /**
	 * 
	 */
	private static final long serialVersionUID = -7191820851036535976L;
	private String grupCodigo;
     private String grupNombre;

    public Grupos() {
    }

    public Grupos(String grupCodigo, String grupNombre) {
       this.grupCodigo = grupCodigo;
       this.grupNombre = grupNombre;
    }
   
    public String getGrupCodigo() {
        return this.grupCodigo;
    }
    
    public void setGrupCodigo(String grupCodigo) {
        this.grupCodigo = grupCodigo;
    }
    public String getGrupNombre() {
        return this.grupNombre;
    }
    
    public void setGrupNombre(String grupNombre) {
        this.grupNombre = grupNombre;
    }




}


