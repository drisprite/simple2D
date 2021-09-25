package simple2D.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import simple2D.player.Controller;
import simple2D.player.Doll;
import simple2D.player.Player;
import simple2D.world.TestM;
import simple2D.world.World;

public class Client {
    private static Socket socket = null;
    private static DataInputStream input = null;
    private static DataOutputStream output = null;

    private static World world = null;
    private static Player player = null;

    public Client() {
        
    }

    // ================================================== //

    public static World getWorld() {
        return world;
    }

    public static Player getPlayer() {
        return player;
    }

    // ================================================== //

    public static void start() {
        try {
            System.out.println("wait for connection...");
            socket = new Socket("localhost", 9000);

            input = new DataInputStream(socket.getInputStream());
            output = new DataOutputStream(socket.getOutputStream());
            System.out.println("connected!");

            while(input != null) {
                read();
            }
        } catch(IOException error) {
            error.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch(IOException error) {
                error.printStackTrace();
            } finally {
                socket = null;
            }
        }
    }

    public static void write(String datum) {
        try {
            output.writeUTF(datum);
        } catch(IOException error) {
            error.printStackTrace();
        }
    }

    public static void read() {
        try {
            interpret(input.readUTF());
        } catch(IOException error) {
            error.printStackTrace();
        }
    }

    public static void interpret(String datum) {
        String[] data = datum.split("::");                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      

        if(data[0].equals("change_world")) {
            switch(data[1]) {
                case "TestM": {
                    world = new TestM();
                } break;
            }
        } else if(data[0].indexOf("add_player") != -1) {
            Doll doll = new Doll();
            Controller controller = new Controller(doll, world);

            Player player = new Player(Integer.parseInt(data[1]));
            player.setController(controller);
            
            world.add(player);
        } else if(data[0].equals("add_me")) {
            player = world.find(Integer.parseInt(data[1]));
            player.getController().popup();
        } else if(data[0].equals("remove_player")) {
            world.remove(Integer.parseInt(data[1]));
        } else if(data[0].equals("move")) {
            Doll doll = world.find(Integer.parseInt(data[1])).getController().getDoll();

            doll.setX(Integer.parseInt(data[2]));
            doll.setY(Integer.parseInt(data[3]));
        }
    }

    public static void main(String[] args) {
        new Thread() {
            public void run() {
                Client.start();
            }
        }.start();

        new Thread() {
            public void run() {
                try {
                    while(true) {
                        Thread.sleep(10);
                        
                        if(Client.world != null) {
                            Client.world.update();
                            Client.world.draw();
                        }
                    }
                } catch(InterruptedException error) {
                    error.printStackTrace();
                }
            }
        }.start();
    }
}