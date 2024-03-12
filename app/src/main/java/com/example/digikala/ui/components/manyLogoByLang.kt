package com.example.digikala.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import com.example.digikala.R
import com.example.digikala.util.Constants.ENGLISH_LNG
import com.example.digikala.util.Constants.USER_LANGUAGE


    @Composable
     fun manyLogoByLang(): Painter {
        return if (USER_LANGUAGE == ENGLISH_LNG) {
            painterResource(id = R.drawable.dollar)
        } else {
            painterResource(id = R.drawable.toman)
        }
    }

