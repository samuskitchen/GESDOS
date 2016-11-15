package com.agileillutions.gesdos.modelo.control;

import java.util.List;

import com.agileillutions.gesdos.modelo.Radiacion;
import com.agileillutions.gesdos.modelo.dto.RadiacionDTO;


/**
* @author Zathura Code Generator http://code.google.com/p/zathura
* www.zathuracode.org
*
*/
public interface IRadiacionLogic {
    public List<Radiacion> getRadiacion() throws Exception;

    /**
         * Save an new Radiacion entity
         */
    public void saveRadiacion(Radiacion entity) throws Exception;

    /**
         * Delete an existing Radiacion entity
         *
         */
    public void deleteRadiacion(Radiacion entity) throws Exception;

    /**
        * Update an existing Radiacion entity
        *
        */
    public void updateRadiacion(Radiacion entity) throws Exception;

    /**
         * Load an existing Radiacion entity
         *
         */
    public Radiacion getRadiacion(String radCod) throws Exception;

    public List<Radiacion> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Radiacion> findPageRadiacion(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberRadiacion() throws Exception;

    public List<RadiacionDTO> getDataRadiacion() throws Exception;
}
