package com.example.randomuser.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.randomuser.data.database.entity.DetailedUser
import com.example.randomuser.ui.ui.theme.RandomUserTheme
import com.example.randomuser.viewmodels.RandomUserViewModel
import com.google.accompanist.glide.rememberGlidePainter

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    RandomUserTheme {

    }
}