package com.araltiparmak.diction.enums

import java.util.*

enum class ContentType {
    TWISTERS, EXERCISES;

    fun lowerCase(): String = this.name.toLowerCase(Locale.ROOT)
}