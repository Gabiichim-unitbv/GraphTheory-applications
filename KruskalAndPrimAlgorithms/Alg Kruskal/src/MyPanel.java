import java.awt.Color;

import java.awt.event.*;
import java.lang.Math;
import java.awt.Graphics;
import java.awt.Point;
import java.util.*;
import javax.swing.*;
import java.io.*;


public class MyPanel extends JPanel {
    private Graph g;
    private int nodeNr = 0;
    private int node_diam = 30;
    private int numberOfNodes = 0;
    static public Vector<Node> listaNoduri;
    static public Vector<Arc> listaArce;
    Point pointStart = null;
    Point pointEnd = null;
    boolean isDragging = false;
    boolean clicked = false;
    JButton kruskalButton;



    Graph.Edge[] edge;

    public MyPanel() {

        kruskalButton = new JButton("Generate Kruskal tree");
        kruskalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                g = new Graph(numberOfNodes, listaArce.size());
                g.setEdges(edge);
                g.KruskalTree();
                clicked = true;
                repaint();
            }
        });

        add(kruskalButton);
        listaNoduri = new Vector<Node>();
        listaArce = new Vector<Arc>();
        edge = new Graph.Edge[100];
        for (int i = 0; i < 100; ++i)
            edge[i] = new Graph.Edge();
        // borderul panel-ului
        setBorder(BorderFactory.createLineBorder(Color.black));
        addMouseListener(new MouseAdapter() {
            //evenimentul care se produce la apasarea mousse-ului
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1)
                    pointStart = e.getPoint();
            }

            //evenimentul care se produce la eliberarea mousse-ului
            public void mouseReleased(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    boolean superposition = true;

                    for (int i = 0; i < listaNoduri.size(); i++) {
                        double distanta = Math.sqrt(Math.pow(listaNoduri.elementAt(i).getCoordX() - e.getX(), 2) +
                                Math.pow(listaNoduri.elementAt(i).getCoordY() - e.getY(), 2));
                        if (distanta < 1.3 * node_diam)
                            superposition = false;
                    }


                    if (!isDragging) {
                        if (superposition == true) {
                            addNode(e.getX(), e.getY());
                            numberOfNodes++;
                        }
                    } else {

                        boolean isOnNodeStart = false;
                        boolean isOnNodeEnd = false;
                        int nodeStart = -1;
                        int nodeEnd = -1;
                        Node startNode = null;
                        Node endNode = null;

                        for (int i = 0; i < listaNoduri.size(); i++) {
                            if ((pointStart.getX() > listaNoduri.elementAt(i).getCoordX()) && (pointStart.getX() < listaNoduri.elementAt(i).getCoordX() + node_diam - 2))
                                if ((pointStart.getY() > listaNoduri.elementAt(i).getCoordY()) && (pointStart.getY() < listaNoduri.elementAt(i).getCoordY() + node_diam - 2)) {
                                    isOnNodeStart = true;
                                    nodeStart = listaNoduri.elementAt(i).getNumber();
                                    startNode = listaNoduri.elementAt(i);
                                }
                            if ((pointEnd.getX() > listaNoduri.elementAt(i).getCoordX()) && (pointEnd.getX() < listaNoduri.elementAt(i).getCoordX() + node_diam - 2))
                                if ((pointEnd.getY() > listaNoduri.elementAt(i).getCoordY()) && (pointEnd.getY() < listaNoduri.elementAt(i).getCoordY() + node_diam - 2)) {
                                    isOnNodeEnd = true;
                                    nodeEnd = listaNoduri.elementAt(i).getNumber();
                                    endNode = listaNoduri.elementAt(i);
                                }
                        }

                        if (isOnNodeStart && isOnNodeEnd && (nodeEnd != nodeStart)) {
                            // enter weight
                            String weight = JOptionPane.showInputDialog("Enter weight");
                            Arc arc = new Arc(pointStart, pointEnd, Integer.parseInt(weight), startNode, endNode);
                            edge[listaArce.size()].src = nodeStart;
                            edge[listaArce.size()].dest = nodeEnd;
                            edge[listaArce.size()].weight = Integer.parseInt(weight);
                            edge[listaArce.size()].arc = arc;
                            listaArce.add(arc);
                        }
                    }
                    pointStart = null;
                    isDragging = false;
                    repaint();
                }
            }
        });


        addMouseMotionListener(new MouseMotionAdapter() {
            //evenimentul care se produce la drag&drop pe mousse
            public void mouseDragged(MouseEvent e) {
                pointEnd = e.getPoint();
                isDragging = true;
                repaint();
            }

        });

    }


    //metoda care se apeleaza la eliberarea mouse-ului
    private void addNode(int x, int y) {
        Node node = new Node(x, y, nodeNr);
        listaNoduri.add(node);
        nodeNr++;

        repaint();
    }


    //se executa atunci cand apelam repaint()
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);//apelez metoda paintComponent din clasa de baza
        g.drawString("This is my Graph!", 10, 20);

        if(clicked)
            for (Arc a : Graph.edgeList) {
            { g.setColor(Color.GREEN);
                a.drawArc(g);}

            for (Node node : Graph.nodeList)
                node.drawNode(g, node_diam, Color.GREEN);
        }
else {
            for (Arc a : listaArce) {
                g.setColor(Color.RED);
                a.drawArc(g);
            }
            if (pointStart != null) {
                g.setColor(Color.RED);
                g.drawLine(pointStart.x, pointStart.y, pointEnd.x, pointEnd.y);
            }
            //deseneaza lista de noduri


            for (int k = 0; k < listaNoduri.size(); k++) {
                listaNoduri.elementAt(k).drawNode(g, node_diam, Color.RED);
            }
        }
    }

}





