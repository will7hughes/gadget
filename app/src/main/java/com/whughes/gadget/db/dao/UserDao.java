package com.whughes.gadget.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.whughes.gadget.db.entity.UserEntity;


@Dao
public interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(UserEntity user);

    @Query("SELECT * from UserIndex WHERE username=:username")
    LiveData<UserEntity> loadUserLiveData(String username);



//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    void insertAll(UserEntity... users);
//
//    @Query("SELECT user_id from UserIndex where username=:username")
//    int getUserID(String username);
//
//    @Query("SELECT * from UserIndex")
//    LiveData<List<UserEntity>> loadAllLiveData();
//
//    @Query("DELETE FROM UserIndex")
//    void deleteAll();
//
//    // Testing only
//    @Query("SELECT * from UserIndex")
//    List<UserEntity> loadAll();
//
//    @Query("SELECT * from UserIndex WHERE username=:username")
//    UserEntity loadUser(String username);
}
