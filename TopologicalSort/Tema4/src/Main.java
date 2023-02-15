import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.atomic.AtomicBoolean;

public class Main
{

    private static void initUI() {
        JFrame topFrame = new JFrame("Topological ordering");
        JFrame f = new JFrame("Algoritmica Grafurilor");
        //sa se inchida aplicatia atunci cand inchid fereastra
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        topFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //imi creez ob MyPanel
        MyPanel myPanel = new MyPanel();
        JPanel topPanel = new TopPanel();
        JLabel topLbl = new JLabel("Topological ordering: ");
        topPanel.setLayout(new FlowLayout());
        topPanel.add(topLbl);
        topFrame.add(topPanel);
        topFrame.setSize(300, 300);


        JButton btnTop = new JButton("Topological sort");
        btnTop.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                topFrame.setVisible(true);
             topPanel.repaint();
            }
        } );
        myPanel.add(btnTop);
        f.add(myPanel);
        //setez dimensiunea ferestrei
        f.setSize(500, 500);
        //fac fereastra vizibila
        f.setVisible(true);
    }


    public static void main(String[] args)
    {
        //pornesc firul de executie grafic
        //fie prin implementarea interfetei Runnable, fie printr-un ob al clasei Thread
        SwingUtilities.invokeLater(new Runnable() //new Thread()
        {
            public void run()
            {
                initUI();
            }
        });


    }
}
