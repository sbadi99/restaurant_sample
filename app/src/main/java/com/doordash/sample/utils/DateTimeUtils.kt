package com.doordash.sample.utils

import com.doordash.sample.model.storefeed.StoresItem
import java.time.Duration
import java.time.Instant

/**
 * DateTime Util class provides Datetime utility functions
 */
internal class DateTimeUtils {
    companion object{
      /**
       * Get closing time duration using
       * @see java.time.Instant
       */
      fun getClosingTime(item: StoresItem?): Int {
            val nextCloseTime = Instant.parse(item?.nextCloseTime)
            val duration: Duration = Duration.between(nextCloseTime, Instant.now()).abs()
             return (duration.seconds % (60 * 60) / 60).toInt()
        }
    }
}