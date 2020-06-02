package com.Ankiety_PZ.query;

import org.hibernate.Session;
import java.util.ArrayList;
import java.util.List;

/**
 * Klasa odpowiada za operacje na danych.
 */

class OperationsOnDataInEntity<Type> extends OperationInSession {

    /**
     * Dodanie obiektu do bazy.
     *
     * @param type obiekt do dodania
     * @return true jeśli się powiodło, w przeciwnym wypadku false
     */
    Boolean add(Type type) {
        return modifyDataInEntity(type, true, false, false, true, false, null);
    }

    /**
     * Dodanie obiektu do bazy w ramach zewntrznej sesji.
     *
     * @param type obiekt do dodania
     * @param session sesja z bazą w ramach, której ma zostać dodana nagroda do bazy.
     * @return true jeśli się powiodło, w przeciwnym wypadku false
     */
    Boolean addWithOutTransaction(Type type, Session session) {
        return modifyDataInEntity(type, true, false, false, false, true, session);
    }

    /**
     * Modyfikacja obiektu do bazy.
     *
     * @param type obiekt do zmodyfikowania
     * @return true jeśli się powiodło, w przeciwnym wypadku false
     */
    Boolean update(Type type) {
        return modifyDataInEntity(type, false, true, false, true, false, null);
    }

    /**
     * Modyfikacja obiektu do bazy w ramach zewntrznej sesji.
     *
     * @param type obiekt do zmodyfikowania
     * @param session sesja z bazą w ramach, której ma zostać dodana nagroda do bazy.
     * @return true jeśli się powiodło, w przeciwnym wypadku false
     */
    Boolean updateWithOutTransaction(Type type, Session session) {
        return modifyDataInEntity(type, false, true, false, false, true, session);
    }

    /**
     * Usunięcie obiektu do bazy.
     *
     * @param type obiekt do usunięcia
     * @return true jeśli się powiodło, w przeciwnym wypadku false
     */
    Boolean delete(Type type) {
        return modifyDataInEntity(type, false, false, true, true, false, null);
    }

    /**
     * Usunięcie obiektu do bazy w ramach zewntrznej sesji.
     *
     * @param type obiekt do usunięcia
     * @param session sesja z bazą w ramach, której ma zostać dodana nagroda do bazy.
     * @return true jeśli się powiodło, w przeciwnym wypadku false
     */
    Boolean deleteWithOutTransaction(Type type, Session session) {
        return modifyDataInEntity(type, false, false, true, false, true, session);
    }

    /**
     * Metoda realizująca operacje CRUD na bazie danych.
     *
     * @param object obiekt wysyłany do bazy
     * @param add czy dodawanie
     * @param update czy modyfikacja
     * @param delete czy usuwanie
     * @param transaction czy w mechanice tranzakcji
     * @param sessionBoolean czy w ramach zewnętrznej sesji
     * @param session zewnętrzna sesja
     * @return true jeśli się powiodło, w przeciwnym wypadku false
     */
    private Boolean modifyDataInEntity(Type object, boolean add, boolean update, boolean delete, boolean transaction, boolean sessionBoolean, Session session) {
        Boolean result = false;
        try {
            if (sessionBoolean) {
                super.session = session;
            } else {
                super.session = openSession();
            }
            if (transaction) {
                super.transaction = beginTransaction(super.session);
            }
            if (add) {
                super.session.save(object);
            }
            if (update) {
                super.session.update(object);
            }
            if (delete) {
                super.session.delete(object);
            }
            if (transaction) {
                commitTransaction(super.transaction);
            }
            result = true;
        } catch (Exception e) {
            if (transaction) {
                rollbackTransaction(super.transaction);
            }
            logException(e);
        } finally {
            if (!sessionBoolean) {
                closeSession(super.session);
            }
        }
        return result;
    }

    /**
     * Wyszukanie pojedynczego obiektu przy pomocy zapytania SQL.
     *
     * @param query treść zapytania do bazy
     * @return obiekt szukany
     */
    Type selectObjectSQL(String query) {
        return selectObject(query, false);
    }

    /**
     * Wyszukanie pojedynczego obiektu przy pomocy zapytania HQL.
     *
     * @param query treść zapytania do bazy
     * @return obiekt szukany
     */
    Type selectObjectHQL(String query) {
        return selectObject(query, true);
    }

    /**
     * Metoda realizująca zapytanie wyciągające pojedynczy obiekt z bazy danych.
     *
     * @param query treść zapytania do bazy
     * @param hql jeśli true to zapytani w postaci HQL, w przeciwnym wypadku to zapytanie w postaci SQL
     * @return obiekt szukany
     */
    private Type selectObject(String query, boolean hql) {
        Type result = null;
        try {
            session = openSession();
            if (hql) {
                result = (Type) session.createQuery(query).uniqueResult();
            } else {
                result = (Type) session.createSQLQuery(query).uniqueResult();
            }
        } catch (Exception e) {
            logException(e);
        } finally {
            closeSession(session);
        }
        return result;
    }

    /**
     * Wyszukanie listy obiektów przy pomocy zapytania SQL.
     *
     * @param query treść zapytania do bazy
     * @return lista szukanych obitków
     */
    List<Type> selectListSQL(String query) {
        return selectList(query, false);
    }

    /**
     * Wyszukanie listy obiektów przy pomocy zapytania HQL.
     *
     * @param query treść zapytania do bazy
     * @return lista szukanych obitków
     */
    List<Type> selectListHQL(String query) {
        return selectList(query, true);
    }

    /**
     * Metoda realizująca zapytanie wyciągające listę obiektów z bazy danych.
     *
     * @param query treść zapytania do bazy
     * @param hql jeśli true to zapytani w postaci HQL, w przeciwnym wypadku to zapytanie w postaci SQL
     * @return lista szukanych obitków
     */
    private List<Type> selectList(String query, boolean hql) {
        List<Type> result = new ArrayList<>();
        try {
            session = openSession();
            if (hql) {
                result = (ArrayList<Type>) session.createQuery(query).list();
            } else {
                result = (ArrayList<Type>) session.createSQLQuery(query).list();
            }
        } catch (Exception e) {
            logException(e);
        } finally {
            closeSession(session);
        }
        return result;
    }


}
