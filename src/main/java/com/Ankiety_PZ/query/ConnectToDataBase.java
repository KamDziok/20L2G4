package com.Ankiety_PZ.query;

import org.hibernate.Session;

public class ConnectToDataBase extends OperationInSession {

    public static final void connectToDataBase(){
        Session session;
        session = openSession();
        closeSession(session);
    }

}
