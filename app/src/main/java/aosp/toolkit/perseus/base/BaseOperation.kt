package aosp.toolkit.perseus.base

import android.annotation.SuppressLint
import android.app.Activity
import android.app.ActivityManager
import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.widget.TextView
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT

import com.topjohnwu.superuser.Shell

import java.io.*
import java.util.regex.Pattern

import aosp.toolkit.perseus.R
import java.util.*

/*
 * OsToolkit - Kotlin
 *
 * Date : 31/12/2018
 *
 * By   : 1552980358
 *
 */

@Suppress("FunctionName")
class BaseOperation {
    companion object {
        fun getPackageVersion(context: Context?): String {
            try {
                return context?.packageManager!!.getPackageInfo(
                    BaseIndex.PackageName, 0
                ).versionName
            } catch (e: java.lang.Exception) {
                e.printStackTrace()
            }
            return ""
        }

        fun getAvailableCore(): Int {
            try {
                val dir = File("/sys/devices/system/cpu/")
                val file: Array<java.io.File> = dir.listFiles(FileFilter {
                    Pattern.matches("cpu[0-9]", it.name)
                })
                return file.size
            } catch (e: java.lang.Exception) {
                e.printStackTrace()
            }
            return 0
        }

        fun setPermission(filePath: String): Boolean {
            val file = File(filePath)

            file.setReadable(true)
            file.setWritable(true)
            file.setExecutable(true)

            return file.canExecute()
        }

        fun suFileReadLine(filePath: String): String {
            val line: String? = try {
                Runtime.getRuntime().exec("su -c cat $filePath").inputStream.bufferedReader().readLine()
            } catch (e: Exception) {
                "Fail"
            }
            return line ?: "Fail"
        }

        fun javaFileReadLine(file: String): String {
            val line: String? = try {
                FileInputStream(file).bufferedReader(Charsets.UTF_8).readLine()
            } catch (e: Exception) {
                "Fail"
            }

            return line ?: "Fail"
        }

        fun checkFilePresent(filePath: String): Boolean {
            val file = File(filePath)
            return file.exists()
        }

        fun readShellContent(command: String): String {
            val su = Shell.su(command).exec().out
            val stringBuilder = StringBuilder()
            for (i: Int in 0 until su.size) {
                stringBuilder.append(su[i])
                if (i != su.size - 1) {
                    stringBuilder.append("\n")
                }
            }

            return if (!stringBuilder.toString().isEmpty()) {
                stringBuilder.toString()
            } else {
                "Fail"
            }
        }


        fun getAndroidVersion(): String {
            when (Build.VERSION.SDK_INT) {
                21 -> return "5.0"
                22 -> return "5.1"
                23 -> return "6.0"
                24 -> return "7.0"
                25 -> return "7.1"
                26 -> return "8.0"
                27 -> return "8.1"
                28 -> return "9.0"
            }
            return "Fail"
        }

        fun getAndroidVersionName(): String {
            when (Build.VERSION.SDK_INT) {
                21, 22 -> return "Lollipop"
                23 -> return "Marshmallow"
                24, 25 -> return "Nougat"
                26, 27 -> return "Oreo"
                28 -> return "Pie"
            }
            return "Fail"
        }

        fun getABIs(): String {
            val stringBuilder = StringBuilder()
            for (i: Int in 0 until Build.SUPPORTED_ABIS.size) {
                stringBuilder.append(Build.SUPPORTED_ABIS[i])
                if (i < Build.SUPPORTED_ABIS.size - 1) {
                    stringBuilder.append("\n")
                }
            }
            return stringBuilder.toString()
        }

        fun getABI64(): String {
            val stringBuilder = StringBuilder()
            for (i: Int in 0 until Build.SUPPORTED_64_BIT_ABIS.size) {
                stringBuilder.append(Build.SUPPORTED_64_BIT_ABIS[i])
                if (i < Build.SUPPORTED_64_BIT_ABIS.size - 1) {
                    stringBuilder.append("\n")
                }
            }
            return stringBuilder.toString()
        }

        fun getABI32(): String {
            val stringBuilder = StringBuilder()
            for (i: Int in 0 until Build.SUPPORTED_32_BIT_ABIS.size) {
                stringBuilder.append(Build.SUPPORTED_32_BIT_ABIS[i])
                if (i < Build.SUPPORTED_32_BIT_ABIS.size - 1) {
                    stringBuilder.append("\n")
                }
            }
            return stringBuilder.toString()
        }

        fun unitConvert(long: Long): String {
            return when {
                long > 1024 * 1024 -> (long / 1024 / 1024).toString() + "MB"
                long > 1024 -> (long / 1024).toString() + "KB"
                else -> long.toString() + "B"
            }
        }

        fun getMemory(context: Context): ActivityManager.MemoryInfo {
            val memoryInfo = ActivityManager.MemoryInfo()
            val activityManager =
                context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
            activityManager.getMemoryInfo(memoryInfo)
            return memoryInfo
        }

        fun ShortToast(context: Context, int: Int) {
            this.ShortToast(context, context.getString(int))
        }

        fun ShortToast(context: Context, exception: Exception) {
            this.ShortToast(context, exception.toString())
        }

        @SuppressLint("InflateParams")
        fun ShortToast(context: Context, string: String) {
            val toast = Toast(context)
            val view = LayoutInflater.from(context).inflate(R.layout.toast, null)
            val textView = view.findViewById<TextView>(R.id.toast)
            textView.text = string
            toast.view = view
            toast.duration = LENGTH_SHORT
            toast.show()
        }

        fun ShortToast(activity: Activity, exception: Exception, UIThread: Boolean) {
            this.ShortToast(activity, exception.toString(), UIThread)
        }

        fun ShortToast(activity: Activity, content: Int, UIThread: Boolean) {
            this.ShortToast(activity, activity.getString(content), UIThread)
        }

        @SuppressLint("InflateParams")
        fun ShortToast(activity: Activity, string: String, UIThread: Boolean) {
            if (UIThread) {
                val toast = Toast(activity as Context)
                val view = LayoutInflater.from(activity as Context).inflate(R.layout.toast, null)
                val textView = view.findViewById<TextView>(R.id.toast)
                textView.text = string
                toast.view = view
                toast.duration = LENGTH_SHORT
                toast.show()
            } else {
                val view = LayoutInflater.from(activity as Context).inflate(R.layout.toast, null)
                val textView = view.findViewById<TextView>(R.id.toast)
                textView.text = string
                activity.runOnUiThread {
                    val t = Toast(activity)
                    t.view = view
                    t.duration = LENGTH_SHORT
                    t.show()
                }
            }
        }
    }

    class File {
        private var file: java.io.File
        private var filePath: String
        private lateinit var fileName: String

        constructor(filePath: String, fileName: String) : super() {
            this.filePath = filePath
            this.fileName = fileName
            file = File(filePath + fileName)
        }

        constructor(filePath: String, containsFileName: Boolean) : super() {
            if (containsFileName) {
                val arrayList = try {
                    Arrays.asList(
                        *filePath.split(
                            (java.io.File.separator).toRegex()
                        ).dropLastWhile { it.isEmpty() }.toTypedArray()
                    )
                } catch (e: Exception) {
                    arrayListOf(filePath)
                }
                this.fileName = if (arrayList.size < 2) {
                    this.filePath = arrayList.first()
                    ""
                } else {
                    for (i: Int in 0 until arrayList.size - 2) {
                        this.filePath = ""
                        this.filePath += arrayList[i] + java.io.File.separator
                    }
                    arrayList[arrayList.size - 1]
                }
            }
            this.filePath = filePath
            file = File(filePath)
        }

        fun setFileName(fileName: String) {
            this.fileName = fileName
        }

        fun isExists(): Boolean {
            return file.exists()
        }

        fun getFilePath(): String {
            return this.filePath
        }

        fun getFileName(): String {
            return if (!::fileName.isInitialized) {
                this.fileName
            } else {
                ""
            }
        }

        fun getFileString(): String {
            return file.toString()
        }

        fun getFile(): java.io.File {
            return this.file
        }

        fun delete(): Boolean {
            return this.file.delete()
        }

        fun createNewFile(): java.io.File {
            file.createNewFile()
            return file
        }
    }
}