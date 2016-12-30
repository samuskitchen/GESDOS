package com.agileillutions.gesdos.modelo.control;

import java.util.List;

import com.agileillutions.gesdos.modelo.Empresas;
import com.agileillutions.gesdos.modelo.dto.EmpresasDTO;


/**
* @author Zathura Code Generator http://code.google.com/p/zathura
* www.zathuracode.org
*
*/
public interface IEmpresasLogic {
    public List<Empresas> getEmpresas() throws Exception;

    /**
         * Save an new Empresas entity
         */
    public void saveEmpresas(Empresas entity) throws Exception;

    /**
         * Delete an existing Empresas entity
         *
         */
    public void deleteEmpresas(Empresas entity) throws Exception;

    /**
        * Update an existing Empresas entity
        *
        */
    public void updateEmpresas(Empresas entity) throws Exception;

    /**
         * Load an existing Empresas entity
         *
         */
    public Empresas getEmpresas(Long empCod) throws Exception;

    public List<Empresas> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Empresas> findPageEmpresas(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberEmpresas() throws Exception;

    public List<EmpresasDTO> getDataEmpresas() throws Exception;
}
