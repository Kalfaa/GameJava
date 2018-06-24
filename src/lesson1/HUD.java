package lesson1;

import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;

public class HUD {
    private ButtonHUD button_compile;

    public void init() throws SlickException {
        SpriteSheet spriteSheet = new SpriteSheet("HUD/UI/preview_164.png", 105, 32);
        button_compile = new ButtonHUD("Test");
        Animation[] animations = new Animation[3];
        animations[0] = loadAnimation(spriteSheet, 1, 2, 2);
        animations[1] = loadAnimation(spriteSheet, 1, 3, 2);
        animations[2] = loadAnimation(spriteSheet, 0, 1, 2);
        button_compile.setAnimations(animations);
    }

    private static final int P_BAR_X = 10;
    private static final int P_BAR_Y = 10;

    public void render(Graphics g) throws SlickException {
        g.resetTransform();
        button_compile.render(g);
    }
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        button_compile.update( gameContainer,  stateBasedGame,i);
    }

    private Animation loadAnimation(SpriteSheet spriteSheet, int startX, int endX, int y) {
        Animation animation = new Animation();
        for (int x = startX; x < endX; x++) {
            animation.addFrame(spriteSheet.getSprite(x, y), 200);
        }
        return animation;
    }
}
