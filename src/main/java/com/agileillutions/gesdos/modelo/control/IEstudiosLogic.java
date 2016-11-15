package com.agileillutions.gesdos.modelo.control;

import java.util.List;

import com.agileillutions.gesdos.modelo.Estudios;
import com.agileillutions.gesdos.modelo.EstudiosId;
import com.agileillutions.gesdos.modelo.dto.EstudiosDTO;


/**
* @author Zathura Code Generator http://code.google.com/p/zathura
* www.zathuracode.org
*
*/
public interface IEstudiosLogic {
    public List<Estudios> getEstudios() throws Exception;

    /**
         * Save an new Estudios entity
         */
    public void saveEstudios(Estudios entity) throws Exception;

    /**
         * Delete an existing Estudios entity
         *
         */
    public void deleteEstudios(Estudios entity) throws Exception;

    /**
        * Update an existing Estudios entity
        *
        */
    public void updateEstudios(Estudios entity) throws Exception;

    /**
         * Load an existing Estudios entity
         *
         */
    public Estudios getEstudios(EstudiosId id) throws Exception;

    public List<Estudios> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Estudios> findPageEstudios(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberEstudios() throws Exception;

    public List<EstudiosDTO> getDataEstudios() throws Exception;
}
