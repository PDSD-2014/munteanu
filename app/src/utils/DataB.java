package utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataB extends SQLiteOpenHelper {
	 // Versiune BD
            private static final int VERSIUNE_BD = 1;
        private static final String PREFIX = "_";

   	 // Nume BD
  	    private static final String NUME_BD = "Data_Set";

   	 // Tabela INDEX
  	    private static final String TABELA_INDEX = "Lectii";

 	 // coloanele tabelei CONTENT
    	private static final String ID2_KEY = "id";
   	    private static final String WORD_KEY = "word";
        private static final String TRANSLATION_KEY = "translation";
        private static final String ANSWER_KEY = "answer";

        // Coloanele tabelei INDEX
        private static final String ID_KEY = "id";
        private static final String NAME_KEY = "nume";

    public DataB(Context context) {
    	super(context, NUME_BD, null, VERSIUNE_BD);
	}

	//Crearea bazei de date
	 @Override
	  public void onCreate(SQLiteDatabase db) {
		 Log.d("DATABASE", "Creating database");
	    	String CREATE_INDEX_TABLE = "CREATE TABLE IF NOT EXISTS " + TABELA_INDEX + "("
	                + ID_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME_KEY + " TEXT )";
		 db.execSQL(CREATE_INDEX_TABLE);
	 }

	// Actualizarea bazei de date
	  @Override
	  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		  db.execSQL("DROP TABLE IF EXIST " +TABELA_INDEX);

		  onCreate(db);
	  }

	  public void dropAllTables(){
		  SQLiteDatabase db = this.getWritableDatabase();
		  List<String> tables = getIndex();
		  for(String table : tables){
			  db.execSQL("DROP TABLE IF EXISTS " + PREFIX + table);

			  db.execSQL("DROP TABLE IF EXISTS " +TABELA_INDEX);
		  }
	  }

	  public void createIndexTable(){
		  SQLiteDatabase db = this.getWritableDatabase();
		  String CREATE_INDEX_TABLE = "CREATE TABLE IF NOT EXISTS " + TABELA_INDEX + "("
	                + ID_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME_KEY + " TEXT )";
		  db.execSQL(CREATE_INDEX_TABLE);
	  }
	  public void createDatabase(String databaseName){

	    SQLiteDatabase db = this.getWritableDatabase();

	    String CREATE_INDEX_TABLE = "CREATE TABLE  IF NOT EXISTS " + PREFIX + databaseName + "("
	                + ID2_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, "
	                + ANSWER_KEY + " TEXT, "
	                + WORD_KEY + " TEXT, "
	                + TRANSLATION_KEY + " TEXT) ";
		 db.execSQL(CREATE_INDEX_TABLE);
	  }
      // Adaugare angajat
  public void addLearnItem(LearnItem item, String tableName) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues valori = new ContentValues();
        valori.put(ANSWER_KEY, item.correctAnswer);
        valori.put(WORD_KEY, item.upperMessage); // Nume Angajat
        valori.put(TRANSLATION_KEY, item.translation);


        // Inserare linie
        db.insert(PREFIX +tableName, null, valori);
            //Inchiderea conexiunii cu baza de date
        db.close();
    }
  public void addIndex(String indexName){
      SQLiteDatabase db = this.getWritableDatabase();

      ContentValues valori = new ContentValues();
      valori.put(NAME_KEY, indexName); // Nume Angajat

      // Inserare linie
      db.insert(TABELA_INDEX, null, valori);
          //Inchiderea conexiunii cu baza de date
      db.close();
  }

  public List<LearnItem> getSelectedItems(ArrayList<String> selected)
  {
	  List<LearnItem> rez = new ArrayList<>();
      SQLiteDatabase db = this.getWritableDatabase();
	  for(String table : selected){
	      String selectQuery = "SELECT  * FROM " + PREFIX +table;

	      Cursor cursor = db.rawQuery(selectQuery, null);

	      // face loop printre linii si adauga in lista
	      if (cursor.moveToFirst()) {
	          do {
	              rez.add(new LearnItem(cursor.getString(1),
	            		  				cursor.getString(2),
	            		  				cursor.getString(3)
	            		  				)
	            		  );
	          } while (cursor.moveToNext());
	      }
	  }

	  return rez;
  }
  public List<String> getIndex() {
      List<String> indexList = new ArrayList<String>();

      // interogare
      String selectQuery = "SELECT  * FROM " + TABELA_INDEX;

      SQLiteDatabase db = this.getWritableDatabase();
      Cursor cursor = db.rawQuery(selectQuery, null);

      // face loop printre linii si adauga in lista
      if (cursor.moveToFirst()) {
          do {
              indexList.add(cursor.getString(1));
          } while (cursor.moveToNext());
      }

      // returneaza lista de angajati
      return indexList;

  }

  public void addAllData(HashMap<String, ArrayList<LearnItem> > data){

	  createIndexTable();
	  dropAllTables();
	  createIndexTable();
	  Set<String> tables = data.keySet();

	  for(String table : tables){
		  createDatabase(table);
		  addIndex(table);

		  ArrayList<LearnItem> temp = data.get(table);

		  for(LearnItem item : temp){
			  addLearnItem(item, table);
		  }
	  }

  }
}