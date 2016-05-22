package hanoitowers;

import javax.swing.*;
import java.awt.*;
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
    private int discHeigth = 20;
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
            dx = (FIRST_PEG.getX() + 3) - (width / 2);
            FIRST_PEG.discs.add(new Disc(dx, dy, width, discHeigth, randomColor()));
            dy = dy - discHeigth;
        }
        repaint();
    }

    private Color randomColor() {
        Random random = new Random();
        int red = random.nextInt(256);
        int green = random.nextInt(256);
        int blue = random.nextInt(256);
        return new Color(red, green, blue);
    }

    public void reset() {
        minWidth = 40;
        discHeigth = 20;
        dy = FIRST_PEG.getyBottom() - discHeigth;
        dx = FIRST_PEG.getX();
        FIRST_PEG.discs.clear();
        SECOND_PEG.discs.clear();
        THIRD_PEG.discs.clear();
        isAdded = false;
        repaint();
    }

    public void play() {
        if (!FIRST_PEG.discs.isEmpty()) {
            new RunHanoiTower(FIRST_PEG, SECOND_PEG, THIRD_PEG,
                    FIRST_PEG.discs.size());
        }
    }

    @Override
    public void paint(Graphics g) {
        g.clearRect(0, 0, 1000, 1000);
        FIRST_PEG.draw(g);
        SECOND_PEG.draw(g);
        THIRD_PEG.draw(g);
    }

    @Override
    public void update(Graphics g) {
        paint(g);
    }
}
