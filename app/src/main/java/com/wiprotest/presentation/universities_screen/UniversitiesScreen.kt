package com.wiprotest.presentation.universities_screen

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.wiprotest.domain.model.Response
import com.wiprotest.presentation.ui.theme.primaryColor
import com.wiprotest.presentation.util.CustomProgressBar
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat

@Composable
@InternalCoroutinesApi
@ExperimentalCoroutinesApi
fun UniversitiesScreen(
    viewModel: UniversitiesViewModel = hiltViewModel(),
    navController: NavController
) {
    val context = LocalContext.current
    var userInputCountry by remember { mutableStateOf(TextFieldValue("India")) }
    var monthYear: String by rememberSaveable {
        mutableStateOf("")
    }
    var canClose by remember {
        mutableStateOf(false)
    }

    val coroutineScope = rememberCoroutineScope()

    Column(
        verticalArrangement = Arrangement.Top,
    ) {
        Row(
            Modifier.padding(top = 24.dp, bottom = 12.dp)
        ) {
            TextField(
                value = userInputCountry,
                onValueChange = {
                    userInputCountry = it
                },
                colors = TextFieldDefaults.textFieldColors(textColor = Color.White),
            label = { Text(text = "Country", color = Color.White) },
                placeholder = { Text(text = "Enter Country", color = Color.White) },
            )
            Button(
                onClick = {
                    viewModel.fetchUniversities(context, "India")
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = primaryColor),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .weight(1F)
            ) {
                Text(text = "Search", color = Color.White)
            }
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Gray),
            contentAlignment = Alignment.Center
        ) {
            when (val usersResponse = viewModel.universityResponseState.value) {
                is Response.Loading -> CustomProgressBar()
                is Response.Success ->
                    if (usersResponse.data.isNotEmpty()) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .align(Alignment.TopStart)
                        ) {
                            LazyColumn(
                                verticalArrangement = Arrangement.spacedBy(10.dp),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 10.dp)
                            ) {
                                items(items = usersResponse.data) { university ->
                                    Row(
                                        horizontalArrangement = Arrangement.Start,
                                        verticalAlignment = Alignment.CenterVertically,
                                        modifier = Modifier
                                            .background(color = Color.White)
                                            .clickable {
                                                showToast(context, "${university.country}")
                                            }

                                            .fillMaxWidth()
                                            .padding(20.dp)
                                    ) {
                                        Column(
                                            verticalArrangement = Arrangement.Center,
                                            horizontalAlignment = Alignment.Start,
                                            modifier = Modifier.padding(start = 16.dp)
                                        ) {
                                            university.name?.let {
                                                Text(
                                                    text = it,
                                                    modifier = Modifier.padding(start = 16.dp),
                                                    color = Color.Black,
                                                    fontSize = 18.sp
                                                )
                                            }
                                            Text(
                                                text = "State  - ${university.stateProvince}",
                                                modifier = Modifier.padding(start = 16.dp),
                                                color = Color.Gray,
                                                fontSize = 18.sp
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    } else {
                        Text(
                            text = "No universities found",
                            modifier = Modifier
                                .padding(top = 24.dp)
                                .align(Alignment.Center),
                            style = TextStyle(
                                fontWeight = FontWeight.Bold,
                                letterSpacing = 4.5.sp
                            ),
                            fontSize = 14.sp,
                            color = Color.Gray
                        )
                    }

                is Error -> {
                    usersResponse.message?.let {
                        Text(
                            text = it,
                            modifier = Modifier.padding(top = 24.dp),
                            style = TextStyle(
                                fontWeight = FontWeight.Bold,
                                letterSpacing = 4.5.sp
                            ),
                            fontSize = 14.sp,
                            color = Color.White
                        )
                    }
                }

                else -> {}
            }
        }
    }
}

private fun showToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}
