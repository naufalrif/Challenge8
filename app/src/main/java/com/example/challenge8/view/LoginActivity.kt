package com.example.challenge8.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.challenge8.MainActivity
import com.example.challenge8.R
import com.example.challenge8.datastore.UserManager
import com.example.challenge8.view.ui.theme.Challenge8Theme
import com.example.challenge8.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
@AndroidEntryPoint
class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Challenge8Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background,)
                {
                    val mContext = LocalContext.current
                    val usermanager = UserManager(mContext)
                    usermanager.boolean.asLiveData().observe(this){
                        if (it == true){
                            mContext.startActivity(Intent(mContext, MainActivity::class.java))
                        }
                    }
                    LoginInterface()
                }
            }
        }
    }
}

@Composable
fun LoginInterface() {
    val mContext = LocalContext.current
    val viewmodeluser = viewModel(modelClass = UserViewModel::class.java)
    val datauser by viewmodeluser.datauserstate.collectAsState()
    val usermanager = UserManager(mContext)
    
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        var email by remember {
            mutableStateOf("")
        }
        var password by rememberSaveable {
            mutableStateOf("")
        }
        var passwordVisible by rememberSaveable {
            mutableStateOf(false)
        }
        Spacer(modifier = Modifier.padding(15.dp))
        Image(
            painter = painterResource(id = R.drawable.person),
            contentDescription = "img",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape)
                .border(5.dp, Color.Black, CircleShape)
        )
        TextField(
            value = email,
            onValueChange = {email = it},
            label = { Text(text = "Input email")},
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 15.dp)
        )
        TextField(
            value = password,
            onValueChange = {password = it},
            label = { Text(text = "Input password")},
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 15.dp)
        )
        Button(
            onClick = {
                if (email.isNotEmpty() && password.isNotEmpty()){
                    for(i in datauser.indices){
                        if (email == datauser[i].email && password == datauser[i].password){
                            GlobalScope.launch {
                                usermanager.setBoolean(true)
                                usermanager.saveData(
                                    datauser[i].id,
                                    datauser[i].username,
                                    datauser[i].password,
                                    datauser[i].email
                                )
                            }
                            Toast.makeText(mContext, "Login sukses", Toast.LENGTH_LONG)
                                .show()
                            mContext.startActivity(Intent(mContext, MainActivity::class.java))
                        }else if(i == datauser.lastIndex && email != datauser[i].email && password != datauser[i].password){
                            Toast.makeText(mContext, "Data tidak ditemukan", Toast.LENGTH_LONG)
                                .show()
                        }
                    }
                }
            },
            modifier = Modifier.padding(top = 20.dp)
        ) {
            Text(text = "Login")
        }
        Text(
            text = "Register di sini",
            Modifier.clickable {
                mContext.startActivity(
                    Intent(mContext,RegisterActivity::class.java)
                )
            })
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview3() {
    Challenge8Theme {
        LoginInterface()
    }
}