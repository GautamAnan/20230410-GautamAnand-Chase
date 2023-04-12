package com.gautam.core.fundamentals

import com.gautam.core.BaseEvent

/**
 * Event listener
 *
 * @constructor Create empty Event listener
 */
interface EventListener{
    /**
     * Event updated
     *
     * @param event
     */
    fun eventUpdated(event: BaseEvent)
}