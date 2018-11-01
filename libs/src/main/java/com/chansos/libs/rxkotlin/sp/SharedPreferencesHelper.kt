package com.chansos.libs.rxkotlin.sp

import android.content.Context
import android.content.SharedPreferences
import com.chansos.libs.rxkotlin.AppManager
import java.util.concurrent.ConcurrentHashMap

/**
 * SharedPreferences操作工具
 * */
@Suppress("UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused")
class SharedPreferencesHelper {
    private val defaultKey: String by lazy {
        AppManager.getContext().packageName
    }
    private val sharedPreferencesMapper: ConcurrentHashMap<String, SharedPreferences> by lazy {
        ConcurrentHashMap<String, SharedPreferences>()
    }

    init {
        setSp()
    }

    fun setSp(key: String = defaultKey): SharedPreferences {
        val sp = AppManager.getContext().getSharedPreferences(key, Context.MODE_PRIVATE)
        sharedPreferencesMapper[key] = sp
        return sp
    }

    fun getSp(key: String = defaultKey): SharedPreferences {
        return sharedPreferencesMapper[key] ?: return setSp(key)
    }

    private fun getEditor(key: String = defaultKey): SharedPreferences.Editor {
        return getSp(key).edit()
    }

    /**
     *
     * */
    fun set(key: String, value: Any) = set(defaultKey, key, value)

    fun set(nameSpace: String, key: String, value: Any) {
        val editor = getEditor(nameSpace)
        when (value.javaClass.simpleName) {
            "String" -> {
                editor.putString(key, value as String)
            }
            "Int" -> {
                editor.putInt(key, value as Int)
            }
            "Long" -> {
                editor.putLong(key, value as Long)
            }
            "Float" -> {
                editor.putFloat(key, value as Float)
            }
            "Boolean" -> {
                editor.putBoolean(key, value as Boolean)
            }
            else -> {
                if (value is Set<*>) {
                    editor.putStringSet(key, value as Set<String>)
                } else {
                    return
                }
            }
        }
        editor.apply()
    }

    fun <T> get(key: String, t: Class<T>, defaultValue: Any?): T? = get(defaultKey, key, t, defaultValue)

    fun <T> get(nameSpace: String, key: String, t: Class<T>, defaultValue: Any?): T? {
        when (t) {
            String::class.java -> {
                return getSp(nameSpace).getString(key, defaultValue as String) as T
            }
            Int::class.java -> {
                return getSp(nameSpace).getInt(key, defaultValue as Int) as T
            }
            Long::class.java -> {
                return getSp(nameSpace).getLong(key, defaultValue as Long) as T
            }
            Float::class.java -> {
                return getSp(nameSpace).getFloat(key, defaultValue as Float) as T
            }
            Boolean::class.java -> {
                return getSp(nameSpace).getBoolean(key, defaultValue as Boolean) as T
            }
            Set::class.java -> {
                if (defaultValue == null) {
                    return getSp(nameSpace).getStringSet(key, defaultValue) as T
                }
                return getSp(nameSpace).getStringSet(key, defaultValue as Set<String>) as T
            }
            else -> {
                return null
            }
        }
    }
}