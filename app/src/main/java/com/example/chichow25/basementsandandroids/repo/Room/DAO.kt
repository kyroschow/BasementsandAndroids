
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import com.example.chichow25.basementsandandroids.repo.Room.GameState

@Dao
interface GameDataDao {

    @Query("SELECT * from gameState")
    fun getAll(): List<GameState>

    @Insert(onConflict = REPLACE)
    fun insert(GameState: GameState)

    @Query("DELETE from gameState")
    fun deleteAll()
}