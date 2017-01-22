package co.bugu.tes.model;

import java.util.Date;

public class PaperPolicy {
    private Integer id;

    private Integer branchId;

    private String code;

    private String questionMetaInfoId;

    private String content;

    private Date createTime;

    private Integer createUserId;

    private Integer departmentId;

    private String name;

    private Integer stationId;

    private Integer status;

    private Date updateTime;

    /**
     * 保密类型， 0 公开， 1 保密
     */
    private Integer privaryType;


    private Integer updateUserId;

    /**
     * 是否百分制
     */
    private Integer percentable;

    public Integer getPercentable() {
        return percentable;
    }

    public void setPercentable(Integer percentable) {
        this.percentable = percentable;
    }

    public Integer getPrivaryType() {
        return privaryType;
    }

    public void setPrivaryType(Integer privaryType) {
        this.privaryType = privaryType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public String getQuestionMetaInfoId() {
        return questionMetaInfoId;
    }

    public void setQuestionMetaInfoId(String questionMetaInfoId) {
        this.questionMetaInfoId = questionMetaInfoId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBranchId() {
        return branchId;
    }

    public void setBranchId(Integer branchId) {
        this.branchId = branchId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStationId() {
        return stationId;
    }

    public void setStationId(Integer stationId) {
        this.stationId = stationId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Integer updateUserId) {
        this.updateUserId = updateUserId;
    }
}