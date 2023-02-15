import javax.swing.*;
import java.awt.*;
import java.util.*;

public class TopPanel extends JPanel {

    private Map<Integer, LinkedList<Integer>> m_adjList;
    private Vector<Integer> ordering;
    public TopPanel()
    {
        ordering = new Vector<>();
    }

    private int DFS(int v, boolean[] analyzed,
                           int[] departure, int time)
    {
        // Marcheaza nodul curent analizat.
        analyzed[v] = true;

        // itereaza pt fiecare incidenta a lui u.
        for (int u: m_adjList.get(v))
        {
            // daca u nu e analizat
            if (!analyzed[u]) {
                time = DFS(u, analyzed, departure, time);
            }
        }


        // set departure time of vertex `v`
        departure[v] = time++;

        return time;
    }

    boolean isAcyclic() {
        boolean[] discovered = new boolean[m_adjList.size()];

        int[] departure = new int[m_adjList.size()];

        int time = 0;

        for (int i = 0; i < m_adjList.size(); i++) {
            if (!discovered[i]) {
                time = DFS(i, discovered, departure, time);
            }

        }

        for (int u = 0; u < m_adjList.size(); u++)
        {

            for (int v: m_adjList.get(u))
            {
                if (departure[u] <= departure[v]) {
                    return false;
                }
            }
        }

        return true;
    }

    void topologicalSortUtil(int v, boolean visited[],
                             Stack stack)
    {
        // Marcheaza nodul curent ca vizitat.
        visited[v] = true;
        Integer i;

        // Merge recursiv pe toate nodurile incidente cu v.
        Iterator<Integer> it = m_adjList.get(v).iterator();
        while (it.hasNext())
        {
            i = it.next();
            if (!visited[i])
                topologicalSortUtil(i, visited, stack);
        }

        // Adauga v in stiva
        stack.push(new Integer(v));
    }

    void topologicalSort()
    {
        Stack stack = new Stack();

        // Marcheaza toate nodurile ca fiind nevizitate
        boolean visited[] = new boolean[m_adjList.size()];
        for (int i = 0; i < m_adjList.size(); i++)
            visited[i] = false;

        // Pentru fiecare varf se apeleaza functia recursiva topologicalSortUtil
        for (int i = 0; i < m_adjList.size(); i++)
            if (visited[i] == false)
                topologicalSortUtil(i, visited, stack);

        // Afiseaza stiva
        while (stack.empty()==false)
        {   Integer node = (Integer) stack.pop();
            ordering.add(node);}
    }

    protected void paintComponent(Graphics g) {
        m_adjList = MyPanel.getAdjList();
        super.paintComponent(g);//apelez metoda paintComponent din clasa de baza
        Graphics2D g2 = (Graphics2D) g;
        Font font = new Font("Serif", Font.PLAIN, 18);

        g2.setFont(font);
        if(m_adjList != null && !isAcyclic())
        {g2.setColor(Color.RED);
            g2.drawString("The graph contains cycles!", 10, 40);
            return;}
        topologicalSort();
        int x = 10;
        int y = 40;
        for(Integer i = 0; i<ordering.size(); i++)
        {
            g2.drawString(ordering.elementAt(i).toString(), x, y);
            x+=25;
            if(i != 0 && i % 11 == 0)
            {y+=40;
            x = 10;}

        }
        ordering.clear();

    }

}
