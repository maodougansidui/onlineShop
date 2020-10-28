package onlineShop;

import onlineShop.model.Cart;
import onlineShop.model.CartItem;
import onlineShop.model.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;


public class Application {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig
                .class);

        SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");

        Session session = sessionFactory.openSession();

        session.beginTransaction();

        Customer customer = new Customer();
        customer.setFirstName("stefan");
        customer.setLastName("laioffer");

        CartItem cartItem1 = new CartItem();
        cartItem1.setQuantity(1);
        cartItem1.setPrice(1);

        CartItem cartItem2 = new CartItem();
        cartItem2.setQuantity(2);
        cartItem2.setPrice(2);

        Cart cart = new Cart();
        cart.setCartItem(new ArrayList<>());
        cart.getCartItem().add(cartItem1);
        cart.getCartItem().add(cartItem2);

        customer.setCart(cart);

        session.save(customer);

        session.getTransaction().commit();
        session.close();
    }
}
