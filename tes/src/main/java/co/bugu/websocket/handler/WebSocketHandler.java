package co.bugu.websocket.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by user on 2017/3/23.
 */
public class WebSocketHandler extends TextWebSocketHandler {
    private Logger logger = LoggerFactory.getLogger(WebSocketHandler.class);
    private static final ArrayList<WebSocketSession> users = new ArrayList<WebSocketSession>();;


    //建立连接后执行
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println("ConnectionEstablished");
        logger.debug("ConnectionEstablished");
        users.add(session);

        session.sendMessage(new TextMessage("connect"));
        session.sendMessage(new TextMessage("new_msg"));

    }

    /**
     * 处理消息
     * @param session
     * @param message
     * @throws Exception
     */
    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        String res = "";
        System.out.println("handleMessage" + message.toString());
        logger.debug("handleMessage" + message.toString());
        String content = message.getPayload().toString();
        Integer length = message.getPayloadLength();
        if("1".equals(content)){
            res = "age";
        }else{
            res = "time";
        }
        //sendMessageToUsers();  
        session.sendMessage(new TextMessage(new Date().toString() + " " + res));
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        if(session.isOpen()){
            session.close();
        }
        users.remove(session);

        logger.debug("handleTransportError" + exception.getMessage());
    }

    /**
     * 连接关闭后执行
     * @param session
     * @param closeStatus
     * @throws Exception
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        users.remove(session);
        logger.debug("afterConnectionClosed" + closeStatus.getReason());

    }

    /**
     * 支持部分信息
     * @return
     */
    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

    /**
     * 给所有在线用户发送消息 
     *
     * @param message
     */
    public void sendMessageToUsers(TextMessage message) {
        for (WebSocketSession user : users) {
            try {
                if (user.isOpen()) {
                    user.sendMessage(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
}
