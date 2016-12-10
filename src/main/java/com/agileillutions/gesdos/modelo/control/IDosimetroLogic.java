package com.agileillutions.gesdos.modelo.control;

import java.util.List;

import com.agileillutions.gesdos.modelo.Dosimetro;
import com.agileillutions.gesdos.modelo.DosimetroId;
import com.agileillutions.gesdos.modelo.dto.DosimetroDTO;


/**
* @author Zathura Code Generator http://code.google.com/p/zathura
* www.zathuracode.org
*
*/
public interface IDosimetroLogic {
    public List<Dosimetro> getDosimetro() throws Exception;

    /**
         * Save an new Dosimetro entity
         */
    public void saveDosimetro(Dosimetro entity) throws Exception;

    /**
         * Delete an existing Dosimetro entity
         *
         */
    public void deleteDosimetro(Dosimetro entity) throws Exception;

    /**
        * Update an existing Dosimetro entity
        *
        */
    public void updateDosimetro(Dosimetro entity) throws Exception;

    /**
         * Load an existing Dosimetro entity
         *
         */
    public Dosimetro getDosimetro(DosimetroId id) throws Exception;

    public List<Dosimetro> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Dosimetro> findPageDosimetro(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberDosimetro() throws Exception;

    public List<DosimetroDTO> getDataDosimetro() throws Exception;

    /**
     * 
     * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava Suarez</a> 
     * @date 27/11/2016
     * @description 
     * @param tipo
     * @param id
     * @return
     * @throws Exception
     */
	public List<DosimetroDTO> getDataDosimetroTipo(String tipo, Long id) throws Exception;
	
	/**
	 * 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava Suarez</a> 
	 * @date 27/11/2016
	 * @description 
	 * @param idEmp
	 * @param idTraba
	 * @return
	 * @throws Exception
	 */
	public List<DosimetroDTO> getDataDosimetroEmpTraba(Long idEmp, Long idTraba, Long idDosi) throws Exception;

	/**
	 * 
	 * @author <a href="mailto:daniel.samkit@gmail.com">Daniel De La Pava Suarez</a> 
	 * @date 27/11/2016
	 * @description 
	 * @param idEmp
	 * @param idTraba
	 * @return
	 * @throws Exception
	 */
	public DosimetroDTO getDataDosimetroUltimo(Long idEmp, Long idTraba)
			throws Exception;

}
