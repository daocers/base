package co.bugu.rpc;

/**
 * Created by user on 2017/5/27.
 */
public interface Constant {
    int ZK_SESSION_TIMEOUT = 5000;
    String ZK_REGISTRY_PATH = "/registry";

    String ZK_DATA_PATH = ZK_REGISTRY_PATH + "/data";
}
