package com.agileillutions.gesdos.modelo.control;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.agileillutions.gesdos.dataaccess.dao.ISociosDAO;
import com.agileillutions.gesdos.exceptions.ZMessManager;
import com.agileillutions.gesdos.modelo.Socios;
import com.agileillutions.gesdos.modelo.dto.SociosDTO;
import com.agileillutions.gesdos.utilities.Utilities;


/**
* @author Zathura Code Generator http://code.google.com/p/zathura
* www.zathuracode.org
*
*/
@Scope("singleton")
@Service("SociosLogic")
public class SociosLogic implements ISociosLogic {
    /**
     * DAO injected by Spring that manages Socios entities
     *
     */
    @Autowired
    private ISociosDAO sociosDAO;

    @Transactional(readOnly = true)
    public List<Socios> getSocios() throws Exception {
        List<Socios> list = new ArrayList<Socios>();

        try {
            list = sociosDAO.findAll();
        } catch (Exception e) {
            throw new ZMessManager().new GettingException(ZMessManager.ALL +
                "Socios");
        } finally {
        }

        return list;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void saveSocios(Socios entity) throws Exception {
        try {
            if (entity.getSocApe1() == null) {
                throw new ZMessManager().new EmptyFieldException("socApe1");
            }

            if ((entity.getSocApe1() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getSocApe1(), 20) == false)) {
                throw new ZMessManager().new NotValidFormatException("socApe1");
            }

            if (entity.getSocApe2() == null) {
                throw new ZMessManager().new EmptyFieldException("socApe2");
            }

            if ((entity.getSocApe2() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getSocApe2(), 20) == false)) {
                throw new ZMessManager().new NotValidFormatException("socApe2");
            }

            if (entity.getSocCod() == null) {
                throw new ZMessManager().new EmptyFieldException("socCod");
            }

            if ((entity.getSocCod() != null) &&
                    (Utilities.checkWordAndCheckWithlength(entity.getSocCod(), 4) == false)) {
                throw new ZMessManager().new NotValidFormatException("socCod");
            }

            if (entity.getSocDes() == null) {
                throw new ZMessManager().new EmptyFieldException("socDes");
            }

            if ((entity.getSocDes() != null) &&
                    (Utilities.checkWordAndCheckWithlength(entity.getSocDes(),
                        50) == false)) {
                throw new ZMessManager().new NotValidFormatException("socDes");
            }

            if (entity.getSocDir() == null) {
                throw new ZMessManager().new EmptyFieldException("socDir");
            }

            if ((entity.getSocDir() != null) &&
                    (Utilities.checkWordAndCheckWithlength(entity.getSocDir(),
                        20) == false)) {
                throw new ZMessManager().new NotValidFormatException("socDir");
            }

            if (entity.getSocFax() == null) {
                throw new ZMessManager().new EmptyFieldException("socFax");
            }

            if ((entity.getSocFax() != null) &&
                    (Utilities.checkWordAndCheckWithlength(entity.getSocFax(),
                        15) == false)) {
                throw new ZMessManager().new NotValidFormatException("socFax");
            }

            if (entity.getSocMail() == null) {
                throw new ZMessManager().new EmptyFieldException("socMail");
            }

            if ((entity.getSocMail() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getSocMail(), 60) == false)) {
                throw new ZMessManager().new NotValidFormatException("socMail");
            }

            if (entity.getSocNit() == null) {
                throw new ZMessManager().new EmptyFieldException("socNit");
            }

