import java.util.*;
import java.util.LinkedList;

class Graph {
    private static int nodeNr;
    private static int[][] adj = new int[100][100];

    public static HashSet<Node> newNodeList;

    public static Vector<Arc> newEdgesList;

    // Create a graph
    Graph(int s) {
        nodeNr = s;
        adj = new int[s][s];
        newNodeList = new HashSet<>();
        newEdgesList = new Vector<>();
    }

    public void setAdj(int[][] adj) {
        Graph.adj = adj;
    }

    int minKey(int[] key, Boolean[] primTree)
    {
        int min = Integer.MAX_VALUE, min_index = -1;

        for (int v = 0; v < nodeNr; v++)
            if (!primTree[v] && key[v] < min) {
                min = key[v];
                min_index = v;
            }

        return min_index;
    }

    void getPrimTree(int parent[])
    {
        for (int i = 1; i < nodeNr; i++)
        {
        for(Arc arc : MyPanel.listaArce)
        {
            if((arc.startNode.getNumber() == parent[i] || arc.startNode.getNumber() == i) && (arc.endNode.getNumber() == i || arc.endNode.getNumber() == parent[i]) && arc.weight == adj[parent[i]][i])
                newEdgesList.add(arc);
        }
        }
        setNewNodeList(newEdgesList);
    }

    void setNewNodeList(Vector<Arc> newEdgesList)
    {
        for(Arc arc : newEdgesList)
        { newNodeList.add(arc.startNode);
            newNodeList.add(arc.endNode);
        }
    }


    void primTree()
    {
        int node[] = new int[nodeNr];
        int weight[] = new int[nodeNr];
        Boolean primTree[] = new Boolean[nodeNr];

        for (int i = 0; i < nodeNr; i++)
        {
            weight[i] = Integer.MAX_VALUE;
            primTree[i] = false;
        }

        weight[0] = 0;
        node[0] = -1;

        for (int count = 0; count < nodeNr - 1; count++) {
            int u = minKey(weight, primTree);
            primTree[u] = true;

            for (int v = 0; v < nodeNr; v++)
            {
                if (adj[u][v] != 0 && !primTree[v] && adj[u][v] < weight[v]) {
                    node[v] = u;
                    weight[v] = adj[u][v];
                }
            }
        }

        getPrimTree(node);
    }
}

