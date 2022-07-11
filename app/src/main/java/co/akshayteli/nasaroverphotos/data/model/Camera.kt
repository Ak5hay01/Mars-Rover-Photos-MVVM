package co.akshayteli.nasaroverphotos.data.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Camera(@SerializedName("full_name") val fullName: String,
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("rover_id") val roverId: Int) : Parcelable {

    fun getNameFullName() = "$name - $fullName"
    fun getCameraName() = " Camera Name - $name"


}