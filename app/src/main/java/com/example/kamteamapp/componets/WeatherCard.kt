package com.example.kamteamapp.componets

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.kamteamapp.data.BodyColor
import com.example.kamteamapp.data.Temp_Weather_Items
import com.example.kamteamapp.ui.item.getChineseDayOfWeek
import com.example.kamteamapp.ui.theme.Weather1
import com.example.kamteamapp.ui.theme.Weather2
import com.example.kamteamapp.ui.theme.Weather3
import com.example.kamteamapp.ui.theme.Weather4

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun WeatherCard(
    item: Temp_Weather_Items, MyColor:List<BodyColor>, CardWeid: Dp, modifier: Modifier = Modifier
){
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MyColor[1].color,
        ),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier
            .padding(5.dp)
            .width(CardWeid)
        //.size(150.dp, 70.dp)
    ) {
        Column(
            modifier = Modifier

        ) {
            Row (){
                Column (
                    modifier = Modifier
                        .padding(bottom = 2.dp, start = 2.dp)
                ){
                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor = MyColor[2].color,
                        ),
                        shape = RoundedCornerShape(3.dp),
                        modifier = Modifier
                            .padding(2.dp)

                    ) {
                        Text(
                            text = String.format("Day %d %s",item.number_day,item.where),
                            style = MaterialTheme.typography.Weather2
                        )
                    }
                    Text(
                        text = String.format("%s日 %s",item.data.dayOfMonth, getChineseDayOfWeek(item.data)),
                        style = MaterialTheme.typography.Weather3
                    )
                }

                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = MyColor[2].color,
                    ),
                    shape = RoundedCornerShape(3.dp),
                    modifier = Modifier
                        .padding(2.dp)
                ) {
                    Column() {
                        Row (verticalAlignment = Alignment.CenterVertically){
                            Image(
                                painter = painterResource(id = item.day_weather.getImageResId()),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(30.dp)
                            )
                            Text(
                                text = String.format("%d℃",item.dat_temp),
                                style = MaterialTheme.typography.Weather4
                            )


                        }
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Image(
                                painter = painterResource(id = item.night_weather.getImageResId()),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(30.dp)
                            )
                            Text(
                                text = String.format("%d℃",item.night_temp),
                                style = MaterialTheme.typography.Weather4
                            )
                        }
                    }
                }

            }

        }
        Text(
            text = item.attention,
            style = MaterialTheme.typography.Weather1
        )

    }
}