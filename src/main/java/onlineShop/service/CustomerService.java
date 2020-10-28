package onlineShop.service;

import onlineShop.dao.CustomerDao;
import onlineShop.model.Cart;
import onlineShop.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    @Autowired
    private CustomerDao customerDao;

    public void addCustomer(Customer customer){
        Cart cart= new Cart();
        customer.setCart(cart);
        customer.getUser().setEnabled(true);
        customerDao.addCustomer(customer);
    }

    public Customer getCustomerByUserName(String userName){
        return customerDao.getCustomerByEmail(userName);
    }
}
