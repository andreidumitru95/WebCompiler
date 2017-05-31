package org.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import org.client.GreetingService;
import org.hibernate.jpa.internal.EntityManagerImpl;
import org.server.persistence.ProgrammingLanguages;
import org.shared.FieldVerifier;
import org.shared.LanguageStrings;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")

public class GreetingServiceImpl extends RemoteServiceServlet implements
        GreetingService {


    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("postgres_test");
    static EntityManager em = emf.createEntityManager();

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
        return CCompiler.compileSource(input, em);
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
        return JavaCompiler.javaCompiler(input, em);
    }


    public void initDatabase() {
//        em = emf.createEntityManager();
        em.getTransaction().begin();
        ProgrammingLanguages pl = new ProgrammingLanguages();
        pl.setName("Java");
        pl.setHelloWorldCode(LanguageStrings.JAVA);
//        em.persist(pl);

        pl = new ProgrammingLanguages();
        pl.setName("C");
        pl.setHelloWorldCode(LanguageStrings.C);
//        em.persist(pl);

        pl = new ProgrammingLanguages();
        pl.setName("C++");
        pl.setHelloWorldCode(LanguageStrings.CPP);
        em.persist(pl);

        try {
            em.flush();
        } catch (Exception e) {
            System.out.println("-----------aici crapa: " + e.getMessage());
        }
    }

}