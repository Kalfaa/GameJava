package game.State;

import compilation.Enigme;
import compilation.Epreuve;
import game.TriggerController;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import java.io.IOException;

class StateGame extends StateBasedGame {
    private static TriggerController triggerController;
    public StateGame() {
        super("Lesson 15 :: StateGame");
        triggerController = new TriggerController(this);
        this.addState(new MapState());
        this.addState(new CodeState());
    }

    public static TriggerController getTriggerController() {
        return triggerController;
    }

    public static void setTriggerController(TriggerController triggerController) {
        StateGame.triggerController = triggerController;
    }

    /**
     * Ici il suffit d'ajouter nos deux boucles de jeux.
     * La première ajoutèe sera celle qui sera utilisée au début
     */
    @Override
    public void initStatesList(GameContainer container) throws SlickException {


        this.getState(CodeState.ID).init(container,this);
        //CodeState codeState =(CodeState) this.getState(CodeState.ID);

        //this.enterState(MapState.ID);
        //codeState.initUI(container);

        Enigme enigme = null;
        try {
            enigme = Enigme.buildEnigmeFromJson("7486");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Epreuve epreuve = new Epreuve(enigme);
        CodeState cs =(CodeState)this.getState(CodeState.ID);
        MapState mapState = (MapState)this.getState(MapState.ID);
        mapState.setOn(false);
        cs.initUI(container,epreuve);
        this.enterState(CodeState.ID);

    }

    public static void main(String[] args) throws SlickException {
        int maxFPS = 60;
        AppGameContainer app = new AppGameContainer(new StateGame(), 1280, 720, false);
        app.setTargetFrameRate(maxFPS);
        app.start();
    }
}