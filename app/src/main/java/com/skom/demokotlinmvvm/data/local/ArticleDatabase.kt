package com.skom.demokotlinmvvm.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.skom.demokotlinmvvm.domain.Article

@Database(
    entities = [LocalArticle::class],
    version = DatabaseMigrations.DB_VERSION
)
abstract class ArticleDatabase : RoomDatabase() {

    abstract fun getArticleDao(): ArticleDao

    companion object {
        const val DB_NAME = "demokotlinmvvm_database"

        @Volatile
        private var INSTANCE: ArticleDatabase? = null

        fun getInstance(context: Context): ArticleDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ArticleDatabase::class.java,
                    DB_NAME
                ).addMigrations(*DatabaseMigrations.MIGRATIONS).build()

                INSTANCE = instance
                return instance
            }
        }
    }
}