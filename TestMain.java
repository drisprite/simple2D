package simple2D;

import simple2D.player.Controller;
import simple2D.player.Pawn;
import simple2D.player.Player;
import simple2D.world.World;

public class TestMain {
    public static void main(String[] args) {
        World world = new World(50, 50);
        
        Controller controller = new Controller();
        Pawn pawn = new Pawn();
        Player player = new Player(controller, pawn);

        world.add(player);

        controller.setIsHost(true);
        controller.popOut();

        new Thread() {
            public void run() {
                try {
                    while(true) {
                        Thread.sleep(100);
                        
                        world.update();
                        world.draw();
                    }
                } catch(InterruptedException error) { }
            }
        }.start();
    }
}
