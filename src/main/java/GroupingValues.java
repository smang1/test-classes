import java.security.acl.Group;
import java.util.*;

/**
 * Created by frup66315 on 04/10/2016.
 */
public class GroupingValues {


    private static void createHBaseDossierLMF(List<List<String>> omfFullList, String currIdDO){

        List<List<String>> listHBaseDossierLMF= new ArrayList<List<String>>();
        HashMap<String, ArrayList<String>> hmap = new HashMap<String, ArrayList<String>>();
        for(int i=0; i <omfFullList.size(); i++ ){
            String key = omfFullList.get(i).get(1);
            if(hmap.containsKey(key)){
               // System.out.println("key already present:"+key);
                ArrayList<String> omf =hmap.get(key);
                omf.add(omfFullList.get(i).get(0));

            } else {
               // System.out.println("key already present:"+key);
                ArrayList<String> omf = new ArrayList<String>();
                omf.add(omfFullList.get(i).get(0));
                hmap.put(key,omf);

            }
        }
        System.out.println(hmap);
       // System.out.println(hmap.keySet());
        //System.out.println("Looping through keys");

        for(String key : hmap.keySet()){
            List<String> rowHBaseDossierLMF = new ArrayList<>();
            System.out.println("Handling key: "+key+" list: "+hmap.get(key));
            rowHBaseDossierLMF.add(currIdDO);
            rowHBaseDossierLMF.add(key);
            rowHBaseDossierLMF.add(hmap.get(key).toString());
            listHBaseDossierLMF.add(rowHBaseDossierLMF);
            System.out.println(listHBaseDossierLMF);

        }
        System.out.println(listHBaseDossierLMF.get(1).get(2));


    }

    public static void main(String[] args) {

        List<List<String>> omfFullList = new ArrayList<List<String>>();
        List<String> omfRowList = new ArrayList<String>();
        omfRowList.add("1");
        omfRowList.add("pp");
        omfFullList.add(omfRowList);
        // omfRowList.clear();
      //  System.out.println("omfFullList"+ omfFullList);
       omfRowList = new ArrayList<String>();
        omfRowList.add("2");
        omfRowList.add("pm");
        omfFullList.add(omfRowList);
       // System.out.println("omfFullList"+ omfFullList);
        //  omfRowList.clear();

        omfRowList = new ArrayList<String>();
        omfRowList.add("3");
        omfRowList.add("pp");
        omfFullList.add(omfRowList);
        System.out.println("omfFullList"+ omfFullList);

        //System.out.println("omfFullList"+ omfFullList);

        createHBaseDossierLMF(omfFullList,"Wow001");



        /*Iterator it =hmap.entrySet().iterator();
        while (it.hasNext()){
            hmap.Entry pair =(hmap.Entry) it.next();

        }*/
/*        for(int i=0; i<hmap.size(); i++){
            System.out.println(hmap.keySet());
        }*/


    }
}
