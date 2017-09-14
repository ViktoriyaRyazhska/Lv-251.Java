package com.softserve.edu.lv251.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * Created by Marian Brynetskyi on 11.07.2017.
 */
@Entity
public class Test extends BaseEntity {

    private String name;

    @OneToMany(mappedBy = "test")
    private List<TestsResult> testsResults;

    public Test() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TestsResult> getTestsResults() {
        return testsResults;
    }

    public void setTestsResults(List<TestsResult> testsResults) {
        this.testsResults = testsResults;
    }

}
