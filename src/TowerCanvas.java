import javax.swing.*;
import java.awt.*;


public class TowerCanvas extends JPanel{

    int [][] pegsArray = {
            {170, 150, 10, 270, 50, 420, 240, 10},
            {480, 150, 10, 270, 370, 420, 240, 10},
            {810, 150, 10, 270, 690, 420, 240, 10}
    };

    private final Peg FIRST_PEG = new Peg(pegsArray[0][0],pegsArray[0][1],pegsArray[0][2],pegsArray[0][3],pegsArray[0][4],pegsArray[0][5],pegsArray[0][6],pegsArray[0][7],this, Color.orange);
    private final Peg SECOND_PEG = new Peg(pegsArray[1][0],pegsArray[1][1],pegsArray[1][2],pegsArray[1][3],pegsArray[1][4],pegsArray[1][5],pegsArray[1][6],pegsArray[1][7], this,Color.orange);
    private final Peg THIRD_PEG = new Peg(pegsArray[2][0],pegsArray[2][1],pegsArray[2][2],pegsArray[2][3],pegsArray[2][4],pegsArray[2][5],pegsArray[2][6],pegsArray[2][7], this,Color.orange);
    private boolean isAdded;
    private int minWidth = 30;
    private int discHeigth = 20;
    private int y = FIRST_PEG.getyBottom() - discHeigth;
    private int x = FIRST_PEG.getX();


    @Override
    public void update(Graphics g) {
        paint(g);
    }

    @Override
    public void paint(Graphics g) {
        g.clearRect(0,0,1000,1000);
        FIRST_PEG.draw(g);
        SECOND_PEG.draw(g);
        THIRD_PEG.draw(g);
    }

    public void addDisc(int c) {

    }
    public void reset(){
        minWidth = 30;
        discHeigth = 20;
        y = FIRST_PEG.getyBottom() - discHeigth;
        x = FIRST_PEG.getX();

    }

    public void play(){
        if (!FIRST_PEG.discs.isEmpty()){

        }
    }
}
