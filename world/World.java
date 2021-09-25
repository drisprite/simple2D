package simple2D.world;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

import simple2D.Console;
import simple2D.player.Doll;
import simple2D.player.Player;

public abstract class World {
    private final HashMap<Integer, Player> players = new HashMap<>();
    private char[][] field = null;

    {
        Collections.synchronizedMap(players);
    }

    public World(int x, int y) {
        field = new char[y][x];

        for(char[] f: field) {
            for(int i = 0; i < x; ++i) {
                f[i] = ' ';
            }
        } // initialize the map.
    }

    // ================================================== //

    public HashMap<Integer, Player> getPlayers() {
        return players;
    }

    public char[][] getField() {
        return field;
    }

    // ================================================== //

    public int getWorldX() {
        return field[0].length;
    }

    public int getWorldY() {
        return field.length;
    }

    public void add(Player player) {
        players.put(player.getUID(), player);
    }

    public void remove(Player player) {
        players.remove(player.getUID());
    }

    public void remove(int UID) {
        Doll doll = players.get(UID).getController().getDoll();
        field[doll.getY()][doll.getX()] = ' ';
        players.remove(UID);
    }

    public Player find(int UID) {
        return players.get(UID);
    }

    public void update() {
        Iterator<Player> list = players.values().iterator();
        
        while(list.hasNext()) {
            Doll doll = list.next().getController().getDoll();
            field[doll.getBY()][doll.getBX()] = ' ';
            field[doll.getY()][doll.getX()] = 'O';
        }
    }

    public abstract void drawBackground();

    public void draw() {
        Console.clear();
        
        for(int i = 0; i < field[0].length; ++i)
            System.out.print("-"); // outline
        System.out.println();

        drawBackground();

        for(int i = 0; i < field.length; ++i) {
            System.out.print("|"); // outline
            System.out.print(field[i]);
            System.out.print("|\n"); // outline
        }

        for(int i = 0; i < field[0].length; ++i)
            System.out.print("-"); // outline
    }

    public abstract boolean canPass(int x, int y);
}