package controller;

import model.InvalidUserInputException;
import model.Operation;
import model.Polynomial;
import model.StringHandler;
import view.CalculatorView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CalculatorController {

    private CalculatorView calcView;
    private StringHandler stringHandler = new StringHandler();
    private Polynomial p1, p2;

    public CalculatorController(CalculatorView calcView) {
        this.calcView = calcView;
    }

    // adding action listeners for the buttons that save the data of each polynomial
    public void addSaveButton1ActionListener(JButton saveBtn)
    {
        saveBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                try
                {
                    p1 = stringHandler.extractPolynomialFromString(calcView.getTextField1String().getText());
                }
                catch (InvalidUserInputException ex)
                {
                    ex.printStackTrace();
                }
                p1.sortMonomials();
                System.out.println("\nPolynomial 1: ");
                System.out.println(p1.convertToString());
            }
        });
    }

    public void addSaveButton2ActionListener(JButton saveBtn)
    {
        saveBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                try
                {
                    p2 = stringHandler.extractPolynomialFromString(calcView.getTextField2String().getText());
                }
                catch (InvalidUserInputException ex)
                {
                    ex.printStackTrace();
                }
                p2.sortMonomials();
                System.out.println("\nPolynomial 2: ");
                System.out.println(p2.convertToString());
            }
        });
    }

    // action listener for the buttons used to clear the textfields
    public void addClearButtonActionListener(JButton clearBtn, final JTextField textField)
    {
        clearBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                textField.setText("");
                calcView.getResultLabel().setText("-");
                calcView.getRemainderLabel().setText("-");
            }
        });
    }

    // adding the button listeners for the operation buttons
    public void addAdditionButtonListener(JButton addBtn)
    {
        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Operation additionOp = new Operation();
                Polynomial additionResult = additionOp.addPolynomials(p1, p2);
                calcView.getResultLabel().setText(additionResult.convertToString());
            }
        });
    }

    public void addSubtractionButtonListener(JButton subtrBtn)
    {
        subtrBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Operation subtractionOp = new Operation();
                Polynomial subtractionResult = subtractionOp.subtractPolynomials(p1, p2);
                calcView.getResultLabel().setText(subtractionResult.convertToString());
            }
        });
    }

    public void addMultiplicationButtonListener(JButton mulBtn)
    {
        mulBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Operation multiplicationOp = new Operation();
                Polynomial multiplicationResult = multiplicationOp.multiplyPolynomials(p1, p2);
                calcView.getResultLabel().setText(multiplicationResult.convertToString());
            }
        });
    }

    public void addDivisionButtonListener(JButton divBtn)
    {
        divBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Operation divisionOp = new Operation();
                calcView.getRemainderLblTag().setText("Remainder:");
                calcView.getRemainderLblTag().setVisible(true);
                ArrayList<Polynomial> divisionResults = divisionOp.dividePolynomials(p1, p2);

                calcView.getResultLabel().setText(divisionResults.get(0).convertToString());
                calcView.getRemainderLabel().setText(divisionResults.get(1).convertToString());
            }
        });
    }

    public void addDerivationButtonListener(JButton derivBtn)
    {
        derivBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Operation derivOp = new Operation();
                Polynomial derivResult = new Polynomial();

                // only one text field can be completed at one time
                if (!calcView.getTextField1String().getText().isEmpty() && calcView.getTextField2String().getText().isEmpty())
                {
                    derivResult = derivOp.derivatePolynomial(p1);
                    calcView.getResultLabel().setText(derivResult.convertToString());
                }
                else if (calcView.getTextField1String().getText().isEmpty() && !calcView.getTextField2String().getText().isEmpty())
                {
                    derivResult = derivOp.derivatePolynomial(p2);
                    calcView.getResultLabel().setText(derivResult.convertToString());
                }
            }
        });
    }

    // TODO: display the coefficients more beautifully, w/ only 2 decimals for instance
    public void addIntegrationButtonListener(JButton intBtn)
    {
        intBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Operation intOp = new Operation();
                Polynomial intResult = new Polynomial();

                if (!calcView.getTextField1String().getText().isEmpty() && calcView.getTextField2String().getText().isEmpty())
                {
                    intResult = intOp.integratePolynomial(p1);
                    calcView.getResultLabel().setText(intResult.convertToString());
                }
                else if (calcView.getTextField1String().getText().isEmpty() && !calcView.getTextField2String().getText().isEmpty())
                {
                    intResult = intOp.integratePolynomial(p2);
                    calcView.getResultLabel().setText(intResult.convertToString());
                }
            }
        });
    }

    // add exit button listener
    public void addExitButtonListener(JButton exitBtn)
    {
        exitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                System.exit(0);
            }
        });
    }

    // add reset button listener
    public void addResetButtonListener(JButton resetBtn)
    {
        resetBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                calcView.getTextField1String().setText("");
                calcView.getTextField2String().setText("");
                calcView.getRemainderLblTag().setVisible(false);
                calcView.getResultLabel().setVisible(false);
                calcView.getResultLabel().setText("-");
            }
        });
    }
}
