package com.agileillutions.gesdos.dataaccess.dao;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.agileillutions.gesdos.dataaccess.api.HibernateDaoImpl;
import com.agileillutions.gesdos.modelo.DetalleContrato;
import com.agileillutions.gesdos.modelo.DetalleContratoId;


/**
 * A data access object (DAO) providing persistence and search support for
 * DetalleContrato entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 *
 * @see lidis.DetalleContrato
 */
@Scope("singleton")
@Repository("DetalleContratoDAO")
public class DetalleContratoDAO extends HibernateDaoImpl<DetalleContrato, DetalleContratoId>
    implements IDetalleContratoDAO {
    private static final Logger log = LoggerFactory.getLogger(DetalleContratoDAO.class);
    @Resource
    private SessionFactory sessionFactory;

    public static IDetalleContratoDAO getFromApplicationContext(
        ApplicationContext ctx) {
        return (IDetalleContratoDAO) ctx.getBean("DetalleContratoDAO");
    }
}
