import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToe {
    private static int player = 0;
    private static String[][] backroundBoard = new String[3][3];
    private static JFrame tttBoard = new JFrame();

    public static void main(String[] args) {

        int start = JOptionPane.showConfirmDialog(null,"Hi! Welcome to Tic-Tac-Toe! Would you " +
                "like to play?","Thank You!", JOptionPane.YES_NO_OPTION);

        if (start == 0) {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    TicTacToe.play();
                }
            });
        } else {
            JOptionPane.showMessageDialog(null,"Thanks for playing Tic-Tac-Toe!","Thank You!", JOptionPane.PLAIN_MESSAGE);

        }



    }
    public static void play() {

        backroundBoard = new String[3][3];

        tttBoard = new JFrame();
        tttBoard.setSize(540,540);
        JPanel[][] board = new JPanel[3][3];
        tttBoard.setLayout(new GridLayout(3,3));

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                JPanel sPanel = new JPanel();
                sPanel.setVisible(true);
                sPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                board[i][j] = sPanel;
            }

        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                JLabel jl = new JLabel("");
                JButton sButton = new JButton();
                sButton.setSize(60,60);
                sButton.setVisible(true);
                board[i][j].add(sButton);
                board[i][j].add(jl);
                int x = i;
                int y = j;

                sButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (player == 0) {
                            jl.setText("X");
                            backroundBoard[x][y] = "X";
                            sButton.setVisible(false);
                            jl.setVisible(true);
                            checkWinner();
                            nextPlayer();
                        } else {
                            jl.setText("O");
                            backroundBoard[x][y] = "O";
                            sButton.setVisible(false);
                            jl.setVisible(true);
                            checkWinner();
                            nextPlayer();
                        }

                    }
                });

            }
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                tttBoard.add(board[i][j]);
            }

        }
        tttBoard.setVisible(true);
    }

    public static void nextPlayer() {
        if (player == 0) {
            player++;
        } else {
            player = 0;
        }
    }

    public static boolean checkFull() {
        boolean bool = true;
        for (int i = 0; i < backroundBoard.length; i++) {
            for (int j = 0; j < backroundBoard[i].length; j++) {
                if (backroundBoard[i][j] == null) {
                    bool = false;
                }
            }
        }
        return bool;
    }


    public static void checkWinner() {

        if ((backroundBoard[0][0] != null && backroundBoard[0][1] != null && backroundBoard[0][2] != null) &&
                backroundBoard[0][1].equals(backroundBoard[0][0]) &&
                backroundBoard[0][0].equals(backroundBoard[0][2])) {
            int answer = JOptionPane.showConfirmDialog(null, "Player " + (player + 1) + " wins! Would you like to play again?", "Winner!", JOptionPane.YES_NO_OPTION);
            tttBoard.setVisible(false);
            if (answer == 0) {
                if (player == 0) {
                    nextPlayer();
                }
                TicTacToe.play();
            } else {
                JOptionPane.showMessageDialog(null,"Thanks for playing Tic-Tac-Toe!","Thank You!", JOptionPane.PLAIN_MESSAGE);
            }
        } else if ((backroundBoard[1][0] != null && backroundBoard[1][1] != null && backroundBoard[1][2] != null) &&
                backroundBoard[1][1].equals(backroundBoard[1][0]) &&
                backroundBoard[1][0].equals(backroundBoard[1][2])) {
            int answer = JOptionPane.showConfirmDialog(null, "Player " + (player + 1) + " wins! Would you like to play again?", "Winner!", JOptionPane.YES_NO_OPTION);
            tttBoard.setVisible(false);
            if (answer == 0) {
                if (player == 0) {
                    nextPlayer();
                }
                TicTacToe.play();
            } else {
                JOptionPane.showMessageDialog(null,"Thanks for playing Tic-Tac-Toe!","Thank You!", JOptionPane.PLAIN_MESSAGE);
            }

        } else if ((backroundBoard[2][0] != null && backroundBoard[2][1] != null && backroundBoard[2][2] != null) &&
                backroundBoard[2][1].equals(backroundBoard[2][0]) &&
                backroundBoard[2][0].equals(backroundBoard[2][2])) {
            int answer = JOptionPane.showConfirmDialog(null, "Player " + (player + 1) + " wins! Would you like to play again?", "Winner!", JOptionPane.YES_NO_OPTION);
            tttBoard.setVisible(false);
            if (answer == 0) {
                if (player == 0) {
                    nextPlayer();
                }
                TicTacToe.play();
            } else {
                JOptionPane.showMessageDialog(null,"Thanks for playing Tic-Tac-Toe!","Thank You!", JOptionPane.PLAIN_MESSAGE);
            }

        } else if ((backroundBoard[0][0] != null && backroundBoard[1][1] != null && backroundBoard[2][2] != null) &&
                backroundBoard[0][0].equals(backroundBoard[2][2]) &&
                backroundBoard[0][0].equals(backroundBoard[1][1])) {
            int answer = JOptionPane.showConfirmDialog(null, "Player " + (player + 1) + " wins! Would you like to play again?", "Winner!", JOptionPane.YES_NO_OPTION);
            tttBoard.setVisible(false);
            if (answer == 0) {
                if (player == 0) {
                    nextPlayer();
                }
                TicTacToe.play();
            } else {
                JOptionPane.showMessageDialog(null,"Thanks for playing Tic-Tac-Toe!","Thank You!", JOptionPane.PLAIN_MESSAGE);
            }

        } else if ((backroundBoard[0][2] != null && backroundBoard[1][1] != null && backroundBoard[2][0] != null) &&
                backroundBoard[0][2].equals(backroundBoard[1][1]) &&
                backroundBoard[1][1].equals(backroundBoard[2][0])) {
            int answer = JOptionPane.showConfirmDialog(null, "Player " + (player + 1) + " wins! Would you like to play again?", "Winner!", JOptionPane.YES_NO_OPTION);
            tttBoard.setVisible(false);
            if (answer == 0) {
                if (player == 0) {
                    nextPlayer();
                }
                TicTacToe.play();
            } else {
                JOptionPane.showMessageDialog(null,"Thanks for playing Tic-Tac-Toe!","Thank You!", JOptionPane.PLAIN_MESSAGE);
            }

        } else if ((backroundBoard[0][0] != null && backroundBoard[1][0] != null && backroundBoard[2][0] != null) &&
                backroundBoard[1][0].equals(backroundBoard[0][0]) &&
                backroundBoard[0][0].equals(backroundBoard[2][0])) {
            int answer = JOptionPane.showConfirmDialog(null, "Player " + (player + 1) + " wins! Would you like to play again?", "Winner!", JOptionPane.YES_NO_OPTION);
            tttBoard.setVisible(false);
            if (answer == 0) {
                if (player == 0) {
                    nextPlayer();
                }
                TicTacToe.play();
            } else {
                JOptionPane.showMessageDialog(null,"Thanks for playing Tic-Tac-Toe!","Thank You!", JOptionPane.PLAIN_MESSAGE);
            }

        } else if ((backroundBoard[0][1] != null && backroundBoard[1][1] != null && backroundBoard[2][1] != null) &&
                backroundBoard[1][1].equals(backroundBoard[0][1]) &&
                backroundBoard[0][1].equals(backroundBoard[2][1])) {
            int answer = JOptionPane.showConfirmDialog(null, "Player " + (player + 1) + " wins! Would you like to play again?", "Winner!", JOptionPane.YES_NO_OPTION);
            tttBoard.setVisible(false);
            if (answer == 0) {
                if (player == 0) {
                    nextPlayer();
                }
                TicTacToe.play();
            } else {
                JOptionPane.showMessageDialog(null,"Thanks for playing Tic-Tac-Toe!","Thank You!", JOptionPane.PLAIN_MESSAGE);
            }

        } else if ((backroundBoard[0][2] != null && backroundBoard[1][2] != null && backroundBoard[2][2] != null) &&
                backroundBoard[1][2].equals(backroundBoard[0][2]) &&
                backroundBoard[0][2].equals(backroundBoard[2][2])) {
            int answer = JOptionPane.showConfirmDialog(null, "Player " + (player + 1) + " wins! Would you like to play again?", "Winner!", JOptionPane.YES_NO_OPTION);
            tttBoard.setVisible(false);
            if (answer == 0) {
                if (player == 0) {
                    nextPlayer();
                }
                TicTacToe.play();
            } else {
                JOptionPane.showMessageDialog(null,"Thanks for playing Tic-Tac-Toe!","Thank You!", JOptionPane.PLAIN_MESSAGE);
            }

        } else if (checkFull()) {
            int answer = JOptionPane.showConfirmDialog(null, "It's a draw! Would you like to play again?", "Winner!", JOptionPane.YES_NO_OPTION);
            tttBoard.setVisible(false);
            if (answer == 0) {
                if (player == 0) {
                    nextPlayer();
                }
                TicTacToe.play();
            } else {
                JOptionPane.showMessageDialog(null,"Thanks for playing Tic-Tac-Toe!","Thank You!", JOptionPane.PLAIN_MESSAGE);
            }
        }



    }


}
