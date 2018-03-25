package com.smang.hbase;

import com.smang.hive.HiveConnection;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;

/**
 * @author SMA
 * @Date 04/07/2017
 */
public class HBaseGetEmp {

    private static Connection hbaseCon = null;

    /**
     * Opens HBase connection
     */
    private static void open() {
        System.out.println("Overture de la connexion HBase");
        Configuration confHbase = HBaseConfiguration.create();
        confHbase.addResource(new Path("/etc/hbase/conf/hbase-site.xml"));
        confHbase.addResource(new Path("/etc/hbase/conf/core-site.xml"));
        try {
            hbaseCon = ConnectionFactory.createConnection(confHbase);
        } catch (IOException e) {
            System.out.println("Error lors de l'ouverture de la connexion HBase: "+  e);
            System.exit(-1);
        }
    }

    /**
     * Close function closes the connection
     * this function must be called only once at the job end because hbase connection is a singleton
     */
    public static void close() {
        if (hbaseCon != null) {
            try {
                System.out.println("Fermeture de la connexion HBase");
                hbaseCon.close();
            } catch (IOException e) {
                System.out.println(e);
                System.exit(-1);
            }
        } else {
            System.out.println("Aucune connexion HBase n'est ouverte");
        }
    }

    /**
     * Creates a secure HBase connection using Kerberos and return it
     *
     * @return Hbase connection object
     */
    public static Connection getConnection() {
        if (hbaseCon == null) {
            open();
        }
        return hbaseCon;
    }




    public static void getParseLC(final String tableName, final String key){
        //  Connection hbaseCon=HBaseConnection.getConnection();
        Table table = null;
        try {
            table = hbaseCon.getTable(TableName.valueOf(tableName));
            //give the rowkey
            Get g = new Get(Bytes.toBytes(key));
            Result r = table.get(g);

            /*EmployeeForHBase emp = new EmployeeForHBase(r);

            System.out.println(emp.id());
            System.out.println(emp.toJson());*/


        } catch (IOException e) {
            //logger.error(e);
            //  HiveAccess.InsertJobStatus(ConfigurationYmlParser.conf.other.status.job.terminated_ko);
            System.exit(1);
        }
    }



    public static void main(String[] args) throws IOException {

        System.out.println("Opening Hive connection just for kerb login");
        HiveConnection.getConnection();
        System.out.println("Opening HBase connection");
        open();
        System.out.println("HBase Connection opened");


        getParseLC("smang:objets","1MY_ID");

        hbaseCon.close();
        System.out.println(" hbaseCon Connection closed");
        HiveConnection.close();
        System.out.println(" HiveConnection Connection closed");

    }
}
