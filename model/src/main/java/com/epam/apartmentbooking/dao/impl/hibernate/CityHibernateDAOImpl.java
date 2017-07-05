package com.epam.apartmentbooking.dao.impl.hibernate;

import com.epam.apartmentbooking.dao.CityDAO;
import com.epam.apartmentbooking.domain.City;
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
import javax.persistence.criteria.Fetch;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository("cityDAO")
@Transactional(readOnly = true)
public class CityHibernateDAOImpl implements CityDAO {

    /*@Autowired
    private SessionFactory sessionFactory;

    protected Session getSession() {
        return this.sessionFactory.getCurrentSession();
    }*/

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<City> findAllCities() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<City> criteriaQuery = criteriaBuilder.createQuery(City.class);
        Root<City> cityRoot = criteriaQuery.from(City.class);
        return entityManager.createQuery(criteriaQuery.orderBy(criteriaBuilder.asc(cityRoot.get("id")))).getResultList();
    }

    @Override
    public City findEntityById(Long id) {
        return entityManager.find(City.class, id);
    }

    @Override
    @Transactional(readOnly = false)
    public boolean remove(Long id) {
        Object persistentInstance = entityManager.find(City.class, id);
        if (persistentInstance != null) {
            entityManager.remove(persistentInstance);
            return true;
        } else {
            return false;
        }
    }

    @Override
    @Transactional(readOnly = false)
    public boolean create(City city) {
        entityManager.persist(city);
        return true;
    }

    @Override
    @Transactional(readOnly = false)
    public boolean update(City city) {
        entityManager.merge(city);
        return true;
    }
}
