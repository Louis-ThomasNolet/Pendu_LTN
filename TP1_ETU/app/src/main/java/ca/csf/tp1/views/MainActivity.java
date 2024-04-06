package ca.csf.tp1.views;


import androidx.annotation.ColorInt;
import androidx.annotation.ContentView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcel;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import ca.csf.tp1.R;
import ca.csf.tp1.enumerations.Words;
import ca.csf.tp1.mc.HangmanMC;

public class MainActivity extends AppCompatActivity{
    Button buttons[] = new Button[26];
    static int status = 1;
    static HangmanMC hangmanMC;
    final int DEAD = 10;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.v("Life Cycle", "ON CREATE");
        super.onCreate(savedInstanceState);
        startGame(savedInstanceState);
    }

    public void startGame(Bundle savedInstanceState){
        setContentView(R.layout.activity_main);
        hangmanMC = new HangmanMC(savedInstanceState);

        if(savedInstanceState != null && savedInstanceState.containsKey("discoveredWord"))
            setWord(savedInstanceState.getString("discoveredWord"));
        else{
            hangmanMC.initDiscoveredWord();
            setWord(hangmanMC.getDiscoveredWord());//definit le mot et transforme les lettres en *
        }

        for (int i = 0; i < 26; i++) {
            int id = getResources().getIdentifier("button" + (char) ('A' + i), "id", getPackageName());
            Button btn = findViewById(id);
            if (null != btn) {
                btn.setOnClickListener(this::onClickLetter);
                buttons[i] = btn;
            }
        }

    }
    private void onClickLetter(View v) {
        int imageId = getResources().getIdentifier("hangmanStatus", "id", getPackageName());//obtiens le id de l'image du bonhomme pendu
        ImageView hangmanStatus = findViewById(imageId);

        int wordId = getResources().getIdentifier("Mot", "id", getPackageName());//obtiens le id du mot
        TextView textView = findViewById(wordId);

        Button b = (Button)v;
        String text = b.getText().toString();//obtiens la lettre tenté
        char letterAsChar = text.toCharArray()[0];
        String letter = "" + letterAsChar;//crée un string avec seulement la lettre



        b.setClickable(false);//empêche de clicker sur la même lettre à nouveau

        hangmanMC.addUsedLetter(letterAsChar);



        String wordToFind = hangmanMC.getWordToFind();
        if(wordToFind.contains(letter)){
            String tempWordToFind = wordToFind;
            char[] switchText = textView.getText().toString().toCharArray();

            //Affiche les lettres trouve
            while(tempWordToFind.contains(letter)){
                int index = tempWordToFind.indexOf(letterAsChar);
                switchText[index] = letterAsChar;
                tempWordToFind = tempWordToFind.replaceFirst(letter, "*");
            }

            String newText = "";
            for(int i = 0; i < switchText.length; i++){
                newText += switchText[i];
            }
            textView.setText(newText);
            b.setBackgroundColor(Color.GREEN);
        }
        else{
            if (getResources().getConfiguration().orientation != Configuration.ORIENTATION_LANDSCAPE)
                updateImage(hangmanStatus);
            else
                updateImageLandscape(hangmanStatus);
            b.setBackgroundColor(Color.YELLOW);
            hangmanMC.setNbErrors(status);
        }
        isGameOver(textView);
    }


    public void setWord(String word){
        int mot = getResources().getIdentifier("Mot", "id", getPackageName());
        TextView textView = findViewById(mot);

        String hiddenText = "";
        for(int i = 0; i < word.length(); i++){
               hiddenText += "*";
        }
        textView.setText(hiddenText);
    }


    public void updateImage(View v){//change le status du bonhomme pendu
        status += 1;
        if(status < 4)
            v.setTranslationX(v.getTranslationX()- 430);
        else
            v.setTranslationX(v.getTranslationX()- 215);
    }
    public void updateImageLandscape(View v){//change le status du bonhomme pendu en mode portrait
        status += 1;
        if(status < 4)
            v.setTranslationX(v.getTranslationX() - 720);
        else
            v.setTranslationX(v.getTranslationX() - 360);
    }

    public void isGameOver(TextView textView){
        if(textView.getText().toString().contains("*") == false) {
            for (Button btn : buttons) {
                btn.setClickable(false);
            }
            textView.setText("BRAVO");
        }
        else if(status == DEAD){
            for (Button btn : buttons) {
                btn.setClickable(false);
            }
            textView.setText("ÉCHEC");
        }
        status = 1;
    }

    //lance une nouvelle partie
    public void startNewGame(View view){
        startGame(null);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.v("Life Cycle", "ON STOP");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.v("Life Cycle", "ON DESTROY");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.v("Life Cycle", "ON RESUME");
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        boolean[] buttonState = savedInstanceState.getBooleanArray("buttonState");
        setButtonState(buttonState);
        hangmanMC.setDiscoveredWord(savedInstanceState.getString("discoveredWord"));
        setWord(savedInstanceState.getString("discoveredWord"));
        Log.v("Life Cycle", "ON RESTOREINSTANCESTATE");
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        boolean[] buttonsState = saveButtonsState();
        outState.putBooleanArray("buttonState", buttonsState);
        outState.putString("discoveredWord", hangmanMC.getDiscoveredWord());
        Log.v("Life Cycle", "ON SAVEINSTANCESTATE");
    }

    public boolean[] saveButtonsState(){
        boolean[] buttonsState = new boolean[26];
        int i = 0;
        for (Button button:buttons) {
            if(button.isClickable())
                buttonsState[i] = true;
            else
                buttonsState[i] = false;
            i++;
        }
        return buttonsState;
    }
    public void setButtonState(boolean[] buttonState){
        int i = 0;
        for (Button button:buttons) {
            if (buttonState[i])
                button.setClickable(true);
            else{
                button.setClickable(false);
            }
            i++;
        }
    }
}