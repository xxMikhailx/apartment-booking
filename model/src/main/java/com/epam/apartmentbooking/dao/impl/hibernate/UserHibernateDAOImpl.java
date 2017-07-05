package com.epam.apartmentbooking.dao.impl.hibernate;

import com.epam.apartmentbooking.dao.UserDAO;
import com.epam.apartmentbooking.domain.User;
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
import java.time.LocalDate;
import java.util.List;

@Repository("userDAO")
@Transactional(readOnly = true)
public class UserHibernateDAOImpl implements UserDAO {

/*
    @Autowired
    private SessionFactory sessionFactory;
*/

/*
    protected Session getSession() {
        return this.sessionFactory.getCurrentSession();
    }
*/

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> findAllUsers(){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> userRoot = criteriaQuery.from(User.class);
        return entityManager.createQuery(criteriaQuery.orderBy(criteriaBuilder.asc(userRoot.get("id")))).getResultList();
    }

    @Override
    public User findEntityById(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public Long findUserIdByEmail(String email) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();

        CriteriaQuery<User> criteria = builder.createQuery(User.class);
        Root<User> root = criteria.from(User.class);
        criteria.where(builder.equal(root.get("email"), email));

        return entityManager.createQuery(criteria).getSingleResult().getId();
    }

    @Override
    @Transactional(readOnly = false)
    public boolean changeUserPassword(String password, Long id){
        User user = entityManager.find(User.class, id);
        user.setPassword(password);
        entityManager.merge(user);
        return true;
    }

    @Override
    @Transactional(readOnly = false)
    public boolean remove(Long id) {
        Object persistentInstance = entityManager.find(User.class, id);
        if (persistentInstance != null) {
            entityManager.remove(persistentInstance);
            return true;
        } else {
            return false;
        }
    }

    @Override
    @Transactional(readOnly = false)
    public boolean create(User user) {
        user.setCreationDate(LocalDate.now());
        entityManager.persist(user);
        return true;
    }

    @Override
    @Transactional(readOnly = false)
    public boolean update(User user) {
        entityManager.merge(user);
        return true;
    }
}
