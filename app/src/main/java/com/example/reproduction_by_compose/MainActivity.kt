package com.example.reproduction_by_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.Face
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

val description="placeholder"
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            var displayPwd by remember {
                mutableStateOf(false)
            }
            val eye = remember(displayPwd) {
                if (displayPwd) R.drawable.visibility_on
                else R.drawable.visibility_off
            }

            Column {
                Spacer(Modifier.weight(0.2f))
                Icon(modifier = Modifier.weight(0.5f))
                Spacer(Modifier.weight(0.2f))
                LoginTextField(msg = "请输入你的账号", leadingImageVectorResource = R.drawable.user
                    , displayInput = true)
                Spacer(Modifier.weight(0.01f))
                val modifier=Modifier.clickable { displayPwd=!displayPwd }
                LoginTextField(modifier, msg = "请输入你的密码",
                    leadingImageVectorResource = eye ,
                    displayInput = displayPwd)
                Spacer(Modifier.weight(0.9f))
            }
        }
    }
}


@Composable
fun Icon(modifier: Modifier){
    Icon(modifier = modifier
        .height(200.dp)
        .fillMaxWidth()
        .padding(20.dp)
        ,
        painter = painterResource(id = R.drawable.coffee), contentDescription = "icon")

}

@Composable
fun LoginTextField(modifier: Modifier=Modifier,msg:String, leadingImageVectorResource: Int,displayInput : Boolean){
    var searchContent by remember {
        mutableStateOf("")
    }
    Row(Modifier.fillMaxWidth(1f),verticalAlignment = Alignment.CenterVertically) {
        OutlinedTextField(modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
           ,value = searchContent, onValueChange = {searchContent=it},
            label = {Text(msg)} ,
            singleLine = true,
            visualTransformation = if (displayInput) VisualTransformation.None else PasswordVisualTransformation(),
            leadingIcon = { Icon(modifier = modifier, painter = painterResource(id = leadingImageVectorResource), contentDescription = description)})
    }

}
@Preview
@Composable
fun TextFieldPreview(){
    LoginTextField(msg = "请输入你的账号", leadingImageVectorResource = R.drawable.visibility_off,
        displayInput = false
    )
}