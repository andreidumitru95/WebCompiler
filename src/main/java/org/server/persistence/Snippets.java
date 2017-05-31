package org.server.persistence;

import com.google.gwt.user.client.rpc.IsSerializable;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Andrei on 5/30/2017.
 */

@Entity
@Table(name = "snippets")
public class Snippets implements IsSerializable {
    private long id;
    private long userId;
    private Date date;
    private String sourceContent;
    private String resultContent;
    private boolean errorFlag;
    private boolean languageId;

    public Snippets() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @JoinColumn(name = "user_id", nullable = false)
    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Column(name = "date", nullable = false)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Column(name = "source_content", nullable = false)
    public String getSourceContent() {
        return sourceContent;
    }

    public void setSourceContent(String sourceContent) {
        this.sourceContent = sourceContent;
    }

    @Column(name = "result_content")
    public String getResultContent() {
        return resultContent;
    }

    public void setResultContent(String resultContent) {
        this.resultContent = resultContent;
    }

    @Column(name = "error_flag", nullable = false)
    public boolean isErrorFlag() {
        return errorFlag;
    }

    public void setErrorFlag(boolean errorFlag) {
        this.errorFlag = errorFlag;
    }

    @JoinColumn(name = "language_id", nullable = false)
    public boolean isLanguageId() {
        return languageId;
    }

    public void setLanguageId(boolean languageId) {
        this.languageId = languageId;
    }
}
