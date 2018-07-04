package game;

import compilation.Enigme;
import compilation.Epreuve;
import game.State.CodeState;
import game.State.MapState;
import game.model.Map;
import game.model.Player;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import java.io.IOException;
import java.util.ArrayList;

public class TriggerController {
    private Map map;
    private Player player;
    private StateBasedGame game;
    public TriggerController(StateBasedGame game) {
        this.game = game;
    }
    private int time ;


    public void update(GameContainer gameContainer,StateBasedGame basedGame,int delta) throws SlickException, IOException {
        if (basedGame.getCurrentStateID()==MapState.ID) {
            for (int objectID = 0; objectID < map.getObjectCount(); objectID++) {
                if (isInTrigger(objectID)) {
                    if ("Enigme".equals(map.getObjectType(objectID))) {

                        launchEnigme(gameContainer, basedGame, map.getObjectProperty(objectID, "ID", ""));
                    }
                }
            }
        }
        if(basedGame.getCurrentStateID()==CodeState.ID){
            CodeState codeState = (CodeState)basedGame.getCurrentState();
            if(codeState.isCompiling()) {
                Epreuve epreuve = codeState.getEpreuve();
                epreuve.set_answer(codeState.get_answer());
                ArrayList arrayList = epreuve.tryIt();
                epreuve.set_test(arrayList);
            }
            codeState.setCompiling(false);

        }

    }

    public void initMapState(Map map ,Player player){
        this.map = map;
        this.player = player;

    }
    //public void

    public void initCodeState(){

    }
    private boolean isInTrigger(int id) {
        return player.getX() > map.getObjectX(id)
                && player.getX() < map.getObjectX(id) + map.getObjectWidth(id)
                && player.getY() > map.getObjectY(id)
                && player.getY() < map.getObjectY(id) + map.getObjectHeight(id);
    }

    private void launchEnigme(GameContainer gameContainer , StateBasedGame basedGame, String objectID) throws SlickException, IOException {
        //Input input = gameContainer.getInput();
        //input.clearKeyPressedRecord();
        Enigme enigme = Enigme.buildEnigmeFromJson(objectID);
        Epreuve epreuve = new Epreuve(enigme);
        CodeState cs =(CodeState)this.game.getState(CodeState.ID);
        MapState mapState = (MapState)this.game.getState(MapState.ID);
        mapState.setOn(false);
        cs.initUI(gameContainer,epreuve);

        this.game.enterState(CodeState.ID);

    }

}