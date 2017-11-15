package com.solstice.solsticechallenge.data.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.solstice.solsticechallenge.Constants;
import com.solstice.solsticechallenge.data.dao.ContactsDao;
import com.solstice.solsticechallenge.data.entities.Contact;

@Database(entities = {Contact.class}, version = Constants.DB_VERSION, exportSchema = false)
public abstract class ContactsDatabase extends RoomDatabase {

    public abstract ContactsDao contactsDao();
}
