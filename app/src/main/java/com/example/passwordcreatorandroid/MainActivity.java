package com.example.passwordcreatorandroid;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;
import com.example.passwordcreatorandroid.bussines.Controller;
import com.example.passwordcreatorandroid.ResetKeysDialog;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private int toggle = 0;
    private Controller appController = new Controller();
    Spinner menu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        Toolbar newToolbar = findViewById(R.id.myToolbar);
        setSupportActionBar(newToolbar);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int itemId = item.getItemId();
        if (itemId == R.id.reset_button) {
            ResetKeysDialog dialog = new ResetKeysDialog(appController);
            dialog.show(getSupportFragmentManager(),"Reset encryption keys");
            return true;
        } else if (itemId == R.id.import_button) {
            return true;
        } else if (itemId == R.id.export_button) {
            return true;
        } else if (itemId == R.id.help) {
            HelpDialog dialog = new HelpDialog();
            dialog.show(getSupportFragmentManager(),"Help");
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public void create(View view){
        EditText field = findViewById(R.id.simplePasswordField);
        TextView label = findViewById(R.id.textLabel);
        TextView resultField = findViewById(R.id.safePassword);
        TextView notif = findViewById(R.id.defaultNotif);
        Button copyButton = findViewById(R.id.copyButton);
        Button showButton = findViewById(R.id.showButton);
        String simplePassword = field.getText().toString();
        String code = appController.codePassword(simplePassword);
        if(code.equals("")){
            label.setText("The password entered is empty");
        }else{
            label.setText("Enter your simple password");
            resultField.setText(code);
            resultField.setVisibility(INVISIBLE);
            notif.setVisibility(VISIBLE);
            copyButton.setVisibility(VISIBLE);
            showButton.setVisibility(VISIBLE);
        }
    }

    public void copy(View view){
        TextView field = findViewById(R.id.safePassword);
        String safePassword = field.getText().toString();
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText(safePassword, safePassword);
        clipboard.setPrimaryClip(clip);

    }

    public void toggleShowPassword(View view){
        TextView resultField = findViewById(R.id.safePassword);
        TextView notif = findViewById(R.id.defaultNotif);
        if(toggle == 1){
            resultField.setVisibility(INVISIBLE);
            notif.setVisibility(VISIBLE);
            toggle = 0;
            return;
        }
        if(toggle == 0){
            resultField.setVisibility(VISIBLE);
            notif.setVisibility(INVISIBLE);
            toggle = 1;
        }

    }

}