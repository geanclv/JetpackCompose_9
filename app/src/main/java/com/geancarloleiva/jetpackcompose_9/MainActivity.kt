package com.geancarloleiva.jetpackcompose_9

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.geancarloleiva.jetpackcompose_9.ui.theme.JetpackCompose_9Theme
import com.geancarloleiva.jetpackcompose_9.ui.theme.Shapes

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackCompose_9Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    createMainCard(this)
                }
            }
        }
    }
}

@Composable
fun createMainCard(context: Context) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Card(
            modifier = Modifier
                .width(200.dp)
                .height(390.dp)
                .padding(12.dp),
            shape = RoundedCornerShape(
                corner = CornerSize(15.dp)
            ),
            elevation = 6.dp
        ) {
            Column(
                modifier = Modifier.height(300.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                createImageProfile()
                Divider(
                    modifier = Modifier.height(1.dp),
                    color = Color.LightGray
                )
                createProfileInfo()
                createPortfolioButton(context)
            }
        }
    }
}

@Composable
private fun createImageProfile(modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier
            .size(150.dp)
            .padding(5.dp),
        shape = CircleShape,
        border = BorderStroke(0.5.dp, Color.LightGray),
        elevation = 6.dp,
        color = MaterialTheme
            .colors.onSurface.copy(alpha = 0.5f)
    ) {
        Image(
            painter = painterResource(id = R.drawable.space_invaders_128),
            contentDescription = "Profile image",
            modifier = modifier.size(135.dp)
        )
    }
}

@Composable
private fun createProfileInfo() {
    Column(modifier = Modifier.padding(5.dp)) {
        Text(
            text = "Geancarlo Leiva",
            style = MaterialTheme.typography.h4,
            color = MaterialTheme.colors.primaryVariant
        )
        Text(
            text = "Digital eXperience specialist",
            modifier = Modifier.padding(3.dp),
            style = MaterialTheme.typography.subtitle1
        )
        Text(text = "https://geancarloleiva.com")
    }
}

@Composable
private fun createPortfolioButton(context: Context) {
    val buttonClickedState = remember {
        mutableStateOf(false)
    }

    Button(
        onClick = {
            buttonClickedState.value = !buttonClickedState.value
        }
    ) {
        if (buttonClickedState.value) {
            portfolioButtonText("Hide Portfolio")
        } else {
            portfolioButtonText("View Portfolio")
        }
    }
    if (buttonClickedState.value) {
        detailContentShow()
    } else {
        detailContentHide()
    }
}

@Composable
private fun portfolioButtonText(textToShow: String) {
    Text(
        text = textToShow,
        style = MaterialTheme.typography.button
    )
}

@Composable
private fun detailContentHide() {
    Box() {}
}

//@Preview
@Composable
private fun detailContentShow() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(5.dp)
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(3.dp),
            shape = RoundedCornerShape(corner = CornerSize(6.dp)),
            border = BorderStroke(2.dp, Color.LightGray)
        ) {
            detailPortfolio(lstPortfolio = listOf("Data 1", "Data 2", "Data 3"))
        }
    }
}

@Composable
private fun detailPortfolio(lstPortfolio: List<String>) {
    LazyColumn {
        items(lstPortfolio) { portfolio ->
            Card(
                modifier = Modifier
                    .padding(13.dp)
                    .fillMaxWidth(),
                shape = RectangleShape,
                elevation = 4.dp
            ) {
                Row(
                    modifier = Modifier
                        .padding(8.dp)
                        .background(MaterialTheme.colors.surface)
                ) {
                    createImageProfile(modifier = Modifier.size(100.dp))
                    Column(
                        modifier = Modifier
                            .padding(7.dp)
                            .align(alignment = Alignment.CenterVertically)
                    ) {
                        Text(
                            text = portfolio,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Detail text",
                            style = MaterialTheme.typography.body2
                        )
                    }
                }
            }
        }
    }
}

//@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    val context: Context = LocalContext.current
    JetpackCompose_9Theme {
        createMainCard(context)
    }
}