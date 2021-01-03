package com.example.zametki.generic;

import android.annotation.SuppressLint;
import android.database.Cursor;

import com.example.zametki.Models.Note;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

import static android.content.Context.MODE_PRIVATE;

public class DB {

    private static final Semaphore Connections = new Semaphore(1, true);

    public static void init() throws InterruptedException {
        Connections.acquire();

        Registry.DB = Registry.baseContext.openOrCreateDatabase(
                "app.db", MODE_PRIVATE, null);

        Registry.DB.execSQL("CREATE TABLE IF NOT EXISTS notes (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "title TEXT, date TEXT, description TEXT)");

        Connections.release();
    }

    public static void addNote(Note note) throws InterruptedException {

        Connections.acquire();

        String query = String.format(
                "INSERT INTO notes (title, date, description) VALUES ('%s', '%s', '%s')",
                note.title,
                note.date,
                note.description
        );

        Registry.DB.execSQL(query);

        Connections.release();
    }

    public static List<Note> getAllNotes() throws InterruptedException {
        Connections.acquire();
        List<Note> notes = new ArrayList<>();

        Cursor result = Registry.DB.rawQuery("SELECT * FROM notes", null);
        if (result.moveToFirst()) {
            do {
                Note note = new Note();
                note.id = result.getInt(result.getColumnIndex("id"));
                note.title = result.getString(result.getColumnIndex("title"));
                note.date = result.getString(result.getColumnIndex("date"));
                note.description = result.getString(result.getColumnIndex("description"));
                notes.add(note);
            } while (result.moveToNext());
        }

        Connections.release();
        return notes;
    }

    public static void updateNote(Note note) throws InterruptedException {
        Connections.acquire();

        @SuppressLint("DefaultLocale") String query = String.format(
                "UPDATE notes SET title = '%s', date = '%s', description = '%s' WHERE id = '%d'",
                note.title,
                note.date,
                note.description,
                note.id
        );

        Registry.DB.execSQL(query);

        Connections.release();
    }
}