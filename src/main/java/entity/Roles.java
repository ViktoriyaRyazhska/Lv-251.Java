package entity;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Created by kilopo on 11.07.2017.
 */
@Entity
public class Roles extends BaseEntity {

    @Column
    String name;

    public Roles() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}