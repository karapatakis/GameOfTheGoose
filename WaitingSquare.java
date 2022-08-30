/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameofthegoose;

/**
 *
 * @author kostas
 */
public class WaitingSquare extends Square {

    private String name;
    

    public WaitingSquare(int number, String name, Board board) {
        super(number, board);
        this.name = name;
    }

    @Override
    public void action() {
        //        Rule (.10)
//        if (name.equalsIgnoreCase("Inn")) {
//            if (playerOnTop != null) {
//                playerOnTop.setFlag("Inn");
//            }
//            return;
//        }
//        //        Rule (.11)
//        if (name.equalsIgnoreCase("Well")) {
//            if (playerOnTop != null) {
//                 playerOnTop.setFlag("Well");
//            }
//            return;
//        }
//        //        Rule (.13)
//        if (name.equalsIgnoreCase("Prison")) {
//            if (playerOnTop != null) {
//                 playerOnTop.setFlag("Prison");
//            }
//            return;
//        }
        playerOnTop.setFlag("ok");
    }
}
