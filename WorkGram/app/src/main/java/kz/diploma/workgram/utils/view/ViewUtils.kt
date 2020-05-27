package kz.diploma.workgram.utils.view

import android.animation.ValueAnimator
import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.content.res.Resources
import android.net.Uri
import android.os.Environment
import android.provider.DocumentsContract
import android.provider.MediaStore
import android.view.View
import androidx.core.content.ContextCompat
import kz.diploma.workgram.utils.PasswordCheckEnum
import java.lang.Exception
import java.util.concurrent.TimeUnit


class ViewUtils {
    companion object {
        fun slideAnimator(start: Int, end: Int, dynamicInfoLayout: View): ValueAnimator {
            val animator = ValueAnimator.ofInt(start, end)
            animator.addUpdateListener { valueAnimator ->
                //Update Height
                val value = valueAnimator.animatedValue as Int
                val layoutParams = dynamicInfoLayout.layoutParams

                layoutParams.height = value
                dynamicInfoLayout.layoutParams = layoutParams
            }
            return animator
        }

        fun getRawPhone(phone: String?): String {
            phone?.let{
                if(it.length >= 10){
                    return it.substring(it.length - 10)
                }
            }

            return ""
        }


        fun statusSetter(pass: String): PasswordCheckEnum {
            val isAtLeast8 = pass.length >= 8
            val hasSpecial = !pass.matches("[A-Za-z0-9 ]*".toRegex())
            val hasUppercase = !pass.equals(pass.toLowerCase())
            val hasLowercase = !pass.equals(pass.toUpperCase())

            if(!isAtLeast8){
                return PasswordCheckEnum.AT_LAST_8
            }

//            if(!hasSpecial){
//                return PasswordCheckEnum.SPEC_CHARS
//            }

//            if(!hasUppercase){
//                return PasswordCheckEnum.HASNT_UPPER
//            }

//            if(!hasLowercase){
//                return PasswordCheckEnum.HASNT_LOWER
//            }
            return PasswordCheckEnum.CORRECT
        }


        fun getDocumentPath(url: String, type: String?): String {
            return Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DOWNLOADS
            ).toString() + url +  "." + type
        }

        fun isPermissionGranted(permission:String, context: Context):Boolean =
            ContextCompat.checkSelfPermission(
                context,
                permission
            ) == PackageManager.PERMISSION_GRANTED

        fun formateMilliSeccond(duration: Long): String {
            return String.format(
                "%02d:%02d ", TimeUnit.MILLISECONDS.toMinutes(duration),
                TimeUnit.MILLISECONDS.toSeconds(duration) - TimeUnit.MINUTES.toSeconds(
                    TimeUnit.MILLISECONDS.toMinutes(
                        duration
                    )
                )
            )
        }

        fun convertDpToPixel(dp: Float): Int {
            val scale = Resources.getSystem().displayMetrics.density
            return (dp * scale + 0.5f).toInt()
        }

        fun getPathFromURI(uri: Uri, context: Context): String {
            var filePath = ""
            try {
                val wholeID = DocumentsContract.getDocumentId(uri)
                val id = wholeID.split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[1]
                val column = arrayOf(MediaStore.Images.Media.DATA)
                val sel = MediaStore.Images.Media._ID + "=?"

                val cursor = context.contentResolver.query(
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    column, sel, arrayOf(id), null
                )

                val columnIndex = cursor!!.getColumnIndex(column[0])
                if (cursor.moveToFirst()) {
                    filePath = cursor.getString(columnIndex)
                }
                cursor.close()
            }catch (e: Exception){}
            return filePath
        }

        fun getVersionName(context: Context, defVersion: String): String{
            var version = defVersion
            try {
                val pInfo: PackageInfo =
                    context.packageManager.getPackageInfo(context.packageName, 0)
                version = pInfo.versionName
            } catch (e: PackageManager.NameNotFoundException) {
                e.printStackTrace()
            }

            return version
        }
    }
}