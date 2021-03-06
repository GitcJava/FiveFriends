package hanoitowers;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TowerFrame extends JFrame {
    static JTextField jTextField;
    TowerCanvas towerCanvas;

    public TowerFrame() {
        super("Hanoi Tower");
        JPanel panel = new JPanel();
        panel.setBackground(Color.BLUE);
        towerCanvas = new TowerCanvas();
        jTextField = new JTextField("3", 2);
        JButton addButton = new JButton("add");

        JButton resetButton = new JButton("reset");
        JButton playButton = new JButton("play");

        panel.add(jTextField);
        panel.add(addButton);
        panel.add(resetButton);
        panel.add(playButton);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addAction();
            }
        });
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                towerCanvas.reset();
            }
        });
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                towerCanvas.play();
            }
        });

        add(towerCanvas, BorderLayout.CENTER);
        add(panel, BorderLayout.NORTH);
        setSize(1000, 1000);
        setLocation(200, 150);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void addAction() {

        try {
            int c = Integer.parseInt(jTextField.getText());
            if (c > 7) {
                JOptionPane.showMessageDialog(null, "please write to number smoll than 7");
            } else {
                towerCanvas.addDisc(c);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "please write to number");
        }
    }

    public static void main(String[] args) {
        TowerFrame
                towerFrame = new TowerFrame();

    }
}
