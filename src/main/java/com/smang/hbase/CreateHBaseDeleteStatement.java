package com.smang.hbase;

import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;

/**
 * Created by SMA on 07/10/2016.
 */
public class CreateHBaseDeleteStatement {

    final static Logger logger = Logger.getLogger(CreateHBaseDeleteStatement.class.getName());

    public static void main(String[] args) {
        int startVal=7442;
        int endVal=7492;

        String myPath="/test/bla/dir1/dir2/abc.txt";

        String basePath = FilenameUtils.getPath(myPath);
       // System.out.println(basePath);

        logger.info("Creating id's");

        for ( int i= startVal; i<=endVal; i++){
            System.out.println("deleteall 'objets_dev_dor', '2LG161100000"+i+"'");

        }
      //  System.out.println(a[1]);
        logger.info("Creating id's - OK");
        logger.error("No error");
    }

}
