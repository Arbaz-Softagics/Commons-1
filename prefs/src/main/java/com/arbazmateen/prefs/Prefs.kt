package com.arbazmateen.prefs

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import kotlin.reflect.KProperty

/**************************************************************************
 ** PrefDelegate
 **************************************************************************/
abstract class PreferenceDelegate<T>(private val context: Context, private val prefName: String = "") {

    protected val prefs: SharedPreferences by lazy {
        if (prefName.isNotEmpty())
            context.getSharedPreferences("${context.packageName}_$prefName", Context.MODE_PRIVATE)
        else
            PreferenceManager.getDefaultSharedPreferences(context)
    }

    abstract operator fun getValue(thisRef: Any?, property: KProperty<*>): T
    abstract operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T)
}

/**************************************************************************
 ** String Preference Delegate
 **************************************************************************/
class StringPreferenceDelegate(
    context: Context,
    private val prefKey: String,
    prefName: String = "",
    private val defaultValue: String = ""
) : PreferenceDelegate<String>(context, prefName) {
    override fun getValue(thisRef: Any?, property: KProperty<*>): String =
        prefs.getString(prefKey, defaultValue) ?: defaultValue

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: String) =
        prefs.edit().putString(prefKey, value).apply()
}

fun stringPreferenceDelegate(context: Context, prefKey: String, prefName: String = "", defaultValue: String = "") =
    StringPreferenceDelegate(context, prefKey, prefName, defaultValue)

/**************************************************************************
 ** Int Preference Delegate
 **************************************************************************/
class IntPreferenceDelegate(
    context: Context,
    private val prefKey: String,
    prefName: String = "",
    private val defaultValue: Int = -1
) : PreferenceDelegate<Int>(context, prefName) {
    override fun getValue(thisRef: Any?, property: KProperty<*>) = prefs.getInt(prefKey, defaultValue) ?: defaultValue

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: Int) =
        prefs.edit().putInt(prefKey, value).apply()
}

fun intPreferenceDelegate(context: Context, prefKey: String, prefName: String = "", defaultValue: Int = -1) =
    IntPreferenceDelegate(context, prefKey, prefName, defaultValue)

/**************************************************************************
 ** Float Preference Delegate
 **************************************************************************/
class FloatPreferenceDelegate(
    context: Context,
    private val prefKey: String,
    prefName: String = "",
    private val defaultValue: Float = -1f
) : PreferenceDelegate<Float>(context, prefName) {
    override fun getValue(thisRef: Any?, property: KProperty<*>) = prefs.getFloat(prefKey, defaultValue) ?: defaultValue

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: Float) =
        prefs.edit().putFloat(prefKey, value).apply()
}

fun floatPreferenceDelegate(context: Context, prefKey: String, prefName: String = "", defaultValue: Float = -1f) =
    FloatPreferenceDelegate(context, prefKey, prefName, defaultValue)

/**************************************************************************
 ** Boolean Preference Delegate
 **************************************************************************/
class BooleanPreferenceDelegate(
    context: Context,
    private val prefKey: String,
    prefName: String = "",
    private val defaultValue: Boolean = false
) : PreferenceDelegate<Boolean>(context, prefName) {
    override fun getValue(thisRef: Any?, property: KProperty<*>) =
        prefs.getBoolean(prefKey, defaultValue) ?: defaultValue

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: Boolean) =
        prefs.edit().putBoolean(prefKey, value).apply()
}

fun booleanPreferenceDelegate(context: Context, prefKey: String, prefName: String, defaultValue: Boolean = false) =
    BooleanPreferenceDelegate(context, prefKey, prefName, defaultValue)

/**************************************************************************
 ** Long Preference Delegate
 **************************************************************************/
class LongPreferenceDelegate(
    context: Context,
    private val prefKey: String,
    prefName: String = "",
    private val defaultValue: Long = -1L
) : PreferenceDelegate<Long>(context, prefName) {
    override fun getValue(thisRef: Any?, property: KProperty<*>) = prefs.getLong(prefKey, defaultValue) ?: defaultValue

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: Long) =
        prefs.edit().putLong(prefKey, value).apply()
}

fun longPreferenceDelegate(context: Context, prefKey: String, prefName: String = "", defaultValue: Long = -1L) =
    LongPreferenceDelegate(context, prefKey, prefName, defaultValue)

/**************************************************************************
 ** String Set Preference Delegate
 **************************************************************************/
class StringSetPreferenceDelegate(
    context: Context,
    private val prefKey: String,
    prefName: String = "",
    private val defaultValue: Set<String> = hashSetOf()
) : PreferenceDelegate<Set<String>>(context, prefName) {
    override fun getValue(thisRef: Any?, property: KProperty<*>): Set<String> =
        prefs.getStringSet(prefKey, defaultValue) ?: defaultValue

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: Set<String>) =
        prefs.edit().putStringSet(prefKey, value).apply()
}

fun stringSetPreferenceDelegate(
    context: Context,
    prefKey: String,
    prefName: String = "",
    defaultValue: Set<String> = hashSetOf()
) = StringSetPreferenceDelegate(context, prefKey, prefName, defaultValue)

/**************************************************************************
 ** Simple Prefs
 **************************************************************************/
class Prefs(private val context: Context, private val preferencesName: String = "") {

    private val sharedPreferences: SharedPreferences
        get() {
            return if (preferencesName.isEmpty())
                PreferenceManager.getDefaultSharedPreferences(context)
            else
                context.getSharedPreferences("${context.packageName}_$preferencesName", Context.MODE_PRIVATE)
        }

