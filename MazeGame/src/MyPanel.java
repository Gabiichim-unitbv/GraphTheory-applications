
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;


public class MyPanel extends JPanel {

    private int[][] inputMatrix;
    private final Graph myGraph;

    private static Node m_startNode;

    private boolean m_inputStyle;


    public MyPanel(boolean inputStyle) {

        m_inputStyle = inputStyle;
        //  int[][] inputMatrix = {
        //        {1, 0, 0, 0, 0, 1},
        //      {1, 1, 1, 1, 1, 1},
        //    {0, 1, 1, 0, 0, 1},
        //  {1, 0, 1, 1, 0, 1},
        //{1, 0, 1, 1, 0, 1}
        //  };


        if (m_inputStyle) {
            inputMatrix = new int[Main.getRows()][Main.getColumns()];
            generateRandMatrix(Main.getRows(), Main.getColumns());
        } else {
            Scanner in = null;
            try {
                File file = new File("src/input.txt");
                in = new Scanner(file);
                Main.setRows(in.nextInt());
                Main.setColumns(in.nextInt());
                inputMatrix = new int[Main.getRows()][Main.getColumns()];

                for (int i = 0; i < Main.getRows(); i++) {
                    for (int j = 0; j < Main.getColumns(); j++) {
                        inputMatrix[i][j] = in.nextInt();
                    }
                }
            } catch (Exception e) {

            }
            // get Matrix from file
        }
        m_startNode = null;
        myGraph = new Graph(inputMatrix);

        repaint();

        addMouseListener(new MouseAdapter() {
            //evenimentul care se produce la apasarea mousse-ului
            public void mousePressed(MouseEvent e) {
                Point chosedPoint = e.getPoint();
                setStartNode(isANodeClicked(chosedPoint));
                repaint();
            }

        });
    }

    public void generateRandMatrix(int rows, int columns)
    {
        Random rand = new Random();
        for(int i = 0; i< rows; i++)
            for(int j = 0; j < columns; j++)
            {
                int randomValue = Math.abs(rand.nextInt()) % 2;
                //if(randomValue == 0)
                  //z  randomValue = Math.abs(rand.nextInt()) % 2;

                inputMatrix[i][j] = randomValue;
            }
    }
        public Node isANodeClicked (Point e)
        {
            for (Node node : myGraph.getNodeList())
                if (e.getX() >= node.getRectangleCoords().getX() && e.getX() <= node.getRectangleCoords().getX() + GUI.rectWidth - 1
                        && e.getY() >= node.getRectangleCoords().getY() && e.getY() <= node.getRectangleCoords().getY() + GUI.rectHeight - 1)
                    return node;
            return null;
        }

    public static int nodePlace(Node node)
    {
        if(node.getIpoz() == 0) // top row
            return 1;

        if(node.getIpoz() == Main.getRows() - 1) // below row
            return 2;

        if(node.getJpoz() == 0) // first column
            return 3;

        if(node.getJpoz() == Main.getColumns() - 1) // last column
            return 4;

        return -1;
    }
        public void setStartNode(Node node)
        {
            m_startNode = node;
        }

        public void setInputStyle(boolean style)
        {
            m_inputStyle = style;
        }


        public static Node getStartNode()
        {
            return m_startNode;
        }


        protected void paintComponent (Graphics g)
        {
            GUI myGUI = new GUI(inputMatrix, g, myGraph);
            Node startNode = getStartNode();
            if(startNode != null)
        {   g.setColor(Color.BLUE);
            g.fillRect((int)startNode.getRectangleCoords().getX(), (int)startNode.getRectangleCoords().getY(), GUI.rectWidth, GUI.rectHeight);
            Graphics2D g2 = (Graphics2D) g;
            GUI.drawTheRoads(g2);}



        }

}





