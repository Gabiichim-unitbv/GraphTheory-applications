import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonFrame extends JFrame {
    static public String type="";
    static public boolean isRandom=false;
    JButton orientat, neorientat;
    void orientat(){
        type="Orientat";
        this.dispose();
        new Graf();
    }
    void neorientat(){
        type="Neorientat";
        this.dispose();
        new Graf();
    }

    public ButtonFrame() {
        JLabel selectText = new JLabel("Selecteaza o optiune: ");
        selectText.setBounds(50, 25, 200, 50);
        orientat=new JButton("Orientat");
        orientat.setBounds(75,100,100,50);
        orientat.setBackground(Color.ORANGE);
        orientat.addActionListener(e->orientat());

        neorientat=new JButton("Neorientat");
        neorientat.setBounds(325,100,100,50);
        neorientat.setBackground(Color.RED);
        neorientat.addActionListener(e->neorientat());

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setSize(500,500);
        setVisible(true);
        add(selectText);
        add(orientat);
        add(neorientat);
    }
}
