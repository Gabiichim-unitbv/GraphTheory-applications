import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
public class Node
{
    private Point point;

    public Node(Point point)
    {
        this.point = point;
    }
    public Point getPoint()
    {
        return this.point;
    }
    public int getCoordX() {
        return (int)point.getX();
    }
    public void setCoords(double coordX, double coordY) {
        this.point.setLocation(coordX, coordY);
    }
    public int getCoordY() {
        return (int)point.getY();
    }


    public void drawNode(Graphics g, int node_diam)
    {
        g.setColor(Color.GREEN);
        g.setFont(new Font("TimesRoman", Font.BOLD, 15));
        g.fillOval(this.getCoordX(), this.getCoordY(), node_diam, node_diam);
        g.setColor(Color.WHITE);
        g.drawOval(this.getCoordX(), this.getCoordY(), node_diam, node_diam);

    }
}
