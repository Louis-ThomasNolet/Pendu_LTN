package ca.csf.tp1.views;

import android.os.Bundle;

import java.util.List;

import ca.csf.tp1.enumerations.GameStatus;

public class HangmanViewMock implements HangmanView {
    private String discoveredWord;
    private GameStatus gameStatus;
    private List<Character> usedLetters;
    private String wordToFind;
    private int nbErrors;
    private Bundle savedInstanceState;

    @Override
    public void update() {

    }

    public HangmanViewMock(){

    }

    public boolean isUpdateCalled(){
        update();
        return true;
    }
}
