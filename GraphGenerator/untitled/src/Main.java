import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
public class Main {
    private static void initUI() {

       Label lblNodeNumber = new Label("Numarul de noduri");
       TextField txtNodeNumber = new TextField();

        Label lblProbability = new Label("Probabilitatea de arce (0-100)");
        TextField txtProbability = new TextField();

        JButton btnGenerateGraph = new JButton("Genereaza un graf");

        JFrame f = new JFrame("Algoritmica Grafurilor");
        //sa se inchida aplicatia atunci cand inchid fereastra
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        f.setLayout(new GridLayout(2, 2));
        f.add(lblNodeNumber);
        f.add(txtNodeNumber);
        f.add(btnGenerateGraph);
        f.add(lblProbability);
        f.add(txtProbability);

        f.pack();
        f.setSize(600, 500);
        f.setVisible(true);

        JFrame g = new JFrame("Algoritmica Grafurilor");
        g.setSize(1920, 1080);
        g.setBackground(Color.BLACK);
        btnGenerateGraph.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String probability = txtProbability.getText();
                String nodeNumber = txtNodeNumber.getText();
                MyPanel myPanel = new MyPanel(Integer.valueOf(nodeNumber), Integer.valueOf(probability));
                g.add(myPanel);
                f.setVisible(false);
                g.setVisible(true);
                g.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            }
        });


    }


    public static void main(String[] args) {
        //pornesc firul de executie grafic
        //fie prin implementarea interfetei Runnable, fie printr-un ob al clasei Thread
        SwingUtilities.invokeLater(new Runnable() //new Thread()
        {
            public void run() {
                initUI();
            }
        });
    }
}

