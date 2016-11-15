package com.agileillutions.gesdos.dataaccess.dao;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.agileillutions.gesdos.dataaccess.api.HibernateDaoImpl;
import com.agileillutions.gesdos.modelo.Estudios;
import com.agileillutions.gesdos.modelo.EstudiosId;


/**
 * A data access object (DAO) providing persistence and search support for
 * Estudios entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 *
 * @see lidis.Estudios
 */
@Scope("singleton")
@Repository("EstudiosDAO")
public class EstudiosDAO extends HibernateDaoImpl<Estudios, EstudiosId>
    implements IEstudiosDAO {
    private static final Logger log = LoggerFactory.getLogger(EstudiosDAO.class);
    @Resource
    private SessionFactory sessionFactory;

    public static IEstudiosDAO getFromApplicationContext(ApplicationContext ctx) {
        return (IEstudiosDAO) ctx.getBean("EstudiosDAO");
    }
}
