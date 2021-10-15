import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;

public class OVChipkaartDAOHibernate implements OVChipkaartDAO {
    @Override
    public boolean save(OVChipkaart ov_chipkaart) throws SQLException {
        try {
            //Create session factory object
            SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
            //getting session object from session factory
            Session session = sessionFactory.openSession();
            //getting transaction object from session object
            session.beginTransaction();

            session.save(ov_chipkaart);
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
    public boolean update(OVChipkaart ov_chipkaart) throws SQLException {
        try {
            //Create session factory object
            SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
            //getting session object from session factory
            Session session = sessionFactory.openSession();
            //getting transaction object from session object
            session.beginTransaction();

            session.update(ov_chipkaart);
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
    public boolean delete(OVChipkaart ov_chipkaart) throws SQLException {
        try {
            //Create session factory object
            SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
            //getting session object from session factory
            Session session = sessionFactory.openSession();
            //getting transaction object from session object
            session.beginTransaction();

            session.delete(ov_chipkaart);
            System.out.println("Deleted Successfully");
            session.getTransaction().commit();
            sessionFactory.close();
            return true;
        } catch (Exception e) {
            System.out.printf("Oops something went wrong: %s%n", e);
            return false;
        }
    }

    @Override
    public OVChipkaart findById(int id) throws SQLException {
        //Create session factory object
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        //getting session object from session factory
        Session session = sessionFactory.openSession();
        //getting transaction object from session object
        session.beginTransaction();
        OVChipkaart ov_chipkaart = (OVChipkaart) session.get(OVChipkaart.class, id);
        session.getTransaction().commit();
        sessionFactory.close();
        return ov_chipkaart;
    }

    @Override
    public List<OVChipkaart> findAll() throws SQLException {
        //Create session factory object
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        //getting session object from session factory
        Session session = sessionFactory.openSession();
        //getting transaction object from session object
        session.beginTransaction();
        Query query = session.createQuery("from OVChipkaart");
        List<OVChipkaart> ov_chipkaarten = query.list();
        session.getTransaction().commit();
        sessionFactory.close();
        return ov_chipkaarten;
    }

    @Override
    public List<OVChipkaart> findByReiziger(Reiziger reiziger) throws SQLException {
        //Create session factory object
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        //getting session object from session factory
        Session session = sessionFactory.openSession();
        //getting transaction object from session object
        session.beginTransaction();
        Query query = session.createQuery("from OVChipkaart where reiziger.reiziger_id = :reiziger_id");
        query.setParameter("reiziger_id", reiziger.getReiziger_id());
        List<OVChipkaart> ov_chipkaarten = query.list();
        session.getTransaction().commit();
        sessionFactory.close();
        return ov_chipkaarten;
    }

    @Override
    public List<OVChipkaart> findByProduct(Product product) throws SQLException {
        //Create session factory object
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        //getting session object from session factory
        Session session = sessionFactory.openSession();
        //getting transaction object from session object
        session.beginTransaction();
        Query query = session.createQuery("from OVChipkaart o inner join o.producten p where p.product_nummer = :product_nummer");
        query.setParameter("product_nummer", product.getProduct_nummer());
        List<OVChipkaart> ov_chipkaarten = query.list();
        for(OVChipkaart ov_chipkaart : ov_chipkaarten) {
            System.out.println(ov_chipkaart);
        }
        session.getTransaction().commit();
        sessionFactory.close();
        return ov_chipkaarten;
    }
}
