package game.HUD;

import org.newdawn.slick.*;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.StateBasedGame;

import java.util.ArrayList;
import java.util.List;

public class HUD {
    private ButtonHUD button_compile;
    private List<TextField> _textFieldList ;
    private WindowCompilation _windowCompilation;
    public void init() throws SlickException {
        SpriteSheet spriteSheet = new SpriteSheet("HUD/UI/preview_164.png", 105, 32);
        button_compile = new ButtonHUD("Test");
        Animation[] animations = new Animation[3];
        animations[0] = loadAnimation(spriteSheet, 1, 2, 2);
        animations[1] = loadAnimation(spriteSheet, 1, 3, 2);
        animations[2] = loadAnimation(spriteSheet, 0, 1, 2);
        button_compile.setAnimations(animations);
        _textFieldList = new ArrayList<TextField>();
        _windowCompilation = new WindowCompilation("test");
        _windowCompilation.setImage(spriteSheet.getSubImage(638,195,96,89));
        _windowCompilation.setOK(spriteSheet.getSubImage(58,280,18,18));
        _windowCompilation.setKO(spriteSheet.getSubImage(83,330,18,18));
    }

    private static final int P_BAR_X = 10;
    private static final int P_BAR_Y = 10;

    public void render(GameContainer gameContainer , Graphics g,boolean isCompiling) throws SlickException {
        g.resetTransform();
        button_compile.render(g);
        for(TextField textField : _textFieldList){
            g.resetTransform();
            textField.render(gameContainer,g);
        }
        if(isCompiling) {
            _windowCompilation.render(g);
        }

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
    public List<TextField> get_textFieldList() {
        return _textFieldList;
    }

    public void set_textFieldList(List<TextField> _textFieldList) {
        this._textFieldList = _textFieldList;
    }
    public  void add_textFieldList(TextField textField){
        this._textFieldList.add(textField);
    }
}