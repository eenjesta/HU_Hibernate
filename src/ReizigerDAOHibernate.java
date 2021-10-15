import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;

public class ReizigerDAOHibernate implements ReizigerDAO {
    @Override
    public boolean save(Reiziger reiziger) throws SQLException {
        try {
            //Create session factory object
            SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
            //getting session object from session factory
            Session session = sessionFactory.openSession();
            //getting transaction object from session object
            session.beginTransaction();

            session.save(reiziger);
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
    public boolean update(Reiziger reiziger) throws SQLException {
        try {
            //Create session factory object
            SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
            //getting session object from session factory
            Session session = sessionFactory.openSession();
            //getting transaction object from session object
            session.beginTransaction();

            session.update(reiziger);
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
    public boolean delete(Reiziger reiziger) throws SQLException {
        try {
            //Create session factory object
            SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
            //getting session object from session factory
            Session session = sessionFactory.openSession();
            //getting transaction object from session object
            session.beginTransaction();

            session.delete(reiziger);
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
    public Reiziger findById(int id) throws SQLException {
        //Create session factory object
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        //getting session object from session factory
        Session session = sessionFactory.openSession();
        //getting transaction object from session object
        session.beginTransaction();
        Reiziger reiziger = (Reiziger) session.get(Reiziger.class, id);
        session.getTransaction().commit();
        sessionFactory.close();
        return reiziger;
    }

    @Override
    public List<Reiziger> findByGbdatum(String datum) throws SQLException {
        //Create session factory object
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        //getting session object from session factory
        Session session = sessionFactory.openSession();
        //getting transaction object from session object
        session.beginTransaction();
        Query query = session.createQuery("from Reiziger where geboortedatum = :geboorte_datum");
        query.setParameter("geboorte_datum", datum);
        List<Reiziger> reizigers = query.list();
        session.getTransaction().commit();
        sessionFactory.close();
        return reizigers;
    }

    @Override
    public List<Reiziger> findAll() throws SQLException {
        //Create session factory object
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        //getting session object from session factory
        Session session = sessionFactory.openSession();
        //getting transaction object from session object
        session.beginTransaction();
        Query query = session.createQuery("from Reiziger");
        List<Reiziger> reizigers = query.list();
        session.getTransaction().commit();
        sessionFactory.close();
        return reizigers;
    }
}
