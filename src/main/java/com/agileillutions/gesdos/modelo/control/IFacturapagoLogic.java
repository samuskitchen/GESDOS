package com.agileillutions.gesdos.modelo.control;

import java.util.List;

import com.agileillutions.gesdos.modelo.Facturapago;
import com.agileillutions.gesdos.modelo.FacturapagoId;
import com.agileillutions.gesdos.modelo.dto.FacturapagoDTO;


/**
* @author Zathura Code Generator http://code.google.com/p/zathura
* www.zathuracode.org
*
*/
public interface IFacturapagoLogic {
    public List<Facturapago> getFacturapago() throws Exception;

    /**
         * Save an new Facturapago entity
         */
    public void saveFacturapago(Facturapago entity) throws Exception;

    /**
         * Delete an existing Facturapago entity
         *
         */
    public void deleteFacturapago(Facturapago entity) throws Exception;

    /**
        * Update an existing Facturapago entity
        *
        */
    public void updateFacturapago(Facturapago entity) throws Exception;

    /**
         * Load an existing Facturapago entity
         *
         */
    public Facturapago getFacturapago(FacturapagoId id)
        throws Exception;

    public List<Facturapago> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Facturapago> findPageFacturapago(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberFacturapago() throws Exception;

    public List<FacturapagoDTO> getDataFacturapago() throws Exception;
}
