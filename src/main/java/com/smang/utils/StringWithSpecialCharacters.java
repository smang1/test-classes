package com.smang.utils;

/**
 * @author smang
 */
public class StringWithSpecialCharacters {

    public static String fromDatamodel(String type) throws Exception {
        switch (type.trim().toUpperCase()) {
            case "STRING": return "STRING";
            case "NUMBER": return "DOUBLE";
/*            case "FLOAT": return "FLOAT";
            case "INTEGER": return INTEGER;
            case "DATE": return DATE;
            case "DATE HEURE": return TIMESTAMP;*/
            case "BOOLEEN":
            case "BOOLÉEN":
            case "BOOLEAN": return "BOOLEAN";
            default: throw new Exception(String.format("Unkown type from datamodel %s", type));
        }
    }

    public static void main(String[] args) {
        String myString = "BOOLÉEN";

        System.out.println("myString: " + myString);
        System.out.println("myString Formatted: " + myString.trim().toUpperCase());


        try {
            System.out.println("Switch: "+ fromDatamodel(myString.trim().toUpperCase()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
