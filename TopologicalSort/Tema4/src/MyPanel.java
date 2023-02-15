import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.PrintWriter;
import java.util.*;
import javax.swing.*;
import java.io.File;
import java.io.IOException;



public class MyPanel extends JPanel {
    private int nodeNr = 0;
    private int node_diam = 30;
    private Vector<Node> listaNoduri;
    private Vector<Arc> listaArce;
    Point pointStart = null;
    Point pointEnd = null;
    boolean isDragging = false;
    int startNode = -1;
    int endNode = -1;

  static private Map<Integer, LinkedList<Integer>> m_arcs;

    private final Vector<Integer> m_predecesors;


    public MyPanel()
    {
        listaNoduri = new Vector<Node>();
        listaArce = new Vector<Arc>();
        m_arcs = new HashMap<>();
        m_predecesors = new Vector<>();

        // borderul panel-ului
        setBorder(BorderFactory.createLineBorder(Color.black));
        addMouseListener(new MouseAdapter() {
            //evenimentul care se produce la apasarea mousse-ului
            public void mousePressed(MouseEvent e) {
                if(checkNodeToArcDistance(e.getPoint()))
                    pointStart = e.getPoint();

            }


            //evenimentul care se produce la eliberarea mousse-ului
            public void mouseReleased(MouseEvent e) {
                if (!isDragging) {
                    Point point = new Point(e.getX(), e.getY());
                    addNode(point);
                }
                else {
                    if(checkNodeToArcDistance(e.getPoint()) && getDistance(e.getPoint(), pointStart) >= 2*node_diam)
                    {	 startNode = getNodeNumber(pointStart);
                        endNode = getNodeNumber(pointEnd);
                        Arc arc = new Arc(pointStart, pointEnd, startNode, endNode);
                        listaArce.add(arc);
                        m_arcs.get(startNode).add(endNode);
                    }

                }
                repaint();
                pointStart = null;
                isDragging = false;
                showAdjacency();
                // afiseaza in fisier matricea de adiacenta la fiecare modificare a grafului
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            //evenimentul care se produce la drag&drop pe mouse
            public void mouseDragged(MouseEvent e) {
                pointEnd = e.getPoint();
                isDragging = true;
                repaint();
            }
        });


    }



    //metoda care se apeleaza la eliberarea mouse-ului
    private void addNode(Point point) {
        boolean collisionFlag = false;
        Node node = new Node(point, nodeNr);

        for(int i =0; i<listaNoduri.size(); i++)
            if(areNodeInCollision(node, listaNoduri.elementAt(i)))
                collisionFlag = true;

        if(!collisionFlag)
        {listaNoduri.add(node);
            m_arcs.put(nodeNr, new LinkedList<>());
            nodeNr++;
            repaint();
        }
    }


    private int getNodeNumber(Point point)
    {
        for(int i =0; i<listaNoduri.size(); i++)
        {
            if(getDistance(point, listaNoduri.elementAt(i).getPoint()) <= node_diam)
                return listaNoduri.elementAt(i).getNumber();
        }
        return -1;
    }
    private boolean checkNodeToArcDistance(Point e) {
        for(int i =0; i<listaNoduri.size(); i++)
        {if (getDistance(listaNoduri.elementAt(i).getPoint(), e) <= node_diam)
            return true;}
        return false;

    }
    private double getDistance(Point p1, Point p2) // D = √((x2 – x1)² + (y2 – y1)²)
    {
        double distance = Math.sqrt(Math.pow(p2.getX()-p1.getX(), 2) + Math.pow(p2.getY()-p1.getY(), 2));

        return distance;
    }



    private boolean areNodeInCollision(Node N1, Node N2)
    {
        if(getDistance(N1.getPoint(), N2.getPoint()) <= 2*node_diam)
            return true;
        return false;
    }

    private boolean checkNodeConnection(int nodeNumber1, int nodeNumber2)
    {
        for(int i = 0; i<listaArce.size(); i++)
            if(nodeNumber1 == listaArce.elementAt(i).getStartNode() && nodeNumber2 == listaArce.elementAt(i).getEndNode()
                    || nodeNumber1 == listaArce.elementAt(i).getEndNode() && nodeNumber2 == listaArce.elementAt(i).getStartNode())
                return true;

        return false;
    }


    private void showAdjacency()
    {
        try {
            File myObj = new File("matrix.txt");
            PrintWriter output = new PrintWriter(myObj);
            output.println(listaNoduri.size());
            for (int i = 0; i < listaNoduri.size(); i++) {
                for (int j = 0; j < listaNoduri.size(); j++) {
                    if (i != j && checkNodeConnection(listaNoduri.elementAt(i).getNumber(), listaNoduri.elementAt(j).getNumber())) {
                        output.write("1 ");
                    } else {
                        output.write("0 ");

                    }
                }
                output.write("\n");
            }
            output.close();
        }
        catch (IOException e)
        {System.out.println("An error occurred :(.");
            e.printStackTrace();}


    }

      static public Map<Integer, LinkedList<Integer>> getAdjList()
        {
            return m_arcs;
        }

    //se executa atunci cand apelam repaint()
    protected void paintComponent(Graphics g)
    {

        super.paintComponent(g);//apelez metoda paintComponent din clasa de baza
        g.drawString("This is my Graph!", 10, 20);

        //deseneaza arcele existente in lista
		/*for(int i=0;i<listaArce.size();i++)
		{
			listaArce.elementAt(i).drawArc(g);
		}*/
        for (Arc a : listaArce)
        {
                   a.drawArc(g);
        }
        //deseneaza arcul curent; cel care e in curs de desenare
        if (pointStart != null)
        {
            g.setColor(Color.RED);
            g.drawLine(pointStart.x, pointStart.y, pointEnd.x, pointEnd.y);
        }
        //deseneaza lista de noduri
        for(int i=0; i<listaNoduri.size(); i++)
        {
            listaNoduri.elementAt(i).drawNode(g, node_diam);
        }
		/*for (Node nod : listaNoduri)
		{
			nod.drawNode(g, node_diam, node_Diam);
		}*/
    }
}
