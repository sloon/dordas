package com.example.smukku.dordas

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Restaurant(@Expose val id: Int?,
                      @SerializedName("cover_img_url") val coverUrl: String?,
                      @Expose val status: String?,
                      @Expose val business: Business?,
                      @Expose val description: String?)

data class Business(@Expose val name: String?)