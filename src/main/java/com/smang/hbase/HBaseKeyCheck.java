package com.smang.hbase;

import com.smang.hive.HiveConnection;
import org.apache.commons.cli.*;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.filter.CompareFilter;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.SingleColumnValueFilter;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.*;

/**
 * Created by smang on 21/02/2017.
 */
public class HBaseKeyCheck {

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

   /* private static void open(){
        System.out.println("Overture de la connexion HBase");
        confHbase = HBaseConfiguration.create();
        *//*confHbase.addResource(new Path("src/main/resources/conf/hbase-site.xml"));
        confHbase.addResource(new Path("src/main/resources/conf/core-site.xml"));*//*
        confHbase.addResource(new Path("/etc/hbase/conf/hbase-site.xml"));
        confHbase.addResource(new Path("/etc/hbase/conf/core-site.xml"));
        try {
            hbaseCon = ConnectionFactory.createConnection(confHbase);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/


    public static Boolean keyExists(final String tableName, final String key){
      //  Connection hbaseCon=HBaseConnection.getConnection();
        Boolean keyExistsFlag=false;
        Table table = null;
        try {
            table = hbaseCon.getTable(TableName.valueOf(tableName));
            //give the rowkey
            Get g = new Get(Bytes.toBytes(key));
            Result r = table.get(g);

            if (r.isEmpty()){
                keyExistsFlag=false;
            } else{
                keyExistsFlag=true;
            }

        } catch (IOException e) {
            //logger.error(e);
          //  HiveAccess.InsertJobStatus(ConfigurationYmlParser.conf.other.status.job.terminated_ko);
            System.exit(1);
        }
        return keyExistsFlag;
    }

    /**
     * Check if every row keys of given list exist in hbase
     * If at least one doesn't, returns false
     *
     * @param tableName table to check
     * @param keys list of row keys
     * @return
     */
    public static Boolean keysAllExist(final String tableName, final List<String> keys) {
        List<Get> gets = new ArrayList<>();
        for(String k: keys) gets.add(new Get(Bytes.toBytes(k)));
        boolean[] existArray = null;
        boolean exist = true;

        try {
            Table table = hbaseCon.getTable(TableName.valueOf(tableName));
            existArray = table.existsAll(gets);
            System.out.println("existArray (check done with id): "+ Arrays.toString(existArray));

            for(boolean b: existArray) {
                if (!b) exist = false;
            }

        } catch (IOException e) {
      //      logger.error(e);
      //      HiveAccess.InsertJobStatus(ConfigurationYmlParser.conf.other.status.job.terminated_ko);
            System.exit(1);
        }
        return exist;
    }


    /**
     * Check if every row keys of given list exist in hbase
     * If at least one doesn't, returns false
     * Limitation: SingleColumnValueFilter - takes a column family, a qualifier, a compare operator
     * and a comparator. If the specified column is not found, all the columns of that row
     * will be emitted
     * @param tableName table to check
     * @param idVerObjMap     A hashmap <idObj, objVersion>
     * @return
     */
    public static Boolean allObjKeysWithGivenVerObjOrAboveExist(final String tableName, final HashMap<String, String> idVerObjMap) {

        System.out.println(String.format("Vérification d'existance dans HBase des clés + verObj suivantes : %s", idVerObjMap));

        List<Get> gets = new ArrayList<>();
        boolean[] existArray = null;

        for(Map.Entry<String, String> currElmt: idVerObjMap.entrySet()) {
            String idObj=currElmt.getKey();
            String verObj=currElmt.getValue();
            Get currGet = new Get(Bytes.toBytes(idObj));
            String cf = idObj.trim().toUpperCase().substring(1,3);
           // System.out.println("curr CF: "+ cf);
            String verObjCol = cf + "_VerObj";
            //System.out.println(verObjCol + ": " + verObj);
            Filter verObjFiter = new SingleColumnValueFilter(Bytes.toBytes(cf),Bytes.toBytes(verObjCol), CompareFilter.CompareOp.GREATER_OR_EQUAL,Bytes.toBytes(verObj));
            currGet.setFilter(verObjFiter);
            gets.add(currGet);
        }

        try {
            Table table = hbaseCon.getTable(TableName.valueOf(tableName));
           // Table table = HBaseConnection.getConnection().getTable(TableName.valueOf(tableName));
            existArray = table.existsAll(gets);
            System.out.println("existArray (check done with id & verObj): "+ Arrays.toString(existArray));
            for (boolean b : existArray) {
                // If any doesn't exist return false
                //logger.debug(String.format("%s exists = %s", keys.get()))
                if (!b) return false;
            }

        } catch (IOException e) {
           // logger.error("Erreur de communication avec HBase", e);
           // Job.exit(ConfigurationYmlParser.conf.other.status.job.terminated_ko, 1);
        }

        // If all exist return true
        return true;
    }



    public static void main(String[] args) throws IOException {

        Options options = new Options();

        Option searchModeOption = new Option("sm", "searchMode", true, "searchMode (id or id-ver" );
        searchModeOption.setRequired(true);
        options.addOption(searchModeOption);

        Option idObjOption = new Option("idObj", "idObj", true, "idObj" );
        idObjOption.setRequired(true);
        options.addOption(idObjOption);

        Option verObjOption = new Option("verObj", "verObj", true, "verObj" );
        verObjOption.setRequired(false);
        options.addOption(verObjOption);

        CommandLineParser parser = new BasicParser();
        HelpFormatter formatter = new HelpFormatter();
        CommandLine cmd;

        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            formatter.printHelp("utility-name", options);

            System.exit(1);
            return;
        }

        String searchModeGiven = cmd.getOptionValue("searchMode");
        String idGiven = cmd.getOptionValue("idObj");
        String verGiven = cmd.getOptionValue("verObj");

        System.out.println(String.format("searchModeGiven: %s idGiven: %s  verGiven: %s", searchModeGiven, idGiven, verGiven));

        System.out.println("Opening Hive connection just for kerb login");
        HiveConnection.getConnection();
        System.out.println("Opening HBase connection");
        open();
        System.out.println("HBase Connection opened");

        long startTime = System.currentTimeMillis();
        if(searchModeGiven.trim().equals("id")){
            List<String> keys=new ArrayList<>();
            keys.add(idGiven.trim());
            System.out.println(keys);
            System.out.println("keysAllExist: "+ keysAllExist("smang:objets",keys));

        } else if (searchModeGiven.trim().equals("id-ver")){
            HashMap <String, String> idObjVerObjMap = new HashMap();
            idObjVerObjMap.put(idGiven.trim(), verGiven.trim());
            System.out.println("allObjKeysWithGivenVerObjOrAboveExist : "+ allObjKeysWithGivenVerObjOrAboveExist("smang:objets",idObjVerObjMap));

        } else {
            System.out.println("ERROR: INVALID 'searchMode'. valide values are 'id' OR 'id-ver'");
        }
        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        System.out.println("***********Time taken: "+ elapsedTime);


        hbaseCon.close();
        System.out.println(" hbaseCon Connection closed");
        HiveConnection.close();
        System.out.println(" HiveConnection Connection closed");

    }
}
