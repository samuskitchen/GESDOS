package com.agileillutions.gesdos.modelo.control;

import java.util.List;

import com.agileillutions.gesdos.modelo.Revelado;
import com.agileillutions.gesdos.modelo.dto.ReveladoDTO;


/**
* @author Zathura Code Generator http://code.google.com/p/zathura
* www.zathuracode.org
*
*/
public interface IReveladoLogic {
    public List<Revelado> getRevelado() throws Exception;

    /**
         * Save an new Revelado entity
         */
    public void saveRevelado(Revelado entity) throws Exception;

    /**
         * Delete an existing Revelado entity
         *
         */
    public void deleteRevelado(Revelado entity) throws Exception;

    /**
        * Update an existing Revelado entity
        *
        */
    public void updateRevelado(Revelado entity) throws Exception;

    /**
         * Load an existing Revelado entity
         *
         */
    public Revelado getRevelado(Long revNro) throws Exception;

    public List<Revelado> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Revelado> findPageRevelado(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberRevelado() throws Exception;

    public List<ReveladoDTO> getDataRevelado() throws Exception;
}
