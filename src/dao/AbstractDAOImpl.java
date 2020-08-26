package dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public abstract class AbstractDAOImpl<T extends Serializable, KeyType extends Serializable> {

    protected Class<T> domainClass = getDomainClass();

    /**
     * Method to return the class of the domain object
     */
    protected abstract Class<T> getDomainClass();
    protected SessionFactory sessionFactory;

    protected Session session;
    private Transaction transaction;
    
    public void setSessionFactory(SessionFactory factory) {
        this.sessionFactory = factory;
    }

    public void beginTransaction(){
        this.session = HibernateUtil.getSession();
        this.transaction = this.session.beginTransaction();
    }
    
    public void commit(){
        this.transaction.commit();
        this.transaction = null;
    }

    @SuppressWarnings("unchecked")
    public T load(KeyType id) {
        Object obj = null;
        try {
            obj = this.session.load(domainClass, id);
            this.session.flush();
            
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return (T) obj;
    }

    @SuppressWarnings("unchecked")
    public T get(KeyType id) {
        Object obj = null;  
        this.session = HibernateUtil.getSession();
        try {
            obj = this.session.get(domainClass, id);
            this.session.flush();            
        } catch (HibernateException e) {
        }
        return (T) obj;
    }

    public void update(T t) {        
        this.session = HibernateUtil.getSession();
        try {
            this.beginTransaction();
            this.session.update(t);
            this.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    public T save(T t) {
        this.session = HibernateUtil.getSession();
        try {
            this.session.save(t);            
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return t;
    }

    public T merge(T t) {
        this.session = HibernateUtil.getSession();
        try {
            this.session.merge(t);            
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return t;
    }

    public void delete(T t) {
        this.session = HibernateUtil.getSession();
        try {
            session.delete(t);
            session.flush();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    public void deleteById(KeyType id) {
        this.session = HibernateUtil.getSession();
        try {            
            Object obj = this.session.load(domainClass, id);
            session.delete(obj);
            session.flush();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    public int count() {
        Long count = null;
        this.session = HibernateUtil.getSession();
        try {
            @SuppressWarnings("rawtypes")
            List list = this.session.createQuery("select count(*) from " + domainClass.getName()).list();
            count = (Long) list.get(0);            
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return count.intValue();
    }

    @SuppressWarnings("unchecked")
    public List<T> list() {
        this.session = HibernateUtil.getSession();
        List<T> list = null;
        try {
          //  list = this.sessionFactory.getCurrentSession().createQuery("from " + domainClass.getName()).list();            
            list = this.session.createQuery("from " + domainClass.getName()).list();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    @SuppressWarnings("unchecked")
    public List<T> search(Object field, Object value) {
        this.session = HibernateUtil.getSession();
        List<T> list = null;
        try {                    
            list = this.session.createQuery("from " + domainClass.getName() + " where " + field + " like '%" + value + "%'").list();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return list;
    }

    @SuppressWarnings("unchecked")
    public List<T> range(int first, int maxResults) {
        List<T> list = null;
        this.session = HibernateUtil.getSession();
        try {
            Query query = this.session.createQuery("from " + domainClass.getName());
            query.setFirstResult(first);
            query.setMaxResults(maxResults);
            list = query.list();            
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return list;
    }

    @SuppressWarnings("unchecked")
    public List<T> page(int pageNumber, int pageSize) {
        List<T> list = null;
        this.session = HibernateUtil.getSession();
        try {
            Query query = this.session.createQuery("from " + domainClass.getName());
            query.setFirstResult((pageNumber - 1) * pageSize);
            query.setMaxResults(pageSize);
            list = query.list();            
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return list;
    }
}