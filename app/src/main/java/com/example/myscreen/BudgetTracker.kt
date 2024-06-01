package com.example.myscreen
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


data class ShoppingItem(val id:Int,
                        var name:String,
                        var quantity:Int,
                        var isEditing:Boolean = false)

@Composable
fun BudgetTracker(){
    var sItems by remember { mutableStateOf(listOf<ShoppingItem>()) }
    var showDialog by remember { mutableStateOf(false)}
    var itemName by remember{ mutableStateOf("") }
    var itemQuantity by remember{ mutableStateOf("") }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ){
Spacer(modifier = Modifier.size(100.dp))
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center){
            Text(text = "Personal Finance Tracker", fontFamily = FontFamily.Serif, fontWeight = FontWeight.Bold,
                fontSize =26.sp)
        }

        Spacer(modifier = Modifier.size(50.dp))
        Button(onClick = {showDialog=true}, modifier= Modifier.align(Alignment.CenterHorizontally)) {
            Text("Add Transaction")
        }

        LazyColumn(
            modifier= Modifier
                .fillMaxSize()
                .padding(16.dp)
        ){
            items(sItems){
                    item -> // rename it with item
                if(item.isEditing){
                    ShoppingItemEditor(item = item , onEditComplete = {
                            editedName, editedQuantity ->
                        sItems = sItems.map { it.copy(isEditing=false) }
                        val editedItem = sItems.find { it.id == item.id }
                        editedItem?.let {
                            it.name = editedName
                            it.quantity = editedQuantity

                        }
                    })
                }else{
                    ShoppingListItem(item = item,
                        onEditClick = {
                            sItems = sItems.map { it.copy(isEditing = it.id==item.id) } // Finding out which item is being edited and changing isEditing to true
                        }, onDeleteClick = {
                            sItems = sItems - item
                        })
                }

            }

        }

    }

    if(showDialog){
        AlertDialog(onDismissRequest = { showDialog=false },
            confirmButton = {
                Row(modifier= Modifier
                    .padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Button(onClick = {
                        if(itemName.isNotBlank()){
                            val newItem = ShoppingItem(
                                id= sItems.size+1,
                                name = itemName,
                                quantity = itemQuantity.toInt(),

                                )
                            sItems = sItems + newItem
                            showDialog=false
                            itemName=""
                            itemQuantity=""
                        }
                    }) {
                        Text("Add")
                    }

                    Button(onClick = { showDialog=false}) {
                        Text("Cancel")
                    }
                }
            },
            title= {Text("Add Your Transaction")},
            text = {
                Column {
                    OutlinedTextField(value = itemName,
                        onValueChange = {itemName = it},
                        singleLine = true,
                        modifier = Modifier.padding(8.dp)
                    )

                    OutlinedTextField(value = itemQuantity,
                        onValueChange = {itemQuantity = it},
                        singleLine = true,
                        modifier = Modifier.padding(8.dp)
                    )

                }
            }
        )

    }

}

@Composable
fun ShoppingItemEditor(item: ShoppingItem, onEditComplete: (String, Int) -> Unit){
    var editedName by remember { mutableStateOf(item.name)}
    var editedQuantity by remember { mutableStateOf(item.quantity.toString())}
    var isEditing by remember { mutableStateOf(item.isEditing)}

    Row(modifier= Modifier
        .fillMaxWidth()
        .background(Color.White)
        .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ){
        Column{
            OutlinedTextField(value = editedName, onValueChange = {editedName = it},
                label ={(Text(text = "Description"))},  singleLine = true,
                modifier= Modifier
                    .wrapContentSize() // only take as space as needed
                    .padding(8.dp) )

            OutlinedTextField(value = editedQuantity, onValueChange = {editedQuantity=it},
                label ={(Text(text = "Amount"))},  singleLine = true,
                modifier= Modifier
                    .wrapContentSize() // only take as space as needed
                    .padding(8.dp) )
        }

        Button(onClick = {
            isEditing=false
            onEditComplete(editedName, editedQuantity.toIntOrNull() ?: 1)
        }){
            Text("Save")
        }

    }
}

@Composable
fun ShoppingListItem(item: ShoppingItem,
                     onEditClick: () -> Unit, // Unit = Void in Java
                     onDeleteClick: () -> Unit
){

    Row(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .border(
                border = BorderStroke(2.dp, Color.Cyan), // BorderStroke is a line
                shape = RoundedCornerShape(20)
            ),
        horizontalArrangement =  Arrangement.SpaceBetween
    ){
        Column (modifier = Modifier
            .weight(1f)
            .padding(8.dp)){
            Row{
                Text(text = item.name, modifier=Modifier.padding(8.dp))
                Text("Amount: ${item.quantity}", modifier=Modifier.padding(8.dp))

            }



        }


        Row(
            modifier= Modifier.padding(8.dp)
        ){
            IconButton(onClick = onEditClick){
                Icon(imageVector = Icons.Default.Edit, contentDescription = null)
            }
            IconButton(onClick = onDeleteClick){
                Icon(imageVector = Icons.Default.Delete, contentDescription = null)
            }
        }
    }


}