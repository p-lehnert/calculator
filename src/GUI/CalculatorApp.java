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
        JButton rootBtn = new JButton("\u221a");
        rootBtn.addActionListener(this::rootAction);
        JButton clearBtn = new JButton("C");
        clearBtn.addActionListener(this::clearAction);
        JButton eraseBtn = new JButton("<-");
        eraseBtn.addActionListener(this::eraseAction);
        // second row
        JButton divisionBtn = new JButton("\u00f7");
        divisionBtn.addActionListener(this::arithmeticDigitAction);
        JButton sevenBtn = new JButton("7");
        sevenBtn.addActionListener(this::arithmeticDigitAction);
        JButton eightBtn = new JButton("8");
        eightBtn.addActionListener(this::arithmeticDigitAction);
        JButton nineBtn = new JButton("9");
        nineBtn.addActionListener(this::arithmeticDigitAction);
        // third row
        JButton multiplyBtn = new JButton("*");
        multiplyBtn.addActionListener(this::arithmeticDigitAction);
        JButton fourBtn = new JButton("4");
        fourBtn.addActionListener(this::arithmeticDigitAction);
        JButton fiveBtn = new JButton("5");
        fiveBtn.addActionListener(this::arithmeticDigitAction);
        JButton sixBtn = new JButton("6");
        sixBtn.addActionListener(this::arithmeticDigitAction);
        // fourth row
        JButton minusBtn = new JButton("-");
        minusBtn.addActionListener(this::arithmeticDigitAction);
        JButton oneBtn = new JButton("1");
        oneBtn.addActionListener(this::arithmeticDigitAction);
        JButton twoBtn = new JButton("2");
        twoBtn.addActionListener(this::arithmeticDigitAction);
        JButton threeBtn = new JButton("3");
        threeBtn.addActionListener(this::arithmeticDigitAction);
        // fifth row
        JButton plusBtn = new JButton("+");
        plusBtn.addActionListener(this::arithmeticDigitAction);
        JButton commaBtn = new JButton(",");
        commaBtn.addActionListener(this::arithmeticDigitAction);
        JButton zeroBtn = new JButton("0");
        zeroBtn.addActionListener(this::arithmeticDigitAction);
        JButton equalsBtn = new JButton("=");
        equalsBtn.addActionListener(this);

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

    /**
     * Adds a '²' to the given position (Caret-Position).
     *
     * @param e Actionevent that is triggered, when squareBtn is pressed
     */
    private void squareAction (ActionEvent e) {
        // check whether textarea is empty
        if (!Objects.equals(textArea.getText(), "")) {
            char charAtCaret = checkCharAtCaret();
            int caretIndex = textArea.getCaretPosition();
            if (Character.isDigit(charAtCaret) || charAtCaret == ')') {
                String calc = textArea.getText().substring(0, caretIndex) + "²" + textArea.getText().substring(caretIndex);
                textArea.setText(calc);
                textArea.setCaretPosition(caretIndex + 1);
            }
        }
        textArea.requestFocus();
    }

    /**
     * Adds a root-symbol and parenthesis.
     *
     * @param e Actionevent
     */
    private void rootAction (ActionEvent e) {
        int caretIndex = textArea.getCaretPosition();

        if (Objects.equals(textArea.getText(), "")) {
            textArea.setText("\u221A()");
        } else if (caretIndex == textArea.getText().length() - 1) {
            textArea.setText(textArea.getText() + " \u221A()");
            textArea.setCaretPosition(caretIndex + 1);
        } else {
            String calc = textArea.getText().substring(0, caretIndex)
                    + "\u221A()" + textArea.getText().substring(caretIndex);
            textArea.setText(calc);
        }
        textArea.requestFocus();
        textArea.setCaretPosition(caretIndex + 2);
    }

    /**
     * Clears textArea.
     *
     * @param e Actionevent
     */
    private void clearAction(ActionEvent e) {
        textArea.setText("");
        textArea.requestFocus();
    }

    /**
     * Erases one Character.
     *
     * @param e Actionevent
     */
    private void eraseAction(ActionEvent e) {
        int caretIndex = textArea.getCaretPosition();
        if (!Objects.equals(textArea.getText(), "") && caretIndex > 0) {
            String calc = textArea.getText().substring(0, caretIndex - 1) + textArea.getText().substring(caretIndex);
            textArea.setText(calc);
            textArea.setCaretPosition(caretIndex - 1);
        }
        textArea.requestFocus();
    }

    /**
     * Adds the pressed arithmetic symbol.
     *
     * @param e Actionevent
     */
    private void arithmeticDigitAction (ActionEvent e) {
        JButton b = (JButton) e.getSource();
        String bValue = b.getText();

        int caretIndex = textArea.getCaretPosition();
        textArea.setText(textArea.getText().substring(0, caretIndex) + bValue + textArea.getText().substring(caretIndex));
        textArea.setCaretPosition(caretIndex + 1);
        textArea.requestFocus();
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        textArea.setText(cutEquation(textArea.getText()));
    }

    /**
     * Returns the last char of @textArea.
     *
     * @return last Character of textArea
     */
    private char checkCharAtCaret () {
        if(textArea.getText() == null) {
            return ' ';
        } else {
            return textArea.getText().charAt(textArea.getCaretPosition() - 1);
        }
    }

    private void checkEquation() {

    }

    private String cutEquation() {
        String equation = textArea.getText();
        int index = 0;
        int spaces = 0;
        if(!Objects.equals(equation, "")) {
            for (int i = 0; i < equation.length(); i++) {
                if(equation.charAt(i) == ' ') {
                    spaces++;
                }
            }
            equation = equation.substring(spaces);
        }

        return equation;
    }

    private String cutEquation(String equationToCut) {
        StringBuilder equation = new StringBuilder();
        boolean symbol = false;
        if(!Objects.equals(equationToCut, "")) {
            for (int i = 0; i < equationToCut.length(); i++) {
                if (equationToCut.charAt(i) == ' ' && symbol) {
                    equation.append(cutEquation(equationToCut.substring(i)));
                    break;
                } else if(equationToCut.charAt(i) != ' ') {
                    symbol = true;
                    equation.append(equationToCut.charAt(i));
                }
            }
        }

        return equation.toString();
    }
}
