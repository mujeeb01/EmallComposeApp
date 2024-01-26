package com.vaultspay.emallcomposeproject.domain.models.auth.signin

import com.google.gson.annotations.SerializedName
import com.vaultspay.emallcomposeproject.domain.models.BaseModel

data class SigninResponse(val data: SigninResponseData?) : BaseModel()

data class SigninResponseData(
    @SerializedName("email_verfication_status") var emailVerficationStatus: Int? = null,
    @SerializedName("phone_verfication_status") var phoneVerficationStatus: Int? = null,
    @SerializedName("user") var user: UserData? = UserData(),
    @SerializedName("access_token") var accessToken: String? = null,
    @SerializedName("token_type") var tokenType: String? = null
)

data class UserData(
    @SerializedName("id") var id: Int? = null,
    @SerializedName("role") var role: Int? = null,
    @SerializedName("role_type") var roleType: String? = null,
    @SerializedName("type") var type: String? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("email") var email: String? = null,
    @SerializedName("avatar") var avatar: String? = null,
    @SerializedName("avatar_original") var avatarOriginal: AvatarOriginal? = AvatarOriginal(),
    @SerializedName("address") var address: String? = null,
    @SerializedName("country") var country: Country? = Country(),
    @SerializedName("city") var city: City? = City(),
    @SerializedName("postal_code") var postalCode: String? = null,
    @SerializedName("country_code") var countryCode: String? = null,
    @SerializedName("phone") var phone: String? = null,
    @SerializedName("email_verfication_status") var emailVerficationStatus: Int? = null,
    @SerializedName("phone_verfication_status") var phoneVerficationStatus: Int? = null,
    @SerializedName("digital_kyc_status") var digitalKycStatus: Int? = null,
    @SerializedName("status") var status: Int? = null,
    @SerializedName("status_msg") var statusMsg: String? = null,
    @SerializedName("banned") var banned: Int? = null,
    @SerializedName("package") var packagee: Package? = Package()

)

data class AvatarOriginal(
    @SerializedName("id"   ) var id   : String? = null,
    @SerializedName("link" ) var link : String? = null
)

data class Package(
    @SerializedName("name") var name: String? = null,
    @SerializedName("amount") var amount: String? = null,
    @SerializedName("tax") var tax: String? = null,
    @SerializedName("total_amount") var totalAmount: String? = null,
    @SerializedName("duration") var duration: String? = null,
    @SerializedName("features") var features: Features? = Features(),
    @SerializedName("logo") var logo: Logo? = Logo(),
    @SerializedName("status") var status: Int? = null
)

data class Logo(
    @SerializedName("id") var id: String? = null,
    @SerializedName("link") var link: String? = null
)

data class Features(
    @SerializedName("classified_product_limit") var classifiedProductLimit: ClassifiedProductLimit? = ClassifiedProductLimit()
)

data class ClassifiedProductLimit(
    @SerializedName("name") var name: String? = null,
    @SerializedName("value") var value: String? = null,
    @SerializedName("end_label") var endLabel: String? = null
)

data class City (

    @SerializedName("id"   ) var id   : Int?    = null,
    @SerializedName("name" ) var name : String? = null

)

data class Country (

    @SerializedName("id"   ) var id   : Int?    = null,
    @SerializedName("name" ) var name : String? = null

)