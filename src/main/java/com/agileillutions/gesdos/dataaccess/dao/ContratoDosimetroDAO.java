package com.agileillutions.gesdos.dataaccess.dao;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.agileillutions.gesdos.dataaccess.api.HibernateDaoImpl;
import com.agileillutions.gesdos.modelo.ContratoDosimetro;


/**
 * A data access object (DAO) providing persistence and search support for
 * ContratoDosimetro entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 *
 * @see lidis.ContratoDosimetro
 */
@Scope("singleton")
@Repository("ContratoDosimetroDAO")
public class ContratoDosimetroDAO extends HibernateDaoImpl<ContratoDosimetro, Long>
    implements IContratoDosimetroDAO {
    private static final Logger log = LoggerFactory.getLogger(ContratoDosimetroDAO.class);
    @Resource
    private SessionFactory sessionFactory;

    public static IContratoDosimetroDAO getFromApplicationContext(
        ApplicationContext ctx) {
        return (IContratoDosimetroDAO) ctx.getBean("ContratoDosimetroDAO");
    }
}
