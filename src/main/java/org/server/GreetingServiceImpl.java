package org.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import org.client.GreetingService;
import org.shared.FieldVerifier;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
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
}