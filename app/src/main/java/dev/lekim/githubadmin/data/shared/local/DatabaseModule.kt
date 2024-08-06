package dev.lekim.githubadmin.data.shared.local

import android.content.Context
import androidx.room.*
import dagger.*
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.lekim.githubadmin.data.user.userList.local.*
import dev.lekim.githubadmin.data.user.UserItem

import javax.inject.Singleton

/**
 * Database class with a singleton Instance object.
 */
@Database(entities = [UserItem::class], version = 1, exportSchema = false)
abstract class GithubAdminDatabase : RoomDatabase() {
    abstract fun userItemDao(): UserItemDao
}

@InstallIn(SingletonComponent::class)
@Module
object GithubAdminDatabaseModule {
    @Singleton
    @Provides
    fun provideGithubAdminDatabase(@ApplicationContext context: Context): GithubAdminDatabase {
        return Room.databaseBuilder(context, GithubAdminDatabase::class.java, "github_admin_db")
            /**
             * Setting this option in your app's database builder means that Room
             * permanently deletes all data from the tables in your database when it
             * attempts to perform a migration with no defined migration path.
             */
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideUserListLocal(db: GithubAdminDatabase): UserListLocal {
        return UserListLocal(db.userItemDao())
    }
}
