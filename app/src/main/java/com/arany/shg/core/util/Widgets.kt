package com.arany.shg.core.util

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arany.shg.R
import com.arany.shg.data.models.Committee
import com.arany.shg.feature_member.data.model.Member
import com.arany.shg.feature_thrift.data.model.Thrift

@Composable
fun CommitteeBasedListWidget(committee: Committee, memberHostingCommittee: Member, thrift: List<Thrift>, member: List<Member>){
    Column {
        Row (modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically){
            Text(text = "${memberHostingCommittee.name}'s Committee")
            Spacer(modifier = Modifier.weight(1f))
            Text(text = "#${committee.committeeId}")
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = "${committee.date}")
        }
        Divider(color = Color.Black, thickness = 2.dp)
        LazyVerticalGrid(
            columns = GridCells.Fixed(4),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            contentPadding = PaddingValues(12.dp),
        ) {
            items(thrift.size) {
                Box (modifier = Modifier.fillMaxWidth()){
                    /*thrift.get(0).
                    Icon(
                        modifier = Modifier
                            .padding(0.dp),
                        painter = painterResource(id = R.drawable.login),
                        contentDescription = null,
                    )*/
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RoleDropdown_Preview() {
    MaterialTheme {
        //CommitteeBasedListWidget()
    }
}