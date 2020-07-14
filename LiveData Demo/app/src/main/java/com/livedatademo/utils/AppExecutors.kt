package com.livedatademo.utils

import android.os.Handler
import android.os.Looper
import java.util.concurrent.Executor
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class AppExecutors(private val diskIO: ExecutorService, private val networkIO: Executor,
                   private val mainThread: Executor) {

    constructor() : this(
        Executors.newSingleThreadExecutor(), Executors.newFixedThreadPool(3),
        MainThreadExecutor
    )

    companion object MainThreadExecutor : Executor {
        private val mainThreadHandler = Handler(Looper.getMainLooper())
        override fun execute(command: Runnable) {
            mainThreadHandler.post(command)
        }
    }

    public fun diskIO(): ExecutorService {
        return diskIO
    }

    public fun networkIO(): Executor {
        return networkIO
    }

    public fun mainThread(): Executor {
        return mainThread
    }

}