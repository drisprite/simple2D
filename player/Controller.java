package simple2D.player;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

import simple2D.world.World;

public class Controller {
    private boolean isHost = false;
    private Player player = null;
    private World world = null;

    public Controller() { }

    public Controller(Player player) {
        this.player = player;
    }

    public void setIsHost(boolean isHost) {
        this.isHost = isHost;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setWorld(World world) {
        this.world = world;
        
    }

    public void popOut() {
        if(isHost) {
            JFrame controller = new JFrame();
            controller.setSize(500, 100);
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
    }

    public void action(KeyEvent event) {
        char c = event.getKeyChar();

        switch(c) {
            case 'W': case 'w': {
                move(player.getPawn().getX(), player.getPawn().getY() - 1);
            } break;

            case 'S': case 's': {
                move(player.getPawn().getX(), player.getPawn().getY() + 1);
            } break;

            case 'A': case 'a': {
                move(player.getPawn().getX() - 1, player.getPawn().getY());
            } break;

            case 'D': case 'd': {
                move(player.getPawn().getX() + 1, player.getPawn().getY());
            } break;
        }
    }

    public void move(int x, int y) {
        if(world == null) return;

        if(x > world.getX()) x = world.getX();
        else if(x < 1) x = 1;

        if(y > world.getY()) y = world.getY();
        else if(y < 1) y = 1;

        Pawn pawn = player.getPawn();
        pawn.setX(x);
        pawn.setY(y);
    }
}
