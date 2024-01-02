package cn.aszswaz.codebook.helper

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import cn.aszswaz.codebook.entity.Account

class AccountHelper(context: Context) : SQLiteOpenHelper(context, "account.db", null, 1) {
    companion object {
        private const val TABLE = "account"
    }


    override fun onCreate(db: SQLiteDatabase?) {
        val sql = "CREATE TABLE IF NOT EXISTS $TABLE (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                "account VARCHAR NOT NULL," +
                "password VARCHAR NOT NULL," +
                "platform VARCHAR NOT NULL," +
                "length INTEGER NOT NULL," +
                "letter INTEGER NOT NULL," +
                "number INTEGER NOT NULL," +
                "symbol INTEGER NOT NULL" +
                ");"
        db?.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }

    fun insert(value: Account) {
        writableDatabase.insert(TABLE, null, toContentValues(value))
    }

    fun update(value: Account) {
        writableDatabase.update(
            TABLE,
            toContentValues(value),
            "id = ?",
            arrayOf(value.id.toString())
        )
    }

    fun findAll(): List<Account> {
        val db = writableDatabase
        val l = arrayListOf<Account>()

        val cursor = db.query(
            TABLE,
            arrayOf(
                "id",
                "account",
                "password",
                "platform",
                "length",
                "letter",
                "number",
                "symbol"
            ),
            null,
            null,
            null,
            null,
            "id",
            null
        )
        for (i in 0..<cursor.count) {
            var index = 0
            cursor.moveToNext()
            l.add(
                Account(
                    id = cursor.getInt(0),
                    account = cursor.getString(++index),
                    password = cursor.getString(++index),
                    platform = cursor.getString(++index),
                    length = cursor.getInt(++index),
                    letter = cursor.getInt(++index) != 0,
                    number = cursor.getInt(++index) != 0,
                    symbol = cursor.getInt(++index) != 0
                )
            )
        }
        cursor.close()
        return l
    }

    private fun toContentValues(value: Account): ContentValues {
        val values = ContentValues()

        values.put("account", value.account)
        values.put("password", value.password)
        values.put("platform", value.platform)
        values.put("length", value.length)
        values.put("letter", value.letter)
        values.put("number", value.number)
        values.put("symbol", value.symbol)
        return values
    }
}