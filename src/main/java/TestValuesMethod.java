import java.util.ArrayList;
import java.util.List;

/**
 * Created by frup66315 on 14/11/2016.
 */
public class TestValuesMethod {

    private static List listA  = new ArrayList<>();



    public static void addA( List  listB){
        listB.add("2");
        System.out.println("In the method: "+listB);

    }
    public static void main(String[] args) {
        listA.add("1");
        addA(listA);

        System.out.println("after the method call, value of a:"+listA);


    }
}
