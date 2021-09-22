package simple2D.player;

import simple2D.world.World;

public class Player {
    private int UID = 0;
    private char initial = 'P';

    private Controller controller = null;
    private Pawn pawn = null;
    private World world = null;

    {
        UID = (int)(Math.random() * 1000);
    }

    public Player() { }

    public Player(Controller controller) {
        this.controller = controller;
        
        controller.setPlayer(this);
    }

    public Player(Controller controller, Pawn pawn) {
        this.controller = controller;
        this.pawn = pawn;

        controller.setPlayer(this);
    }

    public int getUID() {
        return UID;
    }

    public char getInitial() {
        return initial;
    }

    public void setInitial(char initial) {
        this.initial = initial;
    }

    public Controller getController() {
        return controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public Pawn getPawn() {
        return pawn;
    }

    public void setPawn(Pawn pawn) {
        this.pawn = pawn;
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
        world.add(this);

        controller.setWorld(world);
    }
}
