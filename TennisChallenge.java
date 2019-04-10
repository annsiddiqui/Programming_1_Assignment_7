import java.util.Scanner;

/**
 * Class that handles scoring tennis games, sets and matches. This class is
 * meant to be uses text output to the console and takes input from the
 * keyboard.
 *
 * @author Qurrat-al-Ain Siddiqui
 */

public class TennisChallenge {

    /**
     * Shared Scanner used by all methods.
     */
    public Scanner userInput = new Scanner(System.in);

    /** 
     * This method run() calls upon all the methods to determine if user
     * wants to play a game, an overtime game, a set, or a match.
     */

    public void run(){

        String userAnswer;

        System.out.println("Would you like to score a (g)ame, (o)vertime game, (s)et, or a (m)atch?");
        userAnswer = userInput.nextLine();
        userAnswer = userAnswer.toUpperCase();

        if(userAnswer.equals("G"))
        {
            gameWinner();
        }
        else if(userAnswer.equals("O"))
        {
            overtimeGameWinner();
        }
        else if(userAnswer.equals("S"))
        {
            setWinner();
        }
        else if(userAnswer.equals("M"))
        {
            matchWinner();
        }
        else
        {
            System.out.println("Error: Invalid player number entered: " + userAnswer);
        }

    }

    /**
     * The method matchWinner() to score a tennis match.
     * 
     */

    private void matchWinner(){

        int player1MatchScore = 0;
        int player2MatchScore = 0;
        int setWinner = 0;

        while(!((player1MatchScore == 2)||(player2MatchScore == 2)))
        {

            setWinner = setWinner();

            if(setWinner == 1)
            {
                player1MatchScore++;
            }
            else
            {
                player2MatchScore++;
            }

            System.out.println();
            System.out.println("Match Score: " + player1MatchScore + "-" + player2MatchScore);

            if(player1MatchScore > player2MatchScore)
            { 
                System.out.println();
                System.out.println("Match over: Player 1 is the winner.");
            }
            else
            {
                System.out.println();
                System.out.println("Match over: Player 2 is the winner.");
            }

        }

    }

    /**
     * The method setWinner() scores a set of tennis.
     * 
     * The method setWinner() also calls overtimeGameWinner() if there is 
     * a "tie breaker" condition of 6:6 to score the tennis game. 
     * 
     * @ return integer of 1 if Player 1 wins or returns integer of 2 if Player 2 wins.
     */

    public int setWinner(){

        int setWinner = 0; 
        int player1SetScore = 0;
        int player2SetScore = 0;
        int point = 0;
        int overtimeGameWinner;
        final int SET_OVERTIME_CONDITION = 6;

        while(!setOver(player1SetScore,player2SetScore)){

            point = gameWinner();

            if(point == 1)
            {
                player1SetScore++;
            }
            else if(point == 2)
            {
                player2SetScore++;
            }

            if((player1SetScore == SET_OVERTIME_CONDITION) && (player2SetScore == SET_OVERTIME_CONDITION)) 
            { 
                // The above if statement should result in an overtime game.

                overtimeGameWinner = overtimeGameWinner();

                System.out.println();
                System.out.println("Set Score: " + player1SetScore + "-" + player2SetScore);

                if(overtimeGameWinner == 1)
                {
                    player1SetScore++;
                }
                else if (overtimeGameWinner == 2)
                {
                    player2SetScore++;
                }
            }

            System.out.println();
            System.out.println("Set Score: " + player1SetScore + "-" + player2SetScore);
        }

        if (player1SetScore > player2SetScore)
        {
            System.out.println();
            System.out.println("Set over: Player 1 is the winner.");

            setWinner = 1;
        }
        else
        {
            System.out.println();
            System.out.println("Set over: Player 2 is the winner.");

            setWinner = 2;
        }

        return setWinner;
    }

    /** 
     * The method gameWinner() scores a game of tennis
     * 
     * @ parameter Player 1 and Player 2 Scores
     * 
     * @ assumption The user enters either a 1 or 2 to score the game.
     * 
     * @ return integer of 1 if Player 1 wins or returns integer of 2 if Player 2 wins.
     */

    private int gameWinner(){

        int player1Score = 0;
        int player2Score = 0;
        int point = 0;
        int gameWinner = 0;
        String errorString;

        while(!gameOver(player1Score,player2Score))
        {

            if (!userInput.hasNextInt())
            {
                errorString = userInput.next();
                System.out.println("Error: Invalid player number entered: " + errorString);
            }
            else if (userInput.hasNextInt())
            {
                point = userInput.nextInt();

                if (point == 1)
                {
                    player1Score++;
                }
                else if (point == 2)
                {
                    player2Score++;
                }
                else
                {
                    System.out.println("Error: Invalid player number entered: " + point);
                }
            }

            System.out.println("Game Score: " + player1Score + "-" + player2Score);

        }

        if (player1Score > player2Score)
        {
            System.out.println();
            System.out.println("Game over: Player 1 is the winner.");

            gameWinner = 1;
        }
        else
        {
            System.out.println();
            System.out.println("Game over: Player 2 is the winner");

            gameWinner = 2;
        }

        return gameWinner;

    }

    /**
     * The method gameOver() scores overtime game of tennis.
     * 
     * @ parameter Player 1 and Player 2 Scores
     * 
     * @ return of integer of 1 if Player 1 wins and integer of 2 if Player 2 
     */

    private boolean gameOver(int player1Score,int player2Score){

        int scoreDifference = Math.abs(player1Score - player2Score);
        boolean result = false;
        final int SCORE_LIMIT = 4;

        if ((player1Score >= SCORE_LIMIT) && scoreDifference >= 2 || (player2Score >= SCORE_LIMIT) && scoreDifference >=2)
        {
            result = true;
        }

        return result;

    }

    /** 
     * The method setOver() scores the set 
     * 
     * @ parameter is Set Score
     * 
     * @return a boolean value of FALSE, and boolean value of TRUE if the setOver() conditions are met.
     */

    private boolean setOver(int player1setScore, int player2setScore){

        int scoreDifference = Math.abs(player1setScore - player2setScore);
        boolean result = false;
        final int SET_SCORE_LIMIT = 6;
        final int SET_MAXIMUM_LIMIT = 7;

        if ((player1setScore == SET_MAXIMUM_LIMIT) || (player2setScore == SET_MAXIMUM_LIMIT))
        {
            result = true;
        }
        else if ((player1setScore >= SET_SCORE_LIMIT) && scoreDifference >= 2 || (player2setScore >= SET_SCORE_LIMIT) && scoreDifference >=2)
        {
            result = true;
        }

        return result;

    }

    /**
     * The method overtimeGameWinner() scores overtime game of tennis.
     * 
     * @ return integer of 1 if Player 1 wins, and returns an integer of 2 if Player 2 wins.
     * 
     */

    private int overtimeGameWinner() {

        int overtimeWinner = 0;
        int player1Score = 0;
        int player2Score = 0;
        int point = 0;
        String errorString;

        while (!tieGameOver(player1Score,player2Score))
        {

            if (!userInput.hasNextInt())
            {
                errorString = userInput.next();
                System.out.println("Error: Invalid player number entered: " + errorString);
            }
            else if (userInput.hasNextInt())
            {

                point = userInput.nextInt();

                if (point == 1)
                {
                    player1Score++;
                }
                else if (point == 2)
                {
                    player2Score++;
                }
                else
                {
                    System.out.println("Error: Invalid player number entered: " + point);
                }

            }

            System.out.println("Overtime Game Score: " + player1Score + "-" + player2Score);
        }

        if (player1Score > player2Score)
        {
            System.out.println();
            System.out.println("Game Over: Player 1 is the winner.");

            overtimeWinner = 1;
        }
        else if (player2Score > player1Score)
        {
            System.out.println();
            System.out.println("Game Over: Player 2 is the winner.");

            overtimeWinner = 2;
        }

        return overtimeWinner;

    }

    /** 
     * The method tieGameOver() scores a tied game of tennis.
     * 
     * @ parameters Player 1 and Player 2 Scores
     * 
     * @ return result of tennis game.
     */

    private boolean tieGameOver(int player1Score,int player2Score){

        int scoreDifference = Math.abs(player1Score - player2Score);
        boolean result = false;
        final int SCORE_LIMIT = 7;

        if ((player1Score >= SCORE_LIMIT) && scoreDifference >= 2 || (player2Score >= SCORE_LIMIT) && scoreDifference >= 2)
        {
            result = true;
        }

        return result;
    }

} 