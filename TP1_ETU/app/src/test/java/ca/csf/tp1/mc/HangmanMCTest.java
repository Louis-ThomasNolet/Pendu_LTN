package ca.csf.tp1.mc;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.Arrays;
import java.util.List;

import ca.csf.tp1.enumerations.GameStatus;
import ca.csf.tp1.views.HangmanViewMock;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HangmanMCTest {

    private void thisMethodShouldCompile() {
        // Vous ne devez pas appeler cette méthode mais vous devez vous assurer que les
        // appels compilent tous.  Décommentez-les un par un.
        HangmanMC hangmanMC = new HangmanMC(null);
        hangmanMC.setWordToFind("ANY_WORD");
        String anyWord = hangmanMC.getWordToFind();
        hangmanMC.initDiscoveredWord();
        anyWord = hangmanMC.getDiscoveredWord();
        GameStatus status = hangmanMC.getGameStatus();
        List<Character> usedLetters = Arrays.asList(new Character[]{'A', 'B', 'C', 'E'});
        hangmanMC.setUsedLetters(usedLetters);
        hangmanMC.setDiscoveredWord("ANY_WORD");
        int nbErrors = hangmanMC.getNbErrors();
        hangmanMC.playLetter(anyWord.charAt(0));
        HangmanViewMock mockView = new HangmanViewMock();
        boolean updateCalled = mockView.isUpdateCalled();
    }


    @Test
    public void test_01() {
        // A COMPLETER
    }
}

