package com.agileillutions.gesdos.modelo;
// Generated Nov 5, 2016 11:06:58 PM by Hibernate Tools 4.0.0


import java.util.Date;

/**
 * Usuarios generated by hbm2java
 */
public class Usuarios  implements java.io.Serializable {


     /**
	 * 
	 */
	private static final long serialVersionUID = 5215225655715364394L;
	private String usuaCodigo;
     private String usuaApeNom;
     private String usuaPasswo;
     private String usuaPassw1;
     private String usuaPassw2;
     private Long usuaDias;
     private Date usuafecha;

    public Usuarios() {
    }

    public Usuarios(String usuaCodigo, String usuaApeNom, String usuaPasswo, String usuaPassw1, String usuaPassw2, Long usuaDias, Date usuafecha) {
       this.usuaCodigo = usuaCodigo;
       this.usuaApeNom = usuaApeNom;
       this.usuaPasswo = usuaPasswo;
       this.usuaPassw1 = usuaPassw1;
       this.usuaPassw2 = usuaPassw2;
       this.usuaDias = usuaDias;
       this.usuafecha = usuafecha;
    }
   
    public String getUsuaCodigo() {
        return this.usuaCodigo;
    }
    
    public void setUsuaCodigo(String usuaCodigo) {
        this.usuaCodigo = usuaCodigo;
    }
    public String getUsuaApeNom() {
        return this.usuaApeNom;
    }
    
    public void setUsuaApeNom(String usuaApeNom) {
        this.usuaApeNom = usuaApeNom;
    }
    public String getUsuaPasswo() {
        return this.usuaPasswo;
    }
    
    public void setUsuaPasswo(String usuaPasswo) {
        this.usuaPasswo = usuaPasswo;
    }
    public String getUsuaPassw1() {
        return this.usuaPassw1;
    }
    
    public void setUsuaPassw1(String usuaPassw1) {
        this.usuaPassw1 = usuaPassw1;
    }
    public String getUsuaPassw2() {
        return this.usuaPassw2;
    }
    
    public void setUsuaPassw2(String usuaPassw2) {
        this.usuaPassw2 = usuaPassw2;
    }
    public Long getUsuaDias() {
        return this.usuaDias;
    }
    
    public void setUsuaDias(Long usuaDias) {
        this.usuaDias = usuaDias;
    }
    public Date getUsuafecha() {
        return this.usuafecha;
    }
    
    public void setUsuafecha(Date usuafecha) {
        this.usuafecha = usuafecha;
    }




}

