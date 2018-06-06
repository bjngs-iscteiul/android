package inducesmile.com.sid.DataBase;

import android.provider.BaseColumns;

/**
 * Created by joao on 11/04/2018.
 */

public class DataBaseConfig {

    public static class HumidadeTemperatura implements BaseColumns {
        public static final String TABLE_NAME = "HumidadeTemperatura";
        public static final String COLUMN_NAME_IDMEDICAO = "IDMedicao";
        public static final String COLUMN_NAME_IDCULTURA = "IDCultura";
        public static final String COLUMN_NAME_HORAMEDICAO = "HoraMedicao";
        public static final String COLUMN_NAME_VALORMEDICAOTEMPERATURA = "ValorMedicaoTemperatura";
        public static final String COLUMN_NAME_VALORMEDICAOHUMIDADE = "ValorMedicaoHumidade";
        public static final String COLUMN_NAME_DATAMEDICAO = "DataMedicao";

    }

    public static class Alertas implements BaseColumns{
        public static final String TABLE_NAME="Alertas";
        public static final String COLUMN_NAME_IDALERTA="IDAlerta";
        public static final String COLUMN_NAME_TEXTO="texto";
        public static final String COLUMN_NAME_MIGRADO="migrado";
        public static final String COLUMN_NAME_IDCULTURA="IDCultura";
        public static final String COLUMN_NAME_VISTO="visto";
    }

    public static class Cultura implements BaseColumns{
        public static final String TABLE_NAME="Cultura";
        public static final String COLUMN_NAME_IDCULTURA="IDCultura";
        public static final String COLUMN_NAME_EMAIL="Email";
        public static final String COLUMN_NAME_NOMECULTURA="NomeCultura";
        public static final String COLUMN_NAME_LIMITEINFERIORTEMPERATURA="limiteInferiorTemperatura";
        public static final String COLUMN_NAME_LIMITESUPERIORTEMPERATURA="limiteSuperiorTemperatura";
        public static final String COLUMN_NAME_LIMITEINFERIORHUMIDADE="limiteInferiorHumidade";
        public static final String COLUMN_NAME_LIMITESUPERIORHUMIDADE="limiteSuperiorHumidade";

    }


    protected static final String SQL_CREATE_HUMIDADE_TEMPERATURA =
            "CREATE TABLE " + HumidadeTemperatura.TABLE_NAME +
                    " (" + HumidadeTemperatura.COLUMN_NAME_IDMEDICAO + " INTEGER PRIMARY KEY," +
                    HumidadeTemperatura.COLUMN_NAME_IDCULTURA + " INTEGER," +
                    HumidadeTemperatura.COLUMN_NAME_HORAMEDICAO + " TIME," +
                    HumidadeTemperatura.COLUMN_NAME_VALORMEDICAOTEMPERATURA + " INTEGER," +
                    HumidadeTemperatura.COLUMN_NAME_VALORMEDICAOHUMIDADE + " INTEGER," +
                    HumidadeTemperatura.COLUMN_NAME_DATAMEDICAO + " TIME )";

    protected static final String SQL_CREATE_ALERTAS=
            "CREATE TABLE " + Alertas.TABLE_NAME +
                    " (" + Alertas.COLUMN_NAME_IDALERTA + " INTEGER PRIMARY KEY," +
                    Alertas.COLUMN_NAME_IDCULTURA+ " INTEGER, " +
                    Alertas.COLUMN_NAME_MIGRADO+ " INTEGER, " +
                    Alertas.COLUMN_NAME_TEXTO + " TEXT, " +
                    Alertas.COLUMN_NAME_VISTO + " INTEGER)";

    protected static final String SQL_CREATE_CULTURA=
            "CREATE TABLE " + Cultura.TABLE_NAME +
                    " (" + Cultura.COLUMN_NAME_IDCULTURA + " INTEGER PRIMARY KEY," +
                    Cultura.COLUMN_NAME_EMAIL + " TEXT," +
                    Cultura.COLUMN_NAME_NOMECULTURA + " TEXT," +
                    Cultura.COLUMN_NAME_LIMITEINFERIORHUMIDADE + " DOUBLE," +
                    Cultura.COLUMN_NAME_LIMITESUPERIORHUMIDADE + " DOUBLE," +
                    Cultura.COLUMN_NAME_LIMITEINFERIORTEMPERATURA + " DOUBLE," +
                    Cultura.COLUMN_NAME_LIMITESUPERIORTEMPERATURA + " DOUBLE )";

    protected static final String SQL_DELETE_HUMIDADE_TEMPERATURA =
            "DROP TABLE IF EXISTS " + HumidadeTemperatura.TABLE_NAME;


    protected static final String SQL_DELETE_ALERTAS=
            "DROP TABLE IF EXISTS " + Alertas.TABLE_NAME;


    protected static final String SQL_DELETE_CULTURA=
            "DROP TABLE IF EXISTS " + Cultura.TABLE_NAME;
}
