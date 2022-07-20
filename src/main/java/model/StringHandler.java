package model;

public class StringHandler {

    public StringHandler() {}

    public Polynomial extractPolynomialFromString(String polynomialString) throws InvalidUserInputException
    {
        String[] splittedTextArray = modifySignString(polynomialString).split("\\+s*");
        verifyInputCorrectness(splittedTextArray);
        Polynomial newPolynomial = new Polynomial();

        for (String splittedStr : splittedTextArray)
        {
            String coeffStr, degreeStr;
            int coeff = 0, degree = 0;

            if (splittedStr.indexOf('^') != -1)
                degreeStr = splittedStr.substring(splittedStr.indexOf('^') + 1);
            else if (splittedStr.indexOf('x') != -1)
                degreeStr = "1";
            else
                degreeStr = "0";

            if (splittedStr.charAt(0) == '-' && splittedStr.charAt(1) == 'x')
                coeffStr = "-1";
            else if (splittedStr.indexOf('x') == -1)
                coeffStr = splittedStr;
            else if (splittedStr.charAt(0) == 'x')
                coeffStr = "1";
            else
                coeffStr = splittedStr.substring(0, splittedStr.indexOf('x'));

            // parse the strings corresponding to the coefficient and degree of the monomial
            coeff = Integer.parseInt(coeffStr.replace(" ", ""));
            degree = Integer.parseInt(degreeStr);
            newPolynomial.addMonomialToList(new Monomial(coeff, degree));
        }

        return newPolynomial;
    }

    private String modifySignString(String initialStr)
    {
        String finalStr = "";
        finalStr += initialStr.charAt(0);   // store the sign of the monomial in the string
        int index = 1;

        // traverse the entire String
        while (index < initialStr.length())
        {
            if (initialStr.charAt(index) == '-')    // if we encounter the minus sign
                finalStr += '+';    // add a plus preceding it

            finalStr += initialStr.charAt(index);
            ++index;
        }

        return finalStr;
    }

    private void verifyInputCorrectness(String[] input) throws InvalidUserInputException
    {
        String splitters = "x\\^|x";
        for (String inputText : input)
        {
            if (!inputText.equals(""))
            {
                String[] inputGroup = inputText.split(splitters);

                if (inputGroup.length == 1)
                {
                    if (!inputGroup[0].matches("^[0-9]*$||^[0-9.]*$||^-[0-9]*$||^[0-9.]*$"))
                        throw new InvalidUserInputException();
                }

                if (!inputText.contains("x"))
                {
                    if (inputGroup.length == 2)
                    {
                        if (inputGroup[0].equals(""))
                        {
                            if (!inputGroup[1].matches("^[0-9]*$||^[0-9.]*$||^-[0-9]*$||^[0-9.]*$"))
                                throw new InvalidUserInputException();
                        }
                        else if (inputGroup[0].matches("^[0-9]*$||^[0-9.]*$||^-[0-9]*$||^[0-9.]*$") == false ||
                                    inputGroup[1].matches("^[0-9]*$||^[0-9.]*$||^-[0-9]*$||^[0-9.]*$") == false)
                                throw new InvalidUserInputException();
                    }
                }
            }
        }
        return;
    }
}
