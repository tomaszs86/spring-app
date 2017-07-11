package app.spring.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaContext;

import app.spring.model.Role;
import app.spring.repository.RoleRepositoryCustom;

public class RoleRepositoryImpl implements RoleRepositoryCustom {

	@Autowired
	private final EntityManager em;
	
	@Autowired
	public RoleRepositoryImpl(JpaContext context) {
		this.em = context.getEntityManagerByManagedType(Role.class);
	}
	
	@Override
	public List<Role> getAll() { 
		CriteriaQuery<Role> criteria = em.getCriteriaBuilder().createQuery(Role.class);
	    criteria.select(criteria.from(Role.class));
	    return em.createQuery(criteria).getResultList();		
	}
}
