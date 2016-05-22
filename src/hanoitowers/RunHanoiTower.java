package hanoitowers;

import javax.swing.*;

public class RunHanoiTower implements Runnable {

    public Peg first_peg;
    public Peg second_peg;
    public Peg third_peg;
    public int size;

    public RunHanoiTower(Peg first_peg, Peg second_peg, Peg third_peg, int size) {

        this.first_peg = first_peg;
        this.second_peg = second_peg;
        this.third_peg = third_peg;
        this.size = size;
        new Thread(this).start();
    }

    public static void hanoiTower(int n, Peg from, Peg inter, Peg to) {
        if (n == 1) {
            from.moveTo(to);
        } else {
            hanoiTower(n - 1, from, to, inter);
            from.moveTo(to);
            hanoiTower(n - 1, inter, from, to);
        }
    }
    private void win(){
        if (first_peg.discs.isEmpty() && second_peg.discs.isEmpty()
                && !third_peg.discs.isEmpty()){
            JOptionPane.showMessageDialog(null,"You Win...");
        }
    }

    @Override
    public void run() {
        hanoiTower(size, first_peg, second_peg, third_peg);
        win();
    }
}
