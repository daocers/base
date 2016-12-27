package co.bugu.domain.test;

import co.bugu.annotation.Id;
import co.bugu.annotation.Table;

/**
 * Created by daocers on 2016/9/29.
 */
@Table
public class Picture {
    @Id
    private Integer id;
    private String picUrl;
    private Integer idx;

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

    public Integer getIdx() {
        return idx;
    }

    public void setIdx(Integer idx) {
        this.idx = idx;
    }
}
