package com.dreamteam.organizeyourday;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.dreamteam.organizeyourday.DataBase.DatabaseHelper;
import com.dreamteam.organizeyourday.Fragments.FragmentHome;

public class AddNewCardActivity extends AppCompatActivity {


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ThemeManager.setCurrentTheme(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_card);
        final TextInputEditText titleText = (TextInputEditText)findViewById(R.id.inputTitleText);
        TextInputEditText descriptionText = (TextInputEditText)findViewById(R.id.inputTextDescription);
        Button addButton = (Button)findViewById(R.id.addButton);

        Button cancelButton = (Button)findViewById(R.id.cancel_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper db = new DatabaseHelper(ContextContainer.getContext());
                db.addCard(titleText.getText().toString());
                onBackPressed();
            }
        });

cancelButton.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        onBackPressed();
    }
});
    }
}
