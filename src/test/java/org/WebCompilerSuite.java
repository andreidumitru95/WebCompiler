package org;

import org.client.WebCompilerTest;
import com.google.gwt.junit.tools.GWTTestSuite;
import junit.framework.Test;
import junit.framework.TestSuite;

public class WebCompilerSuite extends GWTTestSuite {
  public static Test suite() {
    TestSuite suite = new TestSuite("Tests for WebCompiler");
    suite.addTestSuite(WebCompilerTest.class);
    return suite;
  }
}
