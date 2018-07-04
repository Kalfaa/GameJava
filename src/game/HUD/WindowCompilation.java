package game.HUD;

import game.State.CodeState;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;

public class WindowCompilation {
    private Animation[] animations = new Animation[3];
    private String name ;
    private Image image;
    private Image OK;
    private Image KO;
    public void render(Graphics g) throws SlickException {
        //g.drawImage(KO,300,100);
        //g.drawImage(OK,350,100);
        g.scale(3F , 3F);
        //g.translate(1280/2, 720/2);
        g.drawImage(image,470/3F,200/3F);
        //g.drawString(name,x+25,y+7);
    }
    public WindowCompilation(String name){
        this.name = name ;
    }

    public Animation[] getAnimations() {
        return animations;
    }

    public void setAnimations(Animation[] animations) {
        this.animations = animations;
    }

    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Image getOK() {
        return OK;
    }

    public void setOK(Image OK) {
        this.OK = OK;
    }

    public Image getKO() {
        return KO;
    }

    public void setKO(Image KO) {
        this.KO = KO;
    }
}
