package simple2D.player;

import java.net.Socket;

public class Player {
    private Controller controller = null;
    private int UID = 0;

    private Socket socket = null;

    {
        UID = (int)(Math.random() * 100000);
    }

    public Player() { }

    public Player(Socket socket) {
        this.socket = socket;
    }

    public Player(int UID) {
        this.UID = UID;
    }

    public Player(Socket socket, int UID) {
        this.socket = socket;
        this.UID = UID;
    }

    // ================================================== //

    public Controller getController() {
        return controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public int getUID() {
        return UID;
    }

    public void setUID(int UID) {
        this.UID = UID;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }
}