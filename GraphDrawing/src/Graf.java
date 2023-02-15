import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.atomic.AtomicBoolean;

public class Graf
{	
	private static void initUI() {
        JFrame f = new JFrame("Algoritmica Grafurilor");
        //sa se inchida aplicatia atunci cand inchid fereastra
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


   // JButton btnGenerateGraph = new JButton("Genereaza un graf");

    //graphGenPanel panel2 = new graphGenPanel();
        //imi creez ob MyPanel
        JButton b1 = new JButton("Graf orientat");
        MyPanel myPanel = new MyPanel();
        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            myPanel.chooseGraphType(!myPanel.getGraphType());
            if(myPanel.getGraphType())
            { b1.setText("Graf neorientat");
            }
                else b1.setText("Graf orientat");
            myPanel.repaint();
            }
        } );

      //  btnGenerateGraph.addActionListener(new ActionListener() {
          //  @Override
        //    public void actionPerformed(ActionEvent e) {
            //    f.remove(myPanel);

            //}
        //});
            myPanel.add(b1);
          //  panel2.add(btnGenerateGraph);

            f.add(myPanel);
           // f.add(panel2, BorderLayout.NORTH);
           // f.remove(panel2);

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
