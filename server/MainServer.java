package simple2D.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Collections;
import java.util.HashMap;

import simple2D.player.Player;

public class MainServer {
    private static String ip = "127.0.0.1";
    private static int port = 9000;
    
    private ServerSocket serverSocket = null;
    private HashMap<Integer, Player> clients = new HashMap<>();

    {
        Collections.synchronizedMap(clients);
    }

    public void start() {
        try {
            serverSocket = new ServerSocket();
        } catch(IOException error) {
            error.printStackTrace();
        }
    }
}
