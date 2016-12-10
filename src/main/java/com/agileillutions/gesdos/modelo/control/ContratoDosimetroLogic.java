package com.agileillutions.gesdos.modelo.control;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.agileillutions.gesdos.dataaccess.dao.IContratoDosimetroDAO;
import com.agileillutions.gesdos.exceptions.ZMessManager;
import com.agileillutions.gesdos.modelo.ContratoDosimetro;
import com.agileillutions.gesdos.modelo.dto.ContratoDosimetroDTO;
import com.agileillutions.gesdos.utilities.Utilities;


/**
* @author Zathura Code Generator http://code.google.com/p/zathura
* www.zathuracode.org
*
*/
@Scope("singleton")
@Service("ContratoDosimetroLogic")
public class ContratoDosimetroLogic implements IContratoDosimetroLogic {
    /**
     * DAO injected by Spring that manages ContratoDosimetro entities
     *
     */
    @Autowired
    private IContratoDosimetroDAO contratoDosimetroDAO;

    @Transactional(readOnly = true)
    public List<ContratoDosimetro> getContratoDosimetro()
        throws Exception {
        List<ContratoDosimetro> list = new ArrayList<ContratoDosimetro>();

        try {
            list = contratoDosimetroDAO.findAll();
        } catch (Exception e) {
            throw new ZMessManager().new GettingException(ZMessManager.ALL +
                "ContratoDosimetro");
        } finally {
        }

        return list;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void saveContratoDosimetro(ContratoDosimetro entity)
        throws Exception {
        try {
            if (entity.getConDosi() == null) {
                throw new ZMessManager().new EmptyFieldException("conDosi");
            }

            if ((entity.getConDosi() != null) &&
                    (Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
                        entity.getConDosi(), 22, 0) == false)) {
                throw new ZMessManager().new NotValidFormatException("conDosi");
            }

            if (entity.getConNro() == null) {
                throw new ZMessManager().new EmptyFieldException("conNro");
            }

            if ((entity.getConNro() != null) &&
                    (Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
                        entity.getConNro(), 22, 0) == false)) {
                throw new ZMessManager().new NotValidFormatException("conNro");
            }

            if (entity.getDosCod() == null) {
                throw new ZMessManager().new EmptyFieldException("dosCod");
            }

            if ((entity.getDosCod() != null) &&
                    (Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
                        entity.getDosCod(), 22, 0) == false)) {
                throw new ZMessManager().new NotValidFormatException("dosCod");
            }

            if (getContratoDosimetro(entity.getConDosi()) != null) {
                throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
            }

            contratoDosimetroDAO.save(entity);
        } catch (Exception e) {
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void deleteContratoDosimetro(ContratoDosimetro entity)
        throws Exception {
        if (entity == null) {
            throw new ZMessManager().new NullEntityExcepcion(
                "ContratoDosimetro");
        }

        if (entity.getConDosi() == null) {
            throw new ZMessManager().new EmptyFieldException("conDosi");
        }

        try {
            contratoDosimetroDAO.delete(entity);
        } catch (Exception e) {
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void updateContratoDosimetro(ContratoDosimetro entity)
        throws Exception {
        try {
            if (entity == null) {
                throw new ZMessManager().new NullEntityExcepcion(
                    "ContratoDosimetro");
            }

            if (entity.getConDosi() == null) {
                throw new ZMessManager().new EmptyFieldException("conDosi");
            }

            if ((entity.getConDosi() != null) &&
                    (Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
                        entity.getConDosi(), 22, 0) == false)) {
                throw new ZMessManager().new NotValidFormatException("conDosi");
            }

            if (entity.getConNro() == null) {
                throw new ZMessManager().new EmptyFieldException("conNro");
            }

            if ((entity.getConNro() != null) &&
                    (Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
                        entity.getConNro(), 22, 0) == false)) {
                throw new ZMessManager().new NotValidFormatException("conNro");
            }

            if (entity.getDosCod() == null) {
                throw new ZMessManager().new EmptyFieldException("dosCod");
            }

            if ((entity.getDosCod() != null) &&
                    (Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
                        entity.getDosCod(), 22, 0) == false)) {
                throw new ZMessManager().new NotValidFormatException("dosCod");
            }

            contratoDosimetroDAO.update(entity);
        } catch (Exception e) {
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = true)
    public List<ContratoDosimetroDTO> getDataContratoDosimetro()
        throws Exception {
        try {
            List<ContratoDosimetro> contratoDosimetro = contratoDosimetroDAO.findAll();

            List<ContratoDosimetroDTO> contratoDosimetroDTO = new ArrayList<ContratoDosimetroDTO>();

            for (ContratoDosimetro contratoDosimetroTmp : contratoDosimetro) {
                ContratoDosimetroDTO contratoDosimetroDTO2 = new ContratoDosimetroDTO();

                contratoDosimetroDTO2.setConDosi(contratoDosimetroTmp.getConDosi());
                contratoDosimetroDTO2.setConNro((contratoDosimetroTmp.getConNro() != null)
                    ? contratoDosimetroTmp.getConNro() : null);
                contratoDosimetroDTO2.setDosCod((contratoDosimetroTmp.getDosCod() != null)
                    ? contratoDosimetroTmp.getDosCod() : null);
                contratoDosimetroDTO.add(contratoDosimetroDTO2);
            }

            return contratoDosimetroDTO;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public ContratoDosimetro getContratoDosimetro(Long conDosi)
        throws Exception {
        ContratoDosimetro entity = null;

        try {
            entity = contratoDosimetroDAO.findById(conDosi);
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("ContratoDosimetro");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public List<ContratoDosimetro> findPageContratoDosimetro(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception {
        List<ContratoDosimetro> entity = null;

        try {
            entity = contratoDosimetroDAO.findPage(sortColumnName,
                    sortAscending, startRow, maxResults);
        } catch (Exception e) {
            throw new ZMessManager().new FindingException(
                "ContratoDosimetro Count");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public Long findTotalNumberContratoDosimetro() throws Exception {
        Long entity = null;

        try {
            entity = contratoDosimetroDAO.count();
        } catch (Exception e) {
            throw new ZMessManager().new FindingException(
                "ContratoDosimetro Count");
        } finally {
        }

        return entity;
    }

    /**
    *
    * @param varibles
    *            este arreglo debera tener:
    *
    * [0] = String variable = (String) varibles[i]; representa como se llama la
    * variable en el pojo
    *
    * [1] = Boolean booVariable = (Boolean) varibles[i + 1]; representa si el
    * valor necesita o no ''(comillas simples)usado para campos de tipo string
    *
    * [2] = Object value = varibles[i + 2]; representa el valor que se va a
    * buscar en la BD
    *
    * [3] = String comparator = (String) varibles[i + 3]; representa que tipo
    * de busqueda voy a hacer.., ejemplo: where nombre=william o where nombre<>william,
        * en este campo iria el tipo de comparador que quiero si es = o <>
            *
            * Se itera de 4 en 4..., entonces 4 registros del arreglo representan 1
            * busqueda en un campo, si se ponen mas pues el continuara buscando en lo
            * que se le ingresen en los otros 4
            *
            *
            * @param variablesBetween
            *
            * la diferencia son estas dos posiciones
            *
            * [0] = String variable = (String) varibles[j]; la variable ne la BD que va
            * a ser buscada en un rango
            *
            * [1] = Object value = varibles[j + 1]; valor 1 para buscar en un rango
            *
            * [2] = Object value2 = varibles[j + 2]; valor 2 para buscar en un rango
            * ejempolo: a > 1 and a < 5 --> 1 seria value y 5 seria value2
                *
                * [3] = String comparator1 = (String) varibles[j + 3]; comparador 1
                * ejemplo: a comparator1 1 and a < 5
                    *
                    * [4] = String comparator2 = (String) varibles[j + 4]; comparador 2
                    * ejemplo: a comparador1>1  and a comparador2<5  (el original: a > 1 and a <
                            * 5) *
                            * @param variablesBetweenDates(en
                            *            este caso solo para mysql)
                            *  [0] = String variable = (String) varibles[k]; el nombre de la variable que hace referencia a
                            *            una fecha
                            *
                            * [1] = Object object1 = varibles[k + 2]; fecha 1 a comparar(deben ser
                            * dates)
                            *
                            * [2] = Object object2 = varibles[k + 3]; fecha 2 a comparar(deben ser
                            * dates)
                            *
                            * esto hace un between entre las dos fechas.
                            *
                            * @return lista con los objetos que se necesiten
                            * @throws Exception
                            */
    @Transactional(readOnly = true)
    public List<ContratoDosimetro> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        List<ContratoDosimetro> list = new ArrayList<ContratoDosimetro>();
        String where = new String();
        String tempWhere = new String();

        if (variables != null) {
            for (int i = 0; i < variables.length; i++) {
                if ((variables[i] != null) && (variables[i + 1] != null) &&
                        (variables[i + 2] != null) &&
                        (variables[i + 3] != null)) {
                    String variable = (String) variables[i];
                    Boolean booVariable = (Boolean) variables[i + 1];
                    Object value = variables[i + 2];
                    String comparator = (String) variables[i + 3];

                    if (booVariable.booleanValue()) {
                        tempWhere = (tempWhere.length() == 0)
                            ? ("(model." + variable + " " + comparator + " \'" +
                            value + "\' )")
                            : (tempWhere + " AND (model." + variable + " " +
                            comparator + " \'" + value + "\' )");
                    } else {
                        tempWhere = (tempWhere.length() == 0)
                            ? ("(model." + variable + " " + comparator + " " +
                            value + " )")
                            : (tempWhere + " AND (model." + variable + " " +
                            comparator + " " + value + " )");
                    }
                }

                i = i + 3;
            }
        }

        if (variablesBetween != null) {
            for (int j = 0; j < variablesBetween.length; j++) {
                if ((variablesBetween[j] != null) &&
                        (variablesBetween[j + 1] != null) &&
                        (variablesBetween[j + 2] != null) &&
                        (variablesBetween[j + 3] != null) &&
                        (variablesBetween[j + 4] != null)) {
                    String variable = (String) variablesBetween[j];
                    Object value = variablesBetween[j + 1];
                    Object value2 = variablesBetween[j + 2];
                    String comparator1 = (String) variablesBetween[j + 3];
                    String comparator2 = (String) variablesBetween[j + 4];
                    tempWhere = (tempWhere.length() == 0)
                        ? ("(" + value + " " + comparator1 + " " + variable +
                        " and " + variable + " " + comparator2 + " " + value2 +
                        " )")
                        : (tempWhere + " AND (" + value + " " + comparator1 +
                        " " + variable + " and " + variable + " " +
                        comparator2 + " " + value2 + " )");
                }

                j = j + 4;
            }
        }

        if (variablesBetweenDates != null) {
            for (int k = 0; k < variablesBetweenDates.length; k++) {
                if ((variablesBetweenDates[k] != null) &&
                        (variablesBetweenDates[k + 1] != null) &&
                        (variablesBetweenDates[k + 2] != null)) {
                    String variable = (String) variablesBetweenDates[k];
                    Object object1 = variablesBetweenDates[k + 1];
                    Object object2 = variablesBetweenDates[k + 2];
                    String value = null;
                    String value2 = null;

                    try {
                        Date date1 = (Date) object1;
                        Date date2 = (Date) object2;
                        value = Utilities.formatDateWithoutTimeInAStringForBetweenWhere(date1);
                        value2 = Utilities.formatDateWithoutTimeInAStringForBetweenWhere(date2);
                    } catch (Exception e) {
                        list = null;
                        throw e;
                    }

                    tempWhere = (tempWhere.length() == 0)
                        ? ("(model." + variable + " between \'" + value +
                        "\' and \'" + value2 + "\')")
                        : (tempWhere + " AND (model." + variable +
                        " between \'" + value + "\' and \'" + value2 + "\')");
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
            list = contratoDosimetroDAO.findByCriteria(where);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
        }

        return list;
    }
}
