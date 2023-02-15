import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Main {

    private static Integer rows;
    private static Integer columns;


    static public void initUI()
    {
        // main panel
        JFrame myFrame = new JFrame("Maze Game");
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setSize(1280, 720);


        final JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 130);
        JPanel inputFieldsPanel = new JPanel();
        JPanel inputButtonsPanel = new JPanel();

        final JFrame selectFrame = new JFrame();
        selectFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        selectFrame.setSize(300, 130);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.BLACK);
        buttonPanel.setLayout(new FlowLayout());

        JButton btnGenerate = new JButton("Generate Matrix");
        JButton btnInput = new JButton("Get Input Matrix");
        btnGenerate.setBackground(Color.ORANGE);
        btnInput.setBackground(Color.RED);
        btnGenerate.setFocusable(false);
        btnInput.setFocusable(false);




        inputFieldsPanel.setLayout(new BoxLayout(inputFieldsPanel, BoxLayout.PAGE_AXIS));
        inputButtonsPanel.setLayout(new FlowLayout());

        JLabel lblRows = new JLabel("Rows");
        JTextField txtRows = new JTextField("");
        JLabel lblColumns = new JLabel("Columns");
        JTextField txtColumns = new JTextField("");

        inputFieldsPanel.add(lblRows);
        inputFieldsPanel.add(txtRows);
        inputFieldsPanel.add(lblColumns);
        inputFieldsPanel.add(txtColumns);

        JButton btnOk = new JButton("OK");
        JButton btnCancel = new JButton("Cancel");

        inputButtonsPanel.add(btnOk);
        inputButtonsPanel.add(btnCancel);

        frame.add(inputFieldsPanel, BorderLayout.PAGE_START);
        frame.add(inputButtonsPanel, BorderLayout.PAGE_END);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();

        btnOk.addActionListener(e -> {
            rows = Integer.valueOf(txtRows.getText());
            columns = Integer.valueOf(txtColumns.getText());
            frame.setVisible(false);

            MyPanel myPanel = new MyPanel(true);
            myPanel.setInputStyle(false);
            myFrame.add(myPanel);
            myFrame.setVisible(true);
        });

        btnCancel.addActionListener(e -> frame.dispose());

        buttonPanel.add(btnGenerate);
        buttonPanel.add(btnInput);
        selectFrame.add(buttonPanel);

        selectFrame.setVisible(true);
        btnGenerate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectFrame.setVisible(false);

                frame.setVisible(true);
            }
        });

        btnInput.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectFrame.setVisible(false);

                MyPanel myPanel = new MyPanel(false);

                myFrame.add(myPanel);
                myFrame.setVisible(true);
            }
        });





    }

    public static Integer getRows()
    {
        return rows;
    }

    public static Integer getColumns()
    {
        return columns;
    }

    public static void setRows(int row)
    {
        rows = row;
    }

    public static void setColumns(int column)
    {
        columns = column;
    }

    public static void main(String[] args) {
        //new Thread()
        SwingUtilities.invokeLater(Main::initUI);


    }
}