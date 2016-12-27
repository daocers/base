package co.bugu.domain.test;

import co.bugu.annotation.HasMany;
import co.bugu.annotation.Id;
import co.bugu.annotation.Table;

import java.util.List;

/**
 * Created by daocers on 2016/9/29.
 */
@Table
public class Goods {
    @Id
    private Integer id;
    private Integer count;
    private String name;
    private String description;

    private Brand brand;

    @HasMany
    private List<Attach> attachList;

    @HasMany
    private List<Picture> pictureList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public List<Attach> getAttachList() {
        return attachList;
    }

    public void setAttachList(List<Attach> attachList) {
        this.attachList = attachList;
    }

    public List<Picture> getPictureList() {
        return pictureList;
    }

    public void setPictureList(List<Picture> pictureList) {
        this.pictureList = pictureList;
    }
}
