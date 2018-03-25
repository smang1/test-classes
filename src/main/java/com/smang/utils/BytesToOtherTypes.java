package com.smang.utils;

import org.apache.hadoop.hbase.util.Bytes;

import java.nio.ByteBuffer;

/**
 * Created by smang on 03/11/2016.
 */
public class BytesToOtherTypes {

    public static void main(String[] args) {

        //Bytes(Bytes.toDouble("\xFF".getBytes()));

        ;
        //Integer.toHexString(3)


        //System.out.println(BigInteger.valueOf(3).toByteArray());

        Integer i =3;
        String str = "hello";

        byte[] bytes = ByteBuffer.allocate(4).putInt(3).array();
        byte[] nullBytes = null;
        byte[] strBytes =   str.getBytes();
        System.out.println(bytes);

        for (byte b : bytes) {
            System.out.format("X0%x ", b);
        }

        if(!(nullBytes==null)){
            System.out.println("\ntest null byte: "+ Bytes.toInt(nullBytes));
        } else {
            System.out.println("\nByte value is null");
        }

        System.out.println("test String: "+ Bytes.toInt(strBytes));
        System.out.println("test null: "+ nullBytes);





        //   System.out.println( ;
    }
}
