package com.smang.hive;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author SMA
 * @Date 21/06/2017
 */
public class HiveAccess {
    final static Logger logger = Logger.getLogger(HiveAccess.class.getName());

    /**
     * Creates a Hive statement with all the required properties set
     * @return
     * @throws SQLException
     */
    public static Statement getHiveStatement() throws SQLException {
        Connection hiveConn = HiveConnection.getConnection();
        Statement stt = hiveConn.createStatement();
        stt.execute("set hive.execution.engine=mr");
        stt.execute("set mapred.job.queue.name=test");
        stt.execute("set hive.auto.convert.join=true");
        stt.execute("set mapreduce.map.java.opts=-Xmx4096m" );
        return stt;
    }


    /**
     * insert an object in Hive table
     * @param object : object to insert in Hive
     * @param hiveDataBase : Hive data base where the object is to be inserted
     * @param hiveTable : Hive table where the object is to be inserted
     */

    public static void insertObject(Employee object, String hiveDataBase, String hiveTable) {

        Connection hiveConn=HiveConnection.getConnection();
        try {

            Statement ps = hiveConn.createStatement();
            ps.execute("set hive.exec.dynamic.partition.mode=nonstrict");
            ps.execute("SET hive.exec.dynamic.partition = true");

            String req=object.getInsertSQL(hiveDataBase,hiveTable);
            logger.info("Exécution de la requête Hive "+req);
            ps.execute(req);

        } catch (SQLException e) {
            logger.error(e);
            System.exit(-1);
        }
    }

    /**
     * get the first found object from Hive with the id given in parameter (the id is unique)
     * the field here is a String
     * @param dataBase
     * @param table
     * @param field
     * @param value
     */
    public static ResultSet getObject(String dataBase, String table, String field, String value) {

        Connection hiveConn= HiveConnection.getConnection();

        try {
            Statement ps = hiveConn.createStatement();
            String req="select * from "+dataBase+"."+table+" where "+field+"='"+value+"' limit 1";
            System.out.println(req);
            ResultSet rs=ps.executeQuery(req);
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error(e);
            System.exit(-1);
        }
        return null;

    }

    /**
     * get the first found object from Hive with the id given in parameter (the id is unique)
     * the field here is a float
     * @param dataBase
     * @param table
     * @param field
     * @param value
     */
    public static ResultSet getObject(String dataBase, String table, String field, float value) {

        Connection hiveConn= HiveConnection.getConnection();

        try {
            Statement ps = hiveConn.createStatement();
            String req="select * from "+dataBase+"."+table+" where "+field+"="+value+" limit 1";
            ResultSet rs=ps.executeQuery(req);
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error(e);
            System.exit(-1);
        }
        return null;

    }


}
