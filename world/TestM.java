package simple2D.world;

public class TestM extends World {
    public TestM() {
        this(50, 25);
    }

    public TestM(int x, int y) {
        super(x, y);
    }

    @Override
    public void drawBackground() { }

    @Override
    public boolean canPass(int x, int y) {
        return true;
    }
}
