package com.agileillutions.gesdos.modelo.control;

import java.util.List;

import com.agileillutions.gesdos.modelo.Socios;
import com.agileillutions.gesdos.modelo.dto.SociosDTO;


/**
* @author Zathura Code Generator http://code.google.com/p/zathura
* www.zathuracode.org
*
*/
public interface ISociosLogic {
    public List<Socios> getSocios() throws Exception;

    /**
         * Save an new Socios entity
         */
    public void saveSocios(Socios entity) throws Exception;

    /**
         * Delete an existing Socios entity
         *
         */
    public void deleteSocios(Socios entity) throws Exception;

    /**
        * Update an existing Socios entity
        *
        */
    public void updateSocios(Socios entity) throws Exception;

    /**
         * Load an existing Socios entity
         *
         */
    public Socios getSocios(String socCod) throws Exception;

    public List<Socios> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Socios> findPageSocios(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberSocios() throws Exception;

    public List<SociosDTO> getDataSocios() throws Exception;
}
