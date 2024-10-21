package com.example.calculator

class Operation(private val firstTime: String, private val secondTime: String) {

    fun addTimes(): String {
        val totalSeconds = convertToSeconds(firstTime) + convertToSeconds(secondTime)
        return convertToTimeString(totalSeconds)
    }

    fun subtractTimes(): String {
        val totalSeconds = convertToSeconds(firstTime) - convertToSeconds(secondTime)
        return if (totalSeconds < 0) "0s" else convertToTimeString(totalSeconds)
    }

    private fun convertToSeconds(time: String): Int {
        val regex = Regex("(\\d+h)?(\\d+m)?(\\d+s)?")
        val matchResult = regex.matchEntire(time)

        if (matchResult != null) {

            val (hours, minutes, seconds) = matchResult.destructured

            val hoursInSeconds = hours.removeSuffix("h").toIntOrNull()?.times(3600) ?: 0
            val minutesInSeconds = minutes.removeSuffix("m").toIntOrNull()?.times(60) ?: 0
            val secs = seconds.removeSuffix("s").toIntOrNull() ?: 0
            return hoursInSeconds + minutesInSeconds + secs
        }
            return 0
        //throw IllegalArgumentException("Invalid time format")
    }

    private fun convertToTimeString(totalSeconds: Int): String {
        val hours = totalSeconds / 3600
        val minutes = (totalSeconds % 3600) / 60
        val secs = totalSeconds % 60

        return (if (hours > 0) "${hours}h" else "") +
                (if (minutes > 0) "${minutes}m" else "") +
                if (secs > 0) "${secs}s" else ""
    }
}

