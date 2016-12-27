package co.bugu.domain.test;

import co.bugu.annotation.Id;
import co.bugu.annotation.Table;

/**
 * Created by daocers on 2016/9/29.
 */
@Table
public class Brand {
    @Id
    private Integer id;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
