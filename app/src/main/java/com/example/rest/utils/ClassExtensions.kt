package com.example.rest.utils

internal fun Int?.orZero(): Int = this ?: 0
internal fun Boolean?.orFalse(): Boolean = this ?: false
internal fun Boolean?.orTrue(): Boolean = this ?: true