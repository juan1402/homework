package com.sweatworks.homework.common.utils

import android.os.Bundle
import kotlin.reflect.KClass

data class IntentEvent(
    val clazz: KClass<*>,
    val arguments: Bundle? = null,
    val finishCurrent: Boolean = true
)