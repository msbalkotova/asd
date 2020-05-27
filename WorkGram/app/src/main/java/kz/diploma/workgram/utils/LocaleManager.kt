package kz.diploma.workgram.utils

import android.annotation.TargetApi
import android.content.Context
import android.os.Build
import androidx.preference.PreferenceManager
import java.util.*

class LocaleManager {
    companion object{
        val SELECTED_LANG = "Helper.SelectedLanguage"

        fun onAttach(ctx: Context?, defLang: String): Context?{
            ctx?.let{
                val lang = getPersistedData(ctx, defLang)
                return setLocale(ctx, lang)
            }
           return ctx
        }

        fun setLocale(ctx: Context?, lang: String): Context?{
            persist(ctx, lang)

            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
                return updateResources(ctx, lang)
            }

            return updateResourcesLegacy(ctx, lang)
        }

        @TargetApi(Build.VERSION_CODES.N)
        private fun updateResources(ctx: Context?, lang: String): Context?{
            val locale = Locale(lang)
            Locale.setDefault(locale)

            val config = ctx?.resources?.configuration
            config?.setLocale(locale)
            config?.setLayoutDirection(locale)

            config?.let{
                return ctx.createConfigurationContext(it)
            }

            return ctx
        }

        @SuppressWarnings("deprecation")
        private fun updateResourcesLegacy(ctx: Context?, lang: String): Context? {
            val locale = Locale(lang)
            Locale.setDefault(locale)

            val resources = ctx?.resources
            val config = resources?.configuration
            config?.locale = locale

            resources?.updateConfiguration(config, resources.displayMetrics)
            return ctx
        }

        private fun persist(ctx: Context?, lang: String){
            val sPref = PreferenceManager.getDefaultSharedPreferences(ctx)
            val editor = sPref.edit()

            editor.putString(SELECTED_LANG, lang)
            editor.apply()
        }

        fun getPersistedData(ctx: Context, lang: String): String{
            val sPref = PreferenceManager.getDefaultSharedPreferences(ctx)
            return sPref.getString(SELECTED_LANG, lang)!!
        }

    }
}