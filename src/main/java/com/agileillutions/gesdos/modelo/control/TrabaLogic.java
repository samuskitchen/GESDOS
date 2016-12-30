package com.agileillutions.gesdos.modelo.control;

import java.nio.file.attribute.DosFileAttributeView;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.agileillutions.gesdos.dataaccess.dao.IDosimetroDAO;
import com.agileillutions.gesdos.dataaccess.dao.IEmpresasDAO;
import com.agileillutions.gesdos.dataaccess.dao.ITrabaDAO;
import com.agileillutions.gesdos.exceptions.ZMessManager;
import com.agileillutions.gesdos.modelo.Dosimetro;
import com.agileillutions.gesdos.modelo.DosimetroId;
import com.agileillutions.gesdos.modelo.Empresas;
import com.agileillutions.gesdos.modelo.Traba;
import com.agileillutions.gesdos.modelo.dto.TrabaDTO;
import com.agileillutions.gesdos.utilities.Utilities;

/**
 * @author Zathura Code Generator http://code.google.com/p/zathura
 *         www.zathuracode.org
 *
 */
@Scope("singleton")
@Service("TrabaLogic")
public class TrabaLogic implements ITrabaLogic {
	/**
	 * DAO injected by Spring that manages Traba entities
	 *
	 */
	@Autowired
	private ITrabaDAO trabaDAO;

	@Autowired
	private IDosimetroDAO dosimetroDAO;

	@Autowired
	private IEmpresasDAO empresaDAO;

