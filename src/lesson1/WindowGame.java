package lesson1;

import org.newdawn.slick.*;
import org.newdawn.slick.tiled.TiledMap;

public class WindowGame extends BasicGame {
    GameContainer container;
    TiledMap map;

    public WindowGame() {
        super("Lesson 1 :: WindowGame");
    }

    @Override
    public void init(GameContainer container) throws SlickException {
        this.container = container;
        this.map = new TiledMap("Map/map.tmx");
    }

    @Override
    public void render(GameContainer container, Graphics g) throws SlickException {
        this.map.render(0, 0);
    }

    @Override
    public void update(GameContainer container, int delta) throws SlickException {

    }

    public static void main(String[] args) throws SlickException {
        new AppGameContainer(new WindowGame(), 640, 480, false).start();
    }

    public void keyReleased(int key, char c) {
        if (Input.KEY_ESCAPE == key) {
            container.exit();
        }

    }

}