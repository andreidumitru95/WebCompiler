package org.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface GreetingServiceAsync {
    void greetServer(String input, AsyncCallback<String> callback)
            throws IllegalArgumentException;

    void compileSource(String input, AsyncCallback<String> callback) throws Exception;

    void javaCompiler(String input, AsyncCallback<String> callback) throws Exception;

    void initDatabase(AsyncCallback<Void> async) throws Exception;
}
