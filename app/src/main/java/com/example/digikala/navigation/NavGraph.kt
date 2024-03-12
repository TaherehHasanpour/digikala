package com.example.digikala.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.digikala.ui.screens.basket.BasketScreen
import com.example.digikala.ui.screens.category.CategoryScreen
import com.example.digikala.ui.screens.checkout.CheckoutScreen
import com.example.digikala.ui.screens.checkout.ConfirmPurchaseScreen
import com.example.digikala.ui.screens.home.HomeScreen
import com.example.digikala.ui.screens.profile.ProfileScreen
import com.example.digikala.ui.screens.splash.SplashScreen
import com.example.digikala.ui.screens.home.WebPageScreen
import com.example.digikala.ui.screens.product_detile.AllProductCommentScreen
import com.example.digikala.ui.screens.product_detile.NewCommentScreen
import com.example.digikala.ui.screens.product_detile.ProductDescriptionScreen
import com.example.digikala.ui.screens.product_detile.ProductDetailScreen
import com.example.digikala.ui.screens.product_detile.ProductTechnicalFeaturesScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = ScreenPage.Splash.route) {

        composable(ScreenPage.Splash.route) {
            SplashScreen(navController = navController)
        }
        composable(ScreenPage.Home.route) {
            HomeScreen(navController = navController)
        }
        composable(ScreenPage.Category.route) {
            CategoryScreen(navController = navController)
        }
        composable(ScreenPage.Basket.route) {
            BasketScreen(navController = navController)
        }
        composable(ScreenPage.Profile.route) {
            ProfileScreen(navController = navController)
        }
        composable(ScreenPage.Checkout.route) {
            CheckoutScreen(navController = navController)
        }
        composable(ScreenPage.ProductDetail.route + "/{productId}",
            arguments = listOf(
                navArgument(name = "productId") {
                    type = NavType.StringType
                    defaultValue = ""
                    nullable = true
                }
            )
        ) {
            it.arguments!!.getString("productId")?.let { productId ->
                ProductDetailScreen(navController = navController, productId = productId)
            }

        }
        composable(ScreenPage.ConfirmPurchase.route + "/{orderId}/{orderPrice}",
            arguments = listOf(
                navArgument(name = "orderId") {
                    type = NavType.StringType
                    defaultValue = " "
                    nullable = true
                },
                navArgument(name = "orderPrice") {
                    type = NavType.StringType
                    defaultValue = " "
                    nullable = true
                }
            )) {
            it.arguments!!.getString("orderId")?.let { orderId ->
                it.arguments!!.getString("orderPrice")?.let { orderPrice ->
                    ConfirmPurchaseScreen(
                        navController = navController,
                        orderId = orderId,
                        orderPrice = orderPrice
                    )
                }
            }

        }

        composable(
            ScreenPage.WebView.route + "?url={url}",
            arguments = listOf(navArgument("url") {
                type = NavType.StringType
                defaultValue = ""
                nullable = true
            })
        ) {
            val url = it.arguments?.getString("url")
            url?.let {
                WebPageScreen(navController = navController, url = url)
            }

        }

        composable(
            ScreenPage.ProductDescription.route + "/{description}",
            arguments = listOf(navArgument("description") {
                type = NavType.StringType
                defaultValue = " "
                nullable = true
            })
        ) {
            it.arguments!!.getString("description")?.let { description ->
                ProductDescriptionScreen(navController = navController, description = description)
            }

        }
        composable(
            ScreenPage.ProductTechnicalFeatures.route + "/{jsonString}",
            arguments = listOf(navArgument("jsonString") {
                type = NavType.StringType
                defaultValue = " "
                nullable = true
            })
        ) {
            it.arguments!!.getString("jsonString")?.let { jsonString ->
                ProductTechnicalFeaturesScreen(
                    navController = navController,
                    jsonString = jsonString
                )
            }

        }
        composable(
            ScreenPage.NewComment.route + "?productId={productId}?productName={productName}?imageUrl={imageUrl}",
            arguments = listOf(
                navArgument(name = "productId") {
                    type = NavType.StringType
                    defaultValue = " "
                    nullable = true
                },
                navArgument(name = "productName") {
                    type = NavType.StringType
                    defaultValue = " "
                    nullable = true
                },
                navArgument(name = "imageUrl") {
                    type = NavType.StringType
                    defaultValue = " "
                    nullable = true
                },

                )
        ) {
            it.arguments!!.getString("productId")?.let { productId ->
                it.arguments!!.getString("productName")?.let { productName ->
                    it.arguments!!.getString("imageUrl")?.let { imageUrl ->
                        NewCommentScreen(
                            navController = navController,
                            productId = productId,
                            productName = productName,
                            imageUrl = imageUrl
                        )
                    }
                }

            }
        }
        composable(ScreenPage.AllProductComment.route + "/{productId}/{commentCount}",
            arguments = listOf(
                navArgument(name = "productId") {
                    type = NavType.StringType
                    defaultValue = ""
                    nullable = true
                } ,
                navArgument(name = "commentCount") {
                    type = NavType.StringType
                    defaultValue = ""
                    nullable = true
                }
            )
        ) {
            it.arguments!!.getString("productId")?.let { productId ->
                it.arguments!!.getString("commentCount")?.let { commentCount ->
                    AllProductCommentScreen(navController = navController, productId = productId,commentCount=commentCount)
                }
            }
        }
    }
}