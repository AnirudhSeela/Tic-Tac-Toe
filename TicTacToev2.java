import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToe {
    private static int player = 0;
    private static String[][] backroundBoard = new String[3][3];
    private static JFrame tttBoard = new JFrame();
    private static JLabel[][] labels = new JLabel[3][3];
    private static int pseudoPlayer = 0;

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
            while ((checkWinner2() == 0) || !checkFull()) {
                if (player == 0) {
                    aiMove();

                }
            }
        } else {
            JOptionPane.showMessageDialog(null,"Thanks for playing Tic-Tac-Toe!","Thank You!", JOptionPane.PLAIN_MESSAGE);

        }



    }

    public static void aiMove() {
        int bestScore = Integer.MIN_VALUE;
        String bestMove = "";
        int x = 0;
        int y = 0;

        for (int i = 0; i < 3 ; i++) {
            for (int j = 0; j < 3; j++) {
                if (backroundBoard[i][j] == null || backroundBoard[i][j].equals("")) {
                    backroundBoard[i][j] = "O";
                    int score = minimax(backroundBoard, false);
                    backroundBoard[i][j] = null;
                    if (score > bestScore) {
                        bestScore = score;
                        x = i;
                        y = j;
                    }
                }
            }

        }

        backroundBoard[x][y] = "X";
        labels[x][y].setText("X");
        nextPlayer();
    }

    public static int minimax(String[][] board, boolean maximizing) {

        int[] scores = {10, -10, 0};

        if (checkWinner2() != 0) {

            if (checkWinner2() == 3) {
                pseudoPlayer = 0;
                return 0;
            }
            int score = scores[pseudoPlayer%2];
            pseudoPlayer = 0;

            return score;
        }
        pseudoPlayer++;

        if (maximizing) {
            int bestScore = Integer.MIN_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (backroundBoard[i][j] == null || backroundBoard[i][j].equals("")) {
                        backroundBoard[i][j] = "X";
                        int score = minimax(backroundBoard, false);
                        backroundBoard[i][j] = null;
                        if (score > bestScore) {
                            bestScore = score;
                        }
                    }
                }
            }
            return bestScore;
        } else {
            int bestScore = Integer.MAX_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (backroundBoard[i][j] == null || backroundBoard[i][j].equals("")) {
                        backroundBoard[i][j] = "O";
                        int score = minimax(backroundBoard, true);
                        backroundBoard[i][j] = null;
                        if (score < bestScore) {
                            bestScore = score;
                        }
                    }
                }
            }
            return bestScore;
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
        int count = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                labels[i][j] = new JLabel();
                JButton sButton = new JButton();
                sButton.setSize(60,60);
                sButton.setVisible(true);
                board[i][j].add(sButton);
                board[i][j].add(labels[i][j]);
                int x = i;
                int y = j;
                int num = count;

                sButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (player == 0) {
                            labels[x][y].setText("X");
                            backroundBoard[x][y] = "X";
                            sButton.setVisible(false);
                            labels[x][y].setVisible(true);
                            checkWinner();
                            nextPlayer();
                        } else {
                            labels[x][y].setText("O");
                            backroundBoard[x][y] = "O";
                            sButton.setVisible(false);
                            labels[x][y].setVisible(true);
                            checkWinner();
                            nextPlayer();
                        }

                    }
                });
                count++;

            }
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                tttBoard.add(board[i][j]);
            }

        }
        tttBoard.setVisible(true);

        if (player == 1) {
            aiMove();
        }
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
                if (backroundBoard[i][j] == null || backroundBoard[i][j].equals("")) {
                    bool = false;
                }
            }
        }
        return bool;
    }


    public static boolean checkWinner() {

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
            return true;
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
            return true;
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
            return true;
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
            return true;
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
            return true;
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
            return true;
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
            return true;
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
            return true;
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
            return true;
        } else {
            return false;
        }

    }

    public static int checkWinner2() {

        if ((backroundBoard[0][0] != null && backroundBoard[0][1] != null && backroundBoard[0][2] != null) &&
                backroundBoard[0][1].equals(backroundBoard[0][0]) &&
                backroundBoard[0][0].equals(backroundBoard[0][2])) {
            return (player + 1);
        } else if ((backroundBoard[1][0] != null && backroundBoard[1][1] != null && backroundBoard[1][2] != null) &&
                backroundBoard[1][1].equals(backroundBoard[1][0]) &&
                backroundBoard[1][0].equals(backroundBoard[1][2])) {

            return (player + 1);
        } else if ((backroundBoard[2][0] != null && backroundBoard[2][1] != null && backroundBoard[2][2] != null) &&
                backroundBoard[2][1].equals(backroundBoard[2][0]) &&
                backroundBoard[2][0].equals(backroundBoard[2][2])) {
            return (player + 1);
        } else if ((backroundBoard[0][0] != null && backroundBoard[1][1] != null && backroundBoard[2][2] != null) &&
                backroundBoard[0][0].equals(backroundBoard[2][2]) &&
                backroundBoard[0][0].equals(backroundBoard[1][1])) {
            return (player + 1);
        } else if ((backroundBoard[0][2] != null && backroundBoard[1][1] != null && backroundBoard[2][0] != null) &&
                backroundBoard[0][2].equals(backroundBoard[1][1]) &&
                backroundBoard[1][1].equals(backroundBoard[2][0])) {
            return (player + 1);
        } else if ((backroundBoard[0][0] != null && backroundBoard[1][0] != null && backroundBoard[2][0] != null) &&
                backroundBoard[1][0].equals(backroundBoard[0][0]) &&
                backroundBoard[0][0].equals(backroundBoard[2][0])) {

            return (player + 1);
        } else if ((backroundBoard[0][1] != null && backroundBoard[1][1] != null && backroundBoard[2][1] != null) &&
                backroundBoard[1][1].equals(backroundBoard[0][1]) &&
                backroundBoard[0][1].equals(backroundBoard[2][1])) {

            return (player + 1);
        } else if ((backroundBoard[0][2] != null && backroundBoard[1][2] != null && backroundBoard[2][2] != null) &&
                backroundBoard[1][2].equals(backroundBoard[0][2]) &&
                backroundBoard[0][2].equals(backroundBoard[2][2])) {

            return (player + 1);
        } else if (checkFull()) {
            return 3;
        } else {
            return 0;
        }

    }



}
