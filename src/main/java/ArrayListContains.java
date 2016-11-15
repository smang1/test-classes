import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by frup66315 on 18/08/2016.
 */
public class ArrayListContains {
    public static void main(String[] args) {
        String s = "pp,pm,of,ag,gg,Kk,hh,AB,II,Lf, fL";

        List<String> myList = new ArrayList<String>(Arrays.asList(s.toLowerCase().split(",")));
        System.out.println(myList);

        if (myList.contains("fl")){
            System.out.println("yes, I have fl");
        }

        if (myList.contains("ml")){
            System.out.println("yes, I have ml");
        }
    }
}
