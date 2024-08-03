package com.example.digikala.navigation

sealed class ScreenPage(val route: String) {
    object Splash : ScreenPage("splash-Screen")
    object Home : ScreenPage("home-Screen")
    object Category : ScreenPage("category-Screen")
    object Basket : ScreenPage("basket-Screen")
    object Profile : ScreenPage("profile-Screen")
    object WebView : ScreenPage("WebView-Screen")
    object Checkout : ScreenPage("Checkout-Screen")
    object ProductDetail : ScreenPage("ProductDetail-Screen")
    object ConfirmPurchase : ScreenPage("ConfirmPurchase-Screen")
    object ProductPriceChartScreen : ScreenPage("ProductPriceChart-Screen")
    object ProductDescription : ScreenPage("ProductDescription-Screen")
    object ProductTechnicalFeatures : ScreenPage("ProductTechnicalFeatures-Screen")
    object AllProductComment : ScreenPage("AllProductComment-Screen")
    object NewComment : ScreenPage("NewComment-Screen")

    fun withArgs(vararg args: Any): String {
        return buildString {
            append(route)
            args.forEach {
                append("/$it")
            }
        }
    }
}