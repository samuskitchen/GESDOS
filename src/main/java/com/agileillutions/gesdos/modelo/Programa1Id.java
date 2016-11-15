package com.agileillutions.gesdos.modelo;
// Generated Nov 5, 2016 11:06:58 PM by Hibernate Tools 4.0.0



/**
 * Programa1Id generated by hbm2java
 */
public class Programa1Id  implements java.io.Serializable {


     /**
	 * 
	 */
	private static final long serialVersionUID = -1471055179261862824L;
	private String progCodigo;
     private String evenCodigo;

    public Programa1Id() {
    }

    public Programa1Id(String progCodigo, String evenCodigo) {
       this.progCodigo = progCodigo;
       this.evenCodigo = evenCodigo;
    }
   
    public String getProgCodigo() {
        return this.progCodigo;
    }
    
    public void setProgCodigo(String progCodigo) {
        this.progCodigo = progCodigo;
    }
    public String getEvenCodigo() {
        return this.evenCodigo;
    }
    
    public void setEvenCodigo(String evenCodigo) {
        this.evenCodigo = evenCodigo;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof Programa1Id) ) return false;
		 Programa1Id castOther = ( Programa1Id ) other; 
         
		 return ( (this.getProgCodigo()==castOther.getProgCodigo()) || ( this.getProgCodigo()!=null && castOther.getProgCodigo()!=null && this.getProgCodigo().equals(castOther.getProgCodigo()) ) )
 && ( (this.getEvenCodigo()==castOther.getEvenCodigo()) || ( this.getEvenCodigo()!=null && castOther.getEvenCodigo()!=null && this.getEvenCodigo().equals(castOther.getEvenCodigo()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getProgCodigo() == null ? 0 : this.getProgCodigo().hashCode() );
         result = 37 * result + ( getEvenCodigo() == null ? 0 : this.getEvenCodigo().hashCode() );
         return result;
   }   


}


