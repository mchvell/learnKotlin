package moduleFour

import moduleFour.scheduler.Bucket

val List<Bucket>.spreadMs: Int
    get() = (maxOfOrNull { it.totalMs } ?: 0) - (minOfOrNull { it.totalMs } ?: 0)
