package simple2D.world;

import java.util.Collections;
import java.util.HashMap;

import simple2D.ClearConsole;
import simple2D.player.Pawn;
import simple2D.player.Player;

public class World {
    private int x, y;
    private char[][] geometry = null;

    private HashMap<Integer, Player> list = new HashMap<>();

    {
        Collections.synchronizedMap(list);
    }

    public World() {
        this(0, 0);
    }

    public World(int x, int y) {
        setSize(x, y);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setSize(int x, int y) {
        this.x = x;
        this.y = y;

        geometry = new char[y + 2][x + 2];
        initialize();
    }

    public void initialize() {
        for(char[] c: geometry) {
            for(int i = 0; i < x + 2; ++i) {
                c[i] = ' ';
            }
        }

        for(char[] c: geometry) {
            c[0] = '|';
            c[x + 1] = '|';
        }

        for(int i = 0; i < x + 2; ++i) {
            geometry[0][i] = '-';
            geometry[y + 1][i] = '-';
        }
    }

    public void add(Player player) {
        list.put(player.getUID(), player);
    }

    public void remove(int UID) {
        list.remove(UID);
    }

    public void remove(Player player) {
        list.remove(player.getUID());
    }

    public void update() {
        for(Player player: list.values()) {
            Pawn pawn = player.getPawn();
            
            geometry[pawn.getBY()][pawn.getBX()] = ' ';
        }

        for(Player player: list.values()) {
            Pawn pawn = player.getPawn();
            
            geometry[pawn.getY()][pawn.getX()] = player.getInitial();
        }
    }

    public void draw() {
        ClearConsole.clear();

        for(char[] c: geometry) {
            System.out.println(c);
        }
    }
}
