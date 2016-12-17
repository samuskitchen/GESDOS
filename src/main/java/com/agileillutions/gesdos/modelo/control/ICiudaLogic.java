package com.agileillutions.gesdos.modelo.control;

import java.util.List;

import com.agileillutions.gesdos.modelo.Ciuda;
import com.agileillutions.gesdos.modelo.CiudaId;
import com.agileillutions.gesdos.modelo.dto.CiudaDTO;


/**
* @author Zathura Code Generator http://code.google.com/p/zathura
* www.zathuracode.org
*
*/
public interface ICiudaLogic {
    public List<Ciuda> getCiuda() throws Exception;

    /**
         * Save an new Ciuda entity
         */
    public void saveCiuda(Ciuda entity) throws Exception;

    /**
         * Delete an existing Ciuda entity
         *
         */
    public void deleteCiuda(Ciuda entity) throws Exception;

    /**
        * Update an existing Ciuda entity
        *
        */
    public void updateCiuda(Ciuda entity) throws Exception;

    /**
         * Load an existing Ciuda entity
         *
         */
    public Ciuda getCiuda(CiudaId id) throws Exception;

    public List<Ciuda> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Ciuda> findPageCiuda(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberCiuda() throws Exception;

    public List<CiudaDTO> getDataCiuda() throws Exception;
}
