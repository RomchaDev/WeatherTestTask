package com.example.layer_domain.use_cases

import com.example.layer_domain.use_cases.arguments.UseCaseArgument

// Here I've faced an interesting problem.
// All UseCases are injected with koin, so following code can't work
//
// class SomeUseCase(arg: Arg) : UseCase<T> {
//     override fun execute() {
//         arg.doSmth()
//     }
// }
//
// It is obvious that arguments can't be passed into useCase's constructor from VM.
// That is why I decided to create special class UseCaseArgument
//
//
// I wouldn't use such interface in a real project because it just makes
// code more complicated.
// This code is written only for demonstration of an interesting way of
// solving problem mentioned above.
interface UseCase<T, A : UseCaseArgument> {
    suspend fun execute(arg: A? = null): T
}

// In the real project I would have the following:
// interface UseCase<T>