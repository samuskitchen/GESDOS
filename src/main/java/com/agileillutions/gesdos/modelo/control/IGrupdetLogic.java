package com.agileillutions.gesdos.modelo.control;

import java.util.List;

import com.agileillutions.gesdos.modelo.Grupdet;
import com.agileillutions.gesdos.modelo.GrupdetId;
import com.agileillutions.gesdos.modelo.dto.GrupdetDTO;


/**
* @author Zathura Code Generator http://code.google.com/p/zathura
* www.zathuracode.org
*
*/
public interface IGrupdetLogic {
    public List<Grupdet> getGrupdet() throws Exception;

    /**
         * Save an new Grupdet entity
         */
    public void saveGrupdet(Grupdet entity) throws Exception;

    /**
         * Delete an existing Grupdet entity
         *
         */
    public void deleteGrupdet(Grupdet entity) throws Exception;

    /**
        * Update an existing Grupdet entity
        *
        */
    public void updateGrupdet(Grupdet entity) throws Exception;

    /**
         * Load an existing Grupdet entity
         *
         */
    public Grupdet getGrupdet(GrupdetId id) throws Exception;

    public List<Grupdet> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Grupdet> findPageGrupdet(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberGrupdet() throws Exception;

    public List<GrupdetDTO> getDataGrupdet() throws Exception;
}
