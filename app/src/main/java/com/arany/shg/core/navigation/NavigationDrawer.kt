package com.arany.shg.core.navigation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arany.shg.core.navigation.NavigationHelper.getNavigationItems

@Composable
fun DrawerHeader(){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 64.dp)
        ,
        horizontalArrangement = Arrangement.Center,
    ) {
        Text(text = "Aswin", fontSize = 60.sp)
    }
}

@Composable
fun DrawerBody(
    drawerItems : List<Screen>,
    modifier: Modifier = Modifier,
    itemTextStyle : TextStyle = TextStyle(fontSize = 18.sp),
    onItemClick : (Screen) -> Unit
){

    LazyColumn(modifier){
        items(drawerItems.size){ itemPosition ->
            val item = drawerItems[itemPosition]
            DrawerMenuItem(
                icon = item.navIcon,
                text = item.title,
                itemTextStyle = itemTextStyle,
                onItemClick = { onItemClick(item) }
            )
        }
    }
}

@Composable
private fun DrawerMenuItem(
    icon: (@Composable () -> Unit),
    text: String,
    itemTextStyle: TextStyle = TextStyle(fontSize = 18.sp),
    onItemClick: () -> Unit){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onItemClick() }
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ){
        icon.invoke()
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = text,
            style = itemTextStyle,
            modifier = Modifier.weight(1f))
    }
}

@Preview(showBackground = true)
@Composable
private fun DrawerHeaderPreview(){
    DrawerHeader()
}

@Preview(showBackground = true)
@Composable
private fun DrawerBodyPreview(){
    DrawerBody(drawerItems = getNavigationItems(), onItemClick = {

    })
}