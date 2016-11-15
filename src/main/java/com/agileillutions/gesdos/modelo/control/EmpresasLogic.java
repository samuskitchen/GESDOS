package com.agileillutions.gesdos.modelo.control;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.agileillutions.gesdos.dataaccess.dao.IEmpresasDAO;
import com.agileillutions.gesdos.exceptions.ZMessManager;
import com.agileillutions.gesdos.modelo.Empresas;
import com.agileillutions.gesdos.modelo.dto.EmpresasDTO;
import com.agileillutions.gesdos.utilities.Utilities;


/**
* @author Zathura Code Generator http://code.google.com/p/zathura
* www.zathuracode.org
*
*/
@Scope("singleton")
@Service("EmpresasLogic")
public class EmpresasLogic implements IEmpresasLogic {
    /**
     * DAO injected by Spring that manages Empresas entities
     *
     */
    @Autowired
    private IEmpresasDAO empresasDAO;

    @Transactional(readOnly = true)
    public List<Empresas> getEmpresas() throws Exception {
        List<Empresas> list = new ArrayList<Empresas>();

        try {
            list = empresasDAO.findAll();
        } catch (Exception e) {
            throw new ZMessManager().new GettingException(ZMessManager.ALL +
                "Empresas");
        } finally {
        }

        return list;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void saveEmpresas(Empresas entity) throws Exception {
        try {
            if (entity.getCiuCod() == null) {
                throw new ZMessManager().new EmptyFieldException("Ciudad");
            }

            if ((entity.getCiuCod() != null) &&
                    (Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
                        entity.getCiuCod(), 22, 0) == false)) {
                throw new ZMessManager().new NotValidFormatException("Ciudad");
            }

            if (entity.getDepCod() == null) {
                throw new ZMessManager().new EmptyFieldException("Departamento");
            }

            if ((entity.getDepCod() != null) &&
                    (Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
                        entity.getDepCod(), 22, 0) == false)) {
                throw new ZMessManager().new NotValidFormatException("Departamento");
            }

            if ((entity.getEmpApe1con() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getEmpApe1con(), 20) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "Primer Apellido");
            }

