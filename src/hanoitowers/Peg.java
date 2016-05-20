package hanoitowers;




import figure.Figure;

import java.awt.*;
import java.util.ArrayList;


public class Peg extends Figure {


    ArrayList<Figure> discs = new ArrayList<Figure>();

    private int xBottom;
    private int yBottom;
    private int widthBottom;
    private int heightBottom;
    private TowerCanvas towerCanvas;


    public Peg(int x, int y, int width, int height, int xBottom, int yBottom,
               int widthBottom, int heightBottom, TowerCanvas towerCanvas, Color color) {
        super(x, y, width, height, color);
        this.xBottom = xBottom;
        this.yBottom = yBottom;
        this.widthBottom = widthBottom;
        this.heightBottom = heightBottom;
        this.towerCanvas = towerCanvas;
    }
    public void moveTo(Peg to){
        moveUp(to);
        moveDown(to);
        moveLeftOrRight(to);
    }

    private void moveUp (Peg to){
        while (getLast().getY() > getY() - 50){
            getLast().move(0, -4);
            try {
                Thread.sleep(30);
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
            towerCanvas.repaint();
        }
        to.discs.add(discs.remove(discs.size() - 1));
    }
    private void moveDown(Peg to){
        while(isDoneDown(to)){
            to.getLast().move(0,4);
            try{
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            towerCanvas.repaint();
        }
    }

    private boolean isDoneDown(Peg to) {
        if(to.discs.size() < 2 ){
            return to.getLast().getY() < to.getyBottom() - to.getLast().getHeight();
        }
        else{
            return to.getLast().getY() < (to.discs.get(to.discs.size()-2).getY() - to.getLast().getHeight());
        }
    }

    private void moveLeftOrRight(Peg to) {
        int xS;
        if (getX() > to.getX()) {
            xS = -4;

            while (to.getLast().getX() >= (to.getX() - to.getLast().getWidth()/2));
            to.getLast().move(xS, 0);
            try{
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            towerCanvas.repaint();
        }
        else{
            xS = 4;
            while (to.getLast().getX() <= (to.getX() - to.getLast().getWidth()/2));
            to.getLast().move(xS,0);
            try{
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            towerCanvas.repaint();
        }
    }

    private  Figure getLast()
    {return discs.get(discs.size()-1);}

    public int getxBottom() {
        return xBottom;
    }

    public void setxBottom(int xBottom) {
        this.xBottom = xBottom;
    }

    public int getyBottom() {
        return yBottom;
    }

    public void setyBottom(int yBottom) {
        this.yBottom = yBottom;
    }

    public int getWidthBottom() {
        return widthBottom;
    }

    public void setWidthBottom(int widthBottom) {
        this.widthBottom = widthBottom;
    }

    public int getHeightBottom() {
        return heightBottom;
    }

    public void setHeightBottom(int heightBottom) {
        this.heightBottom = heightBottom;
    }

    public void move(int x, int y){
        setX(getX()+ x);
        setY(getY()+y);
        setxBottom(getxBottom() + x);
        setyBottom(getyBottom() + y);
        for (Figure f : discs) {
            f.move(x,y);
        }
        towerCanvas.repaint();

    }


    @Override
    public boolean isBelong(int x, int y) {
     return (x >= getX() && x <= getX() + getWidth() && y >= getY()
             && y <= getY() + getHeight() || (x >= getxBottom()
             && x <= getxBottom() + getWidthBottom() && y >= getxBottom()
             && y <= getyBottom() + getHeightBottom()));
    }


    @Override
    public void draw(Graphics g) {
        g.setColor(getColor());
        g.fillRect(getX(),getY(), getWidth(), getHeight());
        g.fillRect(getxBottom(),getyBottom(),getWidthBottom(),getHeightBottom());
        for (Figure f : discs) {
            f.draw(g);
        }

    }
}
