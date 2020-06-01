package com.Ankiety_PZ.query;

import java.io.File;
import java.util.Scanner;

/**
 * Klasa odpowiedzialna za wczytywanie przykładowych danych aplikacji do bazy.
 */

public class LoadDump extends OperationInSession {

    public void loadDump(String sciezka) throws Exception {
        try {
            String komendy = "";
            String odczyt;
            Scanner plik = new Scanner(new File(sciezka));

            System.out.println(komendy);

            session = openSession();
            transaction = beginTransaction(session);
            while (plik.hasNextLine()) {
                odczyt = plik.nextLine();
                if (!odczyt.startsWith("--") && !odczyt.startsWith("/*")) komendy += odczyt;
                if (odczyt.endsWith(";") && !odczyt.endsWith("*/;")) {
                    System.out.println(komendy);
                    query = session.createNativeQuery(komendy);
                    query.executeUpdate();
                    komendy = "";
                }
            }
        } catch (Exception e) {
            logException(e);
        } finally {
            closeSession(session);
        }
    }
}