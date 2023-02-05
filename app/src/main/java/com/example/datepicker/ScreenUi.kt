@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.datepicker

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.maxkeppeker.sheets.core.models.base.rememberSheetState
import com.maxkeppeler.sheets.calendar.CalendarDialog
import com.maxkeppeler.sheets.calendar.models.CalendarConfig
import com.maxkeppeler.sheets.calendar.models.CalendarSelection
import com.maxkeppeler.sheets.calendar.models.CalendarStyle
import com.maxkeppeler.sheets.clock.ClockDialog
import com.maxkeppeler.sheets.clock.models.ClockConfig
import com.maxkeppeler.sheets.clock.models.ClockSelection
import java.time.LocalDate
import java.time.LocalTime

@Composable
fun MainScreen(){

    // CALENDAR

    val calendarState = rememberSheetState()
    val selectedDates = remember{ mutableStateOf<List<LocalDate>>(listOf()) }
    val disableDates = listOf(
        LocalDate.now().minusDays(7),
        LocalDate.now().minusDays(12),
        LocalDate.now().plusDays(3),
    )

    CalendarDialog(
        state = calendarState,
        config = CalendarConfig(
            yearSelection = true,
            monthSelection = true,
            style = CalendarStyle.MONTH,
            disabledDates = disableDates
        ),
        selection = CalendarSelection.Dates{ newDates ->
            selectedDates.value = newDates
        }
    )

    // CLOCK

    val clockState = rememberSheetState()
    val selectedTime = remember{ mutableStateOf<LocalTime?>(null) }
    ClockDialog(
        state = clockState,
        config = ClockConfig(
            is24HourFormat = false
        ),
        selection = ClockSelection.HoursMinutesSeconds { hours, minutes, seconds ->
            selectedTime.value = LocalTime.of(hours,minutes,seconds)
        }
    )



    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(100.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        FilledTonalButton(onClick = { calendarState.show() }) {
            Text(text = "Date Picker")
        }
        FilledTonalButton(onClick = { clockState.show()}) {
            Text(text = "Time Picker")
        }
    }
}