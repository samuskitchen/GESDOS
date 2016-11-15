package com.agileillutions.gesdos.modelo.control;

import java.util.List;

import com.agileillutions.gesdos.modelo.Contratos;
import com.agileillutions.gesdos.modelo.ContratosId;
import com.agileillutions.gesdos.modelo.dto.ContratosDTO;


/**
* @author Zathura Code Generator http://code.google.com/p/zathura
* www.zathuracode.org
*
*/
public interface IContratosLogic {
    public List<Contratos> getContratos() throws Exception;

    /**
         * Save an new Contratos entity
         */
    public void saveContratos(Contratos entity) throws Exception;

    /**
         * Delete an existing Contratos entity
         *
         */
    public void deleteContratos(Contratos entity) throws Exception;

    /**
        * Update an existing Contratos entity
        *
        */
    public void updateContratos(Contratos entity) throws Exception;

    /**
         * Load an existing Contratos entity
         *
         */
    public Contratos getContratos(ContratosId id) throws Exception;

    public List<Contratos> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Contratos> findPageContratos(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberContratos() throws Exception;

    public List<ContratosDTO> getDataContratos() throws Exception;
}
