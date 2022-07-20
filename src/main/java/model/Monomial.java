package model;

public class Monomial implements Comparable<Monomial> {

    private double coefficient;
    private int degree;

    public Monomial(double coefficient, int degree)
    {
        this.coefficient = coefficient;
        this.degree = degree;
    }

    // Getters & Setters region
    public double getCoefficient()
    {
        return coefficient;
    }

    public void setCoefficient(double coefficient)
    {
        this.coefficient = coefficient;
    }

    public int getDegree()
    {
        return degree;
    }

    public void setDegree(int degree)
    {
        this.degree = degree;
    }
    // end region

    @Override
    public int compareTo(Monomial next) {
        return(Integer.compare(this.getDegree(), next.getDegree()));
    }
}