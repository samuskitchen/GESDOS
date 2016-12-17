package com.agileillutions.gesdos.modelo.control;

import java.util.List;

import com.agileillutions.gesdos.modelo.Numer;
import com.agileillutions.gesdos.modelo.dto.NumerDTO;


/**
* @author Zathura Code Generator http://code.google.com/p/zathura
* www.zathuracode.org
*
*/
public interface INumerLogic {
    public List<Numer> getNumer() throws Exception;

    /**
         * Save an new Numer entity
         */
    public void saveNumer(Numer entity) throws Exception;

    /**
         * Delete an existing Numer entity
         *
         */
    public void deleteNumer(Numer entity) throws Exception;

    /**
        * Update an existing Numer entity
        *
        */
    public void updateNumer(Numer entity) throws Exception;

    /**
         * Load an existing Numer entity
         *
         */
    public Numer getNumer(Long numCod) throws Exception;

    public List<Numer> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Numer> findPageNumer(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberNumer() throws Exception;

    public List<NumerDTO> getDataNumer() throws Exception;
}
