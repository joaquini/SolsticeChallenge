package com.solstice.solsticechallenge.ui.mvp.model.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.solstice.solsticechallenge.Constants;
import com.solstice.solsticechallenge.ui.mvp.model.dao.ContactsDao;
import com.solstice.solsticechallenge.ui.mvp.model.entities.Contact;

@Database(entities = {Contact.class}, version = Constants.DB_VERSION, exportSchema = false)
public abstract class ContactsDatabase extends RoomDatabase {

    public abstract ContactsDao contactsDao();
}
