package com.example.randomuser.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.randomuser.data.database.entity.RandomUser
import com.example.randomuser.viewmodels.RandomUserViewModel
import com.google.accompanist.glide.rememberGlidePainter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: RandomUserViewModel by viewModels()
    private var userName = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val data = viewModel.users.observeAsState()
            NavHost(
                navController = navController,
                startDestination = "screen1"
            ) {
                composable("screen1") {
                    data.value?.data?.let {
                        UserListScreen(
                            navController,
                            userList = it
                        )
                    }
                }
                composable("screen2") { UserDetailsScreen(navController) }
            }

        }
    }

    @Composable
    fun UserListScreen(navController: NavController, userList: List<RandomUser>) {
        Log.v(MainActivity::class.java.simpleName, "userlist - > ${userList.toString()}")
        Column(modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxWidth()) {
            userList.map { user ->
                Log.v(
                    MainActivity::class.java.simpleName,
                    "it.firstName  -> ${user.firstName}, userName  -> ${user.userName}, pic -> ${user.thumbPicture}"
                )
                UserListItem(user) {
                    viewModel.onUserItemClicked(user.userName)
                    navController.navigate("Screen2")
                }
            }
        }
    }

    @Composable
    fun UserListItem(user: RandomUser, onItemClick: () -> Unit) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = onItemClick)
                .padding(16.dp)
        ) {
            Image(
                painter = rememberGlidePainter(user.thumbPicture),
                contentDescription = null,
                modifier = Modifier
                    .size(64.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(text = "${user.userName} ${user.firstName}")
                Text(text = user.userName, style = MaterialTheme.typography.caption)
            }
        }
    }

    @Composable
    fun UserDetailsScreen(navController: NavController) {
        val data = viewModel.user.observeAsState()
        val user = data.value?.data
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = rememberGlidePainter(user?.pic),
                contentDescription = null,
                modifier = Modifier
                    .size(128.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "${user?.firstName} ${user?.lastName}", style = MaterialTheme.typography.h4)
            user?.let { Text(text = it.email, style = MaterialTheme.typography.subtitle1) }
            user?.let { Text(text = it.userName, style = MaterialTheme.typography.subtitle1) }
            user?.let { Text(text = it.phone, style = MaterialTheme.typography.subtitle1) }
            user?.let { Text(text = it.cellPhone, style = MaterialTheme.typography.subtitle1) }
        }
    }
}