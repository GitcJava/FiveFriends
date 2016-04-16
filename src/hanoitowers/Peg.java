package hanoitowers;


import figures.Figure;

import java.awt.*;
import java.util.ArrayList;


public class Peg extends Figure {


    ArrayList<Figure> discs = new ArrayList<Figure>();

    private int xBottom;
    private int yBottom;
    private int widthBottom;
    private int heightBottom;
    private TowerCanvas towerCanvas;


    public Peg(int x, int y, int width, int height, int xBottom, int yBottom, int widthBottom, int heightBottom, TowerCanvas towerCanvas, Color color) {
        super(x, y, width, height, color);
        this.xBottom = xBottom;
        this.yBottom = yBottom;
        this.widthBottom = widthBottom;
        this.heightBottom = heightBottom;
        this.towerCanvas = towerCanvas;
    }


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


    @Override
    public boolean isBelong(int i, int i1) {
        return false;
    }


    @Override
    public void draw(Graphics graphics) {
        System.out.println();
    }
}
