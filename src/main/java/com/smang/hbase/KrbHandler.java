package com.smang.hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.security.UserGroupInformation;
import org.apache.log4j.Logger;

import java.io.IOException;

/**
 * @author smang
 * @Date 09/06/2017
 */
public class KrbHandler {

    final static Logger logger = Logger.getLogger(KrbHandler.class.getName());

    private static Configuration conf = null;
    private static UserGroupInformation ugi = null;

    /**
     * Login to Kerberos using given configuration, implicitly called on first getUgi()
     *
     * @return logged UGI
     */
    private static UserGroupInformation login() {
        if (conf == null) {
            conf = new Configuration();

            logger.info("Préparation de la configuration Kerberos");
            System.setProperty("java.security.krb5.conf", "/etc/krb5.conf");

            // Set HDFS + HBase conf file paths
            conf.addResource(new Path("file://" + "/etc/hadoop/conf/core-site.xml"));
            conf.addResource(new Path("file://" + "/etc/hadoop/conf/hdfs-site.xml"));
            conf.addResource(new Path("file://" + "/etc/hbase/conf/hbase-site.xml"));

            // Set auth type
            conf.set("hadoop.security.authentication", "kerberos");

            logger.info("Authentification auprès de Kerberos");
            UserGroupInformation.setConfiguration(conf);
            try {
                ugi = UserGroupInformation.loginUserFromKeytabAndReturnUGI(
                        "smang",
                        "/var/smang/keytabs/smang.keytab"
                );
            } catch (IOException e) {
                logger.error("Erreur lors de la connexion à Kerberos", e);
                System.exit(1);
            }
        }

        return ugi;
    }

    public static Configuration getConf() { return conf; }

    public static UserGroupInformation getUgi() {
        if (ugi == null) return login();
        else return ugi;
    }
}

