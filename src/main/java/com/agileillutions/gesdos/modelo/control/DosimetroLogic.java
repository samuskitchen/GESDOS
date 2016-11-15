package com.agileillutions.gesdos.modelo.control;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.agileillutions.gesdos.dataaccess.dao.IContratosDAO;
import com.agileillutions.gesdos.dataaccess.dao.IDosimetroDAO;
import com.agileillutions.gesdos.dataaccess.dao.ITrabaDAO;
import com.agileillutions.gesdos.exceptions.ZMessManager;
import com.agileillutions.gesdos.modelo.Contratos;
import com.agileillutions.gesdos.modelo.ContratosId;
import com.agileillutions.gesdos.modelo.Dosimetro;
import com.agileillutions.gesdos.modelo.DosimetroId;
import com.agileillutions.gesdos.modelo.Traba;
import com.agileillutions.gesdos.modelo.dto.DosimetroDTO;
import com.agileillutions.gesdos.modelo.dto.TrabaDTO;
import com.agileillutions.gesdos.utilities.Utilities;


/**
* @author Zathura Code Generator http://code.google.com/p/zathura
* www.zathuracode.org
*
*/
@Scope("singleton")
@Service("DosimetroLogic")
public class DosimetroLogic implements IDosimetroLogic {
    /**
     * DAO injected by Spring that manages Dosimetro entities
     *
     */
    @Autowired
    private IDosimetroDAO dosimetroDAO;
    
    @Autowired
    private ITrabaDAO trabajadorDAO;
    
    @Autowired
    private IContratosDAO contratosDAO;
    

    @Transactional(readOnly = true)
    public List<Dosimetro> getDosimetro() throws Exception {
        List<Dosimetro> list = new ArrayList<Dosimetro>();

        try {
            list = dosimetroDAO.findAll();
        } catch (Exception e) {
            throw new ZMessManager().new GettingException(ZMessManager.ALL +
                "Dosimetro");
        } finally {
        }

        return list;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void saveDosimetro(Dosimetro entity) throws Exception {
        try {
            if (entity.getId().getTraCed() == null) {
                throw new ZMessManager().new EmptyFieldException("Cedula");
            }

            if ((entity.getId().getTraCed() != null) &&
                    (Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
                        entity.getId().getTraCed(), 22, 0) == false)) {
                throw new ZMessManager().new NotValidFormatException("Cedula");
            }

            if (entity.getId().getDosCod() == null) {
                throw new ZMessManager().new EmptyFieldException("Dosimetro");
            }

            if ((entity.getId().getDosCod() != null) &&
                    (Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
                        entity.getId().getDosCod(), 22, 0) == false)) {
                throw new ZMessManager().new NotValidFormatException("Dosimetro");
            }

            if (entity.getId().getEmpCod() == null) {
                throw new ZMessManager().new EmptyFieldException("Empresa");
            }

            if ((entity.getId().getEmpCod() != null) &&
                    (Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
                        entity.getId().getEmpCod(), 22, 0) == false)) {
                throw new ZMessManager().new NotValidFormatException("Empresa");
            }

            if (entity.getCarCod() == null) {
                throw new ZMessManager().new EmptyFieldException("Cargo");
            }

            if ((entity.getCarCod() != null) &&
                    (Utilities.checkWordAndCheckWithlength(entity.getCarCod(), 4) == false)) {
                throw new ZMessManager().new NotValidFormatException("Cargo");
            }

            if (entity.getDosConNro() == null) {
                throw new ZMessManager().new EmptyFieldException("Contrato");
            }

            if ((entity.getDosConNro() != null) &&
                    (Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
                        entity.getDosConNro(), 22, 0) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "Contrato");
            }

            if (entity.getDosEst() == null) {
                throw new ZMessManager().new EmptyFieldException("Estado");
            }

            if ((entity.getDosEst() != null) &&
                    (Utilities.checkWordAndCheckWithlength(entity.getDosEst(), 1) == false)) {
                throw new ZMessManager().new NotValidFormatException("Estado");
            }

            if (entity.getDosPerRec() == null) {
                throw new ZMessManager().new EmptyFieldException("Periodo Recambio");
            }

            if ((entity.getDosPerRec() != null) &&
                    (Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
                        entity.getDosPerRec(), 22, 0) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "Periodo Recambio");
            }

            if (entity.getDosTipo() == null) {
                throw new ZMessManager().new EmptyFieldException("Tipo");
            }

            if ((entity.getDosTipo() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getDosTipo(), 10) == false)) {
                throw new ZMessManager().new NotValidFormatException("Tipo");
            }

            if (entity.getGeoCod() == null) {
                throw new ZMessManager().new EmptyFieldException("Ingeominas");
            }

            if ((entity.getGeoCod() != null) &&
                    (Utilities.checkWordAndCheckWithlength(entity.getGeoCod(), 2) == false)) {
                throw new ZMessManager().new NotValidFormatException("Ingeominas");
            }

            if (entity.getPraCod() == null) {
                throw new ZMessManager().new EmptyFieldException("Practica");
            }

            if ((entity.getPraCod() != null) &&
                    (Utilities.checkWordAndCheckWithlength(entity.getPraCod(), 3) == false)) {
                throw new ZMessManager().new NotValidFormatException("Practica");
            }

            if (entity.getRadCod() == null) {
                throw new ZMessManager().new EmptyFieldException("Radiación");
            }

            if ((entity.getRadCod() != null) &&
                    (Utilities.checkWordAndCheckWithlength(entity.getRadCod(), 3) == false)) {
                throw new ZMessManager().new NotValidFormatException("Radiación");
            }

            if (entity.getUbiCod() == null) {
                throw new ZMessManager().new EmptyFieldException("Ubicación");
            }

            if ((entity.getUbiCod() != null) &&
                    (Utilities.checkWordAndCheckWithlength(entity.getUbiCod(), 3) == false)) {
                throw new ZMessManager().new NotValidFormatException("Ubicación");
            }

            if (getDosimetro(entity.getId()) != null) {
                throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
            }

            dosimetroDAO.save(entity);
        } catch (Exception e) {
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void deleteDosimetro(Dosimetro entity) throws Exception {
        if (entity == null) {
            throw new ZMessManager().new NullEntityExcepcion("Dosimetro");
        }

        if (entity.getId().getTraCed() == null) {
            throw new ZMessManager().new EmptyFieldException("traCed");
        }

        if (entity.getId().getDosCod() == null) {
            throw new ZMessManager().new EmptyFieldException("dosCod");
        }

        if (entity.getId().getEmpCod() == null) {
            throw new ZMessManager().new EmptyFieldException("empCod");
        }

        try {
            dosimetroDAO.delete(entity);
        } catch (Exception e) {
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void updateDosimetro(Dosimetro entity) throws Exception {
        try {
        	if (entity.getId().getTraCed() == null) {
                throw new ZMessManager().new EmptyFieldException("Cedula");
            }

            if ((entity.getId().getTraCed() != null) &&
                    (Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
                        entity.getId().getTraCed(), 22, 0) == false)) {
                throw new ZMessManager().new NotValidFormatException("Cedula");
            }

            if (entity.getId().getDosCod() == null) {
                throw new ZMessManager().new EmptyFieldException("Dosimetro");
            }

            if ((entity.getId().getDosCod() != null) &&
                    (Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
                        entity.getId().getDosCod(), 22, 0) == false)) {
                throw new ZMessManager().new NotValidFormatException("Dosimetro");
            }

            if (entity.getId().getEmpCod() == null) {
                throw new ZMessManager().new EmptyFieldException("Empresa");
            }

            if ((entity.getId().getEmpCod() != null) &&
                    (Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
                        entity.getId().getEmpCod(), 22, 0) == false)) {
                throw new ZMessManager().new NotValidFormatException("Empresa");
            }

            if (entity.getCarCod() == null) {
                throw new ZMessManager().new EmptyFieldException("Cargo");
            }

            if ((entity.getCarCod() != null) &&
                    (Utilities.checkWordAndCheckWithlength(entity.getCarCod(), 4) == false)) {
                throw new ZMessManager().new NotValidFormatException("Cargo");
            }

            if (entity.getDosConNro() == null) {
                throw new ZMessManager().new EmptyFieldException("Contrato");
            }

            if ((entity.getDosConNro() != null) &&
                    (Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
                        entity.getDosConNro(), 22, 0) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "Contrato");
            }

            if (entity.getDosEst() == null) {
                throw new ZMessManager().new EmptyFieldException("Estado");
            }

            if ((entity.getDosEst() != null) &&
                    (Utilities.checkWordAndCheckWithlength(entity.getDosEst(), 1) == false)) {
                throw new ZMessManager().new NotValidFormatException("Estado");
            }

            if (entity.getDosPerRec() == null) {
                throw new ZMessManager().new EmptyFieldException("Periodo Recambio");
            }

            if ((entity.getDosPerRec() != null) &&
                    (Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
                        entity.getDosPerRec(), 22, 0) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "Periodo Recambio");
            }

            if (entity.getDosTipo() == null) {
                throw new ZMessManager().new EmptyFieldException("Tipo");
            }

            if ((entity.getDosTipo() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getDosTipo(), 10) == false)) {
                throw new ZMessManager().new NotValidFormatException("Tipo");
            }

            if (entity.getGeoCod() == null) {
                throw new ZMessManager().new EmptyFieldException("Ingeominas");
            }

            if ((entity.getGeoCod() != null) &&
                    (Utilities.checkWordAndCheckWithlength(entity.getGeoCod(), 2) == false)) {
                throw new ZMessManager().new NotValidFormatException("Ingeominas");
            }

            if (entity.getPraCod() == null) {
                throw new ZMessManager().new EmptyFieldException("Practica");
            }

            if ((entity.getPraCod() != null) &&
                    (Utilities.checkWordAndCheckWithlength(entity.getPraCod(), 3) == false)) {
                throw new ZMessManager().new NotValidFormatException("Practica");
            }

            if (entity.getRadCod() == null) {
                throw new ZMessManager().new EmptyFieldException("Radiación");
            }

            if ((entity.getRadCod() != null) &&
                    (Utilities.checkWordAndCheckWithlength(entity.getRadCod(), 3) == false)) {
                throw new ZMessManager().new NotValidFormatException("Radiación");
            }

            if (entity.getUbiCod() == null) {
                throw new ZMessManager().new EmptyFieldException("Ubicación");
            }

            if ((entity.getUbiCod() != null) &&
                    (Utilities.checkWordAndCheckWithlength(entity.getUbiCod(), 3) == false)) {
                throw new ZMessManager().new NotValidFormatException("Ubicación");
            }

            dosimetroDAO.update(entity);
        } catch (Exception e) {
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = true)
    public List<DosimetroDTO> getDataDosimetro() throws Exception {
        try {
            List<Dosimetro> dosimetro = dosimetroDAO.findAll();

            List<DosimetroDTO> dosimetroDTO = new ArrayList<DosimetroDTO>();

            for (Dosimetro dosimetroTmp : dosimetro) {
                DosimetroDTO dosimetroDTO2 = new DosimetroDTO();

                dosimetroDTO2.setTraCed(dosimetroTmp.getId().getTraCed());
                dosimetroDTO2.setDosCod(dosimetroTmp.getId().getDosCod());
                dosimetroDTO2.setEmpCod(dosimetroTmp.getId().getEmpCod());
                dosimetroDTO2.setCarCod((dosimetroTmp.getCarCod() != null)
                    ? dosimetroTmp.getCarCod() : null);
                dosimetroDTO2.setDosConNro((dosimetroTmp.getDosConNro() != null)
                    ? dosimetroTmp.getDosConNro() : null);
                dosimetroDTO2.setDosEst((dosimetroTmp.getDosEst() != null)
                    ? dosimetroTmp.getDosEst() : null);
                dosimetroDTO2.setDosPerRec((dosimetroTmp.getDosPerRec() != null)
                    ? dosimetroTmp.getDosPerRec() : null);
                dosimetroDTO2.setDosTipo((dosimetroTmp.getDosTipo() != null)
                    ? dosimetroTmp.getDosTipo() : null);
                dosimetroDTO2.setGeoCod((dosimetroTmp.getGeoCod() != null)
                    ? dosimetroTmp.getGeoCod() : null);
                dosimetroDTO2.setPraCod((dosimetroTmp.getPraCod() != null)
                    ? dosimetroTmp.getPraCod() : null);
                dosimetroDTO2.setRadCod((dosimetroTmp.getRadCod() != null)
                    ? dosimetroTmp.getRadCod() : null);
                dosimetroDTO2.setUbiCod((dosimetroTmp.getUbiCod() != null)
                    ? dosimetroTmp.getUbiCod() : null);
                
                Traba traba = trabajadorDAO.findById(dosimetroDTO2.getTraCed());
                
                dosimetroDTO2.setNombreTrabajador(traba.getTraNom() + traba.getTraApe1() + 
                		(traba.getTraApe2() != null ? traba.getTraApe2() : ""));
                
                ContratosId contratosId = new ContratosId();
                contratosId.setConNro(dosimetroDTO2.getDosConNro());
                contratosId.setEmpCod(dosimetroDTO2.getEmpCod());
                Contratos contratos = contratosDAO.findById(contratosId);
                
                dosimetroDTO2.setFechaInicioContrato(contratos.getConFec());
                dosimetroDTO2.setFechaFacturacionContrato(contratos.getConFecFac());
                
                dosimetroDTO.add(dosimetroDTO2);
            }

            return dosimetroDTO;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public Dosimetro getDosimetro(DosimetroId id) throws Exception {
        Dosimetro entity = null;

        try {
            entity = dosimetroDAO.findById(id);
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("Dosimetro");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public List<Dosimetro> findPageDosimetro(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        List<Dosimetro> entity = null;

        try {
            entity = dosimetroDAO.findPage(sortColumnName, sortAscending,
                    startRow, maxResults);
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("Dosimetro Count");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public Long findTotalNumberDosimetro() throws Exception {
        Long entity = null;

        try {
            entity = dosimetroDAO.count();
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("Dosimetro Count");
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
    public List<Dosimetro> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        List<Dosimetro> list = new ArrayList<Dosimetro>();
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
            list = dosimetroDAO.findByCriteria(where);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
        }

        return list;
    }
}
