package model;
import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Polynomial {

    private ArrayList<Monomial> monomialsList;

    public Polynomial()
    {
        this.monomialsList = new ArrayList<>();
    }

    public void addMonomialToList(Monomial monomial)
    {
        monomialsList.add(monomial);
    }

    public ArrayList<Monomial> getPolynomial()
    {
        return monomialsList;
    }

    public void sortMonomials()     // sorts the monomials in the list in descending order of their degrees
    {
        Collections.sort(this.monomialsList, Collections.<Monomial>reverseOrder());
    }

    // method that converts a polynomial to its string form
    public String convertToString()
    {
        final String[] polynomialString = {""};

        if (this.monomialsList.isEmpty())
            polynomialString[0] = "-";  // if the list of monomials is empty, the resulting string will only contain "-"

        // if the list is not empty, we traverse it
        monomialsList.forEach(monomial -> {
            if (monomial == monomialsList.get(0))
            {
                // converting the coefficient
                if (monomial.getCoefficient() != 0) // if the coefficient is not null
                {
                    if (monomial.getCoefficient() == -1 && monomial.getDegree() > 0)  // if the coefficient is -1 and the degree is positive
                        polynomialString[0] += "-"; // we add the minus sign preceding the monomial
                    else if (monomial.getCoefficient() != 1)   // if the coefficient is anything but 1, we add the number of the coefficient
                        polynomialString[0] += monomial.getCoefficient();   // to the string
                    else if (monomial.getCoefficient() == 1 && monomial.getDegree() == 0)   // if the coefficient is 1 and we have x^0
                        polynomialString[0] += "1"; // add only the value 1
                }
            }
            else
            {
                if (monomial.getCoefficient() != 0)
                {
                    // treating the cases where we have a positive coefficient
                    if (monomial.getCoefficient() == 1 && monomial.getDegree() != 0)
                        polynomialString[0] += "+";
                    else if (monomial.getCoefficient() > 0)
                        polynomialString[0] += "+" + monomial.getCoefficient();
                    else if (monomial.getCoefficient() == -1 && monomial.getDegree() > 0)   // and the case where it's negative
                        polynomialString[0] += "-";
                    else
                        polynomialString[0] += monomial.getCoefficient();
                }
            }

            // converting the degree
            if (monomial.getDegree() != 0 && monomial.getCoefficient() != 0)
            {
                if (monomial.getDegree() == 1)  // if the degree is 1, only "x" will be displayed
                    polynomialString[0] += "x";
                else
                    polynomialString[0] += "x^" + monomial.getDegree();
            }
        });

        return polynomialString[0];
    }
}
