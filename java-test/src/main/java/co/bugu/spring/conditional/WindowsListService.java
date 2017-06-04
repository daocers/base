package co.bugu.spring.conditional;

/**
 * Created by daocers on 2017/6/4.
 */
public class WindowsListService implements ListService {
    @Override
    public String showListCmd() {
        return "dir";
    }
}
