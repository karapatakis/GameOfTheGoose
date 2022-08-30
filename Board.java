/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameofthegoose;

import java.util.ArrayList;

/**
 *
 * @author kosta
 */
public class Board {

    ArrayList<Square> board = new ArrayList<>();

    public void addSquare(Square newSquare) {
        board.add(newSquare);

    }
    public Square getSquare(int i) {
        return board.get(i-1);
    }
}
