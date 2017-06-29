package com.epam.apartmentbooking.dao.impl.hibernate;

import com.epam.apartmentbooking.dao.ApartmentDAO;
import com.epam.apartmentbooking.domain.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.util.List;

@Repository("apartmentDAO")
@Transactional(readOnly = true)
public class ApartmentHibernateDAOImpl implements ApartmentDAO {

    @Autowired
    private SessionFactory sessionFactory;

    protected Session getSession() {
        return this.sessionFactory.getCurrentSession();
    }

    @Override
    public List<Apartment> findAllAvailableApartments() {
        CriteriaBuilder builder = getSession().getCriteriaBuilder();

        CriteriaQuery<Apartment> criteriaQuery = builder.createQuery(Apartment.class);
        Root<Apartment> apartmentRoot = criteriaQuery.from(Apartment.class);
        criteriaQuery.where(builder.equal(apartmentRoot.get("apartmentStatus"), ApartmentStatus.AVAILABLE))
                .orderBy(builder.asc(apartmentRoot.get("id")));
        return getSession().createQuery(criteriaQuery).getResultList();
    }

    @Override
    public List<Apartment> findAllApartments() {
        CriteriaBuilder criteriaBuilder = getSession().getCriteriaBuilder();

        CriteriaQuery<Apartment> criteriaQuery = criteriaBuilder.createQuery(Apartment.class);
        Root<Apartment> apartmentRoot = criteriaQuery.from(Apartment.class);
        return getSession().createQuery(criteriaQuery.orderBy(criteriaBuilder.asc(apartmentRoot.get("id")))).getResultList();
    }

    @Override
    public Apartment findEntityById(Long id) {
        return getSession().get(Apartment.class, id);
    }

    @Override
    public List<Apartment> findAllApartmentsByCriteria(ApartmentCriteria criteria) {
        CriteriaBuilder builder = getSession().getCriteriaBuilder();

        CriteriaQuery<Apartment> criteriaQuery = builder.createQuery(Apartment.class);
        Root<Apartment> apartmentRoot = criteriaQuery.from(Apartment.class);
        Root<City> cityRoot = criteriaQuery.from(City.class);
        Root<Country> countryRoot = criteriaQuery.from(Country.class);

        Predicate predicate = builder.conjunction();
        predicate = addCriteriaEqual(criteria.getTitle(), "title", apartmentRoot, predicate);
        System.out.println(criteria.getApartmentType());
        predicate = addCriteriaEqualEnum(criteria.getApartmentType(), "apartmentType", apartmentRoot, predicate);
        predicate = addCriteriaEqual(criteria.getApartmentStatus(), "apartmentStatus", apartmentRoot, predicate);
        predicate = addCriteriaEqual(criteria.getCityId(), "id", cityRoot, predicate);
        predicate = addCriteriaEqual(criteria.getCountryId(), "id", countryRoot, predicate);
        predicate = addCriteriaGreaterThan(criteria.getMinPrice(), "price", apartmentRoot, predicate);
        predicate = addCriteriaGreaterThan(criteria.getMinGuestNumber(), "maxGuestNumber", apartmentRoot, predicate);
        predicate = addCriteriaGreaterThan(criteria.getMinBedNumber(), "bedNumber", apartmentRoot, predicate);
        predicate = addCriteriaLowerThan(criteria.getMaxPrice(), "price", apartmentRoot, predicate);
        predicate = addCriteriaLowerThan(criteria.getMaxGuestNumber(), "maxGuestNumber", apartmentRoot, predicate);
        predicate = addCriteriaLowerThan(criteria.getMaxBedNumber(), "bedNumber", apartmentRoot, predicate);
        predicate = addCriteriaLike(criteria.getAddress(), "address", apartmentRoot, predicate);

        criteriaQuery = criteriaQuery.select(apartmentRoot).where(predicate).orderBy(builder.asc(apartmentRoot.get("id")));

        return getSession().createQuery(criteriaQuery).getResultList();
    }

    private Predicate addCriteriaEqual(Object fieldToEqual, String fieldName, Root rootObj, Predicate predicate){
        CriteriaBuilder criteriaBuilder = getSession().getCriteriaBuilder();
        if (fieldToEqual != null) {
            predicate = criteriaBuilder.and(
                    predicate,
                    criteriaBuilder.equal(rootObj.get(fieldName), fieldToEqual));
        } else {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.isNull(rootObj.get(fieldName)));
        }
        return predicate;
    }

    // TODO: 6/21/2017 remove commented code
    private Predicate addCriteriaEqualEnum(Enum fieldToEqual, String fieldName, Root rootObj, Predicate predicate){
        CriteriaBuilder criteriaBuilder = getSession().getCriteriaBuilder();
        if (fieldToEqual != null) {
            predicate = criteriaBuilder.and(
                    predicate,
                    criteriaBuilder.equal(rootObj.get(fieldName), fieldToEqual));
        } else {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.isNull(rootObj.get(fieldName)));
        }
        return predicate;
    }

    private Predicate addCriteriaGreaterThan(Number numberToEqual, String fieldName, Root rootObj, Predicate predicate){
        CriteriaBuilder criteriaBuilder = getSession().getCriteriaBuilder();
        if (numberToEqual != null) {
            predicate = criteriaBuilder.and(
                    predicate,
                    criteriaBuilder.ge(rootObj.get(fieldName), numberToEqual));
        } else {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.isNull(rootObj.get(fieldName)));
        }
        return predicate;
    }

    private Predicate addCriteriaLowerThan(Number numberToEqual, String fieldName, Root rootObj, Predicate predicate){
        CriteriaBuilder criteriaBuilder = getSession().getCriteriaBuilder();
        if (numberToEqual != null) {
            predicate = criteriaBuilder.and(
                    predicate,
                    criteriaBuilder.le(rootObj.get(fieldName), numberToEqual));
        } else {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.isNull(rootObj.get(fieldName)));
        }
        return predicate;
    }

    private Predicate addCriteriaLike(String fieldToEqual, String fieldName, Root rootObj, Predicate predicate){
        CriteriaBuilder criteriaBuilder = getSession().getCriteriaBuilder();
        if (fieldToEqual != null) {
            predicate = criteriaBuilder.and(
                    predicate,
                    criteriaBuilder.like(rootObj.get(fieldName), fieldToEqual));
        } else {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.isNull(rootObj.get(fieldName)));
        }
        return predicate;
    }

    @Override
    @Transactional(readOnly = false)
    public boolean remove(Long id) {
        Object persistentInstance = getSession().load(Apartment.class, id);
        if (persistentInstance != null) {
            getSession().delete(persistentInstance);
            return true;
        } else {
            return false;
        }
    }

    @Override
    @Transactional(readOnly = false)
    public boolean create(Apartment apartment) {
        return (Long) getSession().save(apartment) > 0;
    }

    @Override
    @Transactional(readOnly = false)
    public boolean update(Apartment apartment) {
        getSession().update(apartment);
        return true;
    }
}