	@Transactional(readOnly = true)
	public List<Traba> getTraba() throws Exception {
		List<Traba> list = new ArrayList<Traba>();

		try {
			list = trabaDAO.findAll();
		} catch (Exception e) {
			throw new ZMessManager().new GettingException(ZMessManager.ALL
					+ "Traba");
		} finally {
		}

		return list;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveTraba(Traba entity) throws Exception {
		try {
			if (entity.getTraApe1() == null) {
				throw new ZMessManager().new EmptyFieldException(
						"Primer Apellido");
			}

			if ((entity.getTraApe1() != null)
					&& (Utilities.checkWordAndCheckWithlength(
							entity.getTraApe1(), 20) == false)) {
				throw new ZMessManager().new NotValidFormatException(
						"Primer Apellido");
			}

			if (entity.getTraApe2() == null) {
				throw new ZMessManager().new EmptyFieldException(
						"Segundo Apellido");
			}

			if ((entity.getTraApe2() != null)
					&& (Utilities.checkWordAndCheckWithlength(
							entity.getTraApe2(), 20) == false)) {
				throw new ZMessManager().new NotValidFormatException(
						"Segundo Apellido");
			}

			if (entity.getTraCed() == null) {
				throw new ZMessManager().new EmptyFieldException(
						"Cedula Trabajador");
			}

			if ((entity.getTraCed() != null)
					&& (Utilities.checkNumberAndCheckWithPrecisionAndScale(""
							+ entity.getTraCed(), 22, 0) == false)) {
				throw new ZMessManager().new NotValidFormatException(
						"Cedula Trabajador");
			}

			if (entity.getTraEst() == null) {
				throw new ZMessManager().new EmptyFieldException(
						"Estado Trabaajador");
			}

			if ((entity.getTraEst() != null)
					&& (Utilities.checkWordAndCheckWithlength(
							entity.getTraEst(), 1) == false)) {
				throw new ZMessManager().new NotValidFormatException(
						"Estado Trabaajador");
			}

			if (entity.getTraFecIni() == null) {
				throw new ZMessManager().new EmptyFieldException(
						"Fecha Registro en RDC");
			}

			if (entity.getTraNom() == null) {
				throw new ZMessManager().new EmptyFieldException("Nombre");
			}

			if ((entity.getTraNom() != null)
					&& (Utilities.checkWordAndCheckWithlength(
							entity.getTraNom(), 20) == false)) {
				throw new ZMessManager().new NotValidFormatException("Nombre");
			}

			if ((entity.getTraObs() != null)
					&& (Utilities.checkWordAndCheckWithlength(
							entity.getTraObs(), 100) == false)) {
				throw new ZMessManager().new NotValidFormatException(
						"Observación");
			}

			if (entity.getTraSex() == null) {
				throw new ZMessManager().new EmptyFieldException("Genero");
			}

			if ((entity.getTraSex() != null)
					&& (Utilities.checkWordAndCheckWithlength(
							entity.getTraSex(), 1) == false)) {
				throw new ZMessManager().new NotValidFormatException("Genero");
			}

			if (getTraba(entity.getTraCed()) != null) {
				throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
			}

			trabaDAO.save(entity);
		} catch (Exception e) {
			throw e;
		} finally {
		}
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void deleteTraba(Traba entity) throws Exception {
		if (entity == null) {
			throw new ZMessManager().new NullEntityExcepcion("Traba");
		}

		if (entity.getTraCed() == null) {
			throw new ZMessManager().new EmptyFieldException("traCed");
		}

		try {
			trabaDAO.delete(entity);
		} catch (Exception e) {
			throw e;
		} finally {
		}
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void updateTraba(Traba entity) throws Exception {
		try {
			if (entity.getTraApe1() == null) {
				throw new ZMessManager().new EmptyFieldException(
						"Primer Apellido");
			}

			if ((entity.getTraApe1() != null)
					&& (Utilities.checkWordAndCheckWithlength(
							entity.getTraApe1(), 20) == false)) {
				throw new ZMessManager().new NotValidFormatException(
						"Primer Apellido");
			}

			if (entity.getTraApe2() == null) {
				throw new ZMessManager().new EmptyFieldException(
						"Segundo Apellido");
			}

			if ((entity.getTraApe2() != null)
					&& (Utilities.checkWordAndCheckWithlength(
							entity.getTraApe2(), 20) == false)) {
				throw new ZMessManager().new NotValidFormatException(
						"Segundo Apellido");
			}

			if (entity.getTraCed() == null) {
				throw new ZMessManager().new EmptyFieldException(
						"Cedula Trabajador");
			}

			if ((entity.getTraCed() != null)
					&& (Utilities.checkNumberAndCheckWithPrecisionAndScale(""
							+ entity.getTraCed(), 22, 0) == false)) {
				throw new ZMessManager().new NotValidFormatException(
						"Cedula Trabajador");
			}

			if (entity.getTraEst() == null) {
				throw new ZMessManager().new EmptyFieldException(
						"Estado Trabaajador");
			}

			if ((entity.getTraEst() != null)
					&& (Utilities.checkWordAndCheckWithlength(
							entity.getTraEst(), 1) == false)) {
				throw new ZMessManager().new NotValidFormatException(
						"Estado Trabaajador");
			}

			if (entity.getTraFecIni() == null) {
				throw new ZMessManager().new EmptyFieldException(
						"Fecha Registro en RDC");
			}

			if (entity.getTraNom() == null) {
				throw new ZMessManager().new EmptyFieldException("Nombre");
			}

			if ((entity.getTraNom() != null)
					&& (Utilities.checkWordAndCheckWithlength(
							entity.getTraNom(), 20) == false)) {
				throw new ZMessManager().new NotValidFormatException("Nombre");
			}

			if ((entity.getTraObs() != null)
					&& (Utilities.checkWordAndCheckWithlength(
							entity.getTraObs(), 100) == false)) {
				throw new ZMessManager().new NotValidFormatException(
						"Observación");
			}

			if (entity.getTraSex() == null) {
				throw new ZMessManager().new EmptyFieldException("Genero");
			}

			if ((entity.getTraSex() != null)
					&& (Utilities.checkWordAndCheckWithlength(
							entity.getTraSex(), 1) == false)) {
				throw new ZMessManager().new NotValidFormatException("Genero");
			}

			if ((entity.getTraSex() != null)
					&& (Utilities.checkWordAndCheckWithlength(
							entity.getTraSex(), 1) == false)) {
				throw new ZMessManager().new NotValidFormatException("traSex");
			}

			trabaDAO.update(entity);
		} catch (Exception e) {
			throw e;
		} finally {
		}
	}

	@Transactional(readOnly = true)
	public List<TrabaDTO> getDataTraba() throws Exception {
		try {
			
			List<TrabaDTO> trabaDTO = new ArrayList<TrabaDTO>();
			List<Dosimetro> dosimetros = dosimetroDAO.findAll();

			for (Dosimetro dosimetro : dosimetros) {

				Traba traba = trabaDAO.findById(dosimetro.getId().getTraCed());
				Empresas empresas = empresaDAO.findById(dosimetro.getId()
						.getEmpCod());

				TrabaDTO trabaDTO2 = new TrabaDTO();

				trabaDTO2.setTraCed(traba.getTraCed());
				trabaDTO2.setTraApe1((traba.getTraApe1() != null) ? traba
						.getTraApe1() : null);
				trabaDTO2.setTraApe2((traba.getTraApe2() != null) ? traba
						.getTraApe2() : null);
				trabaDTO2.setTraEst((traba.getTraEst() != null) ? traba
						.getTraEst() : null);
				trabaDTO2.setTraFecIni(traba.getTraFecIni());
				trabaDTO2.setTraNom((traba.getTraNom() != null) ? traba
						.getTraNom() : null);
				trabaDTO2.setTraObs((traba.getTraObs() != null) ? traba
						.getTraObs() : null);
				trabaDTO2.setTraSex((traba.getTraSex() != null) ? traba
						.getTraSex() : null);
				trabaDTO.add(trabaDTO2);

				trabaDTO2.setCodEmp(empresas.getEmpCod());
				trabaDTO2
						.setEmpRazSoc(empresas.getEmpRazSoc() != null ? empresas
								.getEmpRazSoc() : null);
				trabaDTO2.setCodDosi(dosimetro.getId().getDosCod());

			}

			return trabaDTO;
		} catch (Exception e) {
			throw e;
		}
	}

	@Transactional(readOnly = true)
	public Traba getTraba(Long traCed) throws Exception {
		Traba entity = null;

		try {
			entity = trabaDAO.findById(traCed);
		} catch (Exception e) {
			throw new ZMessManager().new FindingException("Traba");
		} finally {
		}

		return entity;
	}

	@Transactional(readOnly = true)
	public List<Traba> findPageTraba(String sortColumnName,
			boolean sortAscending, int startRow, int maxResults)
			throws Exception {
		List<Traba> entity = null;

		try {
			entity = trabaDAO.findPage(sortColumnName, sortAscending, startRow,
					maxResults);
		} catch (Exception e) {
			throw new ZMessManager().new FindingException("Traba Count");
		} finally {
		}

		return entity;
	}

	@Transactional(readOnly = true)
	public Long findTotalNumberTraba() throws Exception {
		Long entity = null;

		try {
			entity = trabaDAO.count();
		} catch (Exception e) {
			throw new ZMessManager().new FindingException("Traba Count");
		} finally {
		}

		return entity;
	}

	/**
	 *
	 * @param varibles
	 *            este arreglo debera tener:
	 *
	 *            [0] = String variable = (String) varibles[i]; representa como
	 *            se llama la variable en el pojo
	 *
	 *            [1] = Boolean booVariable = (Boolean) varibles[i + 1];
	 *            representa si el valor necesita o no ''(comillas simples)usado
	 *            para campos de tipo string
	 *
	 *            [2] = Object value = varibles[i + 2]; representa el valor que
	 *            se va a buscar en la BD
	 *
	 *            [3] = String comparator = (String) varibles[i + 3]; representa
	 *            que tipo de busqueda voy a hacer.., ejemplo: where
	 *            nombre=william o where nombre<>william, en este campo iria el
	 *            tipo de comparador que quiero si es = o <>
	 *
	 *            Se itera de 4 en 4..., entonces 4 registros del arreglo
	 *            representan 1 busqueda en un campo, si se ponen mas pues el
	 *            continuara buscando en lo que se le ingresen en los otros 4
	 *
	 *
	 * @param variablesBetween
	 *
	 *            la diferencia son estas dos posiciones
	 *
	 *            [0] = String variable = (String) varibles[j]; la variable ne
	 *            la BD que va a ser buscada en un rango
	 *
	 *            [1] = Object value = varibles[j + 1]; valor 1 para buscar en
	 *            un rango
	 *
	 *            [2] = Object value2 = varibles[j + 2]; valor 2 para buscar en
	 *            un rango ejempolo: a > 1 and a < 5 --> 1 seria value y 5 seria
	 *            value2
	 *
	 *            [3] = String comparator1 = (String) varibles[j + 3];
	 *            comparador 1 ejemplo: a comparator1 1 and a < 5
	 *
	 *            [4] = String comparator2 = (String) varibles[j + 4];
	 *            comparador 2 ejemplo: a comparador1>1 and a comparador2<5 (el
	 *            original: a > 1 and a < 5) *
	 * @param variablesBetweenDates
	 *            (en este caso solo para mysql) [0] = String variable =
	 *            (String) varibles[k]; el nombre de la variable que hace
	 *            referencia a una fecha
	 *
	 *            [1] = Object object1 = varibles[k + 2]; fecha 1 a
	 *            comparar(deben ser dates)
	 *
	 *            [2] = Object object2 = varibles[k + 3]; fecha 2 a
	 *            comparar(deben ser dates)
	 *
	 *            esto hace un between entre las dos fechas.
	 *
	 * @return lista con los objetos que se necesiten
	 * @throws Exception
	 */
	@Transactional(readOnly = true)
	public List<Traba> findByCriteria(Object[] variables,
			Object[] variablesBetween, Object[] variablesBetweenDates)
			throws Exception {
		List<Traba> list = new ArrayList<Traba>();
		String where = new String();
		String tempWhere = new String();

		if (variables != null) {
			for (int i = 0; i < variables.length; i++) {
				if ((variables[i] != null) && (variables[i + 1] != null)
						&& (variables[i + 2] != null)
						&& (variables[i + 3] != null)) {
					String variable = (String) variables[i];
					Boolean booVariable = (Boolean) variables[i + 1];
					Object value = variables[i + 2];
					String comparator = (String) variables[i + 3];

					if (booVariable.booleanValue()) {
						tempWhere = (tempWhere.length() == 0) ? ("(model."
								+ variable + " " + comparator + " \'" + value + "\' )")
								: (tempWhere + " AND (model." + variable + " "
										+ comparator + " \'" + value + "\' )");
					} else {
						tempWhere = (tempWhere.length() == 0) ? ("(model."
								+ variable + " " + comparator + " " + value + " )")
								: (tempWhere + " AND (model." + variable + " "
										+ comparator + " " + value + " )");
					}
				}

				i = i + 3;
			}
		}

		if (variablesBetween != null) {
			for (int j = 0; j < variablesBetween.length; j++) {
				if ((variablesBetween[j] != null)
						&& (variablesBetween[j + 1] != null)
						&& (variablesBetween[j + 2] != null)
						&& (variablesBetween[j + 3] != null)
						&& (variablesBetween[j + 4] != null)) {
					String variable = (String) variablesBetween[j];
					Object value = variablesBetween[j + 1];
					Object value2 = variablesBetween[j + 2];
					String comparator1 = (String) variablesBetween[j + 3];
					String comparator2 = (String) variablesBetween[j + 4];
					tempWhere = (tempWhere.length() == 0) ? ("(" + value + " "
							+ comparator1 + " " + variable + " and " + variable
							+ " " + comparator2 + " " + value2 + " )")
							: (tempWhere + " AND (" + value + " " + comparator1
									+ " " + variable + " and " + variable + " "
									+ comparator2 + " " + value2 + " )");
				}

				j = j + 4;
			}
		}

		if (variablesBetweenDates != null) {
			for (int k = 0; k < variablesBetweenDates.length; k++) {
				if ((variablesBetweenDates[k] != null)
						&& (variablesBetweenDates[k + 1] != null)
						&& (variablesBetweenDates[k + 2] != null)) {
					String variable = (String) variablesBetweenDates[k];
					Object object1 = variablesBetweenDates[k + 1];
					Object object2 = variablesBetweenDates[k + 2];
					String value = null;
					String value2 = null;

					try {
						Date date1 = (Date) object1;
						Date date2 = (Date) object2;
						value = Utilities
								.formatDateWithoutTimeInAStringForBetweenWhere(date1);
						value2 = Utilities
								.formatDateWithoutTimeInAStringForBetweenWhere(date2);
					} catch (Exception e) {
						list = null;
						throw e;
					}

					tempWhere = (tempWhere.length() == 0) ? ("(model."
							+ variable + " between \'" + value + "\' and \'"
							+ value2 + "\')") : (tempWhere + " AND (model."
							+ variable + " between \'" + value + "\' and \'"
							+ value2 + "\')");
				}

				k = k + 2;
			}
		}

		if (tempWhere.length() == 0) {
			where = null;
		} else {
			where = "(" + tempWhere + ")";
		}

		try {
			list = trabaDAO.findByCriteria(where);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		} finally {
		}

		return list;
	}

	@Transactional(readOnly = true)
	@Override
	public List<TrabaDTO> getDataTrabaEmpresa(Long codEmpresa) throws Exception {
		try {

			List<TrabaDTO> trabaDTO = new ArrayList<TrabaDTO>();
			List<Dosimetro> dosimetroDTOs = dosimetroDAO
					.findByCriteria("id.empCod = " + codEmpresa);

			for (Dosimetro dosimetro : dosimetroDTOs) {

				Traba traba = trabaDAO.findById(dosimetro.getId().getTraCed());
				Empresas empresas = empresaDAO.findById(dosimetro.getId()
						.getEmpCod());

				TrabaDTO trabaDTO2 = new TrabaDTO();

				trabaDTO2.setTraCed(traba.getTraCed());
				trabaDTO2.setTraApe1((traba.getTraApe1() != null) ? traba
						.getTraApe1() : null);
				trabaDTO2.setTraApe2((traba.getTraApe2() != null) ? traba
						.getTraApe2() : null);
				trabaDTO2.setTraEst((traba.getTraEst() != null) ? traba
						.getTraEst() : null);
				trabaDTO2.setTraFecIni(traba.getTraFecIni());
				trabaDTO2.setTraNom((traba.getTraNom() != null) ? traba
						.getTraNom() : null);
				trabaDTO2.setTraObs((traba.getTraObs() != null) ? traba
						.getTraObs() : null);
				trabaDTO2.setTraSex((traba.getTraSex() != null) ? traba
						.getTraSex() : null);
				trabaDTO.add(trabaDTO2);

				trabaDTO2.setCodEmp(empresas.getEmpCod());
				trabaDTO2
						.setEmpRazSoc(empresas.getEmpRazSoc() != null ? empresas
								.getEmpRazSoc() : null);
				trabaDTO2.setCodDosi(dosimetro.getId().getDosCod());
			}

			return trabaDTO;
		} catch (Exception e) {
			throw e;
		}
	}
}