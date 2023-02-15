
import util.Pair;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        draw();
    }

    public static void draw() {
        SwingUtilities.invokeLater(Main::initUI);
    }

    private static void initUI() {

        Reader.innitDoc("res/map2.xml");
        ArrayList<Node> listNodes = Reader.readNodes();
        java.util.Map<Pair<Integer, Integer>, Integer> listEdges = Reader.readEdges();
        Map map = new Map(listNodes, listEdges);

        JFrame frame = new JFrame("Luxembourg");
        frame.setSize(700, 900);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(map);
        frame.setVisible(true);
    }
}
