import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.akirachix.todos.model.Todo
import com.akirachix.todos.model.UiState
import com.akirachix.todos.viewModel.TodoViewModel

@Composable
fun TodosScreen(viewModel: TodoViewModel) {
    LaunchedEffect(Unit) {
        viewModel.fetchTodos()
    }

    val todos by viewModel.todos.observeAsState(listOf())
    val uiState by viewModel.uiState.observeAsState(UiState())

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray)
    ) {
        when {
            uiState.isLoading -> {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
            uiState.success != null -> {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.padding(16.dp)
                ) {
                    items(todos) { todo ->
                        TodoCard(todo = todo)
                    }
                }
            }
            uiState.error != null -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = uiState.error.toString())
                }
            }
        }
    }
}

@Composable
fun TodoCard(todo: Todo) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(modifier = Modifier.padding(16.dp, 8.dp)) {
            androidx.compose.foundation.layout.Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = todo.completed,
                    onCheckedChange = null
                )
                Text(
                    text = todo.title,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }
    }
}
