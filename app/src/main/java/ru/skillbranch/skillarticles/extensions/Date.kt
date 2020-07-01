package ru.skillbranch.skillarticles.extensions

import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.abs

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR

fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy"): String {
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date?.shortFormat(): String {
    val pattern = if (this?.isSameDate(Date())!!) "HH:mm" else "dd.MM.yy"
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

private fun Date.isSameDate(date: Date): Boolean {
    val day1 = this.time / DAY
    val day2 = date.time / DAY
    return day1 == day2
}

fun Date.add(value: Int, units: TimeUnits = TimeUnits.SECOND): Date {
    var time = this.time

    time += when (units) {
        TimeUnits.SECOND -> value * SECOND
        TimeUnits.MINUTE -> value * MINUTE
        TimeUnits.HOUR -> value * HOUR
        TimeUnits.DAY -> value * DAY
    }
    this.time = time
    return this
}

fun Date.humanizeDiff(): String {
    var date = this.time
    val currentDate = Date().time
    var diffDate = currentDate.minus(date)
    if (diffDate > 0) {
        return when (diffDate) {
            in 0L until SECOND -> "только что"
            in SECOND until 45 * SECOND -> "несколько секунд назад"
            in 45 * SECOND until 75 * SECOND -> "минуту назад"
            in 75 * SECOND until 45 * MINUTE -> TimeUnits.MINUTE.plural(diffDate.div(MINUTE).toInt()) + " назад"
            in 45 * MINUTE until 75 * MINUTE -> "час назад"
            in 75 * MINUTE until 22 * HOUR -> TimeUnits.HOUR.plural(diffDate.div(HOUR).toInt()) + " назад"
            in 22 * HOUR until 26 * HOUR -> "день назад"
            in 26 * HOUR until 360 * DAY -> TimeUnits.DAY.plural(diffDate.div(DAY).toInt()) + " назад"
            else -> "более года назад"
        }
    } else {
        diffDate = abs(diffDate)
        diffDate += SECOND
        return when (diffDate) {
            in 0L until SECOND -> "только что"
            in SECOND until 45 * SECOND -> "через несколько секунд"
            in 45 * SECOND until 75 * SECOND -> "через минуту"
            in 75 * SECOND until 45 * MINUTE -> "через ${TimeUnits.MINUTE.plural(diffDate.div(MINUTE).toInt())}"
            in 45 * MINUTE until 75 * MINUTE -> "через час"
            in 75 * MINUTE until 22 * HOUR -> "через ${TimeUnits.HOUR.plural(diffDate.div(HOUR).toInt())}"
            in 22 * HOUR until 26 * HOUR -> "через день"
            in 26 * HOUR until 360 * DAY -> "через ${TimeUnits.DAY.plural(diffDate.div(DAY).toInt())}"
            else -> "более чем через год"
        }
    }
}

enum class TimeUnits {
    SECOND,
    MINUTE,
    HOUR,
    DAY;

    fun plural(value: Int): String {
        var unit = this@TimeUnits
        return when (unit) {
            SECOND -> "$value ${when (value.toString().substring(value.toString().length - 1)) {
                "1" -> if (value.toString().length > 1 &&
                    value.toString().substring(value.toString().length - 2) == "11"
                ) {
                    "секунд"
                } else {
                    "секунду"
                }
                "2", "3", "4" -> if (value.toString().length > 1 &&
                    value.toString().substring(
                        value.toString().length - 2,
                        value.toString().length - 1
                    ) == "1"
                ) {
                    "секунд"
                } else {
                    "секунды"
                }
                else -> "секунд"
            }
            }"
            MINUTE -> "$value ${when (value.toString().substring(value.toString().length - 1)) {
                "1" -> if (value.toString().length > 1 &&
                    value.toString().substring(value.toString().length - 2) == "11"
                ) {
                    "минут"
                } else {
                    "минуту"
                }
                "2", "3", "4" -> if (value.toString().length > 1 &&
                    value.toString().substring(
                        value.toString().length - 2,
                        value.toString().length - 1
                    ) == "1"
                ) {
                    "минут"
                } else {
                    "минуты"
                }
                else -> "минут"
            }
            }"
            HOUR -> "$value ${when (value.toString().substring(value.toString().length - 1)) {
                "1" -> if (value.toString().length > 1 &&
                    value.toString().substring(value.toString().length - 2) == "11"
                ) {
                    "часов"
                } else {
                    "час"
                }
                "2", "3", "4" -> if (value.toString().length > 1 &&
                    value.toString().substring(
                        value.toString().length - 2,
                        value.toString().length - 1
                    ) == "1"
                ) {
                    "часов"
                } else {
                    "часа"
                }
                else -> "часов"
            }
            }"
            DAY -> {
                "$value ${when (value.toString().substring(value.toString().length - 1)) {
                    "1" -> if (value.toString().length > 1 &&
                        value.toString().substring(value.toString().length - 2) == "11"
                    ) {
                        "дней"
                    } else {
                        "день"
                    }
                    "2", "3", "4" -> if (value.toString().length > 1 &&
                        value.toString().substring(
                            value.toString().length - 2,
                            value.toString().length - 1
                        ) == "1"
                    ) {
                        "дней"
                    } else {
                        "дня"
                    }
                    else -> "дней"
                }
                }"
            }
        }
    }
}