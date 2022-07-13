package com.example.cookapplite.LoginFeature.usecases

import com.example.cookapplite.LoginFeature.data.UsersRepository

class GetUserLoged(private val usersRepository: UsersRepository) {
    suspend operator fun invoke() = usersRepository.getUserLoged()
}