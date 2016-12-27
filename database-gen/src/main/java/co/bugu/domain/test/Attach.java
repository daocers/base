package co.bugu.domain.test;

import co.bugu.annotation.Id;
import co.bugu.annotation.Table;

/**
 * Created by daocers on 2016/9/29.
 */
@Table
public class Attach {
    @Id
    private Integer id;
    private String picUrl;
    private String propInfo;
    private String propId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getPropInfo() {
        return propInfo;
    }

    public void setPropInfo(String propInfo) {
        this.propInfo = propInfo;
    }

    public String getPropId() {
        return propId;
    }

    public void setPropId(String propId) {
        this.propId = propId;
    }
}
