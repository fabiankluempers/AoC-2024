package kluempers.fabian.aoc.utils

fun <T, R> Pair<T, T>.map(f: (T) -> R) = f(first) to f(second)

fun <T> List<T>.toPair() = component1() to component2()

fun <T> Pair<T, T>.toList() = listOf(first, second)

fun <T> identity(t: T) = t

fun <T> List<T>.uncons() = first() to drop(1)

infix fun <T> T.cons(tail: List<T>) = listOf(this) + tail

inline fun <reified T> List<String>.arrayGrid(f: (Char) -> T) = map { it.map(f).toTypedArray() }.toTypedArray()

fun List<String>.arrayGrid() = arrayGrid(::identity)

data class Index2d(val x: Int, val y: Int)

infix fun Index2d.distance(p2: Index2d) = Distance2d(p2.x - x, p2.y - y)

fun Distance2d.from(point: Index2d) = Index2d(point.x + offsetX, point.y + offsetY)

fun Distance2d.reversed() = Distance2d(-offsetX, -offsetY)

data class Distance2d(val offsetX: Int, val offsetY: Int)

fun <T,R> List<T>.crossproduct(other: List<R>) = flatMap { l -> other.map { r -> l to r } }

// 3,4  2,10 -> -1,6  -> 2,10, 1,16 --- 4,-2