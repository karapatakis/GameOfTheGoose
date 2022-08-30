/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameofthegoose;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author kosta
 */
public class GameOfTheGoose {

    /**
     */
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_RESET = "\u001B[0m";
    
    public static void main(String[] args) {
        
        Board b1 = makeBoard();
        
        Scanner data = new Scanner(System.in);

        System.out.print(" *** Welcome to The Game Of The Goose *** \n\nHow many players: ");
        ArrayList<Player> playerList = new ArrayList<>();
        int maxPlayers = 0;
        boolean goOn = false;

        /*         ----   SETTING UP PLAYERS   ----        */
        do {
            try {
                maxPlayers = data.nextInt();
                goOn = true;
                if (2 > maxPlayers || maxPlayers > 6) {
                    System.err.print("Try again (min:2, max:6): ");
                    goOn = false;
                }

            } catch (InputMismatchException e) {
                data.next();
                goOn = false;
                System.err.print("Give number (integer) again: ");
            }
        } while (goOn == false);

        for (int i = 1; i <= maxPlayers; i++) {
            System.out.print("Player" + " name " + i + ": ");
            Player p1 = new Player(data.next(), b1);
            playerList.add(p1);
        }

        /*         ----   END OF SETTING UP PLAYERS   ----        */
        Player p;
        
        String squareName;
        //     TURNS
        while (b1.getSquare(63).playerOnTop == null) {  // Run until someone wins
            for (int i = 0; i < playerList.size(); i++) {
                p = playerList.get(i);
                System.out.println("'" + ANSI_PURPLE + p.getName() + ANSI_RESET + "' (on square) -> " + ANSI_PURPLE + p.getBoardPosition() + ANSI_RESET);
                
                String choice;
                int roll1, roll2, dice;

                //      DICE ROLL
                do {
                    System.out.print("Type (r)oll, (s)ave, (l)oad, (e)xit : ");
                    try {
                        choice = data.next();
                        if (choice.equals("r")) {
                            roll1 = roll();
                            roll2 = roll();
                            dice = roll1 + roll2;

                            // ***   START  OF  Rule (.7)  ***
                            if (p.getBoardPosition() == 0 && ((roll1 == 6 && roll2 == 3) || (roll1 == 3 && roll2 == 6))) {
                                p.moveTo(26);
                                System.out.println("Dice: " + roll1 + "+" + roll2);
                                System.out.println(ANSI_BLUE + "Moved automaticaly" + ANSI_RESET);
                            } else if (p.getBoardPosition() == 0 && ((roll1 == 5 && roll2 == 4) || (roll1 == 4 && roll2 == 5))) {
                                p.moveTo(53);
                                System.out.println("Dice: " + roll1 + "+" + roll2);
                                System.out.println(ANSI_BLUE + "Moved automaticaly" + ANSI_RESET);
                                //  ***   END  OF  Rule (.7)     ***

                            } else {
                                squareName = p.getFlag(); //check for rules .10, .11, .13
                                if (squareName.equalsIgnoreCase("ok")) {
                                    System.out.println("Dice: " + roll1 + "+" + roll2);
                                    p.moveTo(p.getBoardPosition() + dice);
                                    squareName = p.getFlag();

                                    //check for rule .10
                                    while (squareName.equalsIgnoreCase("Goose")) {
                                        System.out.println(ANSI_BLUE + "Adding automaticaly " + dice + ANSI_RESET);
                                        p.moveTo(p.getBoardPosition() + dice);
                                        squareName = p.getFlag();
                                    }
                                }
                            }
                            
                            System.out.println("You are on square: " + ANSI_PURPLE + p.getBoardPosition() + ANSI_RESET + "\n-------------------");
                            goOn = true;
                        } else if (choice.equalsIgnoreCase("s")) {
                            save(playerList, playerList.indexOf(p), playerList.size());
                            System.out.println(ANSI_BLUE + "\tGame saved" + ANSI_RESET);
                            goOn = false;
                            
                        } else if (choice.equalsIgnoreCase("l")) {
                            throw new NullPointerException(); 
//                            b1 = makeBoard();
//                            playerList = load(b1);
//                            for (int j = 0; j < playerList.size(); j++) {
//                                p = playerList.get(j);
//                                p.moveTo(p.getBoardPosition());         //vazei null to currentBoardPosition, enw einai o idios
//                               i=0;
//                            }
//                            goOn = false;
                            
                        }else if (choice.equalsIgnoreCase("e")){
                            System.out.println(ANSI_BLUE + "\tGame exited" + ANSI_RESET);
                            return;
                        }
                        else {
                            throw new InputMismatchException();
                        }
                    } catch (InputMismatchException e) {
                        System.err.println("Try again.");
                        goOn = false;
                    }catch (NullPointerException e) {
                        System.err.println("Load not working properly.");
                        goOn = false;
                    }
                    
                } while (goOn == false);

                //TESTp.moveTo(60 + 7);
                if (b1.getSquare(63).playerOnTop != null) {
                    System.out.println(ANSI_BLUE + p.getName() + " WON THE GAME!" + ANSI_RESET);
                    return;
                    
                }
            }
        }
        /*         ----   END OF THE GAME   ----        */
        
    }
    
    public static Board makeBoard() {
        Board b1 = new Board();
        for (int i = 1; i <= 63; i++) {
            switch (i) {
                case 5:
                case 9:
                case 14:
                case 18:
                case 23:
                case 27:
                case 32:
                case 36:
                case 41:
                case 45:
                case 50:
                case 54:
                case 59: {
                    MovingSquare newSquare = new MovingSquare(i, "Goose", b1);
                    b1.addSquare(newSquare);
                    break;
                }
                case 6: {
                    MovingSquare newSquare = new MovingSquare(i, "Bridge", b1);
                    b1.addSquare(newSquare);
                    break;
                }
                case 19: {
                    WaitingSquare newSquare = new WaitingSquare(i, "Inn", b1);
                    b1.addSquare(newSquare);
                    break;
                }
                case 31: {
                    WaitingSquare newSquare = new WaitingSquare(i, "Well", b1);
                    b1.addSquare(newSquare);
                    break;
                }
                case 42: {
                    MovingSquare newSquare = new MovingSquare(i, "Labyrinth", b1);
                    b1.addSquare(newSquare);
                    break;
                }
                case 52: {
                    WaitingSquare newSquare = new WaitingSquare(i, "Prison", b1);
                    b1.addSquare(newSquare);
                    break;
                }
                case 58: {
                    MovingSquare newSquare = new MovingSquare(i, "Death", b1);
                    b1.addSquare(newSquare);
                    break;
                }
                default: {
                    Square newSquare = new Square(i, b1);
                    b1.addSquare(newSquare);
                    break;
                }
            }
        }
        /*     ---     END OF MAKING THE BOARD  ---   */
        return b1;
    }
    
    
    public static int roll() {
        int min = 1, max = 6;
        int die = (int) (Math.random() * (max - min + 1) + min);
        return die;
    }
    
    public static void save(ArrayList playerList, int currentPlayer, int allPlayers) {
        try {
        
            PrintWriter file = new PrintWriter("save.txt");
            file.print(currentPlayer);
            file.print(" ");
            file.print(allPlayers);
            file.print("\n");
            
            for (int i = 0; i < playerList.size(); i++) {
                Player player = (Player) playerList.get(i);
                file.print(player.getName());
                file.print(" ");
                file.print(player.getBoardPosition());
                file.print(" ");
                file.print(player.getFlag());
                file.print("\n");
                
            }
            file.close();
            
        } catch (FileNotFoundException e) {
            System.err.println("Error with the saving process.");
        }
        
    }
    
    public static ArrayList load(Board b1) {
        ArrayList<Player> playerList = new ArrayList<>();
        try {
            Scanner file = new Scanner(new FileInputStream("save.txt"));
            int current = file.nextInt();
            int numOfPlayers = file.nextInt();
            
            for (int i = 0; i < numOfPlayers; i++) {
                String name = file.next();
                int BoardPosition = file.nextInt();
                String flag = file.next();
                
                Player p = new Player(name, b1); //create player
                playerList.add(p);                       // add player to arraylist
                int pos = playerList.indexOf(p);
                p = (Player) playerList.get(playerList.indexOf(p)); //get player postition, to be able to add fields
                p.setBoardPosition(BoardPosition);
                p.setFlag(flag);
                
            }
            
        } catch (FileNotFoundException e) {
            System.err.println("Error with the loading process.");
        }
        return playerList;
    }
}
