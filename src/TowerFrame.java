import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Gor_Gevorgyan on 08.04.2016.
 */
public class TowerFrame extends JFrame {

    static JTextField textField;
    TowerCanvas towerCanvas;

    public TowerFrame(){
        super("Hanoi Of Tower");
        JPanel panel = new JPanel();
        panel.setBackground(Color.gray);
        towerCanvas = new TowerCanvas();
        textField = new JTextField("3" , 4);
        // Add Button
        JButton addButton = new  JButton("Add");
        JButton reset = new JButton("Reset");
        JButton play = new JButton("Play");
        panel.add(textField);
        panel.add(addButton);
        panel.add(reset);
        panel.add(play);

        play.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                towerCanvas.play();
            }
        });

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addAction();
            }
        });
        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                towerCanvas.reset();
            }
        });
        add(towerCanvas,BorderLayout.CENTER);
        add(panel,BorderLayout.NORTH);
        setSize(900,500);
        setLocation(200,50);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void addAction() {
        try{
        int num = Integer.parseInt(textField.getText());
        if (num > 7) {
            JOptionPane.showMessageDialog(null, "Please writ to number smole than 7");
        }else {
            towerCanvas.addDisc(num);
        }
        }catch(NumberFormatException num){
            num.printStackTrace();
            JOptionPane.showMessageDialog(null,"Please Input only number");

            }
    }

    public static void main(String[] args) {
        TowerFrame towerFrame = new TowerFrame();
    }
}
