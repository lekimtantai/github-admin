package dev.lekim.githubadmin.data.user.userList.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import dev.lekim.githubadmin.data.user.UserItem

@Dao
interface UserItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsers(users: List<UserItem>)

    @Query("SELECT * from UserItems")
    fun getUsers(): Flow<List<UserItem>>
}