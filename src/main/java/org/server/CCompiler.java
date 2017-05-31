package org.server;

import javax.persistence.EntityManager;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Andrei on 5/31/2017.
 */
public class CCompiler {
    public static String compileSource(String input, EntityManager em) throws IOException {
        String result = "Code compiled!";
        File dir = new File("/");


        try {
            PrintWriter writer = new PrintWriter("testalabala.c", "UTF-8");
            writer.print(input);
            writer.close();
        } catch (IOException e) {
            return e.getMessage();
        }

        String filename = "test.c";


        ProcessBuilder processBuilder =
                new ProcessBuilder("C:\\Program Files (x86)\\CodeBlocks\\MinGW\\bin", "-c", "testalabala.c"); // source in working dir
//        processBuilder.directory(new File ("C:\\Users\\Andrei\\Desktop")); // or whatever

        try {
            Process proc = processBuilder.start();

        } catch (IOException e) {
            return e.getMessage();
        }

//        try {
//            String exeName = filename.substring(0, filename.length() - 2);
//            Process p = Runtime.getRuntime().exec("cmd /C gcc " + filename + " -o " + exeName, null, dir);
////          Process p = Runtime.getRuntime().exec("cmd /C dir", null, dir);
//            BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
//            String line = null;
//            while ((line = in.readLine()) != null) {
//                System.out.println(line);
//            }
//        } catch (IOException e) {
//            return  e.toString();
//        }


        return result;
    }

}
