package org.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import org.client.GreetingService;
import org.shared.FieldVerifier;

import java.io.*;
import java.lang.ProcessBuilder.Redirect;
import java.util.ArrayList;
import java.util.List;

/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements
        GreetingService {

    public String greetServer(String input) throws IllegalArgumentException {
        // Verify that the input is valid.
        if (!FieldVerifier.isValidName(input)) {
            // If the input is not valid, throw an IllegalArgumentException back to
            // the client.
            throw new IllegalArgumentException(
                    "Name must be at least 4 characters long");
        }

        String serverInfo = getServletContext().getServerInfo();
        String userAgent = getThreadLocalRequest().getHeader("User-Agent");

        // Escape data from the client to avoid cross-site script vulnerabilities.
        input = escapeHtml(input);
        userAgent = escapeHtml(userAgent);


        List<String> list = new ArrayList<>();


        return "Hello, " + input + "!<br><br>I am running " + serverInfo
                + ".<br><br>It looks like you are using:<br>" + userAgent;
    }

    public String compileSource(String input) throws IOException {
        String result = "";
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
            throw e;
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

    /**
     * Escape an html string. Escaping data received from the client helps to
     * prevent cross-site script vulnerabilities.
     *
     * @param html the html string to escape
     * @return the escaped string
     */
    private String escapeHtml(String html) {
        if (html == null) {
            return null;
        }
        return html.replaceAll("&", "&amp;").replaceAll("<", "&lt;").replaceAll(
                ">", "&gt;");
    }


    public String javaCompiler(String input) {
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

        System.out.println("----" + command + "\n" + input);

        ProcessBuilder pb = new ProcessBuilder(command);
        pb.redirectOutput(Redirect.INHERIT);
        pb.redirectError(Redirect.INHERIT);
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