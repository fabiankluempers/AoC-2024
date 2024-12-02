package kluempers.fabian.aoc.utils

fun <T, R> Pair<T,T>.map(f: (T) -> R) = f(first) to f(second)

fun <T> List<T>.toPair() = component1() to component2()

fun <T> Pair<T,T>.toList() = listOf(first, second)