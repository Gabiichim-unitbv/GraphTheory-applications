
    import util.Pair;

    import javax.swing.*;
    import java.awt.*;
    import java.awt.event.MouseAdapter;
    import java.awt.event.MouseEvent;
    import java.util.*;
    import java.awt.Font;

    public class Map extends JPanel {
        private final ArrayList<Node> nodes;
        private Node pointStart;
        private Node pointEnd;
        private boolean isMarking = false;
        private boolean isWorking = false;
        private java.util.Map<Node, Node> cameFrom = new HashMap<>();
        private java.util.Map<Node, Integer> costSoFar = new HashMap<>();

        public Map(ArrayList<Node> nodeList, java.util.Map<Pair<Integer, Integer>, Integer> edges) {
            this.nodes = nodeList;
            this.pointStart = null;
            this.pointEnd = null;
            this.addMouseListener(new MouseHandler());
            repaint();
        }

        protected void paintComponent(Graphics g) {

            Graphics2D g2 = (Graphics2D) g;
            g2.clearRect(0, 0, 2000, 2000);

            g.setColor(Color.BLACK);
            g.setFont(new Font("TimesRoman", Font.PLAIN, 30));
            for (Node n : nodes) {
                for (Pair<Integer, Integer> connection : n.getConnections()) {
                    g.drawLine(n.getCenter().x, n.getCenter().y, nodes.get(connection.getKey()).getCenter().x, nodes.get(connection.getKey()).getCenter().y);
                }
            }


            if (pointStart != null) {
                g2.setColor(Color.YELLOW);
                g2.setStroke(new BasicStroke(10));
                g2.drawLine(pointStart.getCenter().x, pointStart.getCenter().y, pointStart.getCenter().x, pointStart.getCenter().y);
                g.drawString("Start id:" + pointStart.getId(), 400, 100);
            }

            if (pointEnd != null) {
                g2.setColor(Color.yellow);
                g2.setStroke(new BasicStroke(10));
                g2.drawLine(pointEnd.getCenter().x, pointEnd.getCenter().y, pointEnd.getCenter().x, pointEnd.getCenter().y);
                g.drawString("End id:" + pointEnd.getId(), 400, 150);
            }

            if (isMarking) {

                Node current = pointEnd;


                while (cameFrom.containsKey(current)) {
                    Node next = cameFrom.get(current);


                    assert current != null;
                    assert next != null;

                    g2.setStroke(new BasicStroke(3));
                    g2.setColor(Color.RED);
                    g2.drawLine(current.getCenter().x, current.getCenter().y, next.getCenter().x, next.getCenter().y);

                    if (next == pointStart) {
                        break;
                    }

                    current = next;
                }

                g.drawString("Total cost: " + costSoFar.get(pointEnd), 400, 200);

                isWorking = false;
                pointStart = null;
                pointEnd = null;


            }
        }

        private class MouseHandler extends MouseAdapter {
            @Override
            public void mouseClicked(MouseEvent e) {
                Point current = e.getPoint();

                Node currNode = null;

                if (pointStart == null || pointEnd == null) {
                    currNode = new Node(0, current.x, current.y);
                    isMarking = false;
                }

                if (pointStart == null) {
                    pointStart = findNearest(currNode);
                    isWorking = true;
                    repaint();
                } else if (pointEnd == null) {
                    pointEnd = findNearest(currNode);
                    repaint();
                    if (pointEnd != pointStart) {

                            dijkstra();

                    } else {
                        pointStart = pointEnd = null;
                    }
                }

                repaint();
            }

        }

        private Node findNearest(Node x) {
            double distance = Double.MAX_VALUE;
            Node seeked = null;

            for (Node node : nodes)
            {
                double newDist = euclideanDist(node.getCenter(), x.getCenter());
                if (newDist < distance) {
                    distance = newDist;
                    seeked = node;
                }
            }

            return seeked;
        }

        private double euclideanDist(Point p1, Point p2)
        {
            int x1 = p1.x;
            int y1 = p1.y;

            int x2 = p2.x;
            int y2 = p2.y;
            return Math.sqrt((double) (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
        }

        private void dijkstra() {
            if (isWorking)
            {

                cameFrom = new HashMap<>();
                costSoFar = new HashMap<>();

                PriorityQueue<Node> frontier = new PriorityQueue<>();

                pointStart.setPriority(0);
                frontier.add(pointStart);

                cameFrom.put(pointStart, null);
                costSoFar.put(pointStart, 0);

                while (!frontier.isEmpty())
                {
                    Node current = frontier.remove();

                    if (current == pointEnd)
                    {
                        isMarking = true; // is found
                        repaint();
                        break;
                    }

                    for (Pair<Integer, Integer> nextConnection : current.getConnections())
                    {

                        Node next = nodes.get(nextConnection.getKey());

                        int newCost = costSoFar.get(current) + nextConnection.getValue();

                        if (!costSoFar.containsKey(next) || newCost < costSoFar.get(next)) {

                            costSoFar.put(next, newCost);
                            next.setPriority(newCost);
                            frontier.add(next);
                            cameFrom.put(next, current);
                        }
                    }
                }

                if (!isMarking)
                {
                    pointEnd = pointStart = null;  // not found
                }
            }
        }
    }
