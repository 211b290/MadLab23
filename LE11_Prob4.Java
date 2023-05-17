class NotesDbHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(
            "CREATE TABLE $TABLE_NAME (" +
                    "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "$COLUMN_TITLE TEXT," +
                    "$COLUMN_CONTENT TEXT," +
                    "$COLUMN_TIMESTAMP INTEGER" +
                    ")"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun insert(note: Note): Long {
        val values = ContentValues().apply {
            put(COLUMN_TITLE, note.title)
            put(COLUMN_CONTENT, note.content)
            put(COLUMN_TIMESTAMP, note.timestamp)
        }
        return writableDatabase.insert(TABLE_NAME, null, values)
    }

    fun update(note: Note): Int {
        val values = ContentValues().apply {
            put(COLUMN_TITLE, note.title)
            put(COLUMN_CONTENT, note.content)
            put(COLUMN_TIMESTAMP, note.timestamp)
        }
        val selection = "$COLUMN_ID = ?"
        val selectionArgs = arrayOf(note.id.toString())
        return writableDatabase.update(TABLE_NAME, values, selection, selectionArgs)
    }

    fun delete(note: Note): Int {
        val selection = "$COLUMN_ID = ?"
        val selectionArgs = arrayOf(note.id.toString())
        return writableDatabase.delete(TABLE_NAME, selection, selectionArgs)
    }

    fun getAllNotes(): List<Note> {
        val cursor = readableDatabase.query(
            TABLE_NAME, null, null, null,
            null, null, "$COLUMN_TIMESTAMP DESC"
        )
        val notes = mutableListOf<Note>()
        with(cursor) {
            while
