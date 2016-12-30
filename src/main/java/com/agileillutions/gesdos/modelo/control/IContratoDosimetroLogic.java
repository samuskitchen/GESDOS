package com.agileillutions.gesdos.modelo.control;

import java.util.List;

import com.agileillutions.gesdos.modelo.ContratoDosimetro;
import com.agileillutions.gesdos.modelo.dto.ContratoDosimetroDTO;


/**
* @author Zathura Code Generator http://code.google.com/p/zathura
* www.zathuracode.org
*
*/
public interface IContratoDosimetroLogic {
    public List<ContratoDosimetro> getContratoDosimetro()
        throws Exception;

    /**
         * Save an new ContratoDosimetro entity
         */
    public void saveContratoDosimetro(ContratoDosimetro entity)
        throws Exception;

    /**
         * Delete an existing ContratoDosimetro entity
         *
         */
    public void deleteContratoDosimetro(ContratoDosimetro entity)
        throws Exception;

    /**
        * Update an existing ContratoDosimetro entity
        *
        */
    public void updateContratoDosimetro(ContratoDosimetro entity)
        throws Exception;

    /**
         * Load an existing ContratoDosimetro entity
         *
         */
    public ContratoDosimetro getContratoDosimetro(Long conDosi)
        throws Exception;

    public List<ContratoDosimetro> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<ContratoDosimetro> findPageContratoDosimetro(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception;

    public Long findTotalNumberContratoDosimetro() throws Exception;

    public List<ContratoDosimetroDTO> getDataContratoDosimetro()
        throws Exception;
}
