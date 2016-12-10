package com.agileillutions.gesdos.modelo.control;

import java.util.List;

import com.agileillutions.gesdos.modelo.Traba;
import com.agileillutions.gesdos.modelo.dto.TrabaDTO;


/**
* @author Zathura Code Generator http://code.google.com/p/zathura
* www.zathuracode.org
*
*/
public interface ITrabaLogic {
    public List<Traba> getTraba() throws Exception;

    /**
         * Save an new Traba entity
         */
    public void saveTraba(Traba entity) throws Exception;

    /**
         * Delete an existing Traba entity
         *
         */
    public void deleteTraba(Traba entity) throws Exception;

    /**
        * Update an existing Traba entity
        *
        */
    public void updateTraba(Traba entity) throws Exception;

    /**
         * Load an existing Traba entity
         *
         */
    public Traba getTraba(Long traCed) throws Exception;

    public List<Traba> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Traba> findPageTraba(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberTraba() throws Exception;

    public List<TrabaDTO> getDataTraba() throws Exception;
    
    /**
     * 
     * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava Suarez</a> 
     * @date 27/11/2016
     * @description 
     * @return
     * @throws Exception
     */
    public List<TrabaDTO> getDataTrabaEmpresa(Long codEmpresa) throws Exception;
    
}
