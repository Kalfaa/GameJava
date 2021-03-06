package game.HUD;

import compilation.Enigme;
import compilation.Epreuve;
import game.State.MapState;
import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class HUDMapState {
    private Image image;
    private Image enigmeBlocImage;
    private Image OK;
    private Image KO;
    private MapState mapState;
    private ButtonQuit button_quit;
    public void init(GameContainer gameContainer, MapState map) throws SlickException {
        SpriteSheet windowscomp = new SpriteSheet("HUD/UI/MapStateHUDBASE.png",10,10);
        SpriteSheet enigmeWindows = new SpriteSheet("HUD/UI/GreyBlock.png",10,10);
        this.enigmeBlocImage = enigmeWindows.getSubImage(688,48,337,240);
        this.image =windowscomp.getSubImage(607,0,672,720);
        mapState = map;
        SpriteSheet spriteSheet = new SpriteSheet("HUD/UI/preview_164.png", 105, 32);
        button_quit = new ButtonQuit("Quit",730,660);

        Animation[] animations = new Animation[3];
        animations[0] = loadAnimation(spriteSheet, 1, 2, 2);
        animations[1] = loadAnimation(spriteSheet, 1, 3, 2);
        animations[2] = loadAnimation(spriteSheet, 0, 1, 2);
        button_quit.setAnimations(animations);
        spriteSheet = new SpriteSheet("HUD/UI/preview_164.png", 105, 32);
        OK = spriteSheet.getSubImage(58,280,18,18);
        KO = spriteSheet.getSubImage(83,330,18,18);
    }


    public void render(GameContainer gameContainer , Graphics g ) throws SlickException {
        g.drawImage(this.image,607,0);
        g.drawImage(this.enigmeBlocImage,688,48);
        ArrayList<Epreuve> epreuves = mapState.getMap().getEpreuves();
        for(int i = 0;i<epreuves.size();i++){
            g.drawString("Enigme " +Integer.toString(i),730,70+(i*50));
            g.drawImage(epreuves.get(i).is_isSucceed()? OK:KO,860,70+(i*50));
        }
        button_quit.render(g);
    }


    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        button_quit.update(gameContainer,stateBasedGame,i);
    }

    private Animation loadAnimation(SpriteSheet spriteSheet, int startX, int endX, int y) {
        Animation animation = new Animation();
        for (int x = startX; x < endX; x++) {
            animation.addFrame(spriteSheet.getSprite(x, y), 200);
        }
        return animation;
    }


}
