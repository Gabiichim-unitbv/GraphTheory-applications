import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;


public class Arc
{
    private Point start;
    private Point end;

    private int startNode;
    private int endNode;

    public Arc(Point start, Point end, int startNode, int endNode)
    {
        this.start = start;
        this.end = end;
        this.startNode = startNode;
        this.endNode = endNode;
    }

    static public void drawArrow(Graphics g, int x0, int y0, int x1,
                                 int y1, int headLength, int headAngle) {
        double offs = headAngle * Math.PI / 180.0;
        double angle = Math.atan2(y0 - y1, x0 - x1);
        int[] xs = { x1 + (int) (headLength * Math.cos(angle + offs)), x1,
                x1 + (int) (headLength * Math.cos(angle - offs)) };
        int[] ys = { y1 + (int) (headLength * Math.sin(angle + offs)), y1,
                y1 + (int) (headLength * Math.sin(angle - offs)) };
        g.drawLine(x0, y0, x1, y1);
        g.drawPolyline(xs, ys, 3);
    }
    public int getStartNode()
    {
        return startNode;
    }

    public int getEndNode()
    {
        return endNode;
    }

    public void drawArc(Graphics g)
    {
        if (start != null)
        {
            g.setColor(Color.RED);
            drawArrow(g, start.x, start.y, end.x, end.y, 20, 20);
        }
    }


}
