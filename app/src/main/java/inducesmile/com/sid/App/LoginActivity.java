package inducesmile.com.sid.App;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONArray;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

import inducesmile.com.sid.Connection.ConnectionHandler;
import inducesmile.com.sid.Helper.UserLogin;
import inducesmile.com.sid.R;
//Esta aplicação serve como base para vos ajudar, precisam de completar os métodos To do de modo a que a aplicação faça o minimo que é suposto, podem adicionar novas features ou mudar a UI se acharem relevante.
public class LoginActivity extends AppCompatActivity {
    private EditText ip, port,username,password;
    private Button login;
    private String READ_HUMIDADE_TEMPERATURA;


    //TODO Create AutoCompleteTextView in this method for all fields

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ip = findViewById(R.id.ip);
        port = findViewById(R.id.port);
        username=findViewById(R.id.username);
        password = findViewById(R.id.password);
        login =  findViewById(R.id.login);
    }

    @SuppressLint("WrongConstant")
    public void loginClick(View v){
        READ_HUMIDADE_TEMPERATURA = "http://" + ip.getText().toString() + ":" + port.getText().toString() + "/getHumidade_Temperatura.php?username=" + username.getText().toString() + "&password="+password.getText().toString();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        new UserLogin(ip.getText().toString(), port.getText().toString(),username.getText().toString(),password.getText().toString());
        ConnectionHandler jParser = new ConnectionHandler();
        HashMap<String, String> params = new HashMap<>();
        params.put("username", username.getText().toString());
        params.put("password", password.getText().toString());
        JSONArray jsonHumidadeTemperatura = jParser.getJSONFromUrl(READ_HUMIDADE_TEMPERATURA, params);
        if (jsonHumidadeTemperatura !=null){
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
            finish();

        }else{
        findViewById(R.id.loginFail).setVisibility(0);}
    }


}
