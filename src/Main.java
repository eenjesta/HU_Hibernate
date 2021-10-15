import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

/**
 * Testklasse - deze klasse test alle andere klassen in deze package.
 *
 * System.out.println() is alleen in deze klasse toegestaan (behalve voor exceptions).
 *
 * @author tijmen.muller@hu.nl
 */
public class Main {
    // Creëer een factory voor Hibernate sessions.
    private static final SessionFactory factory;

    static {
        try {
            // Create a Hibernate session factory
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    /**
     * Retouneer een Hibernate session.
     *
     * @return Hibernate session
     * @throws HibernateException
     */
    private static Session getSession() throws HibernateException {
        return factory.openSession();
    }

    public static void main(String[] args) throws SQLException {
        testDAOHibernate();
    }

    /**
     * P6. Haal alle (geannoteerde) entiteiten uit de database.
     */
    private static void testFetchAll() {
        Session session = getSession();
        try {
            Metamodel metamodel = session.getSessionFactory().getMetamodel();
            for (EntityType<?> entityType : metamodel.getEntities()) {
                Query query = session.createQuery("from " + entityType.getName());

                System.out.println("[Test] Alle objecten van type " + entityType.getName() + " uit database:");
                for (Object o : query.list()) {
                    System.out.println("  " + o);
                }
                System.out.println();
            }
        } finally {
            session.close();
        }
    }

    private static void testDAOHibernate() throws SQLException {
        AdresDAO aDAO = new AdresDAOHibernate();
        ReizigerDAO rDAO = new ReizigerDAOHibernate();
        OVChipkaartDAO oDAO = new OVChipkaartDAOHibernate();
        ProductDAO pDAO = new ProductDAOHibernate();
        // Haal alle adressen op
        List<Adres> adressen = aDAO.findAll();
        for(Adres adres : adressen) {
            System.out.println(adres);
        }

        // Adres opslaan in database
        String gbdatum = "1981-03-14";
        Reiziger siebe = new Reiziger(121, "S", "", "Jenk", LocalDate.parse(gbdatum));
        Adres appelhof = new Adres(7, "6678KL", "25", "Appelhof", "Utrecht", siebe);
        aDAO.save(appelhof);

        // Adres update in database
        appelhof.setStraat("Nuppie");
        aDAO.update(appelhof);

        // Vind Adres bij id
        System.out.println(aDAO.findById(7));

        // Vind Adres bij Reiziger
        System.out.println(aDAO.findByReiziger(siebe));

        // Adres verwijderen uit database
        aDAO.delete(appelhof);

        // Haal alle Reizigers op
        List<Reiziger> reizigers = rDAO.findAll();
        for(Reiziger reiziger : reizigers) {
            System.out.println(reiziger);
        }

        // Sla reiziger op in database
        rDAO.save(siebe);

        // Update reiziger in database
        siebe.setAchternaam("Willemjansen");
        rDAO.update(siebe);

        // Vind reiziger bij ID
        System.out.println(rDAO.findById(121));

//        // Vind reiziger bij geboorte datum
//        System.out.println(rDAO.findByGbdatum("1981-03-14"));

        // Haal alle producten op
        List<Product> producten = pDAO.findAll();
        for(Product product : producten) {
            System.out.println(product);
        }

        // Product opslaan in database
        Product product = new Product(7, "SDOV", "Studenten OV", 12.55);
        pDAO.save(product);

        // Product update in database
        product.setNaam("PROG");
        pDAO.update(product);

        // Product verwijderen uit database
        pDAO.delete(product);

        // Haal alle OV-chipkaarten op
        List<OVChipkaart> ov_chipkaarten = oDAO.findAll();
        for(OVChipkaart ov_chipkaart : ov_chipkaarten) {
            System.out.println(ov_chipkaart);
        }

        // Sla OV-chipkaart op in database
        String geldig_tot = "2021-03-14";
        OVChipkaart ov_chipkaart = new OVChipkaart(908, LocalDate.parse(geldig_tot), 1, 2.50, siebe);
        oDAO.save(ov_chipkaart);

        // Update OV-chipkaart in database
        ov_chipkaart.setSaldo(5.00);
        oDAO.update(ov_chipkaart);

        // Vind OV-chipkaart bij ID
        System.out.println(oDAO.findById(908));

        // Vind OV-chipkaart bij Reiziger
        System.out.println(oDAO.findByReiziger(siebe));

        // Vind OV-chipkaart bij Product
//        System.out.println(oDAO.findByProduct(product));
    }
}
