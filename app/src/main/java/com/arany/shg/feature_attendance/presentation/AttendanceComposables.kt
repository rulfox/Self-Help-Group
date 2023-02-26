package com.arany.shg.feature_attendance.presentation

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arany.shg.feature_attendance.data.model.AttendanceState
import com.arany.shg.ui.theme.SelfHelpGroupTheme

@Composable
fun AttendanceCheckBox(attendanceState: AttendanceState, modifier: Modifier, onAttendanceStatusChangeListener: (isChecked: Boolean) -> Unit, onLeaveAppliedStatusChangeListener: (isChecked: Boolean) -> Unit){
    val cardShape = RoundedCornerShape(16.dp)
    Card(
        modifier = modifier
            .shadow(
                shape = cardShape,
                spotColor = if(attendanceState.isPresent) Color.Green else Color.Red,
                elevation = 8.dp
            ),
        shape = cardShape
    ) {
        Row(modifier = Modifier.padding(horizontal = 8.dp), verticalAlignment = Alignment.CenterVertically) {
            Text(text = attendanceState.memberName, modifier = Modifier.padding(start = 16.dp, end = 16.dp).weight(1f))
            Checkbox(
                checked = attendanceState.isPresent,
                modifier = Modifier.padding(end = 16.dp),
                onCheckedChange = { onAttendanceStatusChangeListener(it) },
            )
            Checkbox(
                checked = attendanceState.leaveApplied,
                onCheckedChange = { onLeaveAppliedStatusChangeListener(it) },
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SelfHelpGroupTheme {
        AttendanceCheckBox(AttendanceState(10,"Aswin", isPresent = true, leaveApplied = true), Modifier.fillMaxWidth(), {}, {})
    }
}