import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;

public class AdresDAOHibernate implements AdresDAO {
    @Override
    public boolean save(Adres adres) {
        try {
            //Create session factory object
            SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
            //getting session object from session factory
            Session session = sessionFactory.openSession();
            //getting transaction object from session object
            session.beginTransaction();

            session.save(adres);
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
    public boolean update(Adres adres) {
        try {
            //Create session factory object
            SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
            //getting session object from session factory
            Session session = sessionFactory.openSession();
            //getting transaction object from session object
            session.beginTransaction();

            session.update(adres);
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
    public boolean delete(Adres adres) {
        try {
            //Create session factory object
            SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
            //getting session object from session factory
            Session session = sessionFactory.openSession();
            //getting transaction object from session object
            session.beginTransaction();

            session.delete(adres);
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
    public Adres findById(int id) throws SQLException {
        //Create session factory object
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        //getting session object from session factory
        Session session = sessionFactory.openSession();
        //getting transaction object from session object
        session.beginTransaction();
        Adres adres = (Adres) session.get(Adres.class, id);
        session.getTransaction().commit();
        sessionFactory.close();
        return adres;
    }

    @Override
    public Adres findByReiziger(Reiziger reiziger) throws SQLException {
        //Create session factory object
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        //getting session object from session factory
        Session session = sessionFactory.openSession();
        //getting transaction object from session object
        session.beginTransaction();
        Query query = session.createQuery("from Adres where reiziger.reiziger_id = :reiziger_id");
        query.setParameter("reiziger_id", reiziger.getReiziger_id());
        Adres adres = (Adres)query.getSingleResult();
        session.getTransaction().commit();
        sessionFactory.close();
        return adres;
    }

    @Override
    public List<Adres> findAll() throws SQLException {
        //Create session factory object
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        //getting session object from session factory
        Session session = sessionFactory.openSession();
        //getting transaction object from session object
        session.beginTransaction();
        Query query = session.createQuery("from Adres");
        List<Adres> adresses = query.list();
        session.getTransaction().commit();
        sessionFactory.close();
        return adresses;
    }
}
