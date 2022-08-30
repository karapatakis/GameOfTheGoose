/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameofthegoose;

import static gameofthegoose.GameOfTheGoose.ANSI_BLUE;
import static gameofthegoose.GameOfTheGoose.ANSI_RESET;

/**
 *
 * @author kostas
 */
public class Player {

    private final String name;
    private final Board board;
    private int board_pos = 0;
    private String flag = "ok";

    public Player(String name, Board board) {
        this.name = name;
        this.board = board;
    }

    public void moveTo(int hop) {
        // Rule (.5)
        if (63 - hop < 1) {
            hop = 63 - (hop - 63);

        }
        Player targetBoardPosition = board.getSquare(hop).playerOnTop;
        Player currentBoardPosition = this;

        if ((board_pos != 0)) {
            currentBoardPosition = board.getSquare(board_pos).playerOnTop;
        }

        // if target is empty
        if (targetBoardPosition == null) {
            board.getSquare(hop).playerOnTop = currentBoardPosition;
            //remove player from starting square
            if (board_pos != 0) {
                board.getSquare(board_pos).playerOnTop = null;
            }
            board_pos = hop;
        } //if positions must switch
        else {
            // Break the infinite loop
            if(currentBoardPosition == targetBoardPosition){
                return;
            }
            System.out.println(ANSI_BLUE+"Switching (" + currentBoardPosition.getName() + ") with (" + targetBoardPosition.getName() + ": "+targetBoardPosition.getBoardPosition()+")"+ANSI_RESET);
            // A to position of B
            board.getSquare(hop).playerOnTop = currentBoardPosition;
            // B to position of A
            if (board_pos != 0) {
                board.getSquare(board_pos).playerOnTop = targetBoardPosition;
                board.getSquare(board_pos).playerOnTop.setBoardPosition(board_pos);
            } else {
                targetBoardPosition.setBoardPosition(board_pos); //if a player is starting from 0
            }
            board_pos = hop;
        }

        /*      Triggering action         */
        board.getSquare(board_pos).action();
    }

    public String getName() {
        return this.name;
    }

    public int getBoardPosition() {
        return this.board_pos;
    }

    public void setBoardPosition(int board_pos) {
        this.board_pos = board_pos;
    }

    public void setFlag(String x) {
        flag = x;
    }

    public String getFlag() {
        return flag;
    }
}
