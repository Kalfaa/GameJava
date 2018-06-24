package lesson1;

import org.newdawn.slick.*;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class CodeState extends BasicGameState {
    public static final int ID = 2;
    private HUD hud = new HUD();
    TextField textField;
    TrueTypeFont font;
    Image background;
    private int time =0 ;
    @Override
    public int getID() {
        return ID;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        this.hud.init();
        this.background = new Image("HUD/UI/codestatebg.jpg");
        font = new TrueTypeFont(new java.awt.Font(java.awt.Font.SERIF,java.awt.Font.BOLD , 26), false);
        textField = new CustomTextField(gameContainer, font, 0, 0, 640, 635);
        textField.setBackgroundColor(Color.white);
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        background.draw(0, 0, gameContainer.getWidth(), gameContainer.getHeight());
        textField.render(gameContainer, graphics);
        this.hud.render(graphics);
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        time +=i ;
        hud.update(gameContainer,stateBasedGame,time);
    }
}
