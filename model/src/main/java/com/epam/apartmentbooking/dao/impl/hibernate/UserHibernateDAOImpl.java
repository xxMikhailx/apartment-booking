package com.epam.apartmentbooking.dao.impl.hibernate;

import com.epam.apartmentbooking.dao.UserDAO;
import com.epam.apartmentbooking.domain.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.List;

//@Repository("userDAO")
@Transactional(readOnly = true)
public class UserHibernateDAOImpl implements UserDAO {

    @Autowired
    private SessionFactory sessionFactory;

    protected Session getSession() {
        return this.sessionFactory.getCurrentSession();
    }

    @Override
    public List<User> findAllUsers(){
        return getSession().createQuery(getSession().getCriteriaBuilder().createQuery(User.class)).getResultList();
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
    public boolean changeUserPassword(String password, Long id){
        return jdbcTemplate.update(SQL_UPDATE_USER_PASSWORD, password, id) > 0;
    }

    @Override
    public boolean remove(Long id) {
        return jdbcTemplate.update(SQL_DELETE_USER, id) > 0;
    }

    @Override
    public boolean create(User user) {
        return jdbcTemplate.update(SQL_CREATE_USER,
                user.getLogin(),
                user.getPassword(),
                user.getEmail(),
                user.getName(),
                user.getSurname(),
                LocalDate.now().toString(),
                user.getRole()) > 0;
    }

    @Override
    public boolean update(User user) {
        return jdbcTemplate.update(SQL_UPDATE_USER_BY_ID,
                user.getLogin(),
                user.getPassword(),
                user.getEmail(),
                user.getName(),
                user.getSurname(),
                user.getRole(),
                user.getId()) > 0;
    }
}
