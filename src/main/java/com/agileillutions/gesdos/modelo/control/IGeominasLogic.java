package com.agileillutions.gesdos.modelo.control;

import java.util.List;

import com.agileillutions.gesdos.modelo.Geominas;
import com.agileillutions.gesdos.modelo.dto.GeominasDTO;


/**
* @author Zathura Code Generator http://code.google.com/p/zathura
* www.zathuracode.org
*
*/
public interface IGeominasLogic {
    public List<Geominas> getGeominas() throws Exception;

    /**
         * Save an new Geominas entity
         */
    public void saveGeominas(Geominas entity) throws Exception;

    /**
         * Delete an existing Geominas entity
         *
         */
    public void deleteGeominas(Geominas entity) throws Exception;

    /**
        * Update an existing Geominas entity
        *
        */
    public void updateGeominas(Geominas entity) throws Exception;

    /**
         * Load an existing Geominas entity
         *
         */
    public Geominas getGeominas(String geoCod) throws Exception;

    public List<Geominas> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Geominas> findPageGeominas(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberGeominas() throws Exception;

    public List<GeominasDTO> getDataGeominas() throws Exception;
}
