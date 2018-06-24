package lesson1;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

class StateGame extends StateBasedGame {

    public StateGame() {
        super("Lesson 15 :: StateGame");
    }

    /**
     * Ici il suffit d'ajouter nos deux boucles de jeux.
     * La première ajoutèe sera celle qui sera utilisée au début
     */
    @Override
    public void initStatesList(GameContainer container) throws SlickException {
        addState(new CodeState());
        addState(new MapState());

    }

    public static void main(String[] args) throws SlickException {
        int maxFPS = 60;
        AppGameContainer app = new AppGameContainer(new StateGame(), 1280, 720, false);
        app.setTargetFrameRate(maxFPS);
        app.start();
    }
}