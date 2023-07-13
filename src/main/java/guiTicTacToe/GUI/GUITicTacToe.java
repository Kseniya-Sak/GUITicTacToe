package guiTicTacToe.GUI;

import guiTicTacToe.gameLogic.ComputerStep;
import guiTicTacToe.gameLogic.FindWinner;
import guiTicTacToe.gameLogic.GameTable;
import guiTicTacToe.gameLogic.GamerStep;


import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GUITicTacToe extends JFrame {
    private static final long serialVersionUID = 8557868928917996779L;
    private final GamerStep gamerStep;
    private final ComputerStep computerStep;

    private JLabel cells[][] = new JLabel[3][3];

    public GUITicTacToe() throws HeadlessException {
        super("Tic-tac-toe");

        this.gamerStep = new GamerStep('X');
        this.computerStep = new ComputerStep('0');

        createGameUITable();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().width / 2);
        this.setVisible(true);
    }

    public void createGameUITable() {
        setLayout(new GridLayout(3,3));
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                final int number = i * 3 + j + 1;
                JLabel panel = new JLabel();
                cells[i][j] = panel;
                panel.setPreferredSize(new Dimension(120, 120));
                panel.setHorizontalAlignment(SwingConstants.CENTER);
                panel.setVerticalAlignment(SwingConstants.CENTER);
                panel.setFont(new Font(Font.SERIF, Font.PLAIN, 35));
                panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                panel.setOpaque(true);
                panel.setBackground(new Color(230, 230, 250));
                this.add(panel);
                panel.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        handelHumanTurn(number);
                    }
                });
            }
        }
        printGameTable();
    }
    // метод обработки хода пользователя (щелчек по ячейке)
    private void handelHumanTurn(int number) {
        if(gamerStep.isCellFree(number)) {
            gamerStep.makeTurn(number, gamerStep.getGamerSymbol());
            printGameTable();
            if (FindWinner.getWinner() == gamerStep.getGamerSymbol()) {
                JOptionPane.showMessageDialog(this, "Game over: You win!");
                System.exit(0);
            }
            if (FindWinner.isDraw()) {
                JOptionPane.showMessageDialog(this, "Game over: Draw!");
                System.exit(0);
            }
            computerStep.makeComputerTurn();
            printGameTable();
            if (FindWinner.getWinner() == computerStep.getGamerSymbol()) {
                JOptionPane.showMessageDialog(this, "Game over: Computer win!");
                System.exit(0);
            }
            if (FindWinner.isDraw()) {
                JOptionPane.showMessageDialog(this, "Game over: Draw!");
                System.exit(0);
            }

        } else {
            JOptionPane.showMessageDialog(this, "Cell is not free! Enter other number");
        }
    }

    // отображение игрового поля
    private void printGameTable() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                cells[i][j].setText(String.valueOf(GameTable.TABLE[i][j]));
            }
        }
    }

}
