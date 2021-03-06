package game.State;

import compilation.Epreuve;
import game.HUD.CustomTextField;
import game.HUD.HUDCodeState;
import game.HUD.WindowCompilation;
import org.newdawn.slick.*;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.io.IOException;
import java.util.List;

public class CodeState extends BasicGameState {
    public static final int ID = 2;
    private HUDCodeState hud ;
    private WindowCompilation windowCompilation;
    private CustomTextField textField;
    private CustomTextField textArea;
    private TrueTypeFont font;
    private Image background;
    private Epreuve epreuve;
    private int time =0 ;
    private boolean isCompiling;

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    private boolean active;

    public boolean isResultCompile() {
        return resultCompile;
    }

    public void setResultCompile(boolean resultCompile) {
        this.resultCompile = resultCompile;
    }

    private boolean resultCompile;
    @Override
    public int getID() {
        return ID;
    }

    public void leave(GameContainer gameContainer, StateBasedGame stateBasedGame){
        textField.nullify();
        textArea.nullify();

    }
    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) {

        //initUI(gameContainer);
    }
    public void initUI(GameContainer gameContainer, Epreuve epreuve) throws SlickException {
        this.epreuve =epreuve;
        hud = new HUDCodeState();
        this.hud.init(gameContainer);
        this.background = new Image("HUD/UI/codestatebg.jpg");
        isCompiling = false;
        font = new TrueTypeFont(new java.awt.Font(java.awt.Font.SERIF,java.awt.Font.BOLD , 26), false);
        this.textField = new CustomTextField(gameContainer, font, 0, 0, 640, 635);
        this.textArea = new CustomTextField(gameContainer, font, 690, 0, 600, 600);
        this.textArea.setBackgroundColor(Color.black);
        this.textField.setBackgroundColor(Color.black);
        //this.textField.setText("ON EST DANS LE BENDO ILICOO\r ilicoo");
        this.textArea.setText(epreuve.get_enigme().get_enonce());
        this.textArea.setArea(true);
        this.textArea.formatText();
        this.hud.add_textFieldList(textField);
        this.hud.add_textFieldList(textArea);
        this.resultCompile =false;
        this.active = true;
    }

    public void destroyUI(){
        textField.deactivate();
        textArea.deactivate();
        textField = null;
        textArea = null ;
    }
    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {

            background.draw(0, 0, gameContainer.getWidth(), gameContainer.getHeight());
            this.hud.render(gameContainer, graphics, resultCompile);
            StateGame.getSuperHUD().render(gameContainer, graphics);
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        if(!active) {
            textField.deactivate();
            textArea.deactivate();
        }
        StateGame.time +=i;
        time +=i ;

        try {
            StateGame.getTriggerController().update(gameContainer, stateBasedGame, i);
        } catch (IOException e) {
            e.printStackTrace();
        }
        hud.update(gameContainer,stateBasedGame,time);

    }

    public Epreuve getEpreuve() {
        return epreuve;
    }

    public void setEpreuve(Epreuve epreuve) {
        this.epreuve = epreuve;
    }

    public boolean isCompiling() {
        return isCompiling;
    }

    public void setCompiling(boolean compiling) {
        this.isCompiling = compiling;
    }

    public String get_answer(){
        List<TextField> hud_textFieldList= hud.get_textFieldList();
        CustomTextField customTextField = (CustomTextField)hud_textFieldList.get(0);
        return customTextField.getText();
    }
}
