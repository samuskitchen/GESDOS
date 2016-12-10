package com.agileillutions.gesdos.modelo.control;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.agileillutions.gesdos.dataaccess.dao.IUsuariosDAO;
import com.agileillutions.gesdos.exceptions.ZMessManager;
import com.agileillutions.gesdos.modelo.Usuarios;
import com.agileillutions.gesdos.modelo.dto.UsuariosDTO;
import com.agileillutions.gesdos.utilities.Utilities;


/**
* @author Zathura Code Generator http://code.google.com/p/zathura
* www.zathuracode.org
*
*/
@Scope("singleton")
@Service("UsuariosLogic")
public class UsuariosLogic implements IUsuariosLogic {
    /**
     * DAO injected by Spring that manages Usuarios entities
     *
     */
    @Autowired
    private IUsuariosDAO usuariosDAO;

    @Transactional(readOnly = true)
    public List<Usuarios> getUsuarios() throws Exception {
        List<Usuarios> list = new ArrayList<Usuarios>();

        try {
            list = usuariosDAO.findAll();
        } catch (Exception e) {
            throw new ZMessManager().new GettingException(ZMessManager.ALL +
                "Usuarios");
        } finally {
        }

        return list;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void saveUsuarios(Usuarios entity) throws Exception {
        try {
            if (entity.getUsuaApeNom() == null) {
                throw new ZMessManager().new EmptyFieldException("usuaApeNom");
            }

            if ((entity.getUsuaApeNom() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getUsuaApeNom(), 30) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "usuaApeNom");
            }

            if (entity.getUsuaCodigo() == null) {
                throw new ZMessManager().new EmptyFieldException("usuaCodigo");
            }

            if ((entity.getUsuaCodigo() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getUsuaCodigo(), 10) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "usuaCodigo");
            }

            if (entity.getUsuaDias() == null) {
                throw new ZMessManager().new EmptyFieldException("usuaDias");
            }

            if ((entity.getUsuaDias() != null) &&
                    (Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
                        entity.getUsuaDias(), 22, 0) == false)) {
                throw new ZMessManager().new NotValidFormatException("usuaDias");
            }

            if (entity.getUsuaPassw1() == null) {
                throw new ZMessManager().new EmptyFieldException("usuaPassw1");
            }

            if ((entity.getUsuaPassw1() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getUsuaPassw1(), 6) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "usuaPassw1");
            }

            if (entity.getUsuaPassw2() == null) {
                throw new ZMessManager().new EmptyFieldException("usuaPassw2");
            }

            if ((entity.getUsuaPassw2() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getUsuaPassw2(), 6) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "usuaPassw2");
            }

            if (entity.getUsuaPasswo() == null) {
                throw new ZMessManager().new EmptyFieldException("usuaPasswo");
            }

            if ((entity.getUsuaPasswo() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getUsuaPasswo(), 6) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "usuaPasswo");
            }

            if (entity.getUsuafecha() == null) {
                throw new ZMessManager().new EmptyFieldException("usuafecha");
            }

            if (getUsuarios(entity.getUsuaCodigo()) != null) {
                throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
            }

            usuariosDAO.save(entity);
        } catch (Exception e) {
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void deleteUsuarios(Usuarios entity) throws Exception {
        if (entity == null) {
            throw new ZMessManager().new NullEntityExcepcion("Usuarios");
        }

        if (entity.getUsuaCodigo() == null) {
            throw new ZMessManager().new EmptyFieldException("usuaCodigo");
        }

        try {
            usuariosDAO.delete(entity);
        } catch (Exception e) {
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void updateUsuarios(Usuarios entity) throws Exception {
        try {
            if (entity == null) {
                throw new ZMessManager().new NullEntityExcepcion("Usuarios");
            }

            if (entity.getUsuaApeNom() == null) {
                throw new ZMessManager().new EmptyFieldException("usuaApeNom");
            }

            if ((entity.getUsuaApeNom() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getUsuaApeNom(), 30) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "usuaApeNom");
            }

            if (entity.getUsuaCodigo() == null) {
                throw new ZMessManager().new EmptyFieldException("usuaCodigo");
            }

            if ((entity.getUsuaCodigo() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getUsuaCodigo(), 10) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "usuaCodigo");
            }

            if (entity.getUsuaDias() == null) {
                throw new ZMessManager().new EmptyFieldException("usuaDias");
            }

            if ((entity.getUsuaDias() != null) &&
                    (Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
                        entity.getUsuaDias(), 22, 0) == false)) {
                throw new ZMessManager().new NotValidFormatException("usuaDias");
            }

            if (entity.getUsuaPassw1() == null) {
                throw new ZMessManager().new EmptyFieldException("usuaPassw1");
            }

            if ((entity.getUsuaPassw1() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getUsuaPassw1(), 6) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "usuaPassw1");
            }

            if (entity.getUsuaPassw2() == null) {
                throw new ZMessManager().new EmptyFieldException("usuaPassw2");
            }

            if ((entity.getUsuaPassw2() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getUsuaPassw2(), 6) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "usuaPassw2");
            }

            if (entity.getUsuaPasswo() == null) {
                throw new ZMessManager().new EmptyFieldException("usuaPasswo");
            }

            if ((entity.getUsuaPasswo() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getUsuaPasswo(), 6) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "usuaPasswo");
            }

            if (entity.getUsuafecha() == null) {
                throw new ZMessManager().new EmptyFieldException("usuafecha");
            }

            usuariosDAO.update(entity);
        } catch (Exception e) {
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = true)
    public List<UsuariosDTO> getDataUsuarios() throws Exception {
        try {
            List<Usuarios> usuarios = usuariosDAO.findAll();

            List<UsuariosDTO> usuariosDTO = new ArrayList<UsuariosDTO>();

            for (Usuarios usuariosTmp : usuarios) {
                UsuariosDTO usuariosDTO2 = new UsuariosDTO();

                usuariosDTO2.setUsuaCodigo(usuariosTmp.getUsuaCodigo());
                usuariosDTO2.setUsuaApeNom((usuariosTmp.getUsuaApeNom() != null)
                    ? usuariosTmp.getUsuaApeNom() : null);
                usuariosDTO2.setUsuaDias((usuariosTmp.getUsuaDias() != null)
                    ? usuariosTmp.getUsuaDias() : null);
                usuariosDTO2.setUsuaPassw1((usuariosTmp.getUsuaPassw1() != null)
                    ? usuariosTmp.getUsuaPassw1() : null);
                usuariosDTO2.setUsuaPassw2((usuariosTmp.getUsuaPassw2() != null)
                    ? usuariosTmp.getUsuaPassw2() : null);
                usuariosDTO2.setUsuaPasswo((usuariosTmp.getUsuaPasswo() != null)
                    ? usuariosTmp.getUsuaPasswo() : null);
                usuariosDTO2.setUsuafecha(usuariosTmp.getUsuafecha());
                usuariosDTO.add(usuariosDTO2);
            }

            return usuariosDTO;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public Usuarios getUsuarios(String usuaCodigo) throws Exception {
        Usuarios entity = null;

        try {
            entity = usuariosDAO.findById(usuaCodigo);
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("Usuarios");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public List<Usuarios> findPageUsuarios(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        List<Usuarios> entity = null;

        try {
            entity = usuariosDAO.findPage(sortColumnName, sortAscending,
                    startRow, maxResults);
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("Usuarios Count");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public Long findTotalNumberUsuarios() throws Exception {
        Long entity = null;

        try {
            entity = usuariosDAO.count();
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("Usuarios Count");
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
    public List<Usuarios> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        List<Usuarios> list = new ArrayList<Usuarios>();
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
            list = usuariosDAO.findByCriteria(where);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
        }

        return list;
    }
}
