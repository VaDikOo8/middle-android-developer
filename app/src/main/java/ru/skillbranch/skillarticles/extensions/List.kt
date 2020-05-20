package ru.skillbranch.skillarticles.extensions

fun List<Pair<Int, Int>>.groupByBounds(bounds: List<Pair<Int, Int>>): List<List<Pair<Int, Int>>> {
    val result = mutableListOf<List<Pair<Int, Int>>>()
    bounds.forEachIndexed { index, (start, end) ->
        val offsetList = mutableListOf<Pair<Int, Int>>()
        val boundRange = start..end
        this.forEach { (start, end) ->
            if (start in boundRange && end in boundRange) offsetList.add(start to end)
        }
        result.addAll(index, listOf(offsetList))
    }
    return result
}