package co.bugu.spring.envionment;

/**
 * Created by daocers on 2017/6/3.
 */
public class DemoService {
    private String content;

    public DemoService(String content){
        super();
        this.content = content;
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
