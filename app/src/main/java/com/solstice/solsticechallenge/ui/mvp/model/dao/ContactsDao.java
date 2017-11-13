package com.solstice.solsticechallenge.ui.mvp.model.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.solstice.solsticechallenge.ui.mvp.model.entities.Contact;

import java.util.List;

@Dao
public interface ContactsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertContact(Contact contact);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertContacts(List<Contact> contact);

    @Update
    public void updateContact(Contact contact);

    @Query("SELECT count(*) FROM contacts")
    public int countContacts();

    @Query("SELECT * FROM contacts WHERE id LIKE :id")
    public List<Contact> findContact(String id);

    @Query("SELECT * FROM contacts WHERE favorite = 1 ORDER BY name")
    public List<Contact> findFavoriteContacts();

    @Query("SELECT * FROM contacts WHERE favorite = 0 ORDER BY name")
    public List<Contact> findNonFavoriteContacts();

    @Query("DELETE FROM contacts")
    public void deleteAllContacts();
}
