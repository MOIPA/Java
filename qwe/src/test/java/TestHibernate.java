import ORMObject.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class TestHibernate {
    public static void main(String[] args) {
        SessionFactory sf = new Configuration().configure().buildSessionFactory();
        Session session = sf.openSession();
        Transaction transaction = session.beginTransaction();

        Product p = new Product();
        p.setName("iphone");
        p.setPrice(11.1f);
        session.save(p);

        session.getTransaction().commit();
        session.close();
        sf.close();
    }
}
