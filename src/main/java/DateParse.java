import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by frup66315 on 29/09/2016.
 */
public class DateParse {
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    private static SimpleDateFormat sdfHBase = new SimpleDateFormat("yyyyMMddHHmmss");
    private static SimpleDateFormat sdfObjet = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");

    private static Date doIhaveTime;

    public static String replaceBoolByString(Boolean input) {
        /*This method converts the input Boolean to String */
        return input == null ? "" : input.toString();
    }

//Date input
    public static void testFormatConversion(){
        //String dateStr = "Mon Jun 18 00:00:00 IST 2012";
        String dateStr ="Mon Oct 17 19:02:02 CEST 2016";
        DateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.US);
       // Date date = null;
        try {

            Date date = (Date)formatter.parse(dateStr);
            System.out.println(formatter.format(date));

            System.out.println("Date Object: "+date);
            System.out.println("Formatted date object: "+sdfHBase.format(date));
        } catch (ParseException e) {
            e.printStackTrace();

        }
    }

    public static String toSherloqqHBaseDateFormat(Date date){
        /*This method checks if the input Date is null, if null it returns an empty string else the same date converted to string in sherloqq Hbase date format*/
        return date == null ? "" : sdfHBase.format(date).toString();

    }


/*        System.out.println(dateStr);
        System.out.println(sdfObjet.format(dateStr));
        System.out.println(sdfHBase.format(sdfObjet.format(dateStr)));*/
       // DateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");



    public static void main(String[] args) {
        try {

            testFormatConversion();
           Date myDate=sdf.parse("2016-09-29 13:29:25");
            //Date myDate=sdf.parse(null);
            System.out.println("myDate  is in date type "+ myDate);
           // sdf.format(myDate);
           // myDate=null;
            System.out.println("changed mydate to null");
            System.out.println("Date in HBase format: "+sdfHBase.format(myDate));
            System.out.println(myDate.getTime());
            System.out.println("myDate  is in my myDateFormat "+ new Timestamp(myDate.getTime()));
            doIhaveTime=new Timestamp(myDate.getTime());
            System.out.println("doIhaveTime: "+ doIhaveTime);
            Boolean test=false;
            System.out.println(replaceBoolByString(test));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
