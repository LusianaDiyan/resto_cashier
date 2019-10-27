package applusiana.practice2;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    int quantity = 2;
    TextView tvquantity;
    TextView txtotal;
    CheckBox cbCream;
    CheckBox cbChoco;
    CheckBox cbSugar;
    CheckBox cbMilk;
    RadioGroup rgOption;
    RadioButton rbCoffee;
    RadioButton rbTea;
    RadioButton rbJuice;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button lokasi = (Button) findViewById(R.id.btnLokasi);
        lokasi.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent maps = new Intent(MainActivity.this, GoogleMaps.class);
                startActivity(maps);
            }
        });
    }

    public void decrement(View view) {
        if (quantity == 0) {
            return;
        }
        quantity = quantity - 1;
        display(quantity);
    }

    public void increment(View view) {
        if (quantity == 10) {
            return;
        }
        quantity = quantity + 1;
        display(quantity);
    }

    public void display(int quantity) {
        // TextView quantityTxt = findViewById(R.id.quantity);
        tvquantity = findViewById(R.id.quantity);
        tvquantity.setText(String.valueOf(quantity));
    }

    public void submit(View view) {

        EditText namaTxt = findViewById(R.id.txtNama);
        String name = namaTxt.getText().toString();

        //menghubungkan xml dan java
        cbCream = findViewById
                (R.id.whipped_cream_checkbox);
        cbChoco = findViewById
                (R.id.chocolate_checkbox);
        cbSugar = findViewById
                (R.id.sugar_checkbox);
        cbMilk = findViewById
                (R.id.milk_checkbox);

        rbTea = findViewById
                (R.id.rbTea);
        rbJuice = findViewById
                (R.id.rbJuice);
        rbCoffee = findViewById
                (R.id.rbCoffee);

        String jenisMinum = pilihMinum();
        String jenisTopping = pilihTopping();
        String harga = String.valueOf(hitungTopping());

        alert("Name: " + name + "\n" + "Order: " + jenisMinum + "\n" + "Topping: "
                + jenisTopping + "\n" + "Price: " + "Rp. " + harga);
    }

    public String pilihMinum() {
        if (rbTea.isChecked()) {
            return "tea";
        } else if (rbCoffee.isChecked()) {
            return "coffee";
        } else {
            return "juice";
        }
    }

    public String pilihTopping() {
        String topping = "";

        if (cbChoco.isChecked()) {
            topping = topping + "Choco, ";
        }
        if (cbCream.isChecked()) {
            topping = topping + "Whipped Cream, ";
        }
        if (cbMilk.isChecked()) {
            topping = topping + "Milk, ";
        }
        if (cbSugar.isChecked()) {
            topping = topping + "Sugar, ";
        }
        return topping;
    }

    public int hitungTopping() {
        int harga = 10000;

        if (cbCream.isChecked()) {
            harga = harga + 1000;
        }
        if (cbChoco.isChecked()) {
            harga = harga + 2000;
        }
        if (cbSugar.isChecked()) {
            harga = harga + 3000;
        }
        if (cbMilk.isChecked()) {
            harga = harga + 4000;
        }
        return harga * quantity;
    }

    public void exit(View view) {
        close();
    }

    public void close() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Apakah Anda Benar-Benar ingin keluar?")
                .setCancelable(false)
                .setPositiveButton("Ya",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int id) {
                                MainActivity.this.finish();
                            }
                        })
                .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int id) {
                        dialog.cancel();

                    }
                }).show();
    }

    private void alert(final String pesan) {
        AlertDialog alertPesan = new AlertDialog
                .Builder(MainActivity.this).create();
        alertPesan.setTitle("Message");
        alertPesan.setMessage(pesan);
        alertPesan.setButton(AlertDialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto : lusianadiyan25@gmail.com "));
                intent.putExtra(Intent.EXTRA_SUBJECT, "Nota Pembelian");
                intent.putExtra(Intent.EXTRA_TEXT, pesan);

                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
                dialog.dismiss();
            }
        });

        alertPesan.setButton(AlertDialog.BUTTON_NEGATIVE, "CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {

            }
        });

        alertPesan.show();

    }
}