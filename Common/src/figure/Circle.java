package figure;


import java.awt.*;

public class Circle extends Figure {


    public Circle(int x, int y, int diameter) {
        this(x, y, diameter, Color.yellow);
    }

    public Circle(int x, int y, int diameter, Color color) {
        super(x, y, diameter, diameter, color);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(getColor());
        g.fillOval(getX(), getY(), getWidth(), getHeight());
    }


    @Override
    public boolean isBelong(int x, int y) { // (x, y)   (getX() + getWidth()/2 , getY() + getWidth()/2)
        return (x - getX() - getWidth() / 2) * (x - getX() - getWidth() / 2)
                + (y - getY() - getHeight() / 2) * (y - getY() - getHeight() / 2) <= getHeight() * getHeight() / 4;
    }

}
