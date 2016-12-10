package com.agileillutions.gesdos.modelo.control;

import java.util.List;

import com.agileillutions.gesdos.modelo.Practica;
import com.agileillutions.gesdos.modelo.dto.PracticaDTO;


/**
* @author Zathura Code Generator http://code.google.com/p/zathura
* www.zathuracode.org
*
*/
public interface IPracticaLogic {
    public List<Practica> getPractica() throws Exception;

    /**
         * Save an new Practica entity
         */
    public void savePractica(Practica entity) throws Exception;

    /**
         * Delete an existing Practica entity
         *
         */
    public void deletePractica(Practica entity) throws Exception;

    /**
        * Update an existing Practica entity
        *
        */
    public void updatePractica(Practica entity) throws Exception;

    /**
         * Load an existing Practica entity
         *
         */
    public Practica getPractica(String praCod) throws Exception;

    public List<Practica> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Practica> findPagePractica(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberPractica() throws Exception;

    public List<PracticaDTO> getDataPractica() throws Exception;
}
