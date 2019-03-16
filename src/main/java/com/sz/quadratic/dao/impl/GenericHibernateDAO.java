package com.sz.quadratic.dao.impl;

import com.sz.quadratic.dao.interfaces.IDAO;
import com.sz.quadratic.interfaces.IEntity;
import org.springframework.stereotype.Repository;

import javax.faces.bean.ManagedBean;
import java.io.Serializable;

@Repository(value = "hibernateDao")
@ManagedBean(name = "hibernateDao")
public class GenericHibernateDAO<T extends IEntity, P extends Serializable> extends AbstractHibernateDAO<T,P> implements IDAO<T,P> {
}