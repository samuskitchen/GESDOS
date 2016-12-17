package com.agileillutions.gesdos.modelo.control;

import java.util.List;

import com.agileillutions.gesdos.modelo.Grupos;
import com.agileillutions.gesdos.modelo.dto.GruposDTO;


/**
* @author Zathura Code Generator http://code.google.com/p/zathura
* www.zathuracode.org
*
*/
public interface IGruposLogic {
    public List<Grupos> getGrupos() throws Exception;

    /**
         * Save an new Grupos entity
         */
    public void saveGrupos(Grupos entity) throws Exception;

    /**
         * Delete an existing Grupos entity
         *
         */
    public void deleteGrupos(Grupos entity) throws Exception;

    /**
        * Update an existing Grupos entity
        *
        */
    public void updateGrupos(Grupos entity) throws Exception;

    /**
         * Load an existing Grupos entity
         *
         */
    public Grupos getGrupos(String grupCodigo) throws Exception;

    public List<Grupos> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Grupos> findPageGrupos(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberGrupos() throws Exception;

    public List<GruposDTO> getDataGrupos() throws Exception;
}
