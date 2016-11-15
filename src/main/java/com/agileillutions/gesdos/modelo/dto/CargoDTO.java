package com.agileillutions.gesdos.modelo.dto;

import java.io.Serializable;


/**
*
* @author Zathura Code Generator http://code.google.com/p/zathura
* www.zathuracode.org
*
*/
public class CargoDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String carCod;
    private String carDes;

    public String getCarCod() {
        return carCod;
    }

    public void setCarCod(String carCod) {
        this.carCod = carCod;
    }

    public String getCarDes() {
        return carDes;
    }

    public void setCarDes(String carDes) {
        this.carDes = carDes;
    }
}
