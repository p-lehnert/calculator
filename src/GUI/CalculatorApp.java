package GUI;

import resources.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class CalculatorApp extends JFrame implements ActionListener {

    private final JTextArea textArea;

    public CalculatorApp () {
        super("Calculator");

        JPanel calcPanel = new JPanel();
        calcPanel.setLayout(new BoxLayout(calcPanel, BoxLayout.Y_AXIS));
        textArea = new JTextArea();
        calcPanel.add(textArea);

        JPanel keyBoardPanel = new JPanel();
        keyBoardPanel.setLayout(new GridLayout(5, 4));

        // first row
        JButton squareBtn = new JButton("x²");
        squareBtn.addActionListener(this::squareAction);
        JButton rootBtn = new JButton("\u221A");
        JButton clearBtn = new JButton("C");
        JButton eraseBtn = new JButton("<-");
        // second row
        JButton divisionBtn = new JButton("/");
        JButton sevenBtn = new JButton("7");
        JButton eightBtn = new JButton("8");
        JButton nineBtn = new JButton("9");
        // third row
        JButton multiplyBtn = new JButton("x");
        JButton fourBtn = new JButton("4");
        JButton fiveBtn = new JButton("5");
        JButton sixBtn = new JButton("6");
        // fourth row
        JButton minusBtn = new JButton("-");
        JButton oneBtn = new JButton("1");
        JButton twoBtn = new JButton("2");
        JButton threeBtn = new JButton("3");
        // fifth row
        JButton plusBtn = new JButton("+");
        JButton commaBtn = new JButton(",");
        JButton zeroBtn = new JButton("0");
        JButton equalsBtn = new JButton("=");

        keyBoardPanel.add(squareBtn);
        keyBoardPanel.add(rootBtn);
        keyBoardPanel.add(clearBtn);
        keyBoardPanel.add(eraseBtn);

        keyBoardPanel.add(divisionBtn);
        keyBoardPanel.add(sevenBtn);
        keyBoardPanel.add(eightBtn);
        keyBoardPanel.add(nineBtn);

        keyBoardPanel.add(multiplyBtn);
        keyBoardPanel.add(fourBtn);
        keyBoardPanel.add(fiveBtn);
        keyBoardPanel.add(sixBtn);

        keyBoardPanel.add(minusBtn);
        keyBoardPanel.add(oneBtn);
        keyBoardPanel.add(twoBtn);
        keyBoardPanel.add(threeBtn);

        keyBoardPanel.add(plusBtn);
        keyBoardPanel.add(commaBtn);
        keyBoardPanel.add(zeroBtn);
        keyBoardPanel.add(equalsBtn);

        calcPanel.add(keyBoardPanel);


        this.add(calcPanel);
        this.setPreferredSize(Constants.SCREEN_SIZE);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void squareAction (ActionEvent e) {
        if (!Objects.equals(textArea.getText(), "")) {

            char lastChar = checkAndCut();

            if (Character.isDigit(lastChar)) {
                textArea.setText(textArea.getText() + "² ");
            }
        }
        textArea.requestFocus();

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    private char checkAndCut () {
        if(textArea.getText() == null) {
            return ' ';
        }
        char lastChar = textArea.getText().charAt(textArea.getText().length() - 1);
        while (lastChar == ' ') {
            lastChar = textArea.getText().charAt(textArea.getText().length() - 1);
            textArea.setText(textArea.getText().substring(0, textArea.getText().length() - 1));
        }
        textArea.setText((textArea.getText() + lastChar));

        return lastChar;
    }
}