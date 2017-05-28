package org.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;

/**
 * Created by Andrei on 5/28/2017.
 */
public class FileSubmit extends Composite{
    private static final String URL = "webcompiler/upload";

    FormPanel form;
    Button submit;
    FileUpload file;

    public FileSubmit() {

        file = new FileUpload();
        file.setName("file");
        file.setTitle("select a file");

        submit = new Button("Submit");
        submit.setTitle("upload file");

        Panel uploadPanel = new FlowPanel();
//        uploadPanel.setStyleName("FileSubmit");
        uploadPanel.add(file);
        uploadPanel.add(submit);

        form = new FormPanel();
        form.setEncoding(FormPanel.ENCODING_MULTIPART);
        form.setMethod(FormPanel.METHOD_POST);
        form.setAction(URL);
        form.setWidget(uploadPanel);

        this.initWidget(form);

        submit.addClickHandler(new SubmitClickHandler());
        form.addSubmitHandler(new FormSubmitHandler());
        form.addSubmitCompleteHandler(new FormSubmitCompleteHandler());
    }


    private class FormSubmitHandler implements FormPanel.SubmitHandler {

        @Override
        public void onSubmit(final FormPanel.SubmitEvent event) {
            submit.setEnabled(false);
        }
    }

    private class FormSubmitCompleteHandler implements FormPanel.SubmitCompleteHandler {

        @Override
        public void onSubmitComplete(final FormPanel.SubmitCompleteEvent event) {
            form.reset();
            submit.setEnabled(true);

        }
    }

    private final class SubmitClickHandler implements ClickHandler {

        @Override
        public void onClick(final ClickEvent event) {
            String filename = file.getFilename();

            if (filename.isEmpty()) {
                Window.alert("Select a file");
                return;
            }

            form.submit();
        }
    }
}
