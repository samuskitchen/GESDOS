package com.agileillutions.gesdos.modelo;
// Generated Nov 5, 2016 11:06:58 PM by Hibernate Tools 4.0.0



/**
 * Programas generated by hbm2java
 */
public class Programas  implements java.io.Serializable {


     /**
	 * 
	 */
	private static final long serialVersionUID = 7611400157832984365L;
	private String progCodigo;
     private String progDescri;

    public Programas() {
    }

    public Programas(String progCodigo, String progDescri) {
       this.progCodigo = progCodigo;
       this.progDescri = progDescri;
    }
   
    public String getProgCodigo() {
        return this.progCodigo;
    }
    
    public void setProgCodigo(String progCodigo) {
        this.progCodigo = progCodigo;
    }
    public String getProgDescri() {
        return this.progDescri;
    }
    
    public void setProgDescri(String progDescri) {
        this.progDescri = progDescri;
    }




}

