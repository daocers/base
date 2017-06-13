package co.bugu.tes.annotation;

/**
 * Created by user on 2017/6/13.
 */
public class MenuInfo {
    private Integer id;
    private String name;
    private Boolean isView;
    private Boolean isBox;
    private String action;
    private String url;

    private String rootPath;
    private String path;
    private Boolean isApi;
    private String method;
    private String controller;

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

    public Boolean getView() {
        return isView;
    }

    public void setView(Boolean view) {
        isView = view;
    }

    public Boolean getBox() {
        return isBox;
    }

    public void setBox(Boolean box) {
        isBox = box;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRootPath() {
        return rootPath;
    }

    public void setRootPath(String rootPath) {
        this.rootPath = rootPath;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Boolean getApi() {
        return isApi;
    }

    public void setApi(Boolean api) {
        isApi = api;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getController() {
        return controller;
    }

    public void setController(String controller) {
        this.controller = controller;
    }
}
