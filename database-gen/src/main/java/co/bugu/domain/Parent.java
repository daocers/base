package co.bugu.domain;

import co.bugu.annotation.*;

import java.util.List;

/**
 * Created by daocers on 2016/9/24.
 */
@Table(value = "parent")
public class Parent {
    @Id()
//    @Index("pk")
    private Integer id;

    @Column
    @Index(value = "idname", columns = {"id", "name"})
    private String name;

    @HasOne(value = "partner_id", reverse = true)
    private Partner partner;

    @HasMany(key = "parent_id", column = "child_id", sort = true, connTable = false)
    private List<Child> children;

    @Ignore
    private String notDbField;

    public Partner getPartner() {
        return partner;
    }

    public void setPartner(Partner partner) {
        this.partner = partner;
    }

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

    public List<Child> getChildren() {
        return children;
    }

    public void setChildren(List<Child> children) {
        this.children = children;
    }

    public String getNotDbField() {
        return notDbField;
    }

    public void setNotDbField(String notDbField) {
        this.notDbField = notDbField;
    }
}
