package org.minhduc.giftplanner.ui.base

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.SwipeToDismissBoxState
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.github.skydoves.colorpicker.compose.AlphaTile
import com.github.skydoves.colorpicker.compose.BrightnessSlider
import com.github.skydoves.colorpicker.compose.ColorEnvelope
import com.github.skydoves.colorpicker.compose.HsvColorPicker
import com.github.skydoves.colorpicker.compose.rememberColorPickerController
import org.minhduc.giftplanner.database.person.Person
import org.minhduc.giftplanner.ui.Screen

object CommonUtils {

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun DatePickerModalInput(
        onDateSelected: (Long?) -> Unit,
        onDismiss: () -> Unit
    ) {
        val datePickerState = rememberDatePickerState(initialDisplayMode = DisplayMode.Input)

        DatePickerDialog(
            onDismissRequest = onDismiss,
            confirmButton = {
                TextButton(onClick = {
                    onDateSelected(datePickerState.selectedDateMillis)
                    onDismiss()
                }) {
                    Text("OK")
                }
            },
            dismissButton = {
                TextButton(onClick = onDismiss) {
                    Text("Cancel")
                }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }

    @Composable
    fun CustomTextField(
        modifier: Modifier, label: String, supportingText: String?,
        isReadOnly: Boolean, isError: Boolean, color: Color,
        value: String, onValueChanged: (String) -> Unit,
    ){
        OutlinedTextField(
            modifier = modifier,
            value = value,
            onValueChange = onValueChanged,
            label = { Text(text = label, color = Color.Black) },
            supportingText = {
                if (supportingText != null) Text(text = supportingText, color = Color.Gray)
            },
            isError = isError,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            readOnly = isReadOnly,
            enabled = !isReadOnly,
            colors = OutlinedTextFieldDefaults.colors(disabledTextColor = color)
        )
    }

    @Composable
    fun DismissBackground(dismissState: SwipeToDismissBoxState) {
        val color = when (dismissState.dismissDirection) {
            SwipeToDismissBoxValue.StartToEnd -> Color.Red
            SwipeToDismissBoxValue.EndToStart -> Color.Red
            SwipeToDismissBoxValue.Settled -> Color.Transparent
        }
        Row(
            modifier = Modifier.fillMaxSize().background(color).padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(Icons.Default.Delete, contentDescription = "delete")
            Icon(Icons.Default.Delete, contentDescription = "delete")
        }
    }

    @Composable
    fun ItemList(person: Person?, onClick:() -> Unit) {
        Card(modifier = Modifier.fillMaxWidth().padding(12.dp).clickable { onClick() },
            elevation = CardDefaults.cardElevation(10.dp)
        ) {
            Column {
                Row(modifier = Modifier.padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    if (person != null) {
                        Text(modifier = Modifier.requiredWidth(200.dp),
                            text = person.name, fontWeight = FontWeight.ExtraBold)
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
                // gift details?
            }
        }
    }

    @Composable
    fun ColorPicker() {
        var hexCode by rememberSaveable { mutableStateOf("") }
        val controller = rememberColorPickerController()

        Column {
            HsvColorPicker(
                modifier = Modifier.fillMaxWidth().height(450.dp).padding(10.dp),
                controller = controller,
                onColorChanged = { colorEnvelope: ColorEnvelope ->
                    hexCode = colorEnvelope.hexCode
                }
            )
            BrightnessSlider(
                modifier = Modifier.fillMaxWidth().padding(10.dp).height(35.dp),
                controller = controller,
            )
            Text(text = hexCode)
            AlphaTile(
                modifier = Modifier.size(80.dp).clip(RoundedCornerShape(6.dp)),
                controller = controller
            )
        }
    }

    @Composable
    fun SmallMenu(showOptions: Boolean, opt1Name: String, opt2Name: String,
                  onDismiss:() -> Unit, onClickOpt1: () -> Unit, onClickOpt2: () -> Unit) {
        DropdownMenu(
            expanded = showOptions,
            onDismissRequest = onDismiss
        ) {
            DropdownMenuItem(
                text = { Text(opt1Name) },
                onClick = onClickOpt1
            )
            HorizontalDivider()
            DropdownMenuItem(
                text = { Text(opt2Name) },
                onClick = onClickOpt2
            )
        }
    }
}