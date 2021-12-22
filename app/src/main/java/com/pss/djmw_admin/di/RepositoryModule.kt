package com.pss.djmw_admin.di

import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.pss.djmw_admin.repository.SignInRepository
import com.pss.djmw_admin.repository.SplashRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideSplashRepository(
        firebaseDatabase: FirebaseDatabase,
        firestore: FirebaseFirestore
    ) = SplashRepository(firebaseDatabase, firestore)

    @Provides
    @Singleton
    fun provideSignInRepository(
        firebaseDatabase: FirebaseDatabase,
        firestore: FirebaseFirestore
    ) = SignInRepository(firebaseDatabase, firestore)
}