package com.agileillutions.gesdos.modelo.control;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.agileillutions.gesdos.dataaccess.dao.IPrograma1DAO;
import com.agileillutions.gesdos.exceptions.ZMessManager;
import com.agileillutions.gesdos.modelo.Programa1;
import com.agileillutions.gesdos.modelo.Programa1Id;
import com.agileillutions.gesdos.modelo.dto.Programa1DTO;
import com.agileillutions.gesdos.utilities.Utilities;


/**
* @author Zathura Code Generator http://code.google.com/p/zathura
* www.zathuracode.org
*
*/
@Scope("singleton")
@Service("Programa1Logic")
public class Programa1Logic implements IPrograma1Logic {
    /**
     * DAO injected by Spring that manages Programa1 entities
     *
     */
    @Autowired
    private IPrograma1DAO programa1DAO;

    @Transactional(readOnly = true)
    public List<Programa1> getPrograma1() throws Exception {
        List<Programa1> list = new ArrayList<Programa1>();

        try {
            list = programa1DAO.findAll();
        } catch (Exception e) {
            throw new ZMessManager().new GettingException(ZMessManager.ALL +
                "Programa1");
        } finally {
        }

        return list;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void savePrograma1(Programa1 entity) throws Exception {
        try {
            if (entity.getId().getProgCodigo() == null) {
                throw new ZMessManager().new EmptyFieldException("progCodigo");
            }

            if ((entity.getId().getProgCodigo() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getId().getProgCodigo(), 7) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "progCodigo");
            }

            if (entity.getId().getEvenCodigo() == null) {
                throw new ZMessManager().new EmptyFieldException("evenCodigo");
            }

            if ((entity.getId().getEvenCodigo() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getId().getEvenCodigo(), 16) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "evenCodigo");
            }

            if (entity.getEvenDescri() == null) {
                throw new ZMessManager().new EmptyFieldException("evenDescri");
            }

            if ((entity.getEvenDescri() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getEvenDescri(), 30) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "evenDescri");
            }

            if (getPrograma1(entity.getId()) != null) {
                throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
            }

            programa1DAO.save(entity);
        } catch (Exception e) {
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void deletePrograma1(Programa1 entity) throws Exception {
        if (entity == null) {
            throw new ZMessManager().new NullEntityExcepcion("Programa1");
        }

        if (entity.getId().getProgCodigo() == null) {
            throw new ZMessManager().new EmptyFieldException("progCodigo");
        }

        if (entity.getId().getEvenCodigo() == null) {
            throw new ZMessManager().new EmptyFieldException("evenCodigo");
        }

        try {
            programa1DAO.delete(entity);
        } catch (Exception e) {
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void updatePrograma1(Programa1 entity) throws Exception {
        try {
            if (entity == null) {
                throw new ZMessManager().new NullEntityExcepcion("Programa1");
            }

            if (entity.getId().getProgCodigo() == null) {
                throw new ZMessManager().new EmptyFieldException("progCodigo");
            }

            if ((entity.getId().getProgCodigo() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getId().getProgCodigo(), 7) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "progCodigo");
            }

            if (entity.getId().getEvenCodigo() == null) {
                throw new ZMessManager().new EmptyFieldException("evenCodigo");
            }

            if ((entity.getId().getEvenCodigo() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getId().getEvenCodigo(), 16) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "evenCodigo");
            }

            if (entity.getEvenDescri() == null) {
                throw new ZMessManager().new EmptyFieldException("evenDescri");
            }

            if ((entity.getEvenDescri() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getEvenDescri(), 30) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "evenDescri");
            }

            programa1DAO.update(entity);
        } catch (Exception e) {
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = true)
    public List<Programa1DTO> getDataPrograma1() throws Exception {
        try {
            List<Programa1> programa1 = programa1DAO.findAll();

            List<Programa1DTO> programa1DTO = new ArrayList<Programa1DTO>();

            for (Programa1 programa1Tmp : programa1) {
                Programa1DTO programa1DTO2 = new Programa1DTO();

                programa1DTO2.setProgCodigo(programa1Tmp.getId().getProgCodigo());
                programa1DTO2.setEvenCodigo(programa1Tmp.getId().getEvenCodigo());
                programa1DTO2.setEvenDescri((programa1Tmp.getEvenDescri() != null)
                    ? programa1Tmp.getEvenDescri() : null);
                programa1DTO.add(programa1DTO2);
            }

            return programa1DTO;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public Programa1 getPrograma1(Programa1Id id) throws Exception {
        Programa1 entity = null;

        try {
            entity = programa1DAO.findById(id);
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("Programa1");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public List<Programa1> findPagePrograma1(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        List<Programa1> entity = null;

        try {
            entity = programa1DAO.findPage(sortColumnName, sortAscending,
                    startRow, maxResults);
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("Programa1 Count");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public Long findTotalNumberPrograma1() throws Exception {
        Long entity = null;

        try {
            entity = programa1DAO.count();
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("Programa1 Count");
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
    public List<Programa1> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        List<Programa1> list = new ArrayList<Programa1>();
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
            list = programa1DAO.findByCriteria(where);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
        }

        return list;
    }
}
