package ca.csf.tp1.mc;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.ContentView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import ca.csf.tp1.enumerations.GameStatus;
import ca.csf.tp1.enumerations.Words;
import ca.csf.tp1.views.HangmanView;


public class HangmanMC implements HangmanView {
    private String discoveredWord;
    private GameStatus gameStatus;
    private List<Character> usedLetters;
    private String wordToFind;
    private int nbErrors;
    private Bundle savedInstanceState;



    public HangmanMC(Bundle savedInstanceState){
        initDiscoveredWord();
        setSavedInstanceState(savedInstanceState);
        setWordToFind(getDiscoveredWord());

        setNbErrors(0);
        setGameStatus(GameStatus.WORD_NOT_FOUND_YET);
        setUsedLetters(new ArrayList<>());
    }

    @Override
    public void update(){//set all the values of the objects to those inside the savedInstanceState

    }


    public void setSavedInstanceState(Bundle savedInstanceState) {
        this.savedInstanceState = savedInstanceState;
    }

    public Bundle getSavedInstanceState() {
        return savedInstanceState;
    }

    public String getWordToFind(){
        return wordToFind;
    }

    public void setWordToFind(String wordToFind) {
        this.wordToFind = wordToFind;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public List<Character> getUsedLetters() {
        return usedLetters;
    }

    public void setUsedLetters(List<Character> usedLetters) {
        this.usedLetters = usedLetters;
    }

    public String getDiscoveredWord() {
        return discoveredWord.toString();
    }

    public void setDiscoveredWord(String discoveredWord) {
        this.discoveredWord = discoveredWord;
    }

    public void setNbErrors(int nbErrors) {
        this.nbErrors = nbErrors;
    }

    public int getNbErrors() {
        return nbErrors;
    }

    public void addUsedLetter(char letter){
        this.usedLetters.add(letter);
    }

    public void initDiscoveredWord(){
        Random rnd = new Random();
        int rndNum = rnd.nextInt(5);

        switch (rndNum) {
            case 1:
                setDiscoveredWord(Words.BONJOUR.toString());
                break;
            case 2:
                setDiscoveredWord(Words.CEGEP.toString());
                break;
            case 3:
                setDiscoveredWord(Words.MATIN.toString());
                break;
            case 4:
                setDiscoveredWord(Words.ORDINATEUR.toString());
                break;
        }
    }

    public void playLetter(char word){

    }
}
