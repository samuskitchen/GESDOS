package com.agileillutions.gesdos.modelo;
// Generated Nov 5, 2016 11:06:58 PM by Hibernate Tools 4.0.0



/**
 * ContratoDosimetro generated by hbm2java
 */
public class ContratoDosimetro  implements java.io.Serializable {


     /**
	 * 
	 */
	private static final long serialVersionUID = -6955637811320214145L;
	private Long conDosi;
     private Long dosCod;
     private Long conNro;

    public ContratoDosimetro() {
    }

    public ContratoDosimetro(Long conDosi, Long dosCod, Long conNro) {
       this.conDosi = conDosi;
       this.dosCod = dosCod;
       this.conNro = conNro;
    }
   
    public Long getConDosi() {
        return this.conDosi;
    }
    
    public void setConDosi(Long conDosi) {
        this.conDosi = conDosi;
    }
    public Long getDosCod() {
        return this.dosCod;
    }
    
    public void setDosCod(Long dosCod) {
        this.dosCod = dosCod;
    }
    public Long getConNro() {
        return this.conNro;
    }
    
    public void setConNro(Long conNro) {
        this.conNro = conNro;
    }




}


