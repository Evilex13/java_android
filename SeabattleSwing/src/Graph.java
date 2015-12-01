import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Graph extends JFrame {

    static Field field = new Field();
    JButton[][] buttons = new JButton[Field.SIZE][Field.SIZE];

    public Graph() {
        JFrame frame = new JFrame("Морской бой");
        JPanel panel = new JPanel();
        frame.setResizable(false);
        frame.add(panel);

        panel.setLayout(new GridLayout(Field.SIZE + 1, Field.SIZE + 1));

        for (int i = 0; i < Field.SIZE + 1; i++) {
            for (int j = 0; j < Field.SIZE + 1; j++) {
                if ((i == 0) && (j == 0)) {
                    JTextField field = new JTextField();
                    panel.add(field);
                }
                if ((i == 0) && (j != 0)) {
                    JTextField field = new JTextField("" + j);
                    field.setHorizontalAlignment(SwingConstants.CENTER);
                    panel.add(field);
                }
                if ((j == 0) && (i != 0)) {
                    JTextField field = new JTextField("" + Field.ALPHABET[i - 1]);
                    field.setHorizontalAlignment(SwingConstants.CENTER);
                    panel.add(field);
                }
                if ((i != 0) && (j != 0)) {
                    buttons[i - 1][j - 1] = new JButton();
                    buttons[i - 1][j - 1].setSize(50, 50);
                    buttons[i - 1][j - 1].addActionListener(listenerBtn);
                    panel.add(buttons[i - 1][j - 1]);
                }
            }
        }
        frame.setSize(660, 660);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public ActionListener listenerBtn = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            int col = 0;
            int row = 0;
            JButton tempBtn = (JButton) e.getSource();
            for (int i = 0; i < Field.SIZE; i++) {
                for (int j = 0; j < Field.SIZE; j++) {
                    if (e.getSource() == buttons[i][j]) {
                        col = i;
                        row = j;
                        break;
                    }
                }
            }
            if ((field.cellsWithShips[col][row] == -1) || (field.cellsWithShips[col][row] == 0)) {
                tempBtn.setBackground(Color.CYAN);
            } else {
                tempBtn.setBackground(Color.GREEN);
            }
            tempBtn.setEnabled(false);
            field.checkShipHealth(col, row);
            for (int i = 0; i < Field.SIZE; i++) {
                for (int j = 0; j < Field.SIZE; j++) {
                    if (field.cells[i][j] == '*') {
                        buttons[i][j].setBackground(Color.CYAN);
                        buttons[i][j].setEnabled(false);
                    } else  if (field.cells[i][j] != '.'){
                        buttons[i][j].setBackground(Color.GREEN);
                        buttons[i][j].setEnabled(false);
                    }
                }
            }
            if (field.isNotGameOver()) {
                JOptionPane.showMessageDialog(null, "Все корабли уничтожены!");
            }
        }
    };

    public static void main(String[] args) {
        new Graph();
    }
}
