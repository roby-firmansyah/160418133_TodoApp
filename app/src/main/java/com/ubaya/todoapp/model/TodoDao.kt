package com.ubaya.todoapp.model

import androidx.room.*
import java.util.*

@Dao
interface TodoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg todo:Todo)

    @Query("SELECT * FROM todo WHERE is_done = 0 ORDER BY priority DESC")
    suspend fun selectAllTodo(): List<Todo>

    @Query("SELECT * FROM todo WHERE uuid = :id")
    suspend fun selectTodo(id:Int):Todo

    @Query("UPDATE todo SET title = :title, notes = :notes, priority = :priority WHERE uuid = :id")
    suspend fun update(title:String, notes:String, priority:Int, id:Int)

    @Query("Update todo SET is_done = :is_done WHERE uuid = :id")
    suspend fun checked(is_done:Int, id:Int)

    @Delete
    suspend fun deleteTodo(todo:Todo)
}