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
public class Square {

    private int number;
    private Board board;
    Player playerOnTop = null;

    public Square(int number, Board board) {
        this.number = number;
        this.board = board;
    }

    public int getNumber() {
        return this.number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public void action() {
            playerOnTop.setFlag("ok");
    }
}
