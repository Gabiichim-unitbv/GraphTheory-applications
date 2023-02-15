import java.awt.Point;

public class Node {
    private final int m_matrixPoz;
    private final Point m_position;
    private  boolean m_exitStatus;

    private Point m_rectangleCoords;




public Node(Point position, int matrixPoz)
{
    m_position = position;
    m_matrixPoz = matrixPoz;
    m_exitStatus = false;


}

    // getters
    public int getIpoz()
{
    return (int)m_position.getX();
}

    public int getJpoz()
    {
        return (int)m_position.getY();
    }

    public int getMatrixPoz()
    {
        return m_matrixPoz;
    }

    public Point getRectangleCoords()
    {
        return m_rectangleCoords;
    }




public void setRectangleCoords(Point rectCoords)
{
    m_rectangleCoords = rectCoords;
}


    public boolean isExitNode()
    {
        return m_exitStatus;
    }

public void makeExitNodeIfCan(int rows, int columns)
{
    if(getIpoz() == 0 || getIpoz() == rows - 1)
        m_exitStatus = true;

    if(getJpoz() == 0 || getJpoz() == columns - 1)
        m_exitStatus = true;
}

@Override
public String toString()
{
    return "" + m_matrixPoz;
}

}
