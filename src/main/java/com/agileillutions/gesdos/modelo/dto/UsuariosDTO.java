package com.agileillutions.gesdos.modelo.dto;

import java.io.Serializable;
import java.util.Date;


/**
*
* @author Zathura Code Generator http://code.google.com/p/zathura
* www.zathuracode.org
*
*/
public class UsuariosDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String usuaApeNom;
    private String usuaCodigo;
    private Long usuaDias;
    private String usuaPassw1;
    private String usuaPassw2;
    private String usuaPasswo;
    private Date usuafecha;

    public String getUsuaApeNom() {
        return usuaApeNom;
    }

    public void setUsuaApeNom(String usuaApeNom) {
        this.usuaApeNom = usuaApeNom;
    }

    public String getUsuaCodigo() {
        return usuaCodigo;
    }

    public void setUsuaCodigo(String usuaCodigo) {
        this.usuaCodigo = usuaCodigo;
    }

    public Long getUsuaDias() {
        return usuaDias;
    }

    public void setUsuaDias(Long usuaDias) {
        this.usuaDias = usuaDias;
    }

    public String getUsuaPassw1() {
        return usuaPassw1;
    }

    public void setUsuaPassw1(String usuaPassw1) {
        this.usuaPassw1 = usuaPassw1;
    }

    public String getUsuaPassw2() {
        return usuaPassw2;
    }

    public void setUsuaPassw2(String usuaPassw2) {
        this.usuaPassw2 = usuaPassw2;
    }

    public String getUsuaPasswo() {
        return usuaPasswo;
    }

    public void setUsuaPasswo(String usuaPasswo) {
        this.usuaPasswo = usuaPasswo;
    }

    public Date getUsuafecha() {
        return usuafecha;
    }

    public void setUsuafecha(Date usuafecha) {
        this.usuafecha = usuafecha;
    }
}
