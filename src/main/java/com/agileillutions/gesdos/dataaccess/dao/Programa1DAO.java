package com.agileillutions.gesdos.dataaccess.dao;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.agileillutions.gesdos.dataaccess.api.HibernateDaoImpl;
import com.agileillutions.gesdos.modelo.Programa1;
import com.agileillutions.gesdos.modelo.Programa1Id;


/**
 * A data access object (DAO) providing persistence and search support for
 * Programa1 entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 *
 * @see lidis.Programa1
 */
@Scope("singleton")
@Repository("Programa1DAO")
public class Programa1DAO extends HibernateDaoImpl<Programa1, Programa1Id>
    implements IPrograma1DAO {
    private static final Logger log = LoggerFactory.getLogger(Programa1DAO.class);
    @Resource
    private SessionFactory sessionFactory;

    public static IPrograma1DAO getFromApplicationContext(
        ApplicationContext ctx) {
        return (IPrograma1DAO) ctx.getBean("Programa1DAO");
    }
}
