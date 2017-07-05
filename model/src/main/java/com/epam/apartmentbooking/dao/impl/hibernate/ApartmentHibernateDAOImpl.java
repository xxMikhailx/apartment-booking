package com.epam.apartmentbooking.dao.impl.hibernate;

import com.epam.apartmentbooking.dao.ApartmentDAO;
import com.epam.apartmentbooking.domain.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.List;

@Repository("apartmentDAO")
@Transactional(readOnly = true)
public class ApartmentHibernateDAOImpl implements ApartmentDAO {

    /*@Autowired
    private SessionFactory sessionFactory;

    protected Session getSession() {
        return this.sessionFactory.getCurrentSession();
    }*/

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Apartment> findAllAvailableApartments() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Apartment> criteriaQuery = builder.createQuery(Apartment.class);
        Root<Apartment> apartmentRoot = criteriaQuery.from(Apartment.class);
        criteriaQuery.where(builder.equal(apartmentRoot.get("apartmentStatus"), ApartmentStatus.AVAILABLE))
                .orderBy(builder.asc(apartmentRoot.get("id")));
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public List<Apartment> findAllApartments() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Apartment> criteriaQuery = criteriaBuilder.createQuery(Apartment.class);
        Root<Apartment> apartmentRoot = criteriaQuery.from(Apartment.class);
        return entityManager.createQuery(criteriaQuery.orderBy(criteriaBuilder.asc(apartmentRoot.get("id")))).getResultList();
    }

    @Override
    public Apartment findEntityById(Long id) {
        return entityManager.find(Apartment.class, id);
    }

    @Override
    public List<Apartment> findAllApartmentsByCriteria(ApartmentCriteria criteria) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Apartment> criteriaQuery = builder.createQuery(Apartment.class);
        Root<Apartment> apartmentRoot = criteriaQuery.from(Apartment.class);
        Join<Apartment, City> apartmentCityJoin = apartmentRoot.join("city");
        Join<City, Country> cityCountryJoin = apartmentCityJoin.join("country");

        Predicate predicate = builder.conjunction();
        if (criteria.getTitle() != null){
            predicate = builder.and(predicate, builder.equal(apartmentRoot.get("title"), criteria.getTitle()));
        }
        if (criteria.getApartmentType() != null){
            predicate = builder.and(predicate, builder.equal(apartmentRoot.get("apartmentType"), criteria.getApartmentType()));
        }
        if (criteria.getApartmentStatus() != null){
            predicate = builder.and(predicate, builder.equal(apartmentRoot.get("apartmentStatus"), criteria.getApartmentStatus()));
        }
        if (criteria.getCityId() != null){
            predicate = builder.and(predicate, builder.equal(apartmentCityJoin.get("id"), criteria.getCityId()));
        }
        if (criteria.getCountryId() != null){
            predicate = builder.and(predicate, builder.equal(cityCountryJoin.get("id"), criteria.getCountryId()));
        }
        if (criteria.getMinPrice() != null){
            predicate = builder.and(predicate, builder.ge(apartmentRoot.get("price"), criteria.getMinPrice()));
        }
        if (criteria.getMinGuestNumber() != null){
            predicate = builder.and(predicate, builder.ge(apartmentRoot.get("maxGuestNumber"), criteria.getMinGuestNumber()));
        }
        if (criteria.getMinBedNumber() != null){
            predicate = builder.and(predicate, builder.ge(apartmentRoot.get("bedNumber"), criteria.getMinBedNumber()));
        }
        if (criteria.getMaxPrice() != null){
            predicate = builder.and(predicate, builder.le(apartmentRoot.get("price"), criteria.getMaxPrice()));
        }
        if (criteria.getMaxGuestNumber() != null){
            predicate = builder.and(predicate, builder.le(apartmentRoot.get("maxGuestNumber"), criteria.getMaxGuestNumber()));
        }
        if (criteria.getMaxBedNumber() != null){
            predicate = builder.and(predicate, builder.le(apartmentRoot.get("bedNumber"), criteria.getMaxBedNumber()));
        }
        if (criteria.getAddress() != null){
            predicate = builder.and(predicate, builder.like(apartmentRoot.get("address"), criteria.getAddress()));
        }

        criteriaQuery = criteriaQuery.select(apartmentRoot).where(predicate).orderBy(builder.asc(apartmentRoot.get("id")));

        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    @Override
    @Transactional(readOnly = false)
    public boolean remove(Long id) {
        Object persistentInstance = entityManager.find(Apartment.class, id);
        if (persistentInstance != null) {
            entityManager.remove(persistentInstance);
            return true;
        } else {
            return false;
        }
    }

    @Override
    @Transactional(readOnly = false)
    public boolean create(Apartment apartment) {
        entityManager.persist(apartment);
        return true;
    }

    @Override
    @Transactional(readOnly = false)
    public boolean update(Apartment apartment) {
        entityManager.merge(apartment);
        return true;
    }
}
