package hanoitowers;

import javax.swing.*;
import java.awt.*;

public class TowerCanvas extends JPanel{

    int[][] pegsArray = {
            {170, 150, 10, 270, 50, 420, 240, 10},
            {490, 150, 10, 270, 370, 420, 240, 10},
            {810, 150, 10, 270, 690, 420, 240, 10}
    };

    private final Peg FIRST_PEG = new Peg( pegsArray[0][0],pegsArray[0][1],pegsArray[0][2], pegsArray[0][3],pegsArray[0][4],pegsArray[0][5],pegsArray[0][6],pegsArray[0][7],this, Color.BLACK);
    private final Peg SECOND_PEG = new Peg( pegsArray[1][0],pegsArray[1][1],pegsArray[1][2], pegsArray[1][3],pegsArray[1][4],pegsArray[1][5],pegsArray[1][6],pegsArray[1][7],this, Color.BLACK);
    private final Peg THIRD_PEG = new Peg( pegsArray[2][0],pegsArray[2][1],pegsArray[2][2], pegsArray[2][3],pegsArray[2][4],pegsArray[2][5],pegsArray[2][6],pegsArray[2][7],this, Color.BLACK);



    public TowerCanvas(){
    }

    public void addDisc(int c) {
        //TODO
    }
    public void reset(){
        //TODO
    }

    public void play(){
        //TODO
    }

}
