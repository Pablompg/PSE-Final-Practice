
package com.pablo.pse5.chat;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/websocket")
public class ChatServer {
    private static final Set<Session> peers = Collections.synchronizedSet(new HashSet<Session>());
    
    @OnOpen
    public void onOpen(Session peer){
        peers.add(peer);
    }
    
    @OnClose
    public void onOClose(Session peer){
        peers.remove(peer);
    }
    
    @OnMessage
    public void message(String message, Session client) throws IOException, EncodeException{
        for (Session peer: peers) {
            peer.getBasicRemote().sendText(message);
        }
    }
}
