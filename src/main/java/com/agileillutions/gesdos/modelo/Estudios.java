package com.agileillutions.gesdos.modelo;
// Generated Nov 5, 2016 11:06:58 PM by Hibernate Tools 4.0.0


import java.util.Date;

/**
 * Estudios generated by hbm2java
 */
public class Estudios  implements java.io.Serializable {


     /**
	 * 
	 */
	private static final long serialVersionUID = 7033652012412269195L;
	private EstudiosId id;
     private Date estFecRec;
     private Long revNro;
     private Double estDos;
     private Long estMesAct;
     private String estRec;
     private String obsCod;

    public Estudios() {
    }

	
    public Estudios(EstudiosId id, Long revNro, Double estDos, String obsCod) {
        this.id = id;
        this.revNro = revNro;
        this.estDos = estDos;
        this.obsCod = obsCod;
    }
    public Estudios(EstudiosId id, Date estFecRec, Long revNro, Double estDos, Long estMesAct, String estRec, String obsCod) {
       this.id = id;
       this.estFecRec = estFecRec;
       this.revNro = revNro;
       this.estDos = estDos;
       this.estMesAct = estMesAct;
       this.estRec = estRec;
       this.obsCod = obsCod;
    }
   
    public EstudiosId getId() {
        return this.id;
    }
    
    public void setId(EstudiosId id) {
        this.id = id;
    }
    public Date getEstFecRec() {
        return this.estFecRec;
    }
    
    public void setEstFecRec(Date estFecRec) {
        this.estFecRec = estFecRec;
    }
    public Long getRevNro() {
        return this.revNro;
    }
    
    public void setRevNro(Long revNro) {
        this.revNro = revNro;
    }
    public Double getEstDos() {
        return this.estDos;
    }
    
    public void setEstDos(Double estDos) {
        this.estDos = estDos;
    }
    public Long getEstMesAct() {
        return this.estMesAct;
    }
    
    public void setEstMesAct(Long estMesAct) {
        this.estMesAct = estMesAct;
    }
    public String getEstRec() {
        return this.estRec;
    }
    
    public void setEstRec(String estRec) {
        this.estRec = estRec;
    }
    public String getObsCod() {
        return this.obsCod;
    }
    
    public void setObsCod(String obsCod) {
        this.obsCod = obsCod;
    }




}

