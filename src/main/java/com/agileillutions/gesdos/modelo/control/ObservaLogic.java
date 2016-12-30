package com.agileillutions.gesdos.modelo.control;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.agileillutions.gesdos.dataaccess.dao.IObservaDAO;
import com.agileillutions.gesdos.exceptions.ZMessManager;
import com.agileillutions.gesdos.modelo.Observa;
import com.agileillutions.gesdos.modelo.dto.ObservaDTO;
import com.agileillutions.gesdos.utilities.Utilities;


/**
* @author Zathura Code Generator http://code.google.com/p/zathura
* www.zathuracode.org
*
*/
@Scope("singleton")
@Service("ObservaLogic")
public class ObservaLogic implements IObservaLogic {
    /**
     * DAO injected by Spring that manages Observa entities
     *
     */
    @Autowired
    private IObservaDAO observaDAO;

    @Transactional(readOnly = true)
    public List<Observa> getObserva() throws Exception {
        List<Observa> list = new ArrayList<Observa>();

        try {
            list = observaDAO.findAll();
        } catch (Exception e) {
            throw new ZMessManager().new GettingException(ZMessManager.ALL +
                "Observa");
        } finally {
        }

        return list;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void saveObserva(Observa entity) throws Exception {
        try {
            if (entity.getObsCod() == null) {
                throw new ZMessManager().new EmptyFieldException("obsCod");
            }

            if ((entity.getObsCod() != null) &&
                    (Utilities.checkWordAndCheckWithlength(entity.getObsCod(), 4) == false)) {
                throw new ZMessManager().new NotValidFormatException("obsCod");
            }

            if ((entity.getObsDes() != null) &&
                    (Utilities.checkWordAndCheckWithlength(entity.getObsDes(),
                        20) == false)) {
                throw new ZMessManager().new NotValidFormatException("obsDes");
            }

            if (getObserva(entity.getObsCod()) != null) {
                throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
            }

            observaDAO.save(entity);
        } catch (Exception e) {
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void deleteObserva(Observa entity) throws Exception {
        if (entity == null) {
            throw new ZMessManager().new NullEntityExcepcion("Observa");
        }

        if (entity.getObsCod() == null) {
            throw new ZMessManager().new EmptyFieldException("obsCod");
        }

        try {
            observaDAO.delete(entity);
        } catch (Exception e) {
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void updateObserva(Observa entity) throws Exception {
        try {
            if (entity == null) {
                throw new ZMessManager().new NullEntityExcepcion("Observa");
            }

            if (entity.getObsCod() == null) {
                throw new ZMessManager().new EmptyFieldException("obsCod");
            }

            if ((entity.getObsCod() != null) &&
                    (Utilities.checkWordAndCheckWithlength(entity.getObsCod(), 4) == false)) {
                throw new ZMessManager().new NotValidFormatException("obsCod");
            }

            if ((entity.getObsDes() != null) &&
                    (Utilities.checkWordAndCheckWithlength(entity.getObsDes(),
                        20) == false)) {
                throw new ZMessManager().new NotValidFormatException("obsDes");
            }

            observaDAO.update(entity);
        } catch (Exception e) {
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = true)
    public List<ObservaDTO> getDataObserva() throws Exception {
        try {
            List<Observa> observa = observaDAO.findAll();

            List<ObservaDTO> observaDTO = new ArrayList<ObservaDTO>();

            for (Observa observaTmp : observa) {
                ObservaDTO observaDTO2 = new ObservaDTO();

                observaDTO2.setObsCod(observaTmp.getObsCod());
                observaDTO2.setObsDes((observaTmp.getObsDes() != null)
                    ? observaTmp.getObsDes() : null);
                observaDTO.add(observaDTO2);
            }

            return observaDTO;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public Observa getObserva(String obsCod) throws Exception {
        Observa entity = null;

        try {
            entity = observaDAO.findById(obsCod);
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("Observa");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public List<Observa> findPageObserva(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        List<Observa> entity = null;

        try {
            entity = observaDAO.findPage(sortColumnName, sortAscending,
                    startRow, maxResults);
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("Observa Count");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public Long findTotalNumberObserva() throws Exception {
        Long entity = null;

        try {
            entity = observaDAO.count();
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("Observa Count");
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
    public List<Observa> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        List<Observa> list = new ArrayList<Observa>();
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
            list = observaDAO.findByCriteria(where);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
        }

        return list;
    }
}
