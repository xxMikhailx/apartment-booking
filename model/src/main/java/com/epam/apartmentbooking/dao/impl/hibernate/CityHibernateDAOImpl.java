package com.epam.apartmentbooking.dao.impl.hibernate;

import com.epam.apartmentbooking.dao.CityDAO;
import com.epam.apartmentbooking.domain.City;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository("cityDAO")
@Transactional(readOnly = true)
public class CityHibernateDAOImpl implements CityDAO {

    @Autowired
    private SessionFactory sessionFactory;

    protected Session getSession() {
        return this.sessionFactory.getCurrentSession();
    }

    @Override
    public List<City> findAllCities() {
        CriteriaBuilder criteriaBuilder = getSession().getCriteriaBuilder();

        CriteriaQuery<City> criteriaQuery = criteriaBuilder.createQuery(City.class);
        Root<City> cityRoot = criteriaQuery.from(City.class);
        return getSession().createQuery(criteriaQuery.orderBy(criteriaBuilder.asc(cityRoot.get("id")))).getResultList();
    }

    @Override
    public City findEntityById(Long id) {
        return getSession().get(City.class, id);
    }

    @Override
    @Transactional(readOnly = false)
    public boolean remove(Long id) {
        Object persistentInstance = getSession().load(City.class, id);
        if (persistentInstance != null) {
            getSession().delete(persistentInstance);
            return true;
        } else {
            return false;
        }
    }

    @Override
    @Transactional(readOnly = false)
    public boolean create(City city) {
        return (Long) getSession().save(city) > 0;
    }

    @Override
    @Transactional(readOnly = false)
    public boolean update(City city) {
        getSession().update(city);
        return true;
    }
}
