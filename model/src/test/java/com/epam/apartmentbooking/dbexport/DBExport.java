package com.epam.apartmentbooking.dbexport;

import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.database.QueryDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;

import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;

public class DBExport
{
    public static void main(String[] args) throws Exception
    {
        // database connection
        Class driverClass = Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection jdbcConnection = DriverManager.getConnection(
                "jdbc:oracle:thin:@epbyminw2971.minsk.epam.com:1521:xe", "test", "test");
        IDatabaseConnection connection = new DatabaseConnection(jdbcConnection);

        // partial database export
        QueryDataSet partialDataSet = new QueryDataSet(connection);
//        partialDataSet.addTable("USERS", "SELECT * FROM USERS");
        partialDataSet.addTable("USERS");
        FlatXmlDataSet.write(partialDataSet, new FileOutputStream("partial.xml"));

        // full database export
        /*IDataSet fullDataSet = connection.createDataSet();
        FlatXmlDataSet.write(fullDataSet, new FileOutputStream("full.xml"));*/

        // dependent tables database export: export table X and all tables that
        // have a PK which is a FK on X, in the right order for insertion
        /*String[] depTableNames =
                TablesDependencyHelper.getAllDependentTables( connection, "X" );
        IDataSet depDataSet = connection.createDataSet( depTableNames );
        FlatXmlDataSet.write(depDataSet, new FileOutputStream("dependents.xml"));*/
    }
}
