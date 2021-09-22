package simple2D.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;

import simple2D.player.Player;

public class MainServer {
    private static String ip = "127.0.0.1";
    private static int port = 9000;
    
    private ServerSocket serverSocket = null;
    private HashMap<Integer, DataOutputStream> clients = new HashMap<>();

    {
        Collections.synchronizedMap(clients);
    }
    
    public static void main(String[] args) {
        new MainServer().start();
    }

    public void start() {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server Opened.");
            
            while(serverSocket != null) {
                System.out.println("Waiting for client...");
                
                Socket socket = serverSocket.accept();
                System.out.println("Found ya!");
                
                try {
                    Receiver r = new Receiver(socket);
                    r.start();
                } catch(IOException error) {
                    error.printStackTrace();
                }
            }    
        } catch(IOException error) {
            error.printStackTrace();
            
            serverSocket.close();
            serverSocket = null;
        }
    }
    
    public void sendData(String data) {
        Iterator iterator = clients.keySet().iterator();
        
        while(iterator.hasNext()) {
            try {
                DataOutputStream output = (DataOutputStream)clients.get(iterator.next());
                output.writeUTF(data);
            } catch(IOException error) {
                error.printStackTrace();
            }
        }
    }
    
    class Receiver extends Thread {
        private Socket socket = null;
        
        private DataInputStream input = null;
        private DataOutputStream output = null;
        
        Receiver(Socket socket) throws IOExecption {
            this.socket = socket;
            
            input = new DataInputStream(socket.getInputStream());
            output = new DataOutputStream(socket.getOutputStream());
        }
        
        public void run() {
            try {
                int UID = input.readInt();
                clients.put(UID, output);
                
                Iterator iterator = clients.keySet().iterator();
                
                while(iterator.hasNext()) {
                    if(clients.size() > 1) {
                        DataOutputStream output = (DataOutputStresm)clients.get(UID);
                        output.writeUTF("add_player:" + iterator.next());
                    } else break;
                }
                
                while(input != null) {
                    sendData(input.readUTF());
                }
            } catch(IOException error) {
                error.printStackTrace();
            } finally {
                clients.remove(UID);
                sendData("remove_player:" + UID);

                System.out.println(UID + " left the server.");
            }
         }
    }
}