            if ((entity.getSocNit() != null) &&
                    (Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
                        entity.getSocNit(), 22, 0) == false)) {
                throw new ZMessManager().new NotValidFormatException("socNit");
            }

            if (entity.getSocNom() == null) {
                throw new ZMessManager().new EmptyFieldException("socNom");
            }

            if ((entity.getSocNom() != null) &&
                    (Utilities.checkWordAndCheckWithlength(entity.getSocNom(),
                        20) == false)) {
                throw new ZMessManager().new NotValidFormatException("socNom");
            }

            if (entity.getSocTel() == null) {
                throw new ZMessManager().new EmptyFieldException("socTel");
            }

            if ((entity.getSocTel() != null) &&
                    (Utilities.checkWordAndCheckWithlength(entity.getSocTel(),
                        11) == false)) {
                throw new ZMessManager().new NotValidFormatException("socTel");
            }

            if (getSocios(entity.getSocCod()) != null) {
                throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
            }

            sociosDAO.save(entity);
        } catch (Exception e) {
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void deleteSocios(Socios entity) throws Exception {
        if (entity == null) {
            throw new ZMessManager().new NullEntityExcepcion("Socios");
        }

        if (entity.getSocCod() == null) {
            throw new ZMessManager().new EmptyFieldException("socCod");
        }

        try {
            sociosDAO.delete(entity);
        } catch (Exception e) {
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void updateSocios(Socios entity) throws Exception {
        try {
            if (entity == null) {
                throw new ZMessManager().new NullEntityExcepcion("Socios");
            }

            if (entity.getSocApe1() == null) {
                throw new ZMessManager().new EmptyFieldException("socApe1");
            }

            if ((entity.getSocApe1() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getSocApe1(), 20) == false)) {
                throw new ZMessManager().new NotValidFormatException("socApe1");
            }

            if (entity.getSocApe2() == null) {
                throw new ZMessManager().new EmptyFieldException("socApe2");
            }

            if ((entity.getSocApe2() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getSocApe2(), 20) == false)) {
                throw new ZMessManager().new NotValidFormatException("socApe2");
            }

            if (entity.getSocCod() == null) {
                throw new ZMessManager().new EmptyFieldException("socCod");
            }

            if ((entity.getSocCod() != null) &&
                    (Utilities.checkWordAndCheckWithlength(entity.getSocCod(), 4) == false)) {
                throw new ZMessManager().new NotValidFormatException("socCod");
            }

            if (entity.getSocDes() == null) {
                throw new ZMessManager().new EmptyFieldException("socDes");
            }

            if ((entity.getSocDes() != null) &&
                    (Utilities.checkWordAndCheckWithlength(entity.getSocDes(),
                        50) == false)) {
                throw new ZMessManager().new NotValidFormatException("socDes");
            }

            if (entity.getSocDir() == null) {
                throw new ZMessManager().new EmptyFieldException("socDir");
            }

            if ((entity.getSocDir() != null) &&
                    (Utilities.checkWordAndCheckWithlength(entity.getSocDir(),
                        20) == false)) {
                throw new ZMessManager().new NotValidFormatException("socDir");
            }

            if (entity.getSocFax() == null) {
                throw new ZMessManager().new EmptyFieldException("socFax");
            }

            if ((entity.getSocFax() != null) &&
                    (Utilities.checkWordAndCheckWithlength(entity.getSocFax(),
                        15) == false)) {
                throw new ZMessManager().new NotValidFormatException("socFax");
            }

            if (entity.getSocMail() == null) {
                throw new ZMessManager().new EmptyFieldException("socMail");
            }

            if ((entity.getSocMail() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getSocMail(), 60) == false)) {
                throw new ZMessManager().new NotValidFormatException("socMail");
            }

            if (entity.getSocNit() == null) {
                throw new ZMessManager().new EmptyFieldException("socNit");
            }

            if ((entity.getSocNit() != null) &&
                    (Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
                        entity.getSocNit(), 22, 0) == false)) {
                throw new ZMessManager().new NotValidFormatException("socNit");
            }

            if (entity.getSocNom() == null) {
                throw new ZMessManager().new EmptyFieldException("socNom");
            }

            if ((entity.getSocNom() != null) &&
                    (Utilities.checkWordAndCheckWithlength(entity.getSocNom(),
                        20) == false)) {
                throw new ZMessManager().new NotValidFormatException("socNom");
            }

            if (entity.getSocTel() == null) {
                throw new ZMessManager().new EmptyFieldException("socTel");
            }

            if ((entity.getSocTel() != null) &&
                    (Utilities.checkWordAndCheckWithlength(entity.getSocTel(),
                        11) == false)) {
                throw new ZMessManager().new NotValidFormatException("socTel");
            }

            sociosDAO.update(entity);
        } catch (Exception e) {
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = true)
    public List<SociosDTO> getDataSocios() throws Exception {
        try {
            List<Socios> socios = sociosDAO.findAll();

            List<SociosDTO> sociosDTO = new ArrayList<SociosDTO>();

            for (Socios sociosTmp : socios) {
                SociosDTO sociosDTO2 = new SociosDTO();

                sociosDTO2.setSocCod(sociosTmp.getSocCod());
                sociosDTO2.setSocApe1((sociosTmp.getSocApe1() != null)
                    ? sociosTmp.getSocApe1() : null);
                sociosDTO2.setSocApe2((sociosTmp.getSocApe2() != null)
                    ? sociosTmp.getSocApe2() : null);
                sociosDTO2.setSocDes((sociosTmp.getSocDes() != null)
                    ? sociosTmp.getSocDes() : null);
                sociosDTO2.setSocDir((sociosTmp.getSocDir() != null)
                    ? sociosTmp.getSocDir() : null);
                sociosDTO2.setSocFax((sociosTmp.getSocFax() != null)
                    ? sociosTmp.getSocFax() : null);
                sociosDTO2.setSocMail((sociosTmp.getSocMail() != null)
                    ? sociosTmp.getSocMail() : null);
                sociosDTO2.setSocNit((sociosTmp.getSocNit() != null)
                    ? sociosTmp.getSocNit() : null);
                sociosDTO2.setSocNom((sociosTmp.getSocNom() != null)
                    ? sociosTmp.getSocNom() : null);
                sociosDTO2.setSocTel((sociosTmp.getSocTel() != null)
                    ? sociosTmp.getSocTel() : null);
                sociosDTO.add(sociosDTO2);
            }

            return sociosDTO;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public Socios getSocios(String socCod) throws Exception {
        Socios entity = null;

        try {
            entity = sociosDAO.findById(socCod);
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("Socios");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public List<Socios> findPageSocios(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        List<Socios> entity = null;

        try {
            entity = sociosDAO.findPage(sortColumnName, sortAscending,
                    startRow, maxResults);
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("Socios Count");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public Long findTotalNumberSocios() throws Exception {
        Long entity = null;

        try {
            entity = sociosDAO.count();
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("Socios Count");
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
    public List<Socios> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        List<Socios> list = new ArrayList<Socios>();
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
            list = sociosDAO.findByCriteria(where);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
        }

        return list;
    }
}
