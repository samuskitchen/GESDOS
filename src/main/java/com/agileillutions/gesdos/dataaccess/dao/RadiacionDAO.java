package com.agileillutions.gesdos.dataaccess.dao;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.agileillutions.gesdos.dataaccess.api.HibernateDaoImpl;
import com.agileillutions.gesdos.modelo.Radiacion;


/**
 * A data access object (DAO) providing persistence and search support for
 * Radiacion entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 *
 * @see lidis.Radiacion
 */
@Scope("singleton")
@Repository("RadiacionDAO")
public class RadiacionDAO extends HibernateDaoImpl<Radiacion, String>
    implements IRadiacionDAO {
    private static final Logger log = LoggerFactory.getLogger(RadiacionDAO.class);
    @Resource
    private SessionFactory sessionFactory;

    public static IRadiacionDAO getFromApplicationContext(
        ApplicationContext ctx) {
        return (IRadiacionDAO) ctx.getBean("RadiacionDAO");
    }
}
