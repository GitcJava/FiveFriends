package hanoitowers;


import java.awt.*;


public class Peg extends Figure {

    protected Peg(int x, int y, int width, int height, FigureCanvas figureCanvas, Color color) {
        super(x, y, width, height, figureCanvas, color);
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
