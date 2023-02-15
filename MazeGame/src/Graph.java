import java.awt.*;
import java.util.*;

public class Graph {

    private int nodeNr;
    private final int[][] m_inputMatrix;
    private final Vector<Node> m_nodeList;
    private final Map<Integer, LinkedList<Node>> m_arcs;

    private final Vector<Integer> m_predecesors;

    public Graph(int[][] inputMatrix) {
        m_nodeList = new Vector<>();
        m_predecesors = new Vector<>();
        m_inputMatrix = inputMatrix;

        m_arcs = new HashMap<>();
        // add the nodes
        nodeNr = -1;
        for (int i = 0; i < Main.getRows(); i++) {
            for (int j = 0; j < Main.getColumns(); j++)
                if (m_inputMatrix[i][j] == 1) {
                    nodeNr++;
                    Node node = new Node(new Point(i, j), nodeNr);
                    node.makeExitNodeIfCan(Main.getRows(), Main.getColumns());
                    m_nodeList.add(node);
                }
        }

        for(int i = 0; i<m_nodeList.size(); i++)
            m_arcs.put(i, new LinkedList<>());

        // add the arcs between the nodes
        for(int i = 0; i < m_nodeList.size(); i++)
        {
         for(int j = 0; j < m_nodeList.size(); j++)
                if(areNeighbours(m_nodeList.elementAt(i), m_nodeList.elementAt(j)))
                    m_arcs.get(i).add(m_nodeList.elementAt(j));

        }


    }

    public int find(int i, int j)
    {
        for(Node node : m_nodeList)
            if(node.getIpoz() == i && node.getJpoz() == j)
                return node.getMatrixPoz();
        return -1;
    }




    public boolean areNeighbours(Node node1, Node node2)
    {
        // left neigh.
        if(node1.getJpoz() - 1 == node2.getJpoz() && node1.getIpoz() == node2.getIpoz())
            return true;

        // right neigh.
        if(node1.getJpoz() +1 == node2.getJpoz() && node1.getIpoz() == node2.getIpoz())
            return true;

        // top neigh.
        if(node1.getIpoz() - 1 == node2.getIpoz() && node1.getJpoz() == node2.getJpoz())
            return true;

        // below neigh.
        if(node1.getIpoz() + 1 == node2.getIpoz() && node1.getJpoz() == node2.getJpoz())
            return true;

        return false;
    }

    public void showGraph()
    {
        System.out.println(this);
    }

    public Vector<Node> getNodeList()
    {
        return m_nodeList;
    }

    @Override
    public String toString()
    {
        for(Map.Entry<Integer, LinkedList<Node>> current : m_arcs.entrySet())
        {
            System.out.print(current.getKey() + ": " + current.getValue() + "\n");
        }
        return "";
    }

    public void breadthFirstSearch(int exitNode)
    {
        for(Node node : m_nodeList)
        {
            m_predecesors.add(-1);
        }
        boolean[] visited = new boolean[nodeNr + 1];
        Queue<Integer> q = new LinkedList<>();
        visited[exitNode] = true;
        q.offer(exitNode);

        while(!q.isEmpty())
        {
            int analyzed = q.poll();
            for(Node node : m_arcs.get(analyzed))
                if(!visited[node.getMatrixPoz()]) {
                    visited[node.getMatrixPoz()] = true;
                    m_predecesors.set(node.getMatrixPoz(), analyzed);
                    q.offer(node.getMatrixPoz());

                }
        }
    }

    public void getRoadFrom(int startNode, Vector<Integer> roads)
    {
        if(m_predecesors.elementAt(startNode) == -1)
            return;
        roads.add(startNode);
        while(m_predecesors.elementAt(startNode) != -1)
        {
            int x = m_predecesors.elementAt(startNode);
            GUI.roads.add(x);

            startNode = x;
        }
        System.out.print("\n");
    }



    public void clearPredecesors()
    {
        m_predecesors.removeAllElements();
    }

}


