package ru.skillbranch.skillarticles.extensions

import java.util.*

fun String?.indexesOf(substr: String, ignoreCase: Boolean = true): List<Int> {
    this ?: return emptyList()
    val result = mutableListOf<Int>()
    if (substr.isNotEmpty()) {
        substr.toLowerCase(ignoreCase).toRegex()
            .findAll(this.toLowerCase(ignoreCase))
            .forEach {
                result.add(it.range.first)
            }
    }
    return result
}

private fun String.toLowerCase(flag: Boolean) = if (flag) this.toLowerCase(Locale.ROOT) else this