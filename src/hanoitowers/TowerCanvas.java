package hanoitowers;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

public class TowerCanvas extends JPanel {

    int[][] pegsArray = {
            {170, 150, 10, 270, 50, 420, 240, 10},
            {490, 150, 10, 270, 370, 420, 240, 10},
            {810, 150, 10, 270, 690, 420, 240, 10}
    };

    private final Peg FIRST_PEG = new Peg(pegsArray[0][0], pegsArray[0][1], pegsArray[0][2], pegsArray[0][3], pegsArray[0][4], pegsArray[0][5], pegsArray[0][6], pegsArray[0][7], this, Color.BLACK);
    private final Peg SECOND_PEG = new Peg(pegsArray[1][0], pegsArray[1][1], pegsArray[1][2], pegsArray[1][3], pegsArray[1][4], pegsArray[1][5], pegsArray[1][6], pegsArray[1][7], this, Color.BLACK);
    private final Peg THIRD_PEG = new Peg(pegsArray[2][0], pegsArray[2][1], pegsArray[2][2], pegsArray[2][3], pegsArray[2][4], pegsArray[2][5], pegsArray[2][6], pegsArray[2][7], this, Color.BLACK);
    private boolean isAdded;
    private int minWidth = 40;
    private int discHeigth = 30;
    private int dx = FIRST_PEG.getX();
    private int dy = FIRST_PEG.getyBottom() - discHeigth;

    public TowerCanvas() {

    }

    public void addDisc(int c) {

        if (isAdded) {
            reset();
        }
        isAdded = true;
        for (int i = c; i > 0; i--) {
            int width = minWidth * i;
            dx = (FIRST_PEG.getX() + 4) - (width / 2);
        }
        repaint();
    }



    public void reset() {
        minWidth = 40;
        discHeigth = 30;
        dx = FIRST_PEG.getX();
        dy = FIRST_PEG.getyBottom() - discHeigth;
        FIRST_PEG.discs.clear();
        SECOND_PEG.discs.clear();
        THIRD_PEG.discs.clear();
        isAdded = false;
        repaint();
    }

    public void play() {
    }

}
