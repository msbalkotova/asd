package kz.diploma.workgram.repositories.vo

import androidx.annotation.Nullable

class Resource<T>(val status: Status, val data: T?, val messageCode: Int?) {
    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }
        if (other == null || javaClass != other.javaClass) {
            return false
        }

        val resource = other as Resource<*>?

        if (status !== resource!!.status) {
            return false
        }
        return if (data != null) data == resource!!.data else resource!!.data == null
    }

    override fun toString(): String {
        return "Resource{" +
                "status=" + status +
                ", data=" + data +
                '}'.toString()
    }

    companion object {
        fun <T> success(@Nullable data: T?): Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }

        fun <T> error(messageCode: Int?, @Nullable data: T?): Resource<T> {
            return Resource(Status.ERROR, data, messageCode)
        }

        fun <T> loading(@Nullable data: T?): Resource<T> {
            return Resource(Status.LOADING, data, null)
        }
    }
}