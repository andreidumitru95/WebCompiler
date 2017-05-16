package org.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import org.shared.LanguageStrings;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

import static java.lang.System.out;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class WebCompiler implements EntryPoint {
    /**
     * The message displayed to the user when the server cannot be reached or
     * returns an error.
     */
    private static final String SERVER_ERROR = "An error occurred while "
            + "attempting to contact the server. Please check your network "
            + "connection and try again.";

    /**
     * Create a remote service proxy to talk to the server-side Greeting service.
     */
    private final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);


    private Button cpp;
    private Button c;
    private Button jv;
    private Button save;
    private VerticalPanel vp;
    private HorizontalPanel hp;
    private Button fileCompile;
    private Button textCompile;
    private FileUpload fileUpload;
    TextArea resultArea;
    private HorizontalPanel resultPanel;
    private HorizontalPanel bottonsPanel;
    private HorizontalPanel optionsPanel;
    private HorizontalPanel sourcePanel;
    private TextArea sourceArea;


    public void onModuleLoad() {
        vp = new VerticalPanel();
        hp = new HorizontalPanel();
        cpp = new Button("C++");
        c = new Button("C");
        jv = new Button("Java");

        jv.setSize("50px", "25px");
        c.setSize("50px", "25px");
        cpp.setSize("50px", "25px");
        optionsPanel = new HorizontalPanel();
        optionsPanel.setSize("150px", "40px");
        optionsPanel.setCellWidth(cpp, "50px");

        optionsPanel.add(cpp);
        optionsPanel.add(c);
        optionsPanel.add(jv);

        sourcePanel = new HorizontalPanel();
        //sourcePanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        sourcePanel.setSize("800px", "600px");

        sourceArea = new TextArea();
        sourceArea.setSize("750px", "550px");
        sourceArea.getElement().setId("source-text");
//        sourceArea.setAlignment(ValueBoxBase.TextAlignment.CENTER);
        Label sourceLabel = new Label("Source:   ");
        sourcePanel.add(sourceLabel);
        sourcePanel.add(sourceArea);


        bottonsPanel = new HorizontalPanel();
        bottonsPanel.setSize("800px", "40px");
        fileCompile = new Button("Compile selected file");
        textCompile = new Button("Compile source-code");
        save= new Button("Save");
        fileUpload = new FileUpload();
        bottonsPanel.add(fileUpload);
        bottonsPanel.add(fileCompile);
        bottonsPanel.add(textCompile);
        bottonsPanel.add(save);
        setHandlers();


        resultPanel = new HorizontalPanel();
        resultPanel.setSize("800px", "200px");
        resultArea = new TextArea();
        resultArea.setSize("750px", "180px");
        resultArea.setReadOnly(true);
        resultArea.setEnabled(false);
        Label resultLabel = new Label("Result:   ");
        resultPanel.add(resultLabel);
        resultPanel.add(resultArea);

//        vp.add(sourcePanel);
//        vp.add(resultPanel);

//        vp.add(hp);
        RootPanel.get("optionsdiv").add(optionsPanel);
        RootPanel.get("buttonsPanel").add(bottonsPanel);
        RootPanel.get("sourcePanel").add(sourcePanel);
        RootPanel.get("resultPanel").add(resultPanel);
    }

    private void setHandlers() {
        fileCompile.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                greetingService.greetServer(fileUpload.getName(), new AsyncCallback<String>() {
                    @Override
                    public void onFailure(Throwable caught) {
                        resultArea.setText("Failed to receive answer from server!");
                    }

                    @Override
                    public void onSuccess(String result) {
                        resultArea.setText(result);
                    }
                });

            }
        });

        textCompile.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                try {
                    greetingService.compileSource(sourceArea.getText(), new AsyncCallback<String>() {
                        @Override
                        public void onFailure(Throwable caught) {
                            resultArea.setText(caught.getMessage());
                        }

                        @Override
                        public void onSuccess(String result) {
                            resultArea.setText(result);
                        }
                    });
                } catch (Exception e) {
                    resultArea.setText(e.getMessage());
                }
            }
        });

        cpp.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {

                sourceArea.setText(LanguageStrings.cpp);
            }
        });

        c.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                sourceArea.setText(LanguageStrings.c);

            }
        });
        jv.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                sourceArea.setText(LanguageStrings.java);

            }
        });
//        save.addClickHandler(new ClickHandler() {
//            @Override
//            public void onClick(ClickEvent event) {
//                try (PrintStream out = new PrintStream(new FileOutputStream("code.txt"))) {
//                    out.print(sourceArea.getValue());
//                }
//               catch (FileNotFoundException e){
//
//               };
//
//            }
//        });

        sourceArea.addKeyUpHandler(new KeyUpHandler() {
            @Override
            public void onKeyUp(KeyUpEvent event) {
                int key = event.getNativeKeyCode();
                if (key == 32 || key == 13 || key == 9) {
                    // event.preventDefault();  numai pt tasta tab

                }


            }
        });
    }
}
