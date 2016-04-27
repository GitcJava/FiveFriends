package figureui;

import figure.Figure;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;


public class FigureCanvas extends JPanel {
    private ArrayList<Figure> figures = new ArrayList<>();

    private int mX;
    private int mY;

    private boolean isSelected;

    public FigureCanvas() {
        setBackground(Color.black);

        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                mousePressedPerformed(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });

        addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                mouseDraggedPerformed(e);
            }

            @Override
            public void mouseMoved(MouseEvent e) {

            }
        });

    }

    public void add(Figure figure) {
        if (figure != null) {
            figures.add(figure);

        }
        update(getGraphics());
    }
    public Figure getSelected() {
        if (isSelected) {
            int size = figures.size();
            return size > 0 ? figures.get(size - 1) : null;
        }
        return null;
    }

    private void mouseDraggedPerformed(MouseEvent e) {
        if (isSelected) {
            getSelected().move(e.getX() - mX, e.getY() - mY);
            repaint();
        }
        mX = e.getX();
        mY = e.getY();
    }

    private void mousePressedPerformed(MouseEvent e) {
        mX = e.getX();
        mY = e.getY();
        select(mX, mY);
        update(getGraphics());
    }

    private void select(int x, int y) {
        int size = figures.size();
        isSelected = false;
        for (int i = size - 1; i >= 0; i--) {
            Figure f = figures.get(i);
            if (f.isBelong(x, y)) {
                figures.add(figures.remove(i));
                isSelected = true;
                return;
            }
        }

    }

    @Override
    public void update(Graphics g) {
        super.update(g);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for (Figure figure : figures) {
            figure.draw(g);
        }
    }

    public void start() throws InterruptedException{
            Thread mult = new Thread((Runnable) this);
            mult.sleep(500);
            mult.start();
        }

    public void remove() throws InterruptedException {
             start();
    }
}

