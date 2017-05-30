package org.server.persistence;

import javax.persistence.*;

/**
 * Created by Andrei on 5/30/2017.
 */

@Entity
@Table(name = "programming_languages")
public class ProgrammingLanguages {
    private long id;
    private String name;
    private String helloWorldCode;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "hello_world_code", nullable = false)
    public String getHelloWorldCode() {
        return helloWorldCode;
    }

    public void setHelloWorldCode(String helloWorldCode) {
        this.helloWorldCode = helloWorldCode;
    }
}
