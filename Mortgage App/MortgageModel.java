package ca.yorku.eecs.mortgage;

public class MortgageModel
{
    public static void main(String[] args)
    {
        MortgageModel myModel = new MortgageModel("100000","10","10");
        System.out.println(myModel.computePayment());

        myModel = new MortgageModel("300000", "20", "4.50");
        System.out.println(myModel.computePayment());

    }

    private double principal;
    private double interest;
    private double amortization;

    public MortgageModel(String p, String a, String i)
    {
        this.principal = Double.parseDouble(p);
        this.interest = Double.parseDouble(i)/100/12;
        this.amortization = Double.parseDouble(a)*12;
    }

    public String computePayment()
    {

        double monthlyPayment = Math.pow(1+this.interest, this.amortization);
        double payment = (this.interest*monthlyPayment*this.principal)/(monthlyPayment-1);
        String result = String.format("$%,.2f",payment);
        return result;
    }
}

