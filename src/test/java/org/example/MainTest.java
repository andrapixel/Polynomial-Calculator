package org.example;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;

import controller.CalculatorController;
import model.InvalidUserInputException;
import model.Operation;
import model.Polynomial;
import model.StringHandler;
import org.junit.Test;


public class MainTest
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    // Operation Testing region
    @Test
    public void testAddition() throws InvalidUserInputException
    {
        StringHandler stringHandler = new StringHandler();
        Polynomial p1 = stringHandler.extractPolynomialFromString("-5x^4+x^3-2x^2+6x-7");
        Polynomial p2 = stringHandler.extractPolynomialFromString("x^5+4x^4+x^3-5x-6");
        Operation op = new Operation();

        Polynomial additionRes = op.addPolynomials(p1, p2);
        assertEquals("x^5-x^4+2.0x^3-2.0x^2+x-13.0", additionRes.convertToString());
    }

    @Test
    public void testAddition2() throws InvalidUserInputException
    {
        StringHandler stringHandler = new StringHandler();
        Polynomial p1 = stringHandler.extractPolynomialFromString("3x^2-x+1");
        Polynomial p2 = stringHandler.extractPolynomialFromString("x-2");
        Operation op = new Operation();

        Polynomial additionRes = op.addPolynomials(p1, p2);
        assertEquals("3.0x^2-1.0", additionRes.convertToString());
    }

    @Test
    public void testSubtraction() throws InvalidUserInputException
    {
        StringHandler stringHandler = new StringHandler();
        Polynomial p1 = stringHandler.extractPolynomialFromString("-5x^4+x^3-2x^2+6x-7");
        Polynomial p2 = stringHandler.extractPolynomialFromString("x^5+4x^4+x^3-5x-6");
        Operation op = new Operation();

        Polynomial subtractionRes = op.subtractPolynomials(p1, p2);
        assertEquals("-x^5-9.0x^4-2.0x^2+11.0x-1.0", subtractionRes.convertToString());
    }

    @Test
    public void testSubtraction2() throws InvalidUserInputException
    {
        StringHandler stringHandler = new StringHandler();
        Polynomial p1 = stringHandler.extractPolynomialFromString("3x^2-x+1");
        Polynomial p2 = stringHandler.extractPolynomialFromString("x-2");
        Operation op = new Operation();

        Polynomial subtractionRes = op.subtractPolynomials(p1, p2);
        assertEquals("3.0x^2-2.0x+3.0", subtractionRes.convertToString());
    }

    @Test
    public void testMultiplication() throws InvalidUserInputException
    {
        StringHandler stringHandler = new StringHandler();
        Polynomial p1 = stringHandler.extractPolynomialFromString("-5x^4+x^3-2x^2+6x-7");
        Polynomial p2 = stringHandler.extractPolynomialFromString("x^5+4x^4+x^3-5x-6");
        Operation op = new Operation();

        Polynomial multiplicationRes = op.multiplyPolynomials(p1, p2);
        assertEquals("-5.0x^9-19.0x^8-3.0x^7-x^6+40.0x^5+3.0x^4-3.0x^3-18.0x^2-x+42.0", multiplicationRes.convertToString());
    }

    @Test
    public void testMultiplication2() throws InvalidUserInputException
    {
        StringHandler stringHandler = new StringHandler();
        Polynomial p1 = stringHandler.extractPolynomialFromString("3x^2-x+1");
        Polynomial p2 = stringHandler.extractPolynomialFromString("x-2");
        Operation op = new Operation();

        Polynomial multiplicationRes = op.multiplyPolynomials(p1, p2);
        assertEquals("3.0x^3-7.0x^2+3.0x-2.0", multiplicationRes.convertToString());
    }

    @Test
    public void testDerivation() throws InvalidUserInputException
    {
        StringHandler stringHandler = new StringHandler();
        Polynomial p = stringHandler.extractPolynomialFromString("-5x^4+x^3-2x^2+6x-7");
        Operation op = new Operation();

        Polynomial derivationRes = op.derivatePolynomial(p);
        assertEquals("-20.0x^3+3.0x^2-4.0x+6.0", derivationRes.convertToString());
    }

    @Test
    public void testDerivation2() throws InvalidUserInputException
    {
        StringHandler stringHandler = new StringHandler();
        Polynomial p = stringHandler.extractPolynomialFromString("x^5+4x^4+x^3-5x-6");
        Operation op = new Operation();

        Polynomial derivationRes = op.derivatePolynomial(p);
        assertEquals("5.0x^4+16.0x^3+3.0x^2-5.0", derivationRes.convertToString());
    }

    @Test
    public void testIntegration() throws InvalidUserInputException
    {
        StringHandler stringHandler = new StringHandler();
        Polynomial p = stringHandler.extractPolynomialFromString("-5x^4+8x^3-x^2+6x-7");
        Operation op = new Operation();

        Polynomial derivationRes = op.integratePolynomial(p);
        assertEquals("-x^5+2.0x^4-0.3333333333333333x^3+3.0x^2-7.0x", derivationRes.convertToString());
    }

    @Test
    public void testIntegration2() throws InvalidUserInputException
    {
        StringHandler stringHandler = new StringHandler();
        Polynomial p = stringHandler.extractPolynomialFromString("x^3+4x^2+5");
        Operation op = new Operation();

        Polynomial derivationRes = op.integratePolynomial(p);
        assertEquals("0.25x^4+1.3333333333333333x^3+5.0x", derivationRes.convertToString());
    }
}
