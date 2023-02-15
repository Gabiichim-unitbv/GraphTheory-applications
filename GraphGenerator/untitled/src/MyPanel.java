
import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.util.SplittableRandom;
import java.util.Vector;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.*;
import java.util.HashSet;


public class MyPanel extends JPanel {

    private int nodeNr;
    private int probability;
    private int node_diam = 30;
    private Vector<Node> listaNoduri;
    private HashSet<Arc> listaArce;

    Point pointStart = null;
    Point pointEnd = null;
    private static int XLimit = 1870;
    private static int YLimit = 950;

    private Random random;



    public MyPanel(int nodeNr, int probability) {
        this.nodeNr = nodeNr;
        this.probability = probability;

        random = new Random();

        listaNoduri = new Vector<Node>();
        listaArce = new HashSet<Arc>();

        if(this.nodeNr > 1000)
            node_diam = 15;
            else if(nodeNr > 200)
                node_diam = 20;

        for(int i = 0; i <nodeNr; i++)
        {
            Point point = new Point(generateRandomNum(20, XLimit), generateRandomNum(20, YLimit));
        addNode(point);
        }

        for(int i = 0; i <nodeNr; i++){
            if(canGenerateArc(probability))
            {
                pointStart = getArcDrawCoords(generateRandomNode());
                pointEnd = getArcDrawCoords(generateRandomNode());
               if(pointStart != pointEnd)
               {Arc arc = new Arc(pointStart, pointEnd);
                listaArce.add(arc);}
            }
        }

        repaint();
    }

    public Point getArcDrawCoords(Node node)
    {

        Point draw = new Point((int)node.getPoint().getX() + node_diam/2, (int)node.getPoint().getY() + node_diam/2);
        return draw;
    }

    public int generateRandomNum(int min, int max)
    {
        int randomNumber = ThreadLocalRandom.current().nextInt(min, max);
        return randomNumber;
    }


    private double getDistance(Point p1, Point p2) // D = √((x2 – x1)² + (y2 – y1)²)
    {
        double distance = Math.sqrt(Math.pow(p2.getX()-p1.getX(), 2) + Math.pow(p2.getY()-p1.getY(), 2));

        return distance;
    }

    public boolean canGenerateArc(int probability)
    {
        SplittableRandom random = new SplittableRandom();
        if (random.nextInt(1,101) <= probability)
            return true;
        return false;
    }



    private boolean areNodesInCollision(Node N1, Node N2)
    {
        if(getDistance(N1.getPoint(), N2.getPoint()) <= node_diam)
            return true;
        return false;
    }

    private void addNode(Point point) {
        boolean collisionFlag = false;
        Node node = new Node(point);

        for(int i =0; i<listaNoduri.size(); i++)
            if(areNodesInCollision(node, listaNoduri.elementAt(i)))
                collisionFlag = true;

        if(!collisionFlag)
        {listaNoduri.add(node);}
    }

    public Node generateRandomNode()
    {
            return listaNoduri.get(random.nextInt(listaNoduri.size()));
    }

    protected void paintComponent (Graphics g)
    {

      //  super.paintComponent(g);//apelez metoda paintComponent din clasa de baza
        g.drawString("This is the random generated Graph!", 10, 20);

        for (Arc arc : listaArce) {
            arc.drawArc(g);
        }

        for (Node nod : listaNoduri) {
            nod.drawNode(g, node_diam);
        }
        //deseneaza arcele existente in lista




    }

}


