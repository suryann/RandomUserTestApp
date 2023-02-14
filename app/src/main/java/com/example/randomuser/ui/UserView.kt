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

class UserView : ComponentActivity() {
    private val viewModel: RandomUserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var userName = intent.extras?.getString("UserName")
        setContent {
            RandomUserTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val data = remember{
                        userName?.let { viewModel.onUserItemClicked(it) }
                    }
                    UserDetailsScreen(data as DetailedUser)
                }
            }
        }
    }
}

@Composable
fun UserDetailsScreen(user: DetailedUser) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = rememberGlidePainter(user.pic),
            contentDescription = null,
            modifier = Modifier
                .size(128.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "${user.firstName} ${user.lastName}", style = MaterialTheme.typography.h4)
        Text(text = user.email, style = MaterialTheme.typography.subtitle1)
        Text(text = user.userName, style = MaterialTheme.typography.subtitle1)
        Text(text = user.phone, style = MaterialTheme.typography.subtitle1)
        Text(text = user.cellPhone, style = MaterialTheme.typography.subtitle1)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    RandomUserTheme {

    }
}