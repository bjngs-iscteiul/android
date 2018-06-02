package inducesmile.com.sid.App;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import inducesmile.com.sid.DataBase.DataBaseHandler;
import inducesmile.com.sid.DataBase.DataBaseReader;
import inducesmile.com.sid.R;

public class AlertasActivity extends AppCompatActivity {

    DataBaseHandler db = new DataBaseHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();

        int idCultura = intent.getIntExtra("idCultura",0);

        setContentView(R.layout.activity_alertas);
        Cursor alertasCursor= getAlertasCursor(idCultura+"");
        Cursor culturaCursor = getCulturaCursor();
        updateNomeCultura(culturaCursor);
        listAlertas(alertasCursor);
    }

    public Cursor getCulturaCursor(){
        DataBaseReader dbReader = new DataBaseReader(db);
        Cursor cursor = dbReader.readCultura();
        return cursor;
    }

    public Cursor getAlertasCursor(String idCultura){
        //To do
        DataBaseReader dbReader = new DataBaseReader(db);
        //EditText idCultura = findViewById(R.id.idCultura);
        //Cursor cursor = dbReader.readAlertas(Integer.valueOf(String.valueOf(idCultura.getText())));
        Cursor cursor = dbReader.readAlertas(Integer.valueOf(String.valueOf(idCultura)));
        return cursor;
    }
    private void updateNomeCultura(Cursor culturaCursor){
        String nome=null;
        while (culturaCursor.moveToNext()){
            nome = culturaCursor.getString(culturaCursor.getColumnIndex("NomeCultura"));
        }

        TextView tv = findViewById(R.id.nome_cultura_alerta_tv);
        if (nome!=null){
        tv.setText(nome);}
    }

    private void listAlertas(Cursor alertasCursor){

        TableLayout table = findViewById(R.id.tableAlertas);
        while (alertasCursor.moveToNext()){
            TableRow row = new TableRow(this);

            row.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT));

            TextView alerta = new TextView(this);
            alerta.setText(alertasCursor.getString(alertasCursor.getColumnIndex("texto")));
            alerta.setPadding(dpAsPixels(16),dpAsPixels(5),20,0);

            row.addView(alerta);

            row.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    v.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_light));
                }
            });




            table.addView(row, new TableLayout.LayoutParams(TableLayout.LayoutParams.FILL_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
        }






    }



private int dpAsPixels(int dp){
    float scale = getResources().getDisplayMetrics().density;
    return (int) (dp*scale + 0.5f);

}

    public void refreshAlerts(View v){

    }


}
