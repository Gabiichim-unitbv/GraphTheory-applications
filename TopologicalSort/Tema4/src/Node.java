import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
public class Node
{
    private Point point;
    private int number;

    public Node(Point point, int number)
    {
        this.point = point;
        this.number = number;
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
    public int getNumber() {
        return number;
    }
    public void setNumber(int number) {
        this.number = number;
    }

    public void drawNode(Graphics g, int node_diam)
    {
        g.setColor(Color.RED);
        g.setFont(new Font("TimesRoman", Font.BOLD, 15));
        g.fillOval(this.getCoordX(), this.getCoordY(), node_diam, node_diam);
        g.setColor(Color.WHITE);
        g.drawOval(this.getCoordX(), this.getCoordY(), node_diam, node_diam);
        if(number < 10)
            g.drawString(((Integer)number).toString(), this.getCoordX()+13, this.getCoordY()+20);
        else
            g.drawString(((Integer)number).toString(), this.getCoordX()+8, this.getCoordY()+20);
    }
}
