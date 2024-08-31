package com.example.kamteamapp.componets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.kamteamapp.data.TimeLinerColor
import com.example.kamteamapp.ui.item.Actions
import com.example.kamteamapp.ui.theme.TimeLiner
import com.example.kamteamapp.ui.theme.TimeLiner2

@Composable
fun TimeLinerLayout(
    actions: Actions,
    modifier: Modifier
){

    val paramter = actions.get_Paramter()
    val width_size = (paramter.start_part-1).dp
    val hour_long_size_4h =((paramter.TimeLang/paramter.destiys).toInt()*4).dp

    val round_size = (paramter.start_part/2).dp

    val samll_round_size = (paramter.start_part/3).dp

    val months = ((paramter.BaseHight+paramter.DayHight)/paramter.destiys).toInt().dp

    Column(
        modifier = Modifier
            .width(width_size)
    ) {
        Card(
            shape = RoundedCornerShape(6.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primary
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(months)
        ){
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Text(
                    text = "6月",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.TimeLiner2,
                    modifier = Modifier
                        .padding(top = 3.dp)
                )
            }
        }

        Card(
            shape = RoundedCornerShape(round_size),
            colors = CardDefaults.cardColors(
                containerColor = TimeLinerColor.HOUR_4_light.color
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(hour_long_size_4h)
        ) {
            LazyColumn(
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
            ) {
                items(3) {
                    Box(
                        modifier = Modifier
                            .size(samll_round_size)
                            .align(Alignment.CenterHorizontally)
                            .clip(CircleShape) // 将矩形裁剪成圆形
                            .background(TimeLinerColor.HOUR_4_normal.color) // 使用列表中的颜色
                    )
                }

            }

        }
        for (index in 1..5) {
            Card(
                shape = RoundedCornerShape(round_size),
                colors = CardDefaults.cardColors(
                    containerColor = TimeLinerColor.HOUR_4_normal.getColorByNumber2(index)
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(hour_long_size_4h)
                    .offset(y = -round_size * 2 * index - round_size * (index - 1) * 2)
            ) {
                LazyColumn(
                    verticalArrangement = Arrangement.SpaceEvenly,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    items(3) {
                        Box(
                            modifier = Modifier
                                .size(samll_round_size)
                                .align(Alignment.CenterHorizontally)
                                .clip(CircleShape) // 将矩形裁剪成圆形
                                .background(TimeLinerColor.HOUR_8_light.getColorByNumber1(index)) // 使用列表中的颜色
                        )
                    }

                }
            }
            Card(
                shape = RoundedCornerShape(round_size),
                colors = CardDefaults.cardColors(
                    containerColor = TimeLinerColor.HOUR_8_light.getColorByNumber1(index)
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(round_size * 2)
                    .offset(y = -(round_size * 2 * index + hour_long_size_4h + round_size * (index - 1) * 2))

            ) {
                Text(
                    text = "%02d".format(index*4),
                    style = MaterialTheme.typography.TimeLiner,
                    modifier = Modifier
                        .padding(start = 2.dp, top = 4.dp)
                )
            }
        }
        Card(
            shape = RoundedCornerShape(round_size),
            colors = CardDefaults.cardColors(
                containerColor = TimeLinerColor.HOUR_24_normal.color
            ),

            modifier = Modifier
                .fillMaxWidth()
                .height(round_size * 2)
                .offset(y = -(round_size * 2 * 6 + round_size * (6 - 1) * 2))

        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Text(
                    text = "%02d".format(6*4),
                    style = MaterialTheme.typography.TimeLiner,
//                    modifier = Modifier
//                        .padding(start = 2.dp, top = 4.dp)
                )
            }
        }


    }
}