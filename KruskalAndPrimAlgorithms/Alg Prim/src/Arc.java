import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;


public class Arc {
    private Point start;
    private Point end;
    public Node startNode;
    public Node endNode;
    public int weight;

    public Arc(Point start, Point end, int weight, Node startNode, Node endNode) {
        this.startNode = startNode;
        this.endNode = endNode;
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    private void drawArrow(Graphics g, int x1, int y1, int x2, int y2, int d, int h) {
        int dx = x2 - x1, dy = y2 - y1;
        double D = Math.sqrt(dx * dx + dy * dy);
        double xm = D - d, xn = xm, ym = h, yn = -h, x;
        double sin = dy / D, cos = dx / D;

        x = xm * cos - ym * sin + x1;
        ym = xm * sin + ym * cos + y1;
        xm = x;

        x = xn * cos - yn * sin + x1;
        yn = xn * sin + yn * cos + y1;
        xn = x;

        int[] xpoints = {x2, (int) xm, (int) xn};
        int[] ypoints = {y2, (int) ym, (int) yn};


        g.setColor(Color.black);
        g.fillPolygon(xpoints, ypoints, 3);
    }

    public void drawArc(Graphics g) {

        if (start != null) {
            g.drawLine(start.x, start.y, end.x, end.y);
            g.setColor(Color.BLACK);
            g.drawString(((Integer) weight).toString(), (start.x + end.x) / 2, (start.y + end.y) / 2);
        }
    }
}
