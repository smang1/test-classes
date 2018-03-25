package com.smang.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by smang on 30/05/2016.
 */
public class LockJobExecutionIfAnotherInstanceIsRunning {

    private static boolean lock()
    {
        try
        {
            final File file=new File("test.lock");
            if (file.createNewFile())
            {
                file.deleteOnExit();
                return true;
            }
            return false;
        }
        catch (IOException e)
        {
            return false;
        }
    }

    public static void main(String[] args) {


        if (!lock())
        {
            System.out.println("Cannot start the job execution. Check that no other instance of this job is running and if so, delete the file lock file before relaunching the job");
            return;
        }

        StringBuffer id = new StringBuffer();
        int a=5;
        String b = String.format("%010d", a);
        System.out.println("formated value: "+ b);
        try {
            Thread.sleep(4000);
            System.out.println("END");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

       /* int count = 0;
        while (count < 1000000) {
            count+=6950;
            id.append("\'");
            id.append(count);
            id.append("\', ");
            //System.out.println("Count is: " + count);
          //  count++;
        }*/


        System.out.println(id);

    }
}
