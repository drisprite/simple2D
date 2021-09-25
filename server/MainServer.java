package simple2D.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import simple2D.player.Controller;
import simple2D.player.Doll;
import simple2D.player.Player;
import simple2D.world.TestM;
import simple2D.world.World;

public class MainServer {
    private static ServerSocket serverSocket = null;
    private static ArrayList<OutputStream> clients = new ArrayList<>();

    private static World world = null;

    {
        Collections.synchronizedList(clients);
    }

    public MainServer(int port) {
        world = new TestM();

        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server is ready.");

            while(serverSocket != null) {
                System.out.println("Wait for connection...");
                Socket socket = serverSocket.accept();
                System.out.println("Found a client. (" + socket.getInetAddress() + "/" + socket.getPort() + ")");

                new Receiver(socket).start();
            }
        } catch(IOException error) {
            // error.printStackTrace();
        } finally {
            try {
                serverSocket.close();
            } catch(IOException error) {
                error.printStackTrace();
            } finally {
                serverSocket = null;
                System.out.println("Server closed.");
            }
        }
    }

    // ================================================== //

    public static World getWorld() {
        return world;
    }

    public static void setWorld(World world) {
        MainServer.world = world;
    }

    // ================================================== //

    public static void sendToAll(String data) {
        for(int i = 0; i < clients.size(); ++i) {
            try {
                DataOutputStream output = new DataOutputStream(clients.get(i));
                output.writeUTF(data);
            } catch(IOException error) {
                // error.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new MainServer(9000);
    }

    class Receiver extends Thread {
        private Socket socket = null;

        public Receiver(Socket socket) {
            this.socket = socket;
        }

        // ================================================== //

        public void run() {
            Player player = null;

            try {
                DataInputStream input = new DataInputStream(socket.getInputStream());
                DataOutputStream output = new DataOutputStream(socket.getOutputStream());

                output.writeUTF("change_world::TestM");
                
                Iterator<Integer> iterator = world.getPlayers().keySet().iterator();
                while(iterator.hasNext()) {
                    output.writeUTF("add_player::" + iterator.next());
                }

                Doll doll = new Doll();
                Controller controller = new Controller(doll, world);
                
                player = new Player(socket);
                player.setController(controller);

                world.add(player);
                clients.add(socket.getOutputStream());

                sendToAll("add_player::" + player.getUID());
                output.writeUTF("add_me::" + player.getUID());

                while(true) {
                    interpret(input.readUTF());
                }
            } catch(IOException error) {
                // error.printStackTrace();
            } finally {
                try {
                    socket.close();
                } catch(IOException error) {
                    // error.printStackTrace();
                } finally {
                    try {
                        clients.remove(socket.getOutputStream());
                        world.remove(player);
                    } catch(IOException error) {
                        // error.printStackTrace();
                    } finally {
                        sendToAll("remove_player::" + player.getUID());
                        socket = null;

                        System.out.println(player.getUID() + " left the server.");
                    }
                }
            }
        }
    }

    public static void interpret(String datum) {
        String[] data = datum.split("::");                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      

        if(data[0].equals("move")) {
            Player player = world.find(Integer.parseInt(data[1]));
            Doll doll = player.getController().getDoll();

            doll.setX(Integer.parseInt(data[2]));
            doll.setY(Integer.parseInt(data[3]));

            sendToAll("move::" + player.getUID() + "::" + doll.getX() + "::" + doll.getY());
        }
    }
}