package simple2D.player;

import simple2D.player.character.Character;

public class Pawn {
    private int x = 1, y = 1;
    private int bx = 1, by = 1;
    private Character character = null;

    public Pawn() { }

    public Pawn(Character character) {
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

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }
}
