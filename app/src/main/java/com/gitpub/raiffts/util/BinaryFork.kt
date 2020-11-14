package com.gitpub.raiffts.util

class BinaryFork<T>(private val truth: Boolean, private var held: T) {

    fun mapIfTrue(runnable: (obj: T) -> T): BinaryFork<T> {
        return if (truth) {
            BinaryFork(truth, runnable.invoke(held))
        } else {
            BinaryFork(truth, held)
        }
    }

    fun mapIfFalse(runnable: (obj: T) -> T): BinaryFork<T> {
        return if (truth) {
            BinaryFork(truth, held)
        } else {
            BinaryFork(truth, runnable.invoke(held))
        }
    }

    fun useIfTrue(runnable: (obj: T) -> Unit): BinaryFork<T> {
        if (truth) {
            runnable.invoke(held)
        }
        return BinaryFork(truth, held)
    }

    fun useIfFalse(runnable: (obj: T) -> Unit): BinaryFork<T> {
        if (!truth) {
            runnable.invoke(held)
        }
        return BinaryFork(truth, held)
    }

    fun end() = held
}

inline fun <T : Any?> T.withPredicate(predicate: (obj: T) -> Boolean): BinaryFork<T> {
    return BinaryFork(predicate.invoke(this), this)
}
