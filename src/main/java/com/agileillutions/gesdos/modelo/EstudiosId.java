package com.agileillutions.gesdos.modelo;
// Generated Nov 5, 2016 11:06:58 PM by Hibernate Tools 4.0.0



/**
 * EstudiosId generated by hbm2java
 */
public class EstudiosId  implements java.io.Serializable {


     /**
	 * 
	 */
	private static final long serialVersionUID = 7253722433773949460L;
	private Long traCed;
     private Long empCod;
     private Long dosCod;
     private Long estAni;
     private Long estMes;

    public EstudiosId() {
    }

    public EstudiosId(Long traCed, Long empCod, Long dosCod, Long estAni, Long estMes) {
       this.traCed = traCed;
       this.empCod = empCod;
       this.dosCod = dosCod;
       this.estAni = estAni;
       this.estMes = estMes;
    }
   
    public Long getTraCed() {
        return this.traCed;
    }
    
    public void setTraCed(Long traCed) {
        this.traCed = traCed;
    }
    public Long getEmpCod() {
        return this.empCod;
    }
    
    public void setEmpCod(Long empCod) {
        this.empCod = empCod;
    }
    public Long getDosCod() {
        return this.dosCod;
    }
    
    public void setDosCod(Long dosCod) {
        this.dosCod = dosCod;
    }
    public Long getEstAni() {
        return this.estAni;
    }
    
    public void setEstAni(Long estAni) {
        this.estAni = estAni;
    }
    public Long getEstMes() {
        return this.estMes;
    }
    
    public void setEstMes(Long estMes) {
        this.estMes = estMes;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof EstudiosId) ) return false;
		 EstudiosId castOther = ( EstudiosId ) other; 
         
		 return ( (this.getTraCed()==castOther.getTraCed()) || ( this.getTraCed()!=null && castOther.getTraCed()!=null && this.getTraCed().equals(castOther.getTraCed()) ) )
 && ( (this.getEmpCod()==castOther.getEmpCod()) || ( this.getEmpCod()!=null && castOther.getEmpCod()!=null && this.getEmpCod().equals(castOther.getEmpCod()) ) )
 && ( (this.getDosCod()==castOther.getDosCod()) || ( this.getDosCod()!=null && castOther.getDosCod()!=null && this.getDosCod().equals(castOther.getDosCod()) ) )
 && ( (this.getEstAni()==castOther.getEstAni()) || ( this.getEstAni()!=null && castOther.getEstAni()!=null && this.getEstAni().equals(castOther.getEstAni()) ) )
 && ( (this.getEstMes()==castOther.getEstMes()) || ( this.getEstMes()!=null && castOther.getEstMes()!=null && this.getEstMes().equals(castOther.getEstMes()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getTraCed() == null ? 0 : this.getTraCed().hashCode() );
         result = 37 * result + ( getEmpCod() == null ? 0 : this.getEmpCod().hashCode() );
         result = 37 * result + ( getDosCod() == null ? 0 : this.getDosCod().hashCode() );
         result = 37 * result + ( getEstAni() == null ? 0 : this.getEstAni().hashCode() );
         result = 37 * result + ( getEstMes() == null ? 0 : this.getEstMes().hashCode() );
         return result;
   }   


}

