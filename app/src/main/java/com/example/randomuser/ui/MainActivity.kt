package com.example.randomuser.ui

import android.content.Intent
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
import androidx.compose.runtime.internal.composableLambda
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.randomuser.data.database.entity.DetailedUser
import com.example.randomuser.data.database.entity.RandomUser
import com.example.randomuser.viewmodels.RandomUserViewModel
import com.google.accompanist.glide.rememberGlidePainter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: RandomUserViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val data = remember {
                viewModel.users.value
            }
            data?.data?.let { UserListScreen(it) }
        }
    }

    @Composable
    fun UserListScreen(userList: List<RandomUser>) {
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
                    val intent = Intent(this@MainActivity, UserView::class.java)
                        .putExtra("UserName", user.userName)
                    startActivity(intent)
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


}