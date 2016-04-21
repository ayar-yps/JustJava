package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 0;


    /**
     * Averiguar que hace esta parte del codigo
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public int calculateTotaPrice(int numberOfCoffes, boolean hasWhipedCream, boolean hasChocolate) {

        int basePrice = 5, total;
        if (hasWhipedCream) {
            basePrice += 1;
        }
        if (hasChocolate) {
            basePrice += 2;
        }

        total = numberOfCoffes * basePrice;

        return (total);
    }


    public String createOrderSummary(String name, boolean hasWhipedCream, boolean hasChocolate) {

        String orderSummary;
        int numberOfCoffes = quantity;
        int total = calculateTotaPrice(numberOfCoffes, hasWhipedCream, hasChocolate);

        orderSummary = "Name: " + name;
        orderSummary += "\nAdd whipped cream? " + hasWhipedCream;
        orderSummary += "\nAdd chocolate? " + hasChocolate;
        orderSummary += "\nQuantity: " + numberOfCoffes;
        orderSummary += "\nTotal: $ " + total;
        orderSummary += "\nThank you!";

        return (orderSummary);
    }


    /**
     * This methods are called when the order button is clicked.
     */
    public void submitOrder(View view) {

        CheckBox whippedCheckBox = (CheckBox) findViewById(R.id.whipped_checkbox);
        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_checkbox);
        EditText nameEditText = (EditText) findViewById(R.id.edit_text_name);

        boolean hasWhipedCream = whippedCheckBox.isChecked();
        boolean hasChocolate = chocolateCheckBox.isChecked();
        String name = nameEditText.getText().toString();

        String orderSummary = createOrderSummary(name, hasWhipedCream, hasChocolate);

//        displayMessage(orderSummary);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"ayar.yps@gmail.com"});
        intent.putExtra(Intent.EXTRA_SUBJECT, "Coffe order");
        intent.putExtra(Intent.EXTRA_TEXT, orderSummary);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }


    }

    public void increment(View view) {
        Toast toastMax = Toast.makeText(this,"Maximum is 99",Toast.LENGTH_SHORT);
        if(quantity<100){
            quantity++;
            display(quantity);
        }else{
            toastMax.show();
        }
    }

    public void decrement(View view) {
        Toast toastMin = Toast.makeText(this,"Minimum is 1",Toast.LENGTH_SHORT);
        if(quantity>1){
            quantity--;
            display(quantity);
        }else{
            toastMin.show();
        }
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(
                R.id.quantity_text_view);
        quantityTextView.setText(String.valueOf(number));
    }

    /**
     * This method displays a message on the screen.
     */
//    public void displayMessage(String str) {
//        TextView messageTextView = (TextView) findViewById(R.id.order_summary_text_view);
//        messageTextView.setText(str);
//    }

}