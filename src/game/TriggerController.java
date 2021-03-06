package game;

import compilation.Enigme;
import compilation.Epreuve;
import game.State.CodeState;
import game.State.MapState;
import game.State.StateGame;
import game.model.Map;
import game.model.Player;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import java.awt.*;
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

    public int checkForTheEnd(){
        int count =0;
        ArrayList<Epreuve> epreuves =map.getEpreuves();
    for( Epreuve epreuve : epreuves ){
        if ( epreuve!=null&&!epreuve.is_isSucceed() ){
            count++;
        }
    }
        return count;
    }


    public void update(GameContainer gameContainer,StateBasedGame basedGame,int delta) throws SlickException, IOException {
        if (basedGame.getCurrentStateID()==MapState.ID ) {
            for (int objectID = 0; objectID < map.getObjectCount(); objectID++) {
                if (isInTrigger(objectID)) {
                    if ("Enigme".equals(map.getObjectType(objectID)) && player.isAction()) {
                        launchEnigme(gameContainer, basedGame, map.getObjectProperty(objectID, "ID", ""));
                    }
                    if ("Sortie".equals(map.getObjectType(objectID))) {
                        System.out.println(checkForTheEnd());
                    }
                }
            }
        }
        if(basedGame.getCurrentStateID()==CodeState.ID){
            CodeState codeState = (CodeState)basedGame.getCurrentState();
            if(codeState.isCompiling()) {
                Epreuve epreuve = codeState.getEpreuve();
                epreuve.set_answer(codeState.get_answer());
                epreuve.createClassesToExecute();
                epreuve.writeMainClass();
                ArrayList arrayList = epreuve.tryIt();
                if( arrayList.size()==2){
                    //epreuve.set_isSucceed(false);
                    epreuve.is_errorStack();
                }
                epreuve.set_test(arrayList);
            }
            codeState.setCompiling(false);

        }

    }

    public void enigmeResolved(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        CodeState cs =(CodeState)this.game.getState(CodeState.ID);
        MapState mapState = (MapState)this.game.getState(MapState.ID);
        cs.setActive(false);
        this.game.enterState(MapState.ID);
    }


    public void initMapState(Map map ,Player player){
        this.map = map;
        this.player = player;

    }
    private boolean isInTrigger(int id) {
        return player.getX() > map.getObjectX(id)
                && player.getX() < map.getObjectX(id) + map.getObjectWidth(id)
                && player.getY()-10 > map.getObjectY(id)
                && player.getY()-10 < map.getObjectY(id) + map.getObjectHeight(id);
    }

    private void launchEnigme(GameContainer gameContainer , StateBasedGame basedGame, String objectID) throws SlickException {
        //Input input = gameContainer.getInput();
        //input.clearKeyPressedRecord();

        Epreuve epreuve = map.getEpreuveByID(objectID) ;
        if (!epreuve.is_isSucceed()) {
            CodeState cs = (CodeState) this.game.getState(CodeState.ID);

            MapState mapState = (MapState) this.game.getState(MapState.ID);
            mapState.setOn(false);
            cs.initUI(gameContainer, epreuve);

            this.game.enterState(CodeState.ID);
        }

    }

}