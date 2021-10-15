import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;

public class ProductDAOHibernate implements ProductDAO {
    @Override
    public boolean save(Product product) throws SQLException {
        try {
            //Create session factory object
            SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
            //getting session object from session factory
            Session session = sessionFactory.openSession();
            //getting transaction object from session object
            session.beginTransaction();

            session.save(product);
            System.out.println("Inserted Successfully");
            session.getTransaction().commit();
            session.close();
            sessionFactory.close();
            return true;
        } catch (Exception e) {
            System.out.printf("Oops something went wrong: %s%n", e);
            return false;
        }
    }

    @Override
    public void saveLinkTable(Product product, OVChipkaart ov_chipkaart) throws SQLException {

    }

    @Override
    public void deleteLinkTable(Product product, OVChipkaart ov_chipkaart) throws SQLException {

    }

    @Override
    public List<Product> findAll() throws SQLException {
        //Create session factory object
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        //getting session object from session factory
        Session session = sessionFactory.openSession();
        //getting transaction object from session object
        session.beginTransaction();
        Query query = session.createQuery("from Product");
        List<Product> producten = query.list();
        session.getTransaction().commit();
        sessionFactory.close();
        return producten;
    }

    @Override
    public List<Product> findByOVChipkaart(OVChipkaart ov_chipkaart) throws SQLException {
        return null;
    }

    @Override
    public boolean update(Product product) throws SQLException {
        try {
            //Create session factory object
            SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
            //getting session object from session factory
            Session session = sessionFactory.openSession();
            //getting transaction object from session object
            session.beginTransaction();

            session.update(product);
            System.out.println("Updated Successfully");
            session.getTransaction().commit();
            sessionFactory.close();
            return true;
        } catch (Exception e) {
            System.out.printf("Oops something went wrong: %s%n", e);
            return false;
        }
    }

    @Override
    public boolean delete(Product product) throws SQLException {
        try {
            //Create session factory object
            SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
            //getting session object from session factory
            Session session = sessionFactory.openSession();
            //getting transaction object from session object
            session.beginTransaction();

            session.delete(product);
            System.out.println("Deleted Successfully");
            session.getTransaction().commit();
            sessionFactory.close();
            return true;
        } catch (Exception e) {
            System.out.printf("Oops something went wrong: %s%n", e);
            return false;
        }
    }
}
