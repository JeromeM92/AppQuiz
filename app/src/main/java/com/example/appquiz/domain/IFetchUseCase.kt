package com.example.appquiz.domain

interface IFetchUseCase<T: Any> {
    /*suspend veut dire que la fontion doit s'executer en async donc en coroutines */
    /*Le T est un type générique Variable, il peut être soit User soit ListQuestion*/
    suspend fun execute(): Result<T>
}