package com.epam.apartmentbooking.dao.impl.hibernate;

import com.epam.apartmentbooking.dao.UserDAO;
import com.epam.apartmentbooking.domain.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository("userDAO")
@Transactional(readOnly = true)
public class UserHibernateDAOImpl implements UserDAO {

    @Autowired
    private SessionFactory sessionFactory;

    protected Session getSession() {
        return this.sessionFactory.getCurrentSession();
    }

    @Override
    public List<User> findAllUsers(){
        CriteriaBuilder criteriaBuilder = getSession().getCriteriaBuilder();

        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> userRoot = criteriaQuery.from(User.class);
        return getSession().createQuery(criteriaQuery.orderBy(criteriaBuilder.asc(userRoot.get("id")))).getResultList();
    }

    @Override
    public User findEntityById(Long id) {
        return getSession().get(User.class, id);
    }

    @Override
    public Long findUserIdByEmail(String email) {
        CriteriaBuilder builder = getSession().getCriteriaBuilder();

        CriteriaQuery<User> criteria = builder.createQuery(User.class);
        Root<User> root = criteria.from(User.class);
        criteria.where(builder.equal(root.get("email"), email));

        return getSession().createQuery(criteria).uniqueResult().getId();
    }

    @Override
    @Transactional(readOnly = false)
    public boolean changeUserPassword(String password, Long id){
        User user = getSession().get(User.class, id);
        user.setPassword(password);
        getSession().update(user);
        return true;
    }

    @Override
    @Transactional(readOnly = false)
    public boolean remove(Long id) {
        Object persistentInstance = getSession().load(User.class, id);
        if (persistentInstance != null) {
            getSession().delete(persistentInstance);
            return true;
        } else {
            return false;
        }
    }

    @Override
    @Transactional(readOnly = false)
    public boolean create(User user) {
        return (Long) getSession().save(user) > 0;
    }

    @Override
    @Transactional(readOnly = false)
    public boolean update(User user) {
        getSession().update(user);
        return true;
    }
}
