package lesson1;

import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;

public class MapState extends BasicGameState {
    public static final int ID = 1;
    private GameContainer container;
    private Map map = new Map();
    private Player player = new Player(map);
    private StateBasedGame game ;
    PlayerController playerController;
    // Les objets sont crées, il nous faut encore les initialiser, et pour cela on va compléter la méthode «  init() ». Un tableau de sprite est représenté par la classe org.newdawn.slick.SpriteSheet, il suffit d'instancier cette classe en lui donnant en argument le nom du fichier et les dimensions des cellules soit 64x64 dans mon cas.



    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        this.container = container;
        this.map.init();
        this.player.init();
        PlayerController playerController = new PlayerController(this.player);
        container.getInput().addKeyListener(playerController);
        this.game = game;
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        this.map.renderBackground();
        this.player.render(g);
        this.map.renderForeground();
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        this.player.update(delta);
        }

    public void keyPressed(int key, char c) {
        switch (key) {
            case Input.KEY_P:
                game.enterState(CodeState.ID);
                break;
        }
    }


    public int getID() {
        return ID;
    }



}