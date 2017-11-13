package com.solstice.solsticechallenge.dagger.module;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.solstice.solsticechallenge.Constants;
import com.solstice.solsticechallenge.ui.mvp.model.dao.ContactsDao;
import com.solstice.solsticechallenge.ui.mvp.model.db.ContactsDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RoomDatabaseModule {

    private ContactsDatabase contactsDatabase;

    public RoomDatabaseModule(Application application) {

        contactsDatabase = Room
                .databaseBuilder(application, ContactsDatabase.class, Constants.DATABASE_NAME)
                .allowMainThreadQueries()
                .build();
    }

    @Provides
    @Singleton
    ContactsDatabase provideContactsDatabase() {
        return contactsDatabase;
    }

    @Provides
    @Singleton
    ContactsDao provideContactsDao(ContactsDatabase contactsDatabase) {
        return contactsDatabase.contactsDao();
    }
}
