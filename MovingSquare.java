/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameofthegoose;

/**
 *
 * @author kosta
 */
public class MovingSquare extends Square {

    private String name;

    public MovingSquare(int number, String name, Board board) {
        super(number, board);
        this.name = name;
    }
    

    @Override
    public void action() {
        System.out.println("You stepped on a '" + name + "'" + " square [" + super.getNumber() + "]");

        //        Rule (.5)
        if (name.equalsIgnoreCase("Goose")) {
            // move player
            playerOnTop.setFlag("Goose");
            return;
        }
//        Rule (.9)
        if (name.equalsIgnoreCase("Bridge")) {
            // move player
            playerOnTop.moveTo(12);
            return;
        }
//        Rule (.14)
        if (name.equalsIgnoreCase("Death")) {
            // move player
            playerOnTop.moveTo(1);
            return;
        }
//        Rule (.12)
        if (name.equalsIgnoreCase("Labyrinth")) {
            // move player
            playerOnTop.moveTo(30);
            return;
        }
        playerOnTop.setFlag("ok");
    }

}
