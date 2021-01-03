package ca.yorku.eecs.mortgage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class EntryForm extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mortgage_layout);
    }

    public void buttonClicked(View v)
    {

        EditText principleView = (EditText) findViewById(R.id.principleTextbox);
        String principle = principleView.getText().toString();
        EditText amortizationView = (EditText) findViewById(R.id.amortizationTextbox);
        String amortization = amortizationView.getText().toString();
        EditText interestView = (EditText) findViewById(R.id.interestTextbox);
        String interest = interestView.getText().toString();


        MortgageModel model = new MortgageModel(principle, amortization, interest);
        String myPayment = model.computePayment();

        ((TextView) findViewById(R.id.answer)).setText(myPayment);


    }
}
