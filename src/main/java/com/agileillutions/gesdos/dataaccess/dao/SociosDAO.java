package com.agileillutions.gesdos.dataaccess.dao;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.agileillutions.gesdos.dataaccess.api.HibernateDaoImpl;
import com.agileillutions.gesdos.modelo.Socios;


/**
 * A data access object (DAO) providing persistence and search support for
 * Socios entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 *
 * @see lidis.Socios
 */
@Scope("singleton")
@Repository("SociosDAO")
public class SociosDAO extends HibernateDaoImpl<Socios, String>
    implements ISociosDAO {
    private static final Logger log = LoggerFactory.getLogger(SociosDAO.class);
    @Resource
    private SessionFactory sessionFactory;

    public static ISociosDAO getFromApplicationContext(ApplicationContext ctx) {
        return (ISociosDAO) ctx.getBean("SociosDAO");
    }
}
