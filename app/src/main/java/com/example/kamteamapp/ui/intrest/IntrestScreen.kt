package com.example.kamteamapp.ui.intrest

import android.app.DatePickerDialog
import android.os.Build
import android.view.View
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.exclude
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text2.input.TextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerFormatter
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScaffoldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

import com.example.kamteamapp.R
import com.example.kamteamapp.ui.navigation.NavigationDestination
import com.example.kamteamapp.ui.theme.Interest1
import com.example.kamteamapp.ui.theme.Interest2
import com.example.kamteamapp.ui.theme.Interest3
import com.example.kamteamapp.ui.theme.KamTeamAppTheme



object IntrestDesatination : NavigationDestination {
    override val route = "intrest"
    override val titleRes = R.string.app_name
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun IntrestScreen(
    navigateToAIUpdate: () -> Unit,
    modifier: Modifier = Modifier
){
    Scaffold(
        topBar = {
            NextItemBar(
            )
        },
        contentWindowInsets = ScaffoldDefaults
            .contentWindowInsets
            .exclude(WindowInsets.navigationBars)
            .exclude(WindowInsets.ime),
    ){paddingValues ->
        Column(
            Modifier
                .fillMaxSize()
                .padding(paddingValues)) {
            Button(
                onClick =  navigateToAIUpdate ,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp, start = 50.dp, end = 50.dp)
            ) {
                Icon(Icons.Filled.ArrowForward, contentDescription = "Next")
                Text("下一步", Modifier.padding(start = 8.dp))
            }
            TopItem()
            IntrestItem()
        }

    }
}

@Composable
fun NextItemBar(

){
    // TODO{顶部栏
}




@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TopItem(
    dateNDayViewModel: DateNDaysViewModel = DateNDaysViewModel(),
    modifier: Modifier = Modifier
){
    var gender by remember { mutableStateOf(TextFieldValue("")) }

    // 更新性别的 lambda 表达式
    val onGenderChange: (TextFieldValue) -> Unit = { newValue ->
        // 检查新值是否为 "男" 或 "女"，如果不是，则不更新状态
        if (newValue.text == "男" || newValue.text == "女") {
            gender = newValue
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp, top = 30.dp)
    ) {
        Text(
            text = "开始个性化定制",
            style = MaterialTheme.typography.Interest1
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "更好地推荐适合你的内容",
            style = MaterialTheme.typography.Interest2
        )
        Spacer(modifier = Modifier.height(8.dp))
        CustomDatePicker(
            label = stringResource(id = R.string.birthday),
            onInputDateChanged = {},
            dateInputState = rememberDateInputState(dateNDayViewModel.birthdaySelected),
            isIllegalInput = true,
        )
        OutlinedTextField(
            value = gender,
            onValueChange = onGenderChange,
            placeholder = { Text("输入性别") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            isError = gender.text != "男" && gender.text != "女",
            keyboardActions = KeyboardActions(),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Number
            ),
        )


    }
}




@Composable
fun IntrestItem(
    modifier: Modifier = Modifier
){

    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier =Modifier.fillMaxSize()
    ){
        Text(text = "请选择你的旅游偏好",
            style = MaterialTheme.typography.Interest1
        )
        val onCategoriesSelected: (Set<String>) -> Unit = { selected ->
            println("Selected categories: $selected")
        }
        val categories = ShowData
        val selectedCategories = remember { mutableStateOf(setOf<String>()) }
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(2),
            contentPadding = PaddingValues(
                start = 12.dp,
                top = 16.dp,
                end = 12.dp,
                bottom = 16.dp
            ),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            content = {
                items(categories) { category ->
                    SelectableChip(
                        modifier = Modifier.fillMaxSize(),
                        text = category,
                        isSelected = selectedCategories.value.contains(category),
                        onClick = {
                            // 切换选中状态
                            selectedCategories.value = if (selectedCategories.value.contains(category)) {
                                selectedCategories.value - category
                            } else {
                                selectedCategories.value + category
                            }
                            // 通知外部回调
                            onCategoriesSelected(selectedCategories.value)
                        }
                    )
                }
            },
            modifier = modifier.fillMaxSize()
        )
    }

}


@Composable
fun SelectableChip(
    modifier: Modifier,
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val contentColor = if (isSelected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.inversePrimary


    Card(
        modifier = modifier
            .clickable(onClick = onClick)
            .padding(horizontal = 8.dp, vertical = 4.dp)
            .border(2.dp, Color.Black, RoundedCornerShape(16.dp)),
        colors = CardDefaults.cardColors(
            containerColor = contentColor
        ),
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 4.dp, end = 4.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            if (isSelected) {
                Icon(
                    imageVector = Icons.Filled.Check,
                    contentDescription = "Selected",
                    //tint = contentColor
                )
                Spacer(modifier = Modifier.width(8.dp))
            }
            Text(
                text = text,
                style = MaterialTheme.typography.Interest3,
            )
        }
    }
}
@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun IntrestScreenPreview(){
    KamTeamAppTheme {
        IntrestScreen(navigateToAIUpdate = {})
    }
}
