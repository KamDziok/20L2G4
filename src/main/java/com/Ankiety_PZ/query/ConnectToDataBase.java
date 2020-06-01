package com.Ankiety_PZ.query;

import org.hibernate.Session;

/**
 * Klasa służy do łączenia z bazą danych.
 */

public class ConnectToDataBase extends OperationInSession {

    public static final void connectToDataBase() {
        Session session;
        session = openSession();
        closeSession(session);
    }

}
