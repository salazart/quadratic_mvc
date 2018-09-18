package com.sz.quadratic.dao.impl;

import com.sz.quadratic.dao.interfaces.IDAO;
import com.sz.quadratic.interfaces.IEntity;
import org.hibernate.criterion.Disjunction;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository(value = "hibernateDao")
public class GenericHibernateDAO<T extends IEntity, P extends Serializable> extends AbstractHibernateDAO<T,P> implements IDAO<T,P> {
}