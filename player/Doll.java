package simple2D.player;

import simple2D.player.character.Character;

public class Doll {
    private Character character = null;
    private int x = 0, y = 0;
    private int bx = 0, by = 0;

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        bx = this.x;
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        by = this.y;
        this.y = y;
    }

    public int getBX() {
        return bx;
    }

    public int getBY() {
        return by;
    }
}
