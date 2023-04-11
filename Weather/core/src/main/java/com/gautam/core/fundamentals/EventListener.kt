package com.gautam.core.fundamentals

import com.gautam.core.BaseEvent

interface EventListener{
    fun eventUpdated(event: BaseEvent)
}