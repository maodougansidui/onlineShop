package onlineShop.dao;

import onlineShop.model.Authorities;
import onlineShop.model.Customer;
import onlineShop.model.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerDao {

    @Autowired
    private SessionFactory sessionFactory;

    public void addCustomer(Customer customer){
        Session session=null;
        Authorities authorities= new Authorities();
        authorities.setEmailId(customer.getUser().getEmailId());
        authorities.setAuthorities("ROLE_USER");

        try{
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(authorities);
            session.save(customer);
            session.getTransaction().commit();
        }catch (Exception ex){
            session.getTransaction().rollback();
        }finally {
            if (session!=null){
                session.close();
            }
        }
    }

    public Customer getCustomerByEmail(String email){
        User user=null;
        try (Session session= sessionFactory.openSession()){
            Criteria criteria= session.createCriteria(User.class);
            user=(User) criteria.add(Restrictions.eq("emailId",email)).uniqueResult();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        if (user!=null){
            return user.getCustomer();
        }
        return null;
    }
}
