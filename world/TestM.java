package simple2D.world;

import java.util.ArrayList;
import java.util.Collections;

public class TestM extends World {
    private ArrayList<int[]> blocks = new ArrayList<>();

    {
        Collections.synchronizedList(blocks);
        blocks.add(new int[]{10, 10});
        blocks.add(new int[]{20, 5});
        blocks.add(new int[]{7, 9});
    }

    public TestM() {
        this(50, 25);
    }

    public TestM(int x, int y) {
        super(x, y);
    }

    @Override
    public void drawBackground() {
        for(int i = 0; i < blocks.size(); ++i) {
            int[] axis = blocks.get(i);
            getField()[axis[1]][axis[0]] = 'B';
        }
    }

    @Override
    public boolean canPass(int x, int y) {
        for(int i = 0; i < blocks.size(); ++i) {
            int[] axis = blocks.get(i);
            if(axis[0] == x && axis[1] == y) return false;
        }
        
        return true;
    }
}