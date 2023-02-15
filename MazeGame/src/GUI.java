import java.awt.*;
import java.awt.geom.Line2D;
import java.util.Vector;



public class GUI {

    static Vector<Integer> roads;
    static int rectWidth = 120;
    static int rectHeight = 60;
    private final int[][] m_inputMatrix;
    private static Graphics gr;
    static private Graph m_myGraph;

    public GUI(int[][] inputMatrix, Graphics g, Graph myGraph) {
        m_inputMatrix = inputMatrix;
        gr = g;
        m_myGraph = myGraph;

        drawTheGrid();


    }


    public void drawTheGrid() {
        // starting from 50 value
        int xPoz = 50;
        int yPoz = 50;
        for (int i = 0; i < Main.getRows(); i++) {
            for (int j = 0; j < Main.getColumns(); j++) {
                if (m_inputMatrix[i][j] == 1) {
                    gr.setColor(Color.WHITE);
                    int index = m_myGraph.find(i, j);
                    if (index != -1)
                        m_myGraph.getNodeList().elementAt(index).setRectangleCoords(new Point(xPoz, yPoz));
                } else
                    gr.setColor(Color.BLACK);

                gr.fillRect(xPoz, yPoz, rectWidth, rectHeight);

                gr.setColor(Color.ORANGE);
                gr.drawRect(xPoz, yPoz, rectWidth, rectHeight);


                xPoz += rectWidth;
            }
            xPoz = 50;
            yPoz += rectHeight;

        }
    }


    public static void drawTheRoads(Graphics2D g) {


        g.setColor(Color.RED);

        GUI.roads = new Vector<>();
        for (Node node : m_myGraph.getNodeList()) {
            if (node.isExitNode()) {
                m_myGraph.breadthFirstSearch(node.getMatrixPoz());
                m_myGraph.getRoadFrom(MyPanel.getStartNode().getMatrixPoz(), GUI.roads);


                for(int i = 0; i < GUI.roads.size() - 1; i++) {
                    {
                        float xStart = (int) m_myGraph.getNodeList().elementAt(roads.elementAt(i)).getRectangleCoords().getX() + rectWidth / 2;
                        float yStart = (int)m_myGraph.getNodeList().elementAt(roads.elementAt(i)).getRectangleCoords().getY() + rectHeight / 2;
                        float xEnd = (int)m_myGraph.getNodeList().elementAt(roads.elementAt(i+1)).getRectangleCoords().getX() + rectWidth / 2;
                        float yEnd = (int)m_myGraph.getNodeList().elementAt(roads.elementAt(i+1)).getRectangleCoords().getY() + rectHeight / 2;

                        g.setStroke(new BasicStroke(5));
                        g.draw(new Line2D.Float(xStart, yStart, xEnd, yEnd));
                    }
                    Node exitNode = m_myGraph.getNodeList().elementAt(roads.elementAt(roads.size() - 1));
                    float xStart = (int) exitNode.getRectangleCoords().getX() + rectWidth / 2;
                    float yStart = (int)exitNode.getRectangleCoords().getY() + rectHeight / 2;

                    int nodePlace = MyPanel.nodePlace(exitNode);

                    switch(nodePlace)
                    {
                        case 1:
                        {float yEnd = (int) exitNode.getRectangleCoords().getY();
                            g.draw(new Line2D.Float(xStart, yStart, xStart, yEnd));
                        break;
                        }

                        case 2:
                        {float yEnd = (int) exitNode.getRectangleCoords().getY() + rectHeight;
                            g.draw(new Line2D.Float(xStart, yStart, xStart, yEnd));
                            break;
                        }

                        case 3:
                        {float xEnd = (int) exitNode.getRectangleCoords().getX();
                            g.draw(new Line2D.Float(xStart, yStart, xEnd, yStart));
                            break;
                        }

                        case 4:
                        {float xEnd = (int) exitNode.getRectangleCoords().getX() + rectWidth;
                            g.draw(new Line2D.Float(xStart, yStart, xEnd, yStart));
                            break;
                        }

                        default:
                            break;
                    }
                }

                GUI.roads.removeAllElements();
                GUI.roads = new Vector<>();
                m_myGraph.clearPredecesors();


            }



        }
    }

}