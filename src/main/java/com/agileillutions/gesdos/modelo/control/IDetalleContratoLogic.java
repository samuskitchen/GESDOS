package com.agileillutions.gesdos.modelo.control;

import java.util.List;

import com.agileillutions.gesdos.modelo.DetalleContrato;
import com.agileillutions.gesdos.modelo.DetalleContratoId;
import com.agileillutions.gesdos.modelo.dto.DetalleContratoDTO;


/**
* @author Zathura Code Generator http://code.google.com/p/zathura
* www.zathuracode.org
*
*/
public interface IDetalleContratoLogic {
    public List<DetalleContrato> getDetalleContrato() throws Exception;

    /**
         * Save an new DetalleContrato entity
         */
    public void saveDetalleContrato(DetalleContrato entity)
        throws Exception;

    /**
         * Delete an existing DetalleContrato entity
         *
         */
    public void deleteDetalleContrato(DetalleContrato entity)
        throws Exception;

    /**
        * Update an existing DetalleContrato entity
        *
        */
    public void updateDetalleContrato(DetalleContrato entity)
        throws Exception;

    /**
         * Load an existing DetalleContrato entity
         *
         */
    public DetalleContrato getDetalleContrato(DetalleContratoId id)
        throws Exception;

    public List<DetalleContrato> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<DetalleContrato> findPageDetalleContrato(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception;

    public Long findTotalNumberDetalleContrato() throws Exception;

    public List<DetalleContratoDTO> getDataDetalleContrato()
        throws Exception;
}
