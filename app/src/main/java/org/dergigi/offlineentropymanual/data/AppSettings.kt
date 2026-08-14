package org.dergigi.offlineentropymanual.data

import android.content.Context

enum class ThemePreference(val storageValue: String, val label: String) {
    Day("day", "Day"),
    Night("night", "Night"),
    DarkNight("darknight", "Dark Night"),
    System("system", "System"),
    ;

    companion object {
        fun fromStorage(value: String?): ThemePreference =
            entries.firstOrNull { it.storageValue == value } ?: System
    }
}

enum class TextSizePreference(val storageValue: String, val label: String, val scale: Float) {
    Tiny("0", "Tiny", 0.8f),
    Small("1", "Small", 0.9f),
    Medium("2", "Medium", 1.0f),
    Large("3", "Large", 1.2f),
    Huge("4", "Huge", 1.4f),
    ;

    companion object {
        fun fromStorage(value: String?): TextSizePreference =
            entries.firstOrNull { it.storageValue == value } ?: Medium
    }
}

object AppSettings {
    private const val PrefsName = "oem_settings"
    private const val KeyTheme = "nightmode"
    private const val KeyTextSize = "fontsize"

    private fun prefs(context: Context) =
        context.getSharedPreferences(PrefsName, Context.MODE_PRIVATE)

    fun theme(context: Context): ThemePreference =
        ThemePreference.fromStorage(prefs(context).getString(KeyTheme, ThemePreference.System.storageValue))

    fun textSize(context: Context): TextSizePreference =
        TextSizePreference.fromStorage(
            prefs(context).getString(KeyTextSize, TextSizePreference.Medium.storageValue),
        )

    fun setTheme(context: Context, value: ThemePreference) {
        prefs(context).edit().putString(KeyTheme, value.storageValue).apply()
    }

    fun setTextSize(context: Context, value: TextSizePreference) {
        prefs(context).edit().putString(KeyTextSize, value.storageValue).apply()
    }
}
