package com.epam.apartmentbooking.dao.impl.hibernate;

import com.epam.apartmentbooking.dao.CountryDAO;
import com.epam.apartmentbooking.domain.Country;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository("countryDAO")
@Transactional(readOnly = true)
public class CountryHibernateDAOImpl implements CountryDAO {

    @Autowired
    private SessionFactory sessionFactory;

    protected Session getSession() {
        return this.sessionFactory.getCurrentSession();
    }

    @Override
    public List<Country> findAllCountries() {
        CriteriaBuilder criteriaBuilder = getSession().getCriteriaBuilder();

        CriteriaQuery<Country> criteriaQuery = criteriaBuilder.createQuery(Country.class);
        Root<Country> countryRoot = criteriaQuery.from(Country.class);
        return getSession().createQuery(criteriaQuery.orderBy(criteriaBuilder.asc(countryRoot.get("id")))).getResultList();
    }

    @Override
    public Country findEntityById(Long id) {
        return getSession().get(Country.class, id);
    }

    @Override
    @Transactional(readOnly = false)
    public boolean remove(Long id) {
        Object persistentInstance = getSession().load(Country.class, id);
        if (persistentInstance != null) {
            getSession().delete(persistentInstance);
            return true;
        } else {
            return false;
        }
    }

    @Override
    @Transactional(readOnly = false)
    public boolean create(Country country) {
        return (Long) getSession().save(country) > 0;
    }

    @Override
    @Transactional(readOnly = false)
    public boolean update(Country country) {
        getSession().update(country);
        return true;
    }
}
