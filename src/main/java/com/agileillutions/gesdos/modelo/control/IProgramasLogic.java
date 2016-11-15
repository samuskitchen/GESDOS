package com.agileillutions.gesdos.modelo.control;

import java.util.List;

import com.agileillutions.gesdos.modelo.Programas;
import com.agileillutions.gesdos.modelo.dto.ProgramasDTO;


/**
* @author Zathura Code Generator http://code.google.com/p/zathura
* www.zathuracode.org
*
*/
public interface IProgramasLogic {
    public List<Programas> getProgramas() throws Exception;

    /**
         * Save an new Programas entity
         */
    public void saveProgramas(Programas entity) throws Exception;

    /**
         * Delete an existing Programas entity
         *
         */
    public void deleteProgramas(Programas entity) throws Exception;

    /**
        * Update an existing Programas entity
        *
        */
    public void updateProgramas(Programas entity) throws Exception;

    /**
         * Load an existing Programas entity
         *
         */
    public Programas getProgramas(String progCodigo) throws Exception;

    public List<Programas> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Programas> findPageProgramas(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberProgramas() throws Exception;

    public List<ProgramasDTO> getDataProgramas() throws Exception;
}
