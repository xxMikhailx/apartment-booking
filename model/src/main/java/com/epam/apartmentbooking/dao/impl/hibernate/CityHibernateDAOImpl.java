package com.epam.apartmentbooking.dao.impl.hibernate;

import com.epam.apartmentbooking.dao.CityDAO;
import com.epam.apartmentbooking.domain.City;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//@Repository("cityDAO")
@Transactional(readOnly = true)
public class CityHibernateDAOImpl implements CityDAO {

    @Autowired
    private SessionFactory sessionFactory;

    protected Session getSession() {
        return this.sessionFactory.getCurrentSession();
    }

    @Override
    public List<City> findAllCities() {
        return getSession().createQuery(getSession().getCriteriaBuilder().createQuery(City.class)).getResultList();
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
