package com.agileillutions.gesdos.modelo.control;

import java.util.List;

import com.agileillutions.gesdos.modelo.Programa1;
import com.agileillutions.gesdos.modelo.Programa1Id;
import com.agileillutions.gesdos.modelo.dto.Programa1DTO;


/**
* @author Zathura Code Generator http://code.google.com/p/zathura
* www.zathuracode.org
*
*/
public interface IPrograma1Logic {
    public List<Programa1> getPrograma1() throws Exception;

    /**
         * Save an new Programa1 entity
         */
    public void savePrograma1(Programa1 entity) throws Exception;

    /**
         * Delete an existing Programa1 entity
         *
         */
    public void deletePrograma1(Programa1 entity) throws Exception;

    /**
        * Update an existing Programa1 entity
        *
        */
    public void updatePrograma1(Programa1 entity) throws Exception;

    /**
         * Load an existing Programa1 entity
         *
         */
    public Programa1 getPrograma1(Programa1Id id) throws Exception;

    public List<Programa1> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Programa1> findPagePrograma1(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberPrograma1() throws Exception;

    public List<Programa1DTO> getDataPrograma1() throws Exception;
}
