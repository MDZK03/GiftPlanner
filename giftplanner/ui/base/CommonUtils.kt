package org.minhduc.giftplanner.ui.base

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.github.skydoves.colorpicker.compose.AlphaTile
import com.github.skydoves.colorpicker.compose.BrightnessSlider
import com.github.skydoves.colorpicker.compose.ColorEnvelope
import com.github.skydoves.colorpicker.compose.HsvColorPicker
import com.github.skydoves.colorpicker.compose.rememberColorPickerController

object CommonUtils {

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
}