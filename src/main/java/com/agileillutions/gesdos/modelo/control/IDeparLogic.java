package com.agileillutions.gesdos.modelo.control;

import java.util.List;

import com.agileillutions.gesdos.modelo.Depar;
import com.agileillutions.gesdos.modelo.dto.DeparDTO;


/**
* @author Zathura Code Generator http://code.google.com/p/zathura
* www.zathuracode.org
*
*/
public interface IDeparLogic {
    public List<Depar> getDepar() throws Exception;

    /**
         * Save an new Depar entity
         */
    public void saveDepar(Depar entity) throws Exception;

    /**
         * Delete an existing Depar entity
         *
         */
    public void deleteDepar(Depar entity) throws Exception;

    /**
        * Update an existing Depar entity
        *
        */
    public void updateDepar(Depar entity) throws Exception;

    /**
         * Load an existing Depar entity
         *
         */
    public Depar getDepar(Long depCod) throws Exception;

    public List<Depar> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Depar> findPageDepar(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberDepar() throws Exception;

    public List<DeparDTO> getDataDepar() throws Exception;
}