            if ((entity.getEmpApe1con2() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getEmpApe1con2(), 20) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "Segundo Apellido");
            }

            if ((entity.getEmpCarCon() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getEmpCarCon(), 50) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "Cargo Contacto");
            }

            if ((entity.getEmpCarCon2() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getEmpCarCon2(), 20) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "Cargo Contacto");
            }

            if ((entity.getEmpCel() != null) &&
                    (Utilities.checkWordAndCheckWithlength(entity.getEmpCel(),
                        11) == false)) {
                throw new ZMessManager().new NotValidFormatException("Celular");
            }

            if ((entity.getEmpCel1() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getEmpCel1(), 11) == false)) {
                throw new ZMessManager().new NotValidFormatException("Celular Contacto");
            }

            if ((entity.getEmpCel2() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getEmpCel2(), 11) == false)) {
                throw new ZMessManager().new NotValidFormatException("Celular Contacto");
            }

            if (entity.getEmpCod() == null) {
                throw new ZMessManager().new EmptyFieldException("empCod");
            }

            if ((entity.getEmpCod() != null) &&
                    (Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
                        entity.getEmpCod(), 22, 0) == false)) {
                throw new ZMessManager().new NotValidFormatException("Codigo");
            }

            if ((entity.getEmpConMail() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getEmpConMail(), 200) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "Mail Contacto");
            }

            if ((entity.getEmpConMail2() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getEmpConMail2(), 200) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "Mail Contacto");
            }

            if ((entity.getEmpDir() != null) &&
                    (Utilities.checkWordAndCheckWithlength(entity.getEmpDir(),
                        70) == false)) {
                throw new ZMessManager().new NotValidFormatException("Dirección");
            }

            if (entity.getEmpEst() == null) {
                throw new ZMessManager().new EmptyFieldException("Estado");
            }

            if ((entity.getEmpEst() != null) &&
                    (Utilities.checkWordAndCheckWithlength(entity.getEmpEst(), 1) == false)) {
                throw new ZMessManager().new NotValidFormatException("Estado");
            }

            if ((entity.getEmpFax() != null) &&
                    (Utilities.checkWordAndCheckWithlength(entity.getEmpFax(),
                        11) == false)) {
                throw new ZMessManager().new NotValidFormatException("Fax");
            }

            if (entity.getEmpFecVin() == null) {
                throw new ZMessManager().new EmptyFieldException("Fecha Vinculación");
            }

            if ((entity.getEmpMail() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getEmpMail(), 50) == false)) {
                throw new ZMessManager().new NotValidFormatException("Collero Ellectronico");
            }

            if (entity.getEmpNit() == null) {
                throw new ZMessManager().new EmptyFieldException("Nit");
            }

            if ((entity.getEmpNit() != null) &&
                    (Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
                        entity.getEmpNit(), 22, 0) == false)) {
                throw new ZMessManager().new NotValidFormatException("Nit");
            }

            if ((entity.getEmpNomCom2() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getEmpNomCom2(), 20) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "Nombre Contacto");
            }

            if ((entity.getEmpNomCon() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getEmpNomCon(), 20) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "Nombre Contacto");
            }

            if ((entity.getEmpObs() != null) &&
                    (Utilities.checkWordAndCheckWithlength(entity.getEmpObs(),
                        100) == false)) {
                throw new ZMessManager().new NotValidFormatException("Observaciones");
            }

            if ((entity.getEmpRazSoc() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getEmpRazSoc(), 50) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "Razón Social");
            }

            if ((entity.getEmpTel() != null) &&
                    (Utilities.checkWordAndCheckWithlength(entity.getEmpTel(),
                        11) == false)) {
                throw new ZMessManager().new NotValidFormatException("Teléfono");
            }

            if (getEmpresas(entity.getEmpCod()) != null) {
                throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
            }

            empresasDAO.save(entity);
        } catch (Exception e) {
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void deleteEmpresas(Empresas entity) throws Exception {
        if (entity == null) {
            throw new ZMessManager().new NullEntityExcepcion("Empresas");
        }

        if (entity.getEmpCod() == null) {
            throw new ZMessManager().new EmptyFieldException("empCod");
        }

        try {
            empresasDAO.delete(entity);
        } catch (Exception e) {
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void updateEmpresas(Empresas entity) throws Exception {
        try {
        	if (entity.getCiuCod() == null) {
                throw new ZMessManager().new EmptyFieldException("Ciudad");
            }

            if ((entity.getCiuCod() != null) &&
                    (Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
                        entity.getCiuCod(), 22, 0) == false)) {
                throw new ZMessManager().new NotValidFormatException("Ciudad");
            }

            if (entity.getDepCod() == null) {
                throw new ZMessManager().new EmptyFieldException("Departamento");
            }

            if ((entity.getDepCod() != null) &&
                    (Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
                        entity.getDepCod(), 22, 0) == false)) {
                throw new ZMessManager().new NotValidFormatException("Departamento");
            }

            if ((entity.getEmpApe1con() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getEmpApe1con(), 20) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "Primer Apellido");
            }

            if ((entity.getEmpApe1con2() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getEmpApe1con2(), 20) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "Segundo Apellido");
            }

            if ((entity.getEmpCarCon() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getEmpCarCon(), 50) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "Cargo Contacto");
            }

            if ((entity.getEmpCarCon2() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getEmpCarCon2(), 20) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "Cargo Contacto");
            }

            if ((entity.getEmpCel() != null) &&
                    (Utilities.checkWordAndCheckWithlength(entity.getEmpCel(),
                        11) == false)) {
                throw new ZMessManager().new NotValidFormatException("Celular");
            }

            if ((entity.getEmpCel1() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getEmpCel1(), 11) == false)) {
                throw new ZMessManager().new NotValidFormatException("Celular Contacto");
            }

            if ((entity.getEmpCel2() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getEmpCel2(), 11) == false)) {
                throw new ZMessManager().new NotValidFormatException("Celular Contacto");
            }

            if (entity.getEmpCod() == null) {
                throw new ZMessManager().new EmptyFieldException("empCod");
            }

            if ((entity.getEmpCod() != null) &&
                    (Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
                        entity.getEmpCod(), 22, 0) == false)) {
                throw new ZMessManager().new NotValidFormatException("Codigo");
            }

            if ((entity.getEmpConMail() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getEmpConMail(), 200) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "Mail Contacto");
            }

            if ((entity.getEmpConMail2() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getEmpConMail2(), 200) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "Mail Contacto");
            }

            if ((entity.getEmpDir() != null) &&
                    (Utilities.checkWordAndCheckWithlength(entity.getEmpDir(),
                        70) == false)) {
                throw new ZMessManager().new NotValidFormatException("Dirección");
            }

            if (entity.getEmpEst() == null) {
                throw new ZMessManager().new EmptyFieldException("Estado");
            }

            if ((entity.getEmpEst() != null) &&
                    (Utilities.checkWordAndCheckWithlength(entity.getEmpEst(), 1) == false)) {
                throw new ZMessManager().new NotValidFormatException("Estado");
            }

            if ((entity.getEmpFax() != null) &&
                    (Utilities.checkWordAndCheckWithlength(entity.getEmpFax(),
                        11) == false)) {
                throw new ZMessManager().new NotValidFormatException("Fax");
            }

            if (entity.getEmpFecVin() == null) {
                throw new ZMessManager().new EmptyFieldException("Fecha Vinculación");
            }

            if ((entity.getEmpMail() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getEmpMail(), 50) == false)) {
                throw new ZMessManager().new NotValidFormatException("Collero Ellectronico");
            }

            if (entity.getEmpNit() == null) {
                throw new ZMessManager().new EmptyFieldException("Nit");
            }

            if ((entity.getEmpNit() != null) &&
                    (Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
                        entity.getEmpNit(), 22, 0) == false)) {
                throw new ZMessManager().new NotValidFormatException("Nit");
            }

            if ((entity.getEmpNomCom2() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getEmpNomCom2(), 20) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "Nombre Contacto");
            }

            if ((entity.getEmpNomCon() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getEmpNomCon(), 20) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "Nombre Contacto");
            }

            if ((entity.getEmpObs() != null) &&
                    (Utilities.checkWordAndCheckWithlength(entity.getEmpObs(),
                        100) == false)) {
                throw new ZMessManager().new NotValidFormatException("Observaciones");
            }

            if ((entity.getEmpRazSoc() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getEmpRazSoc(), 50) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "Razón Social");
            }

            if ((entity.getEmpTel() != null) &&
                    (Utilities.checkWordAndCheckWithlength(entity.getEmpTel(),
                        11) == false)) {
                throw new ZMessManager().new NotValidFormatException("Teléfono");
            }

            empresasDAO.update(entity);
        } catch (Exception e) {
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = true)
    public List<EmpresasDTO> getDataEmpresas() throws Exception {
        try {
            List<Empresas> empresas = empresasDAO.findAll();

            List<EmpresasDTO> empresasDTO = new ArrayList<EmpresasDTO>();

            for (Empresas empresasTmp : empresas) {
                EmpresasDTO empresasDTO2 = new EmpresasDTO();

                empresasDTO2.setEmpCod(empresasTmp.getEmpCod());
                empresasDTO2.setCiuCod((empresasTmp.getCiuCod() != null)
                    ? empresasTmp.getCiuCod() : null);
                empresasDTO2.setDepCod((empresasTmp.getDepCod() != null)
                    ? empresasTmp.getDepCod() : null);
                empresasDTO2.setEmpApe1con((empresasTmp.getEmpApe1con() != null)
                    ? empresasTmp.getEmpApe1con() : null);
                empresasDTO2.setEmpApe1con2((empresasTmp.getEmpApe1con2() != null)
                    ? empresasTmp.getEmpApe1con2() : null);
                empresasDTO2.setEmpCarCon((empresasTmp.getEmpCarCon() != null)
                    ? empresasTmp.getEmpCarCon() : null);
                empresasDTO2.setEmpCarCon2((empresasTmp.getEmpCarCon2() != null)
                    ? empresasTmp.getEmpCarCon2() : null);
                empresasDTO2.setEmpCel((empresasTmp.getEmpCel() != null)
                    ? empresasTmp.getEmpCel() : null);
                empresasDTO2.setEmpCel1((empresasTmp.getEmpCel1() != null)
                    ? empresasTmp.getEmpCel1() : null);
                empresasDTO2.setEmpCel2((empresasTmp.getEmpCel2() != null)
                    ? empresasTmp.getEmpCel2() : null);
                empresasDTO2.setEmpConMail((empresasTmp.getEmpConMail() != null)
                    ? empresasTmp.getEmpConMail() : null);
                empresasDTO2.setEmpConMail2((empresasTmp.getEmpConMail2() != null)
                    ? empresasTmp.getEmpConMail2() : null);
                empresasDTO2.setEmpDir((empresasTmp.getEmpDir() != null)
                    ? empresasTmp.getEmpDir() : null);
                empresasDTO2.setEmpEst((empresasTmp.getEmpEst() != null)
                    ? empresasTmp.getEmpEst() : null);
                empresasDTO2.setEmpFax((empresasTmp.getEmpFax() != null)
                    ? empresasTmp.getEmpFax() : null);
                empresasDTO2.setEmpFecVin(empresasTmp.getEmpFecVin());
                empresasDTO2.setEmpMail((empresasTmp.getEmpMail() != null)
                    ? empresasTmp.getEmpMail() : null);
                empresasDTO2.setEmpNit((empresasTmp.getEmpNit() != null)
                    ? empresasTmp.getEmpNit() : null);
                empresasDTO2.setEmpNomCom2((empresasTmp.getEmpNomCom2() != null)
                    ? empresasTmp.getEmpNomCom2() : null);
                empresasDTO2.setEmpNomCon((empresasTmp.getEmpNomCon() != null)
                    ? empresasTmp.getEmpNomCon() : null);
                empresasDTO2.setEmpObs((empresasTmp.getEmpObs() != null)
                    ? empresasTmp.getEmpObs() : null);
                empresasDTO2.setEmpRazSoc((empresasTmp.getEmpRazSoc() != null)
                    ? empresasTmp.getEmpRazSoc() : null);
                empresasDTO2.setEmpTel((empresasTmp.getEmpTel() != null)
                    ? empresasTmp.getEmpTel() : null);
                empresasDTO.add(empresasDTO2);
            }

            return empresasDTO;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public Empresas getEmpresas(Long empCod) throws Exception {
        Empresas entity = null;

        try {
            entity = empresasDAO.findById(empCod);
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("Empresas");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public List<Empresas> findPageEmpresas(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        List<Empresas> entity = null;

        try {
            entity = empresasDAO.findPage(sortColumnName, sortAscending,
                    startRow, maxResults);
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("Empresas Count");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public Long findTotalNumberEmpresas() throws Exception {
        Long entity = null;

        try {
            entity = empresasDAO.count();
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("Empresas Count");
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
    public List<Empresas> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        List<Empresas> list = new ArrayList<Empresas>();
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
            list = empresasDAO.findByCriteria(where);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
        }

        return list;
    }
}
