package com.example.challenge8.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.Composable
import com.example.challenge8.R
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.challenge8.model.ResponseLogin
import com.example.challenge8.view.ui.theme.Challenge8Theme
import com.example.challenge8.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
@AndroidEntryPoint
class RegisterActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Challenge8Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    RegisterInterface()
                }
            }
        }
    }
}

@Composable
fun RegisterInterface() {
    val mContext = LocalContext.current
    val mDay: Int
    val mYear: Int
    val mMonth: Int
    val mCalendar: Calendar
    val userviewmodel = viewModel(modelClass = UserViewModel::class.java)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .verticalScroll(rememberScrollState())
    ) {
        var username by remember{
            mutableStateOf("")
        }

        var password by rememberSaveable{
            mutableStateOf("")
        }

        var email by remember{
            mutableStateOf("")
        }

        var passconfirm by rememberSaveable{
            mutableStateOf("")
        }

        Image(
            painter = painterResource(id = R.drawable.person),
            contentDescription = "img",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(128.dp)
                .clip(CircleShape)
                .border(5.dp, Color.Gray, CircleShape)
                .align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.padding(15.dp))
        TextField(
            value = username,
            onValueChange = { username = it },
            label = { Text(text = "Input username") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 15.dp)
        )
        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text(text = "Input email") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 15.dp)
        )
//        TextField(
//            value = password,
//            onValueChange = { password = it },
//            label = { Text(text = "Input password") },
//            singleLine = true,
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(bottom = 15.dp),
//            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
//            }
//        )
        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text(text = "Input password") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 15.dp)
        )
        TextField(value = passconfirm,
            onValueChange = {passconfirm = it},
            label = {Text(text = "Input konfirmasi password")},
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 15.dp)
        )

        androidx.compose.material.Button(
            onClick = {
                if (username.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()
                    && passconfirm.isNotEmpty()
                ){
                    userviewmodel.userRegister(
                        ResponseLogin(
                            email, username, password
                        )
                    )
                    Toast.makeText(mContext, "Register sukses", Toast.LENGTH_LONG)
                        .show()
                    mContext.startActivity(Intent(mContext, LoginActivity::class.java))
                }else{
                    Toast.makeText(mContext,"Isi semua data",Toast.LENGTH_LONG)
                        .show()
                }
            },
            modifier = Modifier
                .padding(top = 20.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            Text(text = "Register")
            
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview2() {
    Challenge8Theme {
        RegisterInterface()
    }
}