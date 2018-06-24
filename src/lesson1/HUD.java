package lesson1;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class HUD {
    private Image playerbars;

    public void init() throws SlickException {
        this.playerbars = new Image("hud/player-bar.png");
    }

    private static final int P_BAR_X = 10;
    private static final int P_BAR_Y = 10;

    public void render(Graphics g) {
        g.resetTransform();
        g.drawImage(this.playerbars, P_BAR_X, P_BAR_Y);
    }
}
