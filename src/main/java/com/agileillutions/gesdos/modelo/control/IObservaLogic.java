package com.agileillutions.gesdos.modelo.control;

import java.util.List;

import com.agileillutions.gesdos.modelo.Observa;
import com.agileillutions.gesdos.modelo.dto.ObservaDTO;


/**
* @author Zathura Code Generator http://code.google.com/p/zathura
* www.zathuracode.org
*
*/
public interface IObservaLogic {
    public List<Observa> getObserva() throws Exception;

    /**
         * Save an new Observa entity
         */
    public void saveObserva(Observa entity) throws Exception;

    /**
         * Delete an existing Observa entity
         *
         */
    public void deleteObserva(Observa entity) throws Exception;

    /**
        * Update an existing Observa entity
        *
        */
    public void updateObserva(Observa entity) throws Exception;

    /**
         * Load an existing Observa entity
         *
         */
    public Observa getObserva(String obsCod) throws Exception;

    public List<Observa> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Observa> findPageObserva(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberObserva() throws Exception;

    public List<ObservaDTO> getDataObserva() throws Exception;
}
