package com.smang.hive;



import com.smang.hbase.KrbHandler;
import org.apache.log4j.Logger;

import java.security.PrivilegedAction;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by SMA on 28/11/2016.
 * Singleton Class to create Hive Connection and to make it accessible from everywhere
 */
public class HiveConnection {
    private final static Logger logger = Logger.getLogger(HiveConnection.class.getName());

    private static Connection hiveCon = null;

    private static void open() {
        KrbHandler.getUgi().doAs(new PrivilegedAction<Object>() {
            @Override
            public Object run() {
                logger.info("Ouverture de la connexion Hive");

                try {

                    Class.forName("org.apache.hive.jdbc.HiveDriver");
                    hiveCon = DriverManager.getConnection(String.format(
                            "jdbc:hive2://%s:%s/default;principal=%s",
                            "hdfacdn1",
                            "10000",
                            "hive/localhost@KRB.SMANG.COM"
                    ));

                } catch (Exception e) {
                    logger.error("Erreur d'ouverture de la connexion Hive", e);
                    System.exit(1);
                }

                return null;
            }
        });
    }

    /**
     * Close function closes the connection
     * this function must be called only once at the job end because Hive connection is a singleton
     */
    public static void close() {
        if (hiveCon != null) {
            try {
                logger.info("Fermeture de la connexion Hive");
                hiveCon.close();
            } catch (SQLException e) {
                logger.error("Erreur à la fermeture de la connexion Hive", e);
                System.exit(1);
            }
        } else {
            logger.info("Aucune connexion Hive n'est ouverte");
        }
    }

    public static Connection getConnection() {
        if (hiveCon == null) {
            open();
        }
        return hiveCon;
    }

}

