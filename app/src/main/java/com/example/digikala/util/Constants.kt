package com.example.digikala.util

import com.example.digikala.BuildConfig

object Constants {
    const val ENGLISH_LNG = "en"
    const val PERSON_LNG = "fa"
    const val DATASTORE_NAME = "Digikala_dataStore"
    const val ZARINPAL_MERCHANT_ID = "ce7101df-cb08-41f8-a20b-b21995173d8f"
    var USER_LANGUAGE = "USER_LANGUAGE"
    var USER_TOKEN = "USER_TOKEN"
    var USER_ID = "USER_ID"
    var USER_PHONE = "USER_PHONE"
    var USER_PASSWORD = "USER_PASSWORD"
    const val BASE_URL = "https://truelearn-digikala.iran.liara.run/api/"
    const val TIMEOUT_IN_SECOND: Long = 60
    const val API_KEY = BuildConfig.X_API_KEY
    const val KEY = BuildConfig.KEY
    const val IV = BuildConfig.IV
    const val SHOPPING_CART_TABLE = "shopping_cart"
    const val FAVORITE_LIST_TABLE = "favorite_list_table"
    const val DATABASE_NAME = "digikala_db"

    const val DIGIJET_URL = "https://www.digikalajet.com/user/address"
    const val DIGIPLUS_URL = "https://www.digikala.com/plus/landing/?promo_name=plus&promo_position=circle_badge"
    const val DIGISTYLE_URL = "https://www.digistyle.com/sale-landing/?utm_source=digikala&utm_medium=circle_badge&utm_campaign=style&promo_name=style&promo_position=circle_badge"
    const val DIGIPEY_URL ="https://www.digikala.com/my-digipay/?promo_name=my-digipay&promo_position=circle_badge"
    const val PINDO ="https://www.pindo.ir/?utm_source=digikala&utm_medium=circle_badge&utm_campaign=pindo&promo_name=pindo&promo_position=circle_badge"
    const val GIFT_CARE = "https://www.digikala.com/landing/gift-card-landing/?promo_name=gift_landing&promo_position=circle_badge"
}