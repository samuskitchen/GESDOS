package com.agileillutions.gesdos.modelo.control;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.agileillutions.gesdos.dataaccess.dao.IEstudiosDAO;
import com.agileillutions.gesdos.dataaccess.dao.ITrabaDAO;
import com.agileillutions.gesdos.exceptions.ZMessManager;
import com.agileillutions.gesdos.modelo.Dosimetro;
import com.agileillutions.gesdos.modelo.Estudios;
import com.agileillutions.gesdos.modelo.EstudiosId;
import com.agileillutions.gesdos.modelo.Traba;
import com.agileillutions.gesdos.modelo.dto.EstudiosDTO;
import com.agileillutions.gesdos.utilities.Utilities;


/**
* @author Zathura Code Generator http://code.google.com/p/zathura
* www.zathuracode.org
*
*/
@Scope("singleton")
@Service("EstudiosLogic")
public class EstudiosLogic implements IEstudiosLogic {
    /**
     * DAO injected by Spring that manages Estudios entities
     *
     */
    @Autowired
    private IEstudiosDAO estudiosDAO;

    @Autowired
    private ITrabaDAO trabaDAO; 
    
    @Transactional(readOnly = true)
    public List<Estudios> getEstudios() throws Exception {
        List<Estudios> list = new ArrayList<Estudios>();

        try {
            list = estudiosDAO.findAll();
        } catch (Exception e) {
            throw new ZMessManager().new GettingException(ZMessManager.ALL +
                "Estudios");
        } finally {
        }

        return list;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void saveEstudios(Estudios entity) throws Exception {
        try {
            if (entity.getId().getTraCed() == null) {
                throw new ZMessManager().new EmptyFieldException("Trabajador");
            }

            if ((entity.getId().getTraCed() != null) &&
                    (Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
                        entity.getId().getTraCed(), 22, 0) == false)) {
                throw new ZMessManager().new NotValidFormatException("Trabajador");
            }

            if (entity.getId().getEmpCod() == null) {
                throw new ZMessManager().new EmptyFieldException("Empresa");
            }

            if ((entity.getId().getEmpCod() != null) &&
                    (Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
                        entity.getId().getEmpCod(), 22, 0) == false)) {
                throw new ZMessManager().new NotValidFormatException("Empresa");
            }

            if (entity.getId().getDosCod() == null) {
                throw new ZMessManager().new EmptyFieldException("Dosimetro");
            }

            if ((entity.getId().getDosCod() != null) &&
                    (Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
                        entity.getId().getDosCod(), 22, 0) == false)) {
                throw new ZMessManager().new NotValidFormatException("Dosimetro");
            }

            if (entity.getId().getEstAni() == null) {
                throw new ZMessManager().new EmptyFieldException("Año Estudio");
            }

            if ((entity.getId().getEstAni() != null) &&
                    (Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
                        entity.getId().getEstAni(), 22, 0) == false)) {
                throw new ZMessManager().new NotValidFormatException("Año estudio");
            }

            if (entity.getId().getEstMes() == null) {
                throw new ZMessManager().new EmptyFieldException("Mes Estudio");
            }

            if ((entity.getId().getEstMes() != null) &&
                    (Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
                        entity.getId().getEstMes(), 22, 0) == false)) {
                throw new ZMessManager().new NotValidFormatException("Mes Estudio");
            }

            if (entity.getEstDos() == null) {
                throw new ZMessManager().new NotValidFormatException("Dosis");
            }

            if ((entity.getEstMesAct() != null) &&
                    (Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
                        entity.getEstMesAct(), 22, 0) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "Mes Actual");
            }

            if ((entity.getEstRec() != null) &&
                    (Utilities.checkWordAndCheckWithlength(entity.getEstRec(), 1) == false)) {
                throw new ZMessManager().new NotValidFormatException("Estado");
            }

            if (entity.getObsCod() == null) {
                throw new ZMessManager().new EmptyFieldException("Obsevacion");
            }

            if ((entity.getObsCod() != null) &&
                    (Utilities.checkWordAndCheckWithlength(entity.getObsCod(), 4) == false)) {
                throw new ZMessManager().new NotValidFormatException("Obsevacion");
            }

            if (entity.getRevNro() == null) {
                throw new ZMessManager().new EmptyFieldException("Numero Revelado");
            }

            if ((entity.getRevNro() != null) &&
                    (Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
                        entity.getRevNro(), 22, 0) == false)) {
                throw new ZMessManager().new NotValidFormatException("Numero Revelado");
            }

            if (getEstudios(entity.getId()) != null) {
                throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
            }

            estudiosDAO.save(entity);
        } catch (Exception e) {
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void deleteEstudios(Estudios entity) throws Exception {
        if (entity == null) {
            throw new ZMessManager().new NullEntityExcepcion("Estudios");
        }

        if (entity.getId().getTraCed() == null) {
            throw new ZMessManager().new EmptyFieldException("traCed");
        }

        if (entity.getId().getEmpCod() == null) {
            throw new ZMessManager().new EmptyFieldException("empCod");
        }

        if (entity.getId().getDosCod() == null) {
            throw new ZMessManager().new EmptyFieldException("dosCod");
        }

        if (entity.getId().getEstAni() == null) {
            throw new ZMessManager().new EmptyFieldException("estAni");
        }

        if (entity.getId().getEstMes() == null) {
            throw new ZMessManager().new EmptyFieldException("estMes");
        }

        try {
            estudiosDAO.delete(entity);
        } catch (Exception e) {
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void updateEstudios(Estudios entity) throws Exception {
        try {
        	if (entity.getId().getTraCed() == null) {
                throw new ZMessManager().new EmptyFieldException("Trabajador");
            }

            if ((entity.getId().getTraCed() != null) &&
                    (Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
                        entity.getId().getTraCed(), 22, 0) == false)) {
                throw new ZMessManager().new NotValidFormatException("Trabajador");
            }

            if (entity.getId().getEmpCod() == null) {
                throw new ZMessManager().new EmptyFieldException("Empresa");
            }

            if ((entity.getId().getEmpCod() != null) &&
                    (Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
                        entity.getId().getEmpCod(), 22, 0) == false)) {
                throw new ZMessManager().new NotValidFormatException("Empresa");
            }

            if (entity.getId().getDosCod() == null) {
                throw new ZMessManager().new EmptyFieldException("Dosimetro");
            }

            if ((entity.getId().getDosCod() != null) &&
                    (Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
                        entity.getId().getDosCod(), 22, 0) == false)) {
                throw new ZMessManager().new NotValidFormatException("Dosimetro");
            }

            if (entity.getId().getEstAni() == null) {
                throw new ZMessManager().new EmptyFieldException("Año Estudio");
            }

            if ((entity.getId().getEstAni() != null) &&
                    (Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
                        entity.getId().getEstAni(), 22, 0) == false)) {
                throw new ZMessManager().new NotValidFormatException("Año estudio");
            }

            if (entity.getId().getEstMes() == null) {
                throw new ZMessManager().new EmptyFieldException("Mes Estudio");
            }

            if ((entity.getId().getEstMes() != null) &&
                    (Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
                        entity.getId().getEstMes(), 22, 0) == false)) {
                throw new ZMessManager().new NotValidFormatException("Mes Estudio");
            }

            if (entity.getEstDos() == null) {
                throw new ZMessManager().new NotValidFormatException("Dosis");
            }

            if ((entity.getEstMesAct() != null) &&
                    (Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
                        entity.getEstMesAct(), 22, 0) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "Mes Actual");
            }

            if ((entity.getEstRec() != null) &&
                    (Utilities.checkWordAndCheckWithlength(entity.getEstRec(), 1) == false)) {
                throw new ZMessManager().new NotValidFormatException("Estado");
            }

            if (entity.getObsCod() == null) {
                throw new ZMessManager().new EmptyFieldException("Obsevacion");
            }

            if ((entity.getObsCod() != null) &&
                    (Utilities.checkWordAndCheckWithlength(entity.getObsCod(), 4) == false)) {
                throw new ZMessManager().new NotValidFormatException("Obsevacion");
            }

            if (entity.getRevNro() == null) {
                throw new ZMessManager().new EmptyFieldException("Numero Revelado");
            }

            if ((entity.getRevNro() != null) &&
                    (Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
                        entity.getRevNro(), 22, 0) == false)) {
                throw new ZMessManager().new NotValidFormatException("Numero Revelado");
            }

            estudiosDAO.update(entity);
        } catch (Exception e) {
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = true)
    public List<EstudiosDTO> getDataEstudios() throws Exception {
        try {
            List<Estudios> estudios = estudiosDAO.findAll();
            
            List<EstudiosDTO> estudiosDTO = new ArrayList<EstudiosDTO>();

            for (Estudios estudiosTmp : estudios) {
                EstudiosDTO estudiosDTO2 = new EstudiosDTO();
                Traba traba = trabaDAO.findById(estudiosTmp.getId().getTraCed());

                estudiosDTO2.setTraCed(estudiosTmp.getId().getTraCed());
                estudiosDTO2.setEmpCod(estudiosTmp.getId().getEmpCod());
                estudiosDTO2.setDosCod(estudiosTmp.getId().getDosCod());
                estudiosDTO2.setEstAni(estudiosTmp.getId().getEstAni());
                estudiosDTO2.setEstMes(estudiosTmp.getId().getEstMes());
                estudiosDTO2.setEstDos((estudiosTmp.getEstDos() != null)
                    ? estudiosTmp.getEstDos() : null);
                estudiosDTO2.setEstFecRec(estudiosTmp.getEstFecRec());
                estudiosDTO2.setEstMesAct((estudiosTmp.getEstMesAct() != null)
                    ? estudiosTmp.getEstMesAct() : null);
                estudiosDTO2.setEstRec((estudiosTmp.getEstRec() != null)
                    ? estudiosTmp.getEstRec() : null);
                estudiosDTO2.setObsCod((estudiosTmp.getObsCod() != null)
                    ? estudiosTmp.getObsCod() : null);
                estudiosDTO2.setRevNro((estudiosTmp.getRevNro() != null)
                    ? estudiosTmp.getRevNro() : null);
                
                String nombre = traba.getTraNom() != null ? traba.getTraNom() : "";
                String primerApellido = traba.getTraApe1() != null ? traba.getTraApe1() : "";
                String segunoApellido =  traba.getTraApe2() != null ? traba.getTraApe2() : "";
                
                
                estudiosDTO2.setNombreTrabajador(nombre + " " + primerApellido + " " + segunoApellido);
                estudiosDTO.add(estudiosDTO2);
            }

            return estudiosDTO;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public Estudios getEstudios(EstudiosId id) throws Exception {
        Estudios entity = null;

        try {
            entity = estudiosDAO.findById(id);
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("Estudios");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public List<Estudios> findPageEstudios(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        List<Estudios> entity = null;

        try {
            entity = estudiosDAO.findPage(sortColumnName, sortAscending,
                    startRow, maxResults);
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("Estudios Count");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public Long findTotalNumberEstudios() throws Exception {
        Long entity = null;

        try {
            entity = estudiosDAO.count();
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("Estudios Count");
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
    public List<Estudios> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        List<Estudios> list = new ArrayList<Estudios>();
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
            list = estudiosDAO.findByCriteria(where);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
        }

        return list;
    }
    
    /**
     * 
     * @author <a href="mailto:daniel@takum.co">Daniel De La Pava Suarez</a> 
     * @date 27/11/2016
     * @description 
     * @param idEmpresa
     * @param idTrabaja
     * @return
     * @throws Exception
     */
    @Transactional(readOnly = true)
    @Override
    public List<EstudiosDTO> getDataEstudiosParam(Long idEmpresa, Long idTrabaja) throws Exception {
        try {
            List<Estudios> estudios = new ArrayList<Estudios>();

            if(null != idEmpresa && null != idTrabaja){
            	estudios = estudiosDAO.findByCriteria("id.empCod = " + idEmpresa + " and id.traCed = " + idTrabaja);
            }else if(null != idEmpresa){
            	estudios = estudiosDAO.findByCriteria("id.empCod = " + idEmpresa);
            }else if(null != idTrabaja){
            	estudios = estudiosDAO.findByCriteria("id.traCed = " + idTrabaja);
            }
            
            List<EstudiosDTO> estudiosDTO = new ArrayList<EstudiosDTO>();

            for (Estudios estudiosTmp : estudios) {
                EstudiosDTO estudiosDTO2 = new EstudiosDTO();
                Traba traba = trabaDAO.findById(estudiosTmp.getId().getTraCed());

                estudiosDTO2.setTraCed(estudiosTmp.getId().getTraCed());
                estudiosDTO2.setEmpCod(estudiosTmp.getId().getEmpCod());
                estudiosDTO2.setDosCod(estudiosTmp.getId().getDosCod());
                estudiosDTO2.setEstAni(estudiosTmp.getId().getEstAni());
                estudiosDTO2.setEstMes(estudiosTmp.getId().getEstMes());
                estudiosDTO2.setEstDos((estudiosTmp.getEstDos() != null)
                    ? estudiosTmp.getEstDos() : null);
                estudiosDTO2.setEstFecRec(estudiosTmp.getEstFecRec());
                estudiosDTO2.setEstMesAct((estudiosTmp.getEstMesAct() != null)
                    ? estudiosTmp.getEstMesAct() : null);
                estudiosDTO2.setEstRec((estudiosTmp.getEstRec() != null)
                    ? estudiosTmp.getEstRec() : null);
                estudiosDTO2.setObsCod((estudiosTmp.getObsCod() != null)
                    ? estudiosTmp.getObsCod() : null);
                estudiosDTO2.setRevNro((estudiosTmp.getRevNro() != null)
                    ? estudiosTmp.getRevNro() : null);
                
                String nombre = traba.getTraNom() != null ? traba.getTraNom() : "";
                String primerApellido = traba.getTraApe1() != null ? traba.getTraApe1() : "";
                String segunoApellido =  traba.getTraApe2() != null ? traba.getTraApe2() : "";
                
                
                estudiosDTO2.setNombreTrabajador(nombre + " " + primerApellido + " " + segunoApellido);
                estudiosDTO.add(estudiosDTO2);
            }

            return estudiosDTO;
        } catch (Exception e) {
            throw e;
        }
    }
}
