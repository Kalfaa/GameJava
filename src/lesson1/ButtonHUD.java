package lesson1;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;

public class ButtonHUD {
    private boolean clicked = false;
    private Animation[] animations = new Animation[3];
    int x = 120;
    int y = 660;
    int last_anim;
    private String name;


    public void render(Graphics g) throws SlickException {
        g.drawAnimation(animations[clicked ? 1:0],x,y);
        g.drawString(name,x+25,y+7);
    }
    public ButtonHUD(String name){
        this.name = name ;
    }

    public Animation[] getAnimations() {
        return animations;
    }

    public void setAnimations(Animation[] animations) {
        this.animations = animations;
    }

    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame,int i) throws SlickException {
        if(clicked && i - last_anim > 100){
            this.clicked = false;
        }
        this.animations[0].update(i);
        int posX = Mouse.getX();
        int posY = (int)(720*(1-(float)Mouse.getY()/720));
        if(Mouse.isButtonDown(0)){
        if((posX> this.x && posX <this.animations[0].getWidth()+this.x)&&(posY>this.y && this.animations[0].getHeight()+this.y>posY)) {
            if (Mouse.isButtonDown(0)) {
                clicked = true ;
                this.last_anim = i ;
            }
        }
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}