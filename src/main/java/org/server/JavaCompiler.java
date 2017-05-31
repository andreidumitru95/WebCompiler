package org.server;

import javax.persistence.EntityManager;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * Created by Andrei on 5/31/2017.
 */
public class JavaCompiler {
    public static String javaCompiler(String input, EntityManager em) {
        String result = "";
        String fileName = "testJava.java";
        try {
            PrintWriter writer = new PrintWriter(fileName, "UTF-8");
            writer.print(input);
            writer.close();
        } catch (IOException e) {
            return e.getMessage();
        }

        String location = System.getProperty("user.dir");

        String command = "java " + location + "\\" +fileName;

        System.out.println("----" + command + "\n" +location + "\n" + input);

        ProcessBuilder pb = new ProcessBuilder(command);
        pb.redirectOutput(ProcessBuilder.Redirect.INHERIT);
        pb.redirectError(ProcessBuilder.Redirect.INHERIT);
        try {
            Process p = pb.start();
            BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

//        try {
//            Process p = Runtime.getRuntime().exec(command);
//            return p.getOutputStream().
//        } catch (IOException e) {
//            return e.getMessage();
//        }

//        java.util.Scanner s = null;
//        try {
//            s = new java.util.Scanner(Runtime.getRuntime().exec(command).getInputStream()).useDelimiter("\\A");
//        } catch (IOException e) {
//            return e.getMessage();
//        }
//        return s.hasNext() ? s.next() : "";



//        Runtime rt = Runtime.getRuntime();
////        String[] commands = {"system.exe","-get t"};
//        Process proc = null;
//        try {
//            proc = rt.exec(command);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        BufferedReader stdInput = new BufferedReader(new
//                InputStreamReader(proc.getInputStream()));
//
//        BufferedReader stdError = new BufferedReader(new
//                InputStreamReader(proc.getErrorStream()));
//
//// read the output from the command
//        System.out.println("Here is the standard output of the command:\n");
//        String s = null;
//        try {
//            while ((s = stdInput.readLine()) != null) {
//                System.out.println(s);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//// read any errors from the attempted command
//        System.out.println("Here is the standard error of the command (if any):\n");
//        try {
//            while ((s = stdError.readLine()) != null) {
//                System.out.println(s);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        return "nu a mers";
    }
}
