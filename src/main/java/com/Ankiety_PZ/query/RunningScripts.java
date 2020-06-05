package com.Ankiety_PZ.query;
import org.apache.ibatis.jdbc.ScriptRunner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;

public class RunningScripts {

    public static void exeSqlFile(String link, String user, String haslo, String source) throws Exception {
        DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        String mysqlUrl = link;
        Connection con = DriverManager.getConnection(mysqlUrl, user, haslo);
        ScriptRunner sr = new ScriptRunner(con);
        Reader reader = new BufferedReader(new FileReader(source));
        sr.runScript(reader);
        sr.closeConnection();
    }
}
