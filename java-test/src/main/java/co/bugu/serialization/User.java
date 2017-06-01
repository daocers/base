package co.bugu.serialization;

/**
 * Created by user on 2017/6/1.
 */
public class User {
    private String name;
    private String password;

    private Long age;

    public User(){

    }

    public User(String name, String password, Long age) {
        this.name = name;
        this.password = password;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }
}
