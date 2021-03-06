package com.agileillutions.gesdos.modelo;
// Generated Nov 5, 2016 11:06:58 PM by Hibernate Tools 4.0.0



/**
 * ContratosId generated by hbm2java
 */
public class ContratosId  implements java.io.Serializable {


     /**
	 * 
	 */
	private static final long serialVersionUID = 6355007732294378376L;
	private Long conNro;
     private Long empCod;

    public ContratosId() {
    }

    public ContratosId(Long conNro, Long empCod) {
       this.conNro = conNro;
       this.empCod = empCod;
    }
   
    public Long getConNro() {
        return this.conNro;
    }
    
    public void setConNro(Long conNro) {
        this.conNro = conNro;
    }
    public Long getEmpCod() {
        return this.empCod;
    }
    
    public void setEmpCod(Long empCod) {
        this.empCod = empCod;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof ContratosId) ) return false;
		 ContratosId castOther = ( ContratosId ) other; 
         
		 return ( (this.getConNro()==castOther.getConNro()) || ( this.getConNro()!=null && castOther.getConNro()!=null && this.getConNro().equals(castOther.getConNro()) ) )
 && ( (this.getEmpCod()==castOther.getEmpCod()) || ( this.getEmpCod()!=null && castOther.getEmpCod()!=null && this.getEmpCod().equals(castOther.getEmpCod()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getConNro() == null ? 0 : this.getConNro().hashCode() );
         result = 37 * result + ( getEmpCod() == null ? 0 : this.getEmpCod().hashCode() );
         return result;
   }   


}


