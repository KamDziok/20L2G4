package com.Ankiety_PZ.hibernate;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public abstract class OperationInSession {

    protected Session session = null;
    protected Query query = null;
    protected Criteria criteria = null;
    protected Transaction transaction = null;

    protected static Transaction beginTransaction(Session session){
        return session.beginTransaction();
    }

    protected static Session openSession(){
        return HibernateUtil.getSessionFactory().openSession();
    }

    protected static void commitTransaction(Transaction transaction){
        transaction.commit();
    }

    protected static void transactionRollback(Transaction transaction){
        if(transaction.isActive()) {
            transaction.rollback();
        }
    }

    protected static void sessionClose(Session session){
        if(session.isOpen()){
            session.close();
        }
    }

    protected static void logException(Exception e) {
        System.out.println("-- exception --");
        System.err.println("Exception: "+e.getClass().getName());
        System.err.println("Exception Message: "+e.getMessage());
        System.out.println("---------");
    }

}
