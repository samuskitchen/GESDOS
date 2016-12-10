package com.agileillutions.gesdos.modelo.control;

import java.util.List;

import com.agileillutions.gesdos.modelo.Cargo;
import com.agileillutions.gesdos.modelo.dto.CargoDTO;


/**
* @author Zathura Code Generator http://code.google.com/p/zathura
* www.zathuracode.org
*
*/
public interface ICargoLogic {
    public List<Cargo> getCargo() throws Exception;

    /**
         * Save an new Cargo entity
         */
    public void saveCargo(Cargo entity) throws Exception;

    /**
         * Delete an existing Cargo entity
         *
         */
    public void deleteCargo(Cargo entity) throws Exception;

    /**
        * Update an existing Cargo entity
        *
        */
    public void updateCargo(Cargo entity) throws Exception;

    /**
         * Load an existing Cargo entity
         *
         */
    public Cargo getCargo(String carCod) throws Exception;

    public List<Cargo> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Cargo> findPageCargo(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberCargo() throws Exception;

    public List<CargoDTO> getDataCargo() throws Exception;
}
