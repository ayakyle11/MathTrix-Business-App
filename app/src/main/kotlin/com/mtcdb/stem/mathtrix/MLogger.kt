package com.mtcdb.stem.mathtrix

import android.content.Context
import java.io.BufferedReader
import java.io.File
import java.io.FileWriter
import java.io.IOException
import java.io.InputStreamReader
import java.util.concurrent.Executors
import java.util.regex.Pattern

object MLogger {

    private const val DEBUG = "DEBUG"
    private const val WARNING = "WARNING"
    private const val ERROR = "ERROR"
    private const val INFO = "INFO"
    private val TYPE_PATTERN = Pattern.compile("^(.*\\d) ([ADEIW]) (.*): (.*)")

    private var mInitialized: Boolean = false
    private lateinit var mContext: Context

    fun initialize(context: Context) {
        if (mInitialized) {
            return
        }
        mInitialized = true
        mContext = context.applicationContext

        start()
    }

    private fun start() {
        Executors.newSingleThreadExecutor().execute {
            try {
                clear()
                val file = File(mContext.getExternalFilesDir(null), "app_logs.txt")
                file.createNewFile()
                val process = Runtime.getRuntime().exec("logcat -f " + file.absolutePath)
                val reader = BufferedReader(InputStreamReader(process.inputStream))
                var line: String? = null
                while (reader.readLine().also { line = it } != null) {
                    val matcher = TYPE_PATTERN.matcher(line)
                    if (matcher.matches()) {
                        val type = matcher.group(2)
                        if (type != null) {
                            when (type) {
                                "D" -> debug(line!!)
                                "E" -> error(line!!)
                                "W" -> warning(line!!)
                                "I" -> info(line!!)
                            }
                        } else {
                            debug(line!!)
                        }
                    }
                }
            } catch (e: IOException) {
                error("IOException occurred on Logger: " + e.message)
            }
        }
    }

    private fun clear() {
        Runtime.getRuntime().exec("logcat -c")
    }

    private fun debug(message: String) {
        writeLogToFile(DEBUG, message)
    }

    private fun warning(message: String) {
        writeLogToFile(WARNING, message)
    }

    private fun error(message: String) {
        writeLogToFile(ERROR, message)
    }

    private fun info(message: String) {
        writeLogToFile(INFO, message)
    }

    private fun writeLogToFile(type: String, message: String) {
        try {
            val file = File(mContext.getExternalFilesDir(null), "app_logs.txt")
            val writer = FileWriter(file, true)
            writer.write("[$type] $message\n")
            writer.flush()
            writer.close()
        } catch (e: IOException) {
            error("IOException occurred while writing log to file: " + e.message)
        }
    }
}
