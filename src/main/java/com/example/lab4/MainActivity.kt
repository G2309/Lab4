package com.example.lab4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.lab4.ui.theme.Lab4Theme
import androidx.compose.runtime.mutableStateListOf as mutableStateListOf
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.material3.CardElevation

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lab4Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    textandbutton()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)//OptIn me permite utilizar TextField y otras funciones experimentales
@Composable
fun textandbutton() {
    //Declaraci�n de variables
    var itemList = remember { mutableStateListOf<recipe>()}
    var url by remember { mutableStateOf("") }
    var reci by remember {mutableStateOf("")}
    Column(modifier = Modifier.fillMaxSize()) {
        //Imprime un texto de bienvenida
        Text("Bienvenido, ingresa un nombre y un url de la receta")
        TextField(value = reci, onValueChange = {reci = it}, label = {"Nombre de la receta: "})
        TextField(value = url, onValueChange = {url = it}, label = {"URL: "})
        //Boton que agrega elementos a la lista
        Button(
            onClick = {
                if(reci.isNotEmpty() && url.isNotEmpty()) {
                    itemList.add(recipe(name = reci, imageURL = url))
                    reci = ""
                    url = ""
                }
            }) {
                Text("Agregar")
        }
        LazyColumn { itemsIndexed(itemList){idex, item -> Card(item, 4.dp)
            }
        }
    }
}
@Composable
private fun LazyItemScope.Card(item: recipe, elevation: Dp) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
    ) {
        Text("Receta: ")
        Text(text = item.name) // Mostrar el nombre de la receta
    }
}


@Preview(showBackground = true)
@Composable
fun textandbuttonPreview() {
        Lab4Theme {
             textandbutton()
        }
}
