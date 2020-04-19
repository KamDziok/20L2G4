package com.Ankiety_PZ.query;

import org.hibernate.*;

import java.io.File;
import java.util.Scanner;

public class LoadDump extends OperationInSession {
    public void loadDump(String sciezka) throws Exception {
        try {
            String komendy = null;
            Scanner plik = new Scanner(new File(sciezka));

            while (plik.hasNextLine()) {
                komendy += plik.nextLine();
            }

            session = openSession();
            transaction = beginTransaction(session);
            query = session.createNativeQuery("BEGIN " + komendy + " END;");
            query.executeUpdate();
        } catch(Exception e){
            logException(e);
        }finally{
            sessionClose(session);
        }
    }
}