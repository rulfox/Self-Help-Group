package com.arany.shg.core.composables

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.arany.shg.core.util.CheckBoxState

object Widgets {
    @Composable
    fun CheckBoxWidget(checkBoxState: CheckBoxState, modifier: Modifier, onCheckedChangeListener: (isChecked: Boolean) -> Unit){
        Row(modifier = modifier) {
            Text(text = checkBoxState.text, modifier = Modifier.padding(16.dp))
            Checkbox(
                // below line we are setting
                // the state of checkbox.
                checked = checkBoxState.isChecked,
                // below line is use to add padding
                // to our checkbox.
                modifier = Modifier.padding(16.dp),
                // below line is use to add on check
                // change to our checkbox.
                onCheckedChange = { onCheckedChangeListener(it) },
            )
        }
    }
}