package com.Ankiety_PZ.query;

import com.Ankiety_PZ.hibernate.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Klasa odpowiada za operacje w sesji.
 */

public abstract class OperationInSession {

    protected Session session = null;
    protected Query query = null;
    protected Criteria criteria = null;
    protected Transaction transaction = null;

    protected static Session openSession() {
        return HibernateUtil.getSessionFactory().openSession();
    }

    /**
     * Otorzenie sesji
     *
     * @param session sesja, którą chcemy otworzyć
     */
    protected static void closeSession(Session session) {
        if (session.isOpen()) {
            session.close();
        }
    }

    /**
     * Rozpoczęcie transakcji
     *
     * @param session sesja w ramach której ma odbywać się tranzakcja.
     * @return tranzakcja
     */
    protected static Transaction beginTransaction(Session session) {
        return session.beginTransaction();
    }

    /**
     * Zatwierdzenie tranzakcji
     *
     * @param transaction tranzakcja, którą mamy zatwierdzić.
     */
    protected static void commitTransaction(Transaction transaction) {
        transaction.commit();
    }

    /**
     * Cofnięcie tranzakcji
     *
     * @param transaction tranzakcja, którą chcemy cofnąć
     */
    protected static void rollbackTransaction(Transaction transaction) {
        if (transaction.isActive()) {
            transaction.rollback();
        }
    }

    /**
     * Wyświetlenie informacji o błędach.
     *
     * @param e wyjątek, który nastąpił
     */
    protected static void logException(Exception e) {
        System.out.println("-- exception --");
        System.err.println("Exception: " + e.getClass().getName());
        System.err.println("Exception Message: " + e.getMessage());
        System.out.println("---------");
    }

}
