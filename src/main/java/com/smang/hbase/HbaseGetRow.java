package com.smang.hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.security.UserGroupInformation;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.security.PrivilegedAction;

/**
 * Created by smang on 24/10/2016.
 */
public class HbaseGetRow {

    //final static Logger logger = Logger.getLogger(HBaseAccess.class.getName());
    private static UserGroupInformation loggedKRBUser;
    private static Configuration confHbase;
    private static Connection hbaseCon;

    public static void setHBaseConnectionVariable( Connection con){
        /*hack function to set the global variable hbaseConn which
         cannot be done inside a closure function */
        hbaseCon=con;

    }

    public static Connection createHBaseConnection() {

        loggedKRBUser.doAs(new PrivilegedAction<Void>() {

            @SuppressWarnings("deprecation")
            @Override
            public Void run() {
                try {
                    Connection con = ConnectionFactory
                            .createConnection(confHbase);
                    setHBaseConnectionVariable(con);
                } catch (IOException e) {
                    System.exit(1);
                }

                return null;
            }
        });

        return hbaseCon;
    }

    public static String getFullObjet(String rowKey, Connection hbaseCon) {
        // String ts=null;
        String objetData=null;

        try {
            Table table = hbaseCon.getTable(TableName.valueOf(""));
            //give the rowkey
            Get g = new Get(Bytes.toBytes(rowKey));
            Result r = table.get(g);
            // byte [] value = r.getValue(Bytes.toBytes(cf), Bytes.toBytes("ts"));
            byte [] value = r.getRow();
            objetData = Bytes.toString(value);

        } catch (IOException e) {
            System.exit(1);
        }
        //System.out.println(objetData);
        return objetData;


    }

    public static void main(String[] args) {

        Connection hbCon = createHBaseConnection();

        getFullObjet("off1",hbCon);

    }

}
