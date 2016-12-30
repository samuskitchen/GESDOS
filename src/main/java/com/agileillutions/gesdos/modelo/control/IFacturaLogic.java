package com.agileillutions.gesdos.modelo.control;

import java.util.List;

import com.agileillutions.gesdos.modelo.Factura;
import com.agileillutions.gesdos.modelo.FacturaId;
import com.agileillutions.gesdos.modelo.dto.FacturaDTO;


/**
* @author Zathura Code Generator http://code.google.com/p/zathura
* www.zathuracode.org
*
*/
public interface IFacturaLogic {
    public List<Factura> getFactura() throws Exception;

    /**
         * Save an new Factura entity
         */
    public void saveFactura(Factura entity) throws Exception;

    /**
         * Delete an existing Factura entity
         *
         */
    public void deleteFactura(Factura entity) throws Exception;

    /**
        * Update an existing Factura entity
        *
        */
    public void updateFactura(Factura entity) throws Exception;

    /**
         * Load an existing Factura entity
         *
         */
    public Factura getFactura(FacturaId id) throws Exception;

    public List<Factura> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Factura> findPageFactura(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberFactura() throws Exception;

    public List<FacturaDTO> getDataFactura() throws Exception;

    /**
     * 
     * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava Suarez</a>
     * @date 15/12/2016
     * @description 
     * @param codEmpresa
     * @param codContrato
     * @return
     * @throws Exception
     */
	public List<FacturaDTO> getDataFacturaEmpresaContrato(Long codEmpresa, Long codContrato) throws Exception;
}
