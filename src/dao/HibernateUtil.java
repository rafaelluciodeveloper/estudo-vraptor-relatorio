package dao;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

/**
 *
 * @author Marcos H. Costa
 */
public class HibernateUtil {

    private static SessionFactory factory;
    private static Configuration conf;

    static {
        conf = new AnnotationConfiguration();
        conf.configure("/hibernate.cfg.xml");
        factory = conf.buildSessionFactory();
    }

    public static Session getSession() {
        return factory.openSession();
    }

    public void gerarBanco() {
        SchemaExport se = new SchemaExport(conf);
        se.create(true, true);
    }

    public static void main(String[] args) {
        HibernateUtil t = new HibernateUtil();
        t.gerarBanco();
    }
}