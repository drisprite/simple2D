package simple2D;

import java.io.IOException;

public class Console {
    public static final void clear() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch(IOException | InterruptedException error) {
            error.printStackTrace();
        }
    }
}
