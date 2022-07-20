package model;

import java.util.ArrayList;

public class Operation {

    public Operation() {}

    public Polynomial addPolynomials(Polynomial p1, Polynomial p2)
    {
        Polynomial additionResult = new Polynomial();
        int i = 0, j = 0;

        while (i < p1.getPolynomial().size() && j < p2.getPolynomial().size())
        {
            Monomial mono1 = p1.getPolynomial().get(i);
            Monomial mono2 = p2.getPolynomial().get(j);

            if (mono1.getDegree() == mono2.getDegree())  // if the monomials have equal degrees
            {
                additionResult.addMonomialToList(new Monomial(mono1.getCoefficient() + mono2.getCoefficient(),
                        mono1.getDegree()));
                i++;
                j++;
            }
            else if (mono1.getDegree() > mono2.getDegree())
            {
                additionResult.addMonomialToList(new Monomial(mono1.getCoefficient(), mono1.getDegree()));
                i++;
            }
            else
            {
                additionResult.addMonomialToList(new Monomial(mono2.getCoefficient(), mono2.getDegree()));
                j++;
            }
        }

        // if there are still monomials left in either of the polynomials, add them to the result as well
        while (i < p1.getPolynomial().size())
        {
            additionResult.addMonomialToList(new Monomial(p1.getPolynomial().get(i).getCoefficient(), p1.getPolynomial().get(i).getDegree()));
            i++;
        }
        while (j < p2.getPolynomial().size())
        {
            additionResult.addMonomialToList(new Monomial(p2.getPolynomial().get(j).getCoefficient(), p2.getPolynomial().get(j).getDegree()));
            j++;
        }

        return additionResult;
    }

    public Polynomial subtractPolynomials(Polynomial p1, Polynomial p2)
    {
        Polynomial subtractionResult = new Polynomial();
        int i = 0, j = 0;

        while (i < p1.getPolynomial().size() && j < p2.getPolynomial().size())
        {
            Monomial mono1 = p1.getPolynomial().get(i);
            Monomial mono2 = p2.getPolynomial().get(j);

            if (mono1.getDegree() == mono2.getDegree())  // if the monomials have equal degrees
            {
                subtractionResult.addMonomialToList(new Monomial(mono1.getCoefficient() - mono2.getCoefficient(),
                        mono1.getDegree()));
                i++;
                j++;
            }
            else if (mono1.getDegree() > mono2.getDegree())
            {
                subtractionResult.addMonomialToList(new Monomial(mono1.getCoefficient(), mono1.getDegree()));
                i++;
            }
            else
            {
                subtractionResult.addMonomialToList(new Monomial(-mono2.getCoefficient(), mono2.getDegree()));
                j++;
            }
        }

        while (i < p1.getPolynomial().size())
        {
            subtractionResult.addMonomialToList(new Monomial(p1.getPolynomial().get(i).getCoefficient(),
                    p1.getPolynomial().get(i).getDegree()));
            i++;
        }
        while (j < p2.getPolynomial().size())
        {
            subtractionResult.addMonomialToList(new Monomial(-p2.getPolynomial().get(j).getCoefficient(), p2.getPolynomial().get(j).getDegree()));
            j++;
        }
        return subtractionResult;
    }

    public Polynomial multiplyPolynomials(Polynomial p1, Polynomial p2)
    {
        Polynomial temp = new Polynomial();
        Polynomial multiplicationResult = new Polynomial();
        multiplicationResult.addMonomialToList(new Monomial(0, 0));
        int i = 0, j = 0;

        while (i < p1.getPolynomial().size())
        {
            j = 0;
            while (j < p2.getPolynomial().size())
            {
                temp.addMonomialToList(new Monomial(p1.getPolynomial().get(i).getCoefficient() *
                        p2.getPolynomial().get(j).getCoefficient(), p1.getPolynomial().get(i).getDegree() +
                        p2.getPolynomial().get(j).getDegree()));
                ++j;
            }
            multiplicationResult = addPolynomials(multiplicationResult, temp);
            temp.getPolynomial().clear();
            ++i;
        }

        return multiplicationResult;
    }

    public ArrayList<Polynomial> dividePolynomials(Polynomial p1, Polynomial p2)
    {
        ArrayList<Polynomial> results = new ArrayList<>();
        Polynomial quotient = new Polynomial();
        quotient.addMonomialToList(new Monomial(0, 0)); // initially the quotient is 0

        // while the degree of the remainder is >= than the remainder of the quotient
        while (!p1.getPolynomial().isEmpty() && p1.getPolynomial().get(0).getDegree() >= p2.getPolynomial().get(0).getDegree())
        {
            quotient.addMonomialToList(new Monomial(p1.getPolynomial().get(0).getCoefficient() / p2.getPolynomial().get(0).getCoefficient(),
                    p1.getPolynomial().get(0).getDegree() - p2.getPolynomial().get(0).getDegree()));
            Polynomial toSubtract = multiplyPolynomials(quotient, p2);
            p1 = subtractPolynomials(p1, toSubtract);
        }

        results.add(quotient);
        results.add(p1);
        return results;
    }

    public Polynomial derivatePolynomial(Polynomial p)
    {
        Polynomial derivationResult = new Polynomial();
        int i = 0;

        while (i < p.getPolynomial().size())
        {
            derivationResult.addMonomialToList(new Monomial(p.getPolynomial().get(i).getCoefficient() *
                    p.getPolynomial().get(i).getDegree(), p.getPolynomial().get(i).getDegree() - 1));
            ++i;
        }

        return derivationResult;
    }

    public Polynomial integratePolynomial(Polynomial p)
    {
        Polynomial integrationResult = new Polynomial();
        int i = 0;

        while (i < p.getPolynomial().size())
        {
            integrationResult.addMonomialToList(new Monomial(p.getPolynomial().get(i).getCoefficient() / (p.getPolynomial().get(i).getDegree() + 1),
                    p.getPolynomial().get(i).getDegree() + 1));
            ++i;
        }

        return integrationResult;
    }
}