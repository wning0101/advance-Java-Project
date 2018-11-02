package edu.pdx.cs410J.tennis;

import java.util.Scanner;
import java.util.Random;
public class Tennis {

  /**
   * Main program that parses the command line, creates a
   * <code>Student</code>, and prints a description of the TennisKata to
   * standard out by invoking its <code>toString</code> method.
   */
  public static void main(String[] args) {
    //System.err.println("Missing command line arguments");
    Scanner sc = new Scanner(System.in);

      Random rand = new Random();

    Player player1 = new Player(0);
    Player player2 = new Player(0);

    int op = 0;
    while (op != 4){

      System.out.println("What do you want to do?\n" +
              "1. Player1 scores\n" +
              "2. Player2 scores\n" +
              "3. Show the current player's score\n");
      //op = sc.nextInt();

      op = rand.nextInt(2)+1;
      if(op == 1) {


          if (player1.getScore() == 40 && player2.getScore() == 40){
              player1.winAdv();
          }
          else if(player1.getScore() == 45 && player2.getScore() == 40)
          {
              System.out.println("Player 1 WIN!");
              op = 4;
          }
          else if(player1.getScore() == 40 && player2.getScore() == 45){
              player2.loseAdv();
          }
          else
              player1.winScore();

          if (player1.getScore() == 50) {
              System.out.println("Player 1 WIN!");
              op = 4;
          }
      }
      else if (op == 2){

          if (player1.getScore() == 40 && player2.getScore() == 40) {
              player2.winAdv();
          }
          else if(player2.getScore() == 45 && player1.getScore() == 40)
          {
              System.out.println("Player 2 WIN!");
              op = 4;
          }
          else if(player2.getScore() == 40 && player1.getScore() == 45){
              player1.loseAdv();
          }
          else
              player2.winScore();
          if (player2.getScore() == 50) {
              System.out.println("Player 2 WIN!");
              op = 4;
          }
      }
      else if (op == 3)
      {
          if(player1.getScore() == 45)
              System.out.println("Player 1 gets advantage. ");
          else
              System.out.println("Player 1: " + player1.getScore());
          if(player2.getScore() == 45)
              System.out.println("Player 2 gets advantage. ");
          else
            System.out.println("Player 2: " + player2.getScore());
      }

    }
      if(player1.getScore() == 45)
          System.out.println("Player 1 gets advantage. ");
      else
          System.out.println("Player 1: " + player1.getScore());
      if(player2.getScore() == 45)
          System.out.println("Player 2 gets advantage. ");
      else
          System.out.println("Player 2: " + player2.getScore());
  }


}