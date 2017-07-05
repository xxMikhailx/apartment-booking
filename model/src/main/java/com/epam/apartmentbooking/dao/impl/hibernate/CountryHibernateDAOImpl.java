package com.epam.apartmentbooking.dao.impl.hibernate;

import com.epam.apartmentbooking.dao.CountryDAO;
import com.epam.apartmentbooking.domain.Country;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository("countryDAO")
@Transactional(readOnly = true)
public class CountryHibernateDAOImpl implements CountryDAO {

    /*@Autowired
    private SessionFactory sessionFactory;

    protected Session getSession() {
        return this.sessionFactory.getCurrentSession();
    }*/

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Country> findAllCountries() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Country> criteriaQuery = criteriaBuilder.createQuery(Country.class);
        Root<Country> countryRoot = criteriaQuery.from(Country.class);
        return entityManager.createQuery(criteriaQuery.orderBy(criteriaBuilder.asc(countryRoot.get("id")))).getResultList();
    }

    @Override
    public Country findEntityById(Long id) {
        return entityManager.find(Country.class, id);
    }

    @Override
    @Transactional(readOnly = false)
    public boolean remove(Long id) {
        Object persistentInstance = entityManager.find(Country.class, id);
        if (persistentInstance != null) {
            entityManager.remove(persistentInstance);
            return true;
        } else {
            return false;
        }
    }

    @Override
    @Transactional(readOnly = false)
    public boolean create(Country country) {
        entityManager.persist(country);
        return true;
    }

    @Override
    @Transactional(readOnly = false)
    public boolean update(Country country) {
        entityManager.merge(country);
        return true;
    }
}
