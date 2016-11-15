import java.io.File;

/**
 * Created by frup66315 on 11/10/2016.
 */
public class FSFileFolderExists {

    private static boolean fsFileExists(String path){
        File f =new File (path);
        if (f.exists() && f.isFile()){
            System.out.println("File exists: "+path);
            return true;
        } else {
            System.out.println("File does not exists: "+ path);
            return false;
        }

    }

    private static boolean fsDirectoryExists(String path){
        File f =new File (path);
        if (f.exists() && f.isDirectory()){
            System.out.println("Directory exists: "+path);
            return true;
        } else {
            System.out.println("Directory does not exists: "+ path);
            return false;
        }

    }

    public static void main(String[] args) {
        String inPath = args[0];

        fsFileExists(inPath);
        fsDirectoryExists(inPath);
    }
}
