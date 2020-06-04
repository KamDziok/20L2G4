package com.Ankiety_PZ.query;

import org.hibernate.Session;

/**
 * Klasa służy do łączenia z bazą danych.
 */

public class ConnectToDataBase extends OperationInSession {

    public static final boolean connectToDataBase() {
        boolean result = false;
        Session session = null;
        try {
            session = openSession();
            closeSession(session);
            result = true;
        }catch (Exception e) {
            result = false;
        }
        return result;
    }

}
