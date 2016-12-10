package com.agileillutions.gesdos.modelo.control;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.agileillutions.gesdos.dataaccess.dao.IDetalleContratoDAO;
import com.agileillutions.gesdos.exceptions.ZMessManager;
import com.agileillutions.gesdos.modelo.DetalleContrato;
import com.agileillutions.gesdos.modelo.DetalleContratoId;
import com.agileillutions.gesdos.modelo.dto.DetalleContratoDTO;
import com.agileillutions.gesdos.utilities.Utilities;


/**
* @author Zathura Code Generator http://code.google.com/p/zathura
* www.zathuracode.org
*
*/
@Scope("singleton")
@Service("DetalleContratoLogic")
public class DetalleContratoLogic implements IDetalleContratoLogic {
    /**
     * DAO injected by Spring that manages DetalleContrato entities
     *
     */
    @Autowired
    private IDetalleContratoDAO detalleContratoDAO;

    @Transactional(readOnly = true)
    public List<DetalleContrato> getDetalleContrato() throws Exception {
        List<DetalleContrato> list = new ArrayList<DetalleContrato>();

        try {
            list = detalleContratoDAO.findAll();
        } catch (Exception e) {
            throw new ZMessManager().new GettingException(ZMessManager.ALL +
                "DetalleContrato");
        } finally {
        }

        return list;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void saveDetalleContrato(DetalleContrato entity)
        throws Exception {
        try {
            if (entity.getId().getDetConCod() == null) {
                throw new ZMessManager().new EmptyFieldException("detConCod");
            }

            if ((entity.getId().getDetConCod() != null) &&
                    (Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
                        entity.getId().getDetConCod(), 22, 0) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "detConCod");
            }

            if (entity.getId().getConNro() == null) {
                throw new ZMessManager().new EmptyFieldException("conNro");
            }

            if ((entity.getId().getConNro() != null) &&
                    (Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
                        entity.getId().getConNro(), 22, 0) == false)) {
                throw new ZMessManager().new NotValidFormatException("conNro");
            }

            if (entity.getDetEst() == null) {
                throw new ZMessManager().new EmptyFieldException("detEst");
            }

            if ((entity.getDetEst() != null) &&
                    (Utilities.checkWordAndCheckWithlength(entity.getDetEst(), 1) == false)) {
                throw new ZMessManager().new NotValidFormatException("detEst");
            }

            if (entity.getDetNumUsu() == null) {
                throw new ZMessManager().new EmptyFieldException("detNumUsu");
            }

            if ((entity.getDetNumUsu() != null) &&
                    (Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
                        entity.getDetNumUsu(), 22, 0) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "detNumUsu");
            }

            if ((entity.getDetObs() != null) &&
                    (Utilities.checkWordAndCheckWithlength(entity.getDetObs(),
                        500) == false)) {
                throw new ZMessManager().new NotValidFormatException("detObs");
            }

            if (getDetalleContrato(entity.getId()) != null) {
                throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
            }

            detalleContratoDAO.save(entity);
        } catch (Exception e) {
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void deleteDetalleContrato(DetalleContrato entity)
        throws Exception {
        if (entity == null) {
            throw new ZMessManager().new NullEntityExcepcion("DetalleContrato");
        }

        if (entity.getId().getDetConCod() == null) {
            throw new ZMessManager().new EmptyFieldException("detConCod");
        }

        if (entity.getId().getConNro() == null) {
            throw new ZMessManager().new EmptyFieldException("conNro");
        }

        try {
            detalleContratoDAO.delete(entity);
        } catch (Exception e) {
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void updateDetalleContrato(DetalleContrato entity)
        throws Exception {
        try {
            if (entity == null) {
                throw new ZMessManager().new NullEntityExcepcion(
                    "DetalleContrato");
            }

            if (entity.getId().getDetConCod() == null) {
                throw new ZMessManager().new EmptyFieldException("detConCod");
            }

            if ((entity.getId().getDetConCod() != null) &&
                    (Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
                        entity.getId().getDetConCod(), 22, 0) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "detConCod");
            }

            if (entity.getId().getConNro() == null) {
                throw new ZMessManager().new EmptyFieldException("conNro");
            }

            if ((entity.getId().getConNro() != null) &&
                    (Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
                        entity.getId().getConNro(), 22, 0) == false)) {
                throw new ZMessManager().new NotValidFormatException("conNro");
            }

            if (entity.getDetEst() == null) {
                throw new ZMessManager().new EmptyFieldException("detEst");
            }

            if ((entity.getDetEst() != null) &&
                    (Utilities.checkWordAndCheckWithlength(entity.getDetEst(), 1) == false)) {
                throw new ZMessManager().new NotValidFormatException("detEst");
            }

            if (entity.getDetNumUsu() == null) {
                throw new ZMessManager().new EmptyFieldException("detNumUsu");
            }

            if ((entity.getDetNumUsu() != null) &&
                    (Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
                        entity.getDetNumUsu(), 22, 0) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "detNumUsu");
            }

            if ((entity.getDetObs() != null) &&
                    (Utilities.checkWordAndCheckWithlength(entity.getDetObs(),
                        500) == false)) {
                throw new ZMessManager().new NotValidFormatException("detObs");
            }

            detalleContratoDAO.update(entity);
        } catch (Exception e) {
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = true)
    public List<DetalleContratoDTO> getDataDetalleContrato()
        throws Exception {
        try {
            List<DetalleContrato> detalleContrato = detalleContratoDAO.findAll();

            List<DetalleContratoDTO> detalleContratoDTO = new ArrayList<DetalleContratoDTO>();

            for (DetalleContrato detalleContratoTmp : detalleContrato) {
                DetalleContratoDTO detalleContratoDTO2 = new DetalleContratoDTO();

                detalleContratoDTO2.setDetConCod(detalleContratoTmp.getId()
                                                                   .getDetConCod());
                detalleContratoDTO2.setConNro(detalleContratoTmp.getId()
                                                                .getConNro());
                detalleContratoDTO2.setDetEst((detalleContratoTmp.getDetEst() != null)
                    ? detalleContratoTmp.getDetEst() : null);
                detalleContratoDTO2.setDetNumUsu((detalleContratoTmp.getDetNumUsu() != null)
                    ? detalleContratoTmp.getDetNumUsu() : null);
                detalleContratoDTO2.setDetObs((detalleContratoTmp.getDetObs() != null)
                    ? detalleContratoTmp.getDetObs() : null);
                detalleContratoDTO.add(detalleContratoDTO2);
            }

            return detalleContratoDTO;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public DetalleContrato getDetalleContrato(DetalleContratoId id)
        throws Exception {
        DetalleContrato entity = null;

        try {
            entity = detalleContratoDAO.findById(id);
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("DetalleContrato");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public List<DetalleContrato> findPageDetalleContrato(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception {
        List<DetalleContrato> entity = null;

        try {
            entity = detalleContratoDAO.findPage(sortColumnName, sortAscending,
                    startRow, maxResults);
        } catch (Exception e) {
            throw new ZMessManager().new FindingException(
                "DetalleContrato Count");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public Long findTotalNumberDetalleContrato() throws Exception {
        Long entity = null;

        try {
            entity = detalleContratoDAO.count();
        } catch (Exception e) {
            throw new ZMessManager().new FindingException(
                "DetalleContrato Count");
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
    public List<DetalleContrato> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        List<DetalleContrato> list = new ArrayList<DetalleContrato>();
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
            list = detalleContratoDAO.findByCriteria(where);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
        }

        return list;
    }
}
