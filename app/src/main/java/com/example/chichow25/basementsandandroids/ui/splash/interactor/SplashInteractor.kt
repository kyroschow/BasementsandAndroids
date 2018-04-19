package com.example.chichow25.basementsandandroids.ui.splash.interactor

import android.content.Context
import com.example.chichow25.basementsandandroids.repo.gamedata.Monster
import com.example.chichow25.basementsandandroids.repo.retrofit.DndApi
import com.example.chichow25.basementsandandroids.repo.retrofit.models.Armor
import com.example.chichow25.basementsandandroids.repo.retrofit.models.Weapon
import com.example.chichow25.basementsandandroids.repo.room.GameState
import com.example.chichow25.basementsandandroids.repo.room.GameStateRepo
import com.example.chichow25.basementsandandroids.ui.base.interactor.BaseInteractor
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by per6 on 4/17/18.
 */
class SplashInteractor @Inject constructor(private val mContext: Context, private val gameStateRepoHelper: GameStateRepo, private val apiHelper: DndApi) : BaseInteractor(), SplashMVPInteractor{
    override fun provideArmor(index: Int): Observable<Armor> = apiHelper.getArmourAt(index)

    override fun provideWeapon(index: Int): Observable<Weapon> = apiHelper.getWeaponAt(index)

    override fun provideMonster(index: Int): Observable<Monster> = apiHelper.getMonsterAt(index)

    override fun getGameState(): Observable<List<GameState>> {
        return gameStateRepoHelper.loadGameStates()
    }

}