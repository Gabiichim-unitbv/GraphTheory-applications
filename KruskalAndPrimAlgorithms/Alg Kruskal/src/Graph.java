import java.util.*;
import java.util.LinkedList;

class Graph {
    static public HashSet<Node> nodeList = new HashSet<>();
    static public Vector<Arc> edgeList = new Vector<>();
    static class Edge implements Comparable<Edge> {
        int src, dest, weight;
        Arc arc;
        public int compareTo(Edge compareEdge)
        {
            return this.weight - compareEdge.weight;
        }
    };

    class subset {
        int parent, rank;
    };

    int V, E;
    Edge edge[];
    Graph(int v, int e)
    {
        V = v;
        E = e;
        edge = new Edge[E];
        for (int i = 0; i < e; ++i)
            edge[i] = new Edge();
    }

    void setEdges(Edge[] edgeCopy){
        for (int i = 0; i < E; ++i)
            edge[i] = edgeCopy[i];
    }

    int find(subset subsets[], int i)
    {

        if (subsets[i].parent != i)
            subsets[i].parent
                    = find(subsets, subsets[i].parent);

        return subsets[i].parent;
    }

    void Union(subset subsets[], int x, int y)
    {
        int xroot = find(subsets, x);
        int yroot = find(subsets, y);

        if (subsets[xroot].rank < subsets[yroot].rank)
            subsets[xroot].parent = yroot;
        else if (subsets[xroot].rank > subsets[yroot].rank)
            subsets[yroot].parent = xroot;

        else {
            subsets[yroot].parent = xroot;
            subsets[xroot].rank++;
        }
    }


    void KruskalTree()
    {
        Edge Graph[] = new Edge[V];

        int e = 0;

        int i = 0;
        for (i = 0; i < V; ++i)
            Graph[i] = new Edge();

        Arrays.sort(edge);

        subset subsets[] = new subset[V];
        for (i = 0; i < V; ++i)
            subsets[i] = new subset();

        for (int v = 0; v < V; ++v) {
            subsets[v].parent = v;
            subsets[v].rank = 0;
        }

        i = 0;

        while (e < V - 1) {
            Edge next_edge = edge[i++];

            int x = find(subsets, next_edge.src);
            int y = find(subsets, next_edge.dest);

            if (x != y) {
                Graph[e++] = next_edge;
                Union(subsets, x, y);
            }
        }

        int minimumCost = 0;
        for (i = 0; i < e; ++i) {
            for(Arc arc : MyPanel.listaArce)
            {
                if((arc.startNode == Graph[i].arc.startNode || arc.startNode == Graph[i].arc.endNode)
                        && (arc.endNode == Graph[i].arc.endNode || arc.endNode == Graph[i].arc.startNode) && arc.weight == Graph[i].weight)
                    edgeList.add(arc);
            }
            minimumCost += Graph[i].weight;
        }
        System.out.println("Arbore de cost minim: "
                + minimumCost);
        setNodeList(edgeList);
    }

    void setNodeList(Vector<Arc> newEdgesList)
    {
        for(Arc arc : newEdgesList)
        {
            nodeList.add(arc.startNode);
            nodeList.add(arc.endNode);
        }
    }

}

