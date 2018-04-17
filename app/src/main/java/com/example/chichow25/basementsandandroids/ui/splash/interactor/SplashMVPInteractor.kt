package com.example.chichow25.basementsandandroids.ui.splash.interactor

import com.example.chichow25.basementsandandroids.repo.gamedata.Monster
import com.example.chichow25.basementsandandroids.repo.retrofit.models.Armor
import com.example.chichow25.basementsandandroids.repo.retrofit.models.Weapon
import com.example.chichow25.basementsandandroids.repo.room.GameState
import com.example.chichow25.basementsandandroids.ui.base.interactor.MVPInteractor
import io.reactivex.Observable

/**
 * Created by per6 on 4/17/18.
 */
interface SplashMVPInteractor: MVPInteractor {
    fun getGameState(): Observable<List<GameState>>
    fun provideArmor(index: Int): Observable<Armor>
    fun provideWeapon(index: Int): Observable<Weapon>
    fun provideMonster(index: Int): Observable<Monster>
}