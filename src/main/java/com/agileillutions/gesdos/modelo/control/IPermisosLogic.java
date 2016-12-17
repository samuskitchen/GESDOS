package com.agileillutions.gesdos.modelo.control;

import java.util.List;

import com.agileillutions.gesdos.modelo.Permisos;
import com.agileillutions.gesdos.modelo.PermisosId;
import com.agileillutions.gesdos.modelo.dto.PermisosDTO;


/**
* @author Zathura Code Generator http://code.google.com/p/zathura
* www.zathuracode.org
*
*/
public interface IPermisosLogic {
    public List<Permisos> getPermisos() throws Exception;

    /**
         * Save an new Permisos entity
         */
    public void savePermisos(Permisos entity) throws Exception;

    /**
         * Delete an existing Permisos entity
         *
         */
    public void deletePermisos(Permisos entity) throws Exception;

    /**
        * Update an existing Permisos entity
        *
        */
    public void updatePermisos(Permisos entity) throws Exception;

    /**
         * Load an existing Permisos entity
         *
         */
    public Permisos getPermisos(PermisosId id) throws Exception;

    public List<Permisos> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Permisos> findPagePermisos(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberPermisos() throws Exception;

    public List<PermisosDTO> getDataPermisos() throws Exception;
}
