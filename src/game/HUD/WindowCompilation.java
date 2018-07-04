package game.HUD;

import compilation.Enigme;
import game.State.CodeState;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;

import java.util.ArrayList;

public class WindowCompilation {
    private Animation[] animations = new Animation[3];
    private String name ;
    private Image image;
    private Image OK;
    private Image KO;
    private Enigme enigme;
    private ArrayList test;
    public void render(Graphics g) throws SlickException {
        //g.drawImage(KO,300,100);
        //g.drawImage(OK,350,100);
        //Font font = new TrueTypeFont(new java.awt.Font(java.awt.Font.SERIF,java.awt.Font.BOLD , 26), false);
        g.scale(3F , 3F);
        //g.translate(1280/2, 720/2);
        g.drawImage(image,470/3F,200/3F);
        g.resetTransform();
        g.setColor(Color.black);
        if(test!=null) {
            boolean[] booleans = (boolean[])test.get(0);
            if (!enigme.get_nameTest1().equals("")) {
                g.drawString("TEST1", 490, 240);
                Image verif;
                if (booleans[0] ) {
                    verif = this.OK;
                } else {
                    verif = this.KO;
                }
                g.drawImage(verif, 570, 240);
            }

            if (!enigme.get_nameTest2().equals("")) {
                g.drawString("TEST2", 490, 300);
                Image verif;
                if (booleans[1]) {
                    verif = this.OK;
                } else {
                    verif = this.KO;
                }
                g.drawImage(verif, 570, 300);
            }

            if (!enigme.get_nameTest2().equals("")) {
                g.drawString("TEST3", 490, 360);
                Image verif;
                if (booleans[2]) {
                    verif = this.OK;
                } else {
                    verif = this.KO;
                }
                g.drawImage(verif, 570, 360);
            }
        }

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
        CodeState codeState = (CodeState)stateBasedGame.getCurrentState();
        this.test = codeState.getEpreuve().get_test();
        this.enigme = codeState.getEpreuve().get_enigme();
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
