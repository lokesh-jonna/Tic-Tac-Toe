import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeGUI extends JFrame {
    private JButton[][] buttons = new JButton[3][3];
    private char currentPlayer = 'X';

    public TicTacToeGUI() {
        setTitle("Tic-Tac-Toe");
        setSize(300, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 3));

        initializeButtons();
    }

    private void initializeButtons() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col] = new JButton("");
                buttons[row][col].setFont(new Font("Arial", Font.PLAIN, 40));
                buttons[row][col].setBackground(Color.WHITE);
                buttons[row][col].addActionListener(new ButtonListener(row, col));

                add(buttons[row][col]);
            }
        }
    }

    private class ButtonListener implements ActionListener {
        private int row;
        private int col;

        public ButtonListener(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (buttons[row][col].getText().equals("") && !checkWin()) {
                buttons[row][col].setText(String.valueOf(currentPlayer));
                buttons[row][col].setForeground(currentPlayer == 'X' ? Color.BLUE : Color.RED);
                buttons[row][col].setEnabled(false);

                if (checkWin()) {
                    String winnerMessage = "Player " + currentPlayer + " wins!";
                    JOptionPane.showMessageDialog(null, winnerMessage, "Game Over", JOptionPane.INFORMATION_MESSAGE);
                    System.exit(0); // Close the game
                } else if (isBoardFull()) {
                    JOptionPane.showMessageDialog(null, "It's a draw!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
                    resetBoard();
                } else {
                    currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
                }
            }
        }
    }

    private boolean checkWin() {
        for (int i = 0; i < 3; i++) {
            // Check rows
            if (buttons[i][0].getText().equals(String.valueOf(currentPlayer))
                    && buttons[i][1].getText().equals(String.valueOf(currentPlayer))
                    && buttons[i][2].getText().equals(String.valueOf(currentPlayer))) {
                return true;
            }
            // Check columns
            if (buttons[0][i].getText().equals(String.valueOf(currentPlayer))
                    && buttons[1][i].getText().equals(String.valueOf(currentPlayer))
                    && buttons[2][i].getText().equals(String.valueOf(currentPlayer))) {
                return true;
            }
        }
        // Check diagonals
        if (buttons[0][0].getText().equals(String.valueOf(currentPlayer))
                && buttons[1][1].getText().equals(String.valueOf(currentPlayer))
                && buttons[2][2].getText().equals(String.valueOf(currentPlayer))) {
            return true;
        }
        if (buttons[0][2].getText().equals(String.valueOf(currentPlayer))
                && buttons[1][1].getText().equals(String.valueOf(currentPlayer))
                && buttons[2][0].getText().equals(String.valueOf(currentPlayer))) {
            return true;
        }
        return false;
    }

    private boolean isBoardFull() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (buttons[row][col].getText().equals("")) {
                    return false;
                }
            }
        }
        return true;
    }

    private void resetBoard() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col].setText("");
                buttons[row][col].setEnabled(true);
            }
        }
        currentPlayer = 'X';
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TicTacToeGUI game = new TicTacToeGUI();
            game.setVisible(true);
        });
    }
}
