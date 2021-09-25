package simple2D.player;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

import simple2D.server.Client;
import simple2D.world.World;

public class Controller {    
    private Doll doll = null;
    private World world = null;

    public Controller() { }

    public Controller(Doll doll) {
        this.doll = doll;
    }

    public Controller(Doll doll, World world) {
        this.doll = doll;
        this.world = world;
    }

    // ================================================== //

    public Doll getDoll() {
        return doll;
    }

    public void setDoll(Doll doll) {
        this.doll = doll;
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    // ================================================== //

    public void popup() {
        JFrame controller = new JFrame();
        controller.setSize(500, 500);
        controller.setLayout(null);

        class Key implements KeyListener {
            @Override
            public void keyPressed(KeyEvent event) {
                action(event);
            }

            @Override
            public void keyTyped(KeyEvent event) { }

            @Override
            public void keyReleased(KeyEvent event) { }
        };

        controller.setVisible(true);
        controller.addKeyListener(new Key());
    }

    private void action(KeyEvent event) {
        char c = event.getKeyChar();

        switch(c) {
            case 'W': case 'w': {
                move(doll.getX(), doll.getY() - 1);
            } break;

            case 'S': case 's': {
                move(doll.getX(), doll.getY() + 1);
            } break;

            case 'A': case 'a': {
                move(doll.getX() - 1, doll.getY());
            } break;

            case 'D': case 'd': {
                move(doll.getX() + 1, doll.getY());
            } break;
        }
    }

    public void move(int x, int y) {
        if(doll != null && world != null) {
            if(x >= world.getWorldX()) x = world.getWorldX() - 1;
            else if(x < 0) x = 0;

            if(y >= world.getWorldY()) y = world.getWorldY() - 1;
            else if(y < 0) y = 0;

            if(world.canPass(x, y)) {
                // doll.setX(x);
                // doll.setY(y);

                Client.write("move::" + Client.getPlayer().getUID() + "::" + x + "::" + y);
            }
        }
    }
}
