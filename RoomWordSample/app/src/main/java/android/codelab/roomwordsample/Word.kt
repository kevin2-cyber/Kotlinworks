package android.codelab.roomwordsample

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "word_table")
data class Word(
     @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "word")
    val word: String)
