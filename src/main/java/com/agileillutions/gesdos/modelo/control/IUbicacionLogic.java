package com.agileillutions.gesdos.modelo.control;

import java.util.List;

import com.agileillutions.gesdos.modelo.Ubicacion;
import com.agileillutions.gesdos.modelo.dto.UbicacionDTO;


/**
* @author Zathura Code Generator http://code.google.com/p/zathura
* www.zathuracode.org
*
*/
public interface IUbicacionLogic {
    public List<Ubicacion> getUbicacion() throws Exception;

    /**
         * Save an new Ubicacion entity
         */
    public void saveUbicacion(Ubicacion entity) throws Exception;

    /**
         * Delete an existing Ubicacion entity
         *
         */
    public void deleteUbicacion(Ubicacion entity) throws Exception;

    /**
        * Update an existing Ubicacion entity
        *
        */
    public void updateUbicacion(Ubicacion entity) throws Exception;

    /**
         * Load an existing Ubicacion entity
         *
         */
    public Ubicacion getUbicacion(String ubiCod) throws Exception;

    public List<Ubicacion> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Ubicacion> findPageUbicacion(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberUbicacion() throws Exception;

    public List<UbicacionDTO> getDataUbicacion() throws Exception;
}
