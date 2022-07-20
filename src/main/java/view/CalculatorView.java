package view;

import controller.CalculatorController;

import javax.swing.*;

public class CalculatorView extends JFrame {
    private JButton divisionBtn;
    private JPanel calculatorPanel;
    private JButton subtractionBtn;
    private JButton additionBtn;
    private JButton multiplicationBtn;
    private JButton derivateBtn;
    private JButton integrateBtn;
    private JTextField polynomialTxt1;
    private JTextField polynomialTxt2;
    private JButton clearBtn1;
    private JButton clearBtn2;
    private JButton savePol1Btn;
    private JButton savePol2Btn;
    private JLabel resultLbl;
    private JLabel remainderLbl;
    private JButton resetBtn;
    private JButton exitBtn;
    private JLabel remainderLblTag;

    public CalculatorView() {
        super("Polynom Calculator");
        setContentPane(calculatorPanel);
        setSize(520, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

        CalculatorController calcController = new CalculatorController(this);

        // setting up panel components in their initial state
        remainderLbl.setVisible(false);
        remainderLblTag.setVisible(false);

        // setting the action listeners corresponding to the components
        calcController.addClearButtonActionListener(clearBtn1, polynomialTxt1);
        calcController.addClearButtonActionListener(clearBtn2, polynomialTxt2);

        calcController.addSaveButton1ActionListener(savePol1Btn);
        calcController.addSaveButton2ActionListener(savePol2Btn);

        calcController.addAdditionButtonListener(additionBtn);
        calcController.addSubtractionButtonListener(subtractionBtn);
        calcController.addMultiplicationButtonListener(multiplicationBtn);
        calcController.addDivisionButtonListener(divisionBtn);
        calcController.addDerivationButtonListener(derivateBtn);
        calcController.addIntegrationButtonListener(integrateBtn);

        calcController.addExitButtonListener(exitBtn);
        calcController.addResetButtonListener(resetBtn);
    }

    public JTextField getTextField1String()
    {
        return this.polynomialTxt1;
    }

    public JTextField getTextField2String()
    {
        return this.polynomialTxt2;
    }

    public JLabel getResultLabel()
    {
        return this.resultLbl;
    }

    public JLabel getRemainderLabel()
    {
        return this.remainderLbl;
    }

    public JLabel getRemainderLblTag() {
        return this.remainderLblTag;
    }
}