    companion object {
        const val DEFAULT_STRING_VALUE = ""
        const val DEFAULT_INT_VALUE = -1
        const val DEFAULT_DOUBLE_VALUE = -1.0
        const val DEFAULT_FLOAT_VALUE = -1f
        const val DEFAULT_LONG_VALUE = -1L
        const val DEFAULT_BOOLEAN_VALUE = false
    }

    fun get(key: String, defaultValue: String): String = getString(key, defaultValue) ?: defaultValue
    fun get(key: String, defaultValue: Int): Int = getInt(key, defaultValue) ?: defaultValue
    fun get(key: String, defaultValue: Long): Long = getLong(key, defaultValue) ?: defaultValue
    fun get(key: String, defaultValue: Float): Float = getFloat(key, defaultValue) ?: defaultValue
    fun get(key: String, defaultValue: Double): Double = getDouble(key, defaultValue) ?: defaultValue
    fun get(key: String, defaultValue: Boolean): Boolean = getBoolean(key, defaultValue) ?: defaultValue
    fun get(key: String, defaultValue: Set<String>): Set<String> = getStringSet(key, defaultValue) ?: defaultValue

    fun put(key: String, value: String) = putString(key, value)
    fun put(key: String, value: Int) = putInt(key, value)
    fun put(key: String, value: Long) = putLong(key, value)
    fun put(key: String, value: Float) = putFloat(key, value)
    fun put(key: String, value: Double) = putDouble(key, value)
    fun put(key: String, value: Boolean) = putBoolean(key, value)
    fun put(key: String, value: Set<String>) = putStringSet(key, value)

    fun get(res: Int, defaultValue: String): String =
        getString(context.resources.getString(res), defaultValue) ?: defaultValue

    fun get(res: Int, defaultValue: Int): Int =
        getInt(context.resources.getString(res), defaultValue) ?: defaultValue

    fun get(res: Int, defaultValue: Long): Long =
        getLong(context.resources.getString(res), defaultValue) ?: defaultValue

    fun get(res: Int, defaultValue: Float): Float =
        getFloat(context.resources.getString(res), defaultValue) ?: defaultValue

    fun get(res: Int, defaultValue: Double): Double =
        getDouble(context.resources.getString(res), defaultValue) ?: defaultValue

    fun get(res: Int, defaultValue: Boolean): Boolean =
        getBoolean(context.resources.getString(res), defaultValue) ?: defaultValue

    fun get(res: Int, defaultValue: Set<String>): Set<String> =
        getStringSet(context.resources.getString(res), defaultValue) ?: defaultValue

    fun put(res: Int, value: String) = putString(context.resources.getString(res), value)
    fun put(res: Int, value: Int) = putInt(context.resources.getString(res), value)
    fun put(res: Int, value: Long) = putLong(context.resources.getString(res), value)
    fun put(res: Int, value: Float) = putFloat(context.resources.getString(res), value)
    fun put(res: Int, value: Double) = putDouble(context.resources.getString(res), value)
    fun put(res: Int, value: Boolean) = putBoolean(context.resources.getString(res), value)
    fun put(res: Int, value: Set<String>) = putStringSet(context.resources.getString(res), value)

    fun getString(key: String, defaultValue: String = DEFAULT_STRING_VALUE): String =
        sharedPreferences.getString(key, defaultValue) ?: defaultValue

    fun putString(key: String, value: String) {
        sharedPreferences.edit().putString(key, value).apply()
    }

    fun getInt(key: String, defaultValue: Int = DEFAULT_INT_VALUE): Int = sharedPreferences.getInt(key, defaultValue)

    fun putInt(key: String, value: Int) {
        sharedPreferences.edit().putInt(key, value).apply()
    }

    fun getFloat(key: String, defaultValue: Float = DEFAULT_FLOAT_VALUE): Float =
        sharedPreferences.getFloat(key, defaultValue)

    fun putFloat(key: String, value: Float) {
        sharedPreferences.edit().putFloat(key, value).apply()
    }

    fun getLong(key: String, defaultValue: Long = DEFAULT_LONG_VALUE): Long =
        sharedPreferences.getLong(key, defaultValue)

    fun putLong(key: String, value: Long) {
        sharedPreferences.edit().putLong(key, value).apply()
    }

    fun getBoolean(key: String, defaultValue: Boolean = DEFAULT_BOOLEAN_VALUE): Boolean =
        sharedPreferences.getBoolean(key, defaultValue)

    fun putBoolean(key: String, value: Boolean) {
        sharedPreferences.edit().putBoolean(key, value).apply()
    }

    fun getDouble(key: String, defaultValue: Double = DEFAULT_DOUBLE_VALUE): Double {
        return if (!contains(key)) defaultValue else java.lang.Double.longBitsToDouble(getLong(key))
    }

    fun putDouble(key: String, value: Double) {
        putLong(key, java.lang.Double.doubleToRawLongBits(value))
    }

    fun getStringSet(key: String, defaultValue: Set<String>): Set<String> =
        sharedPreferences.getStringSet(key, defaultValue) ?: defaultValue

    fun putStringSet(key: String, value: Set<String>) {
        sharedPreferences.edit().putStringSet(key, value).apply()
    }

    fun remove(key: String) {
        sharedPreferences.edit().remove(key).apply()
    }

    // examle
    // key in Prefs(this)
    operator fun contains(key: String): Boolean {
        return sharedPreferences.contains(key)
    }

    fun clear() {
        sharedPreferences.edit().clear().apply()
    }

}