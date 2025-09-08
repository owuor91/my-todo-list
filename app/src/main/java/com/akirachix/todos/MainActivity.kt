import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.akirachix.todos.api.ApiClient
import com.akirachix.todos.model.Todo
import com.akirachix.todos.model.UiState
import kotlinx.coroutines.launch
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue



@Composable
fun TodoScreen() {
  var todos by remember { mutableStateOf<List<Todo>>(emptyList()) }
  var uiState by remember { mutableStateOf(UiState(isLoading = true)) }
  val coroutineScope = rememberCoroutineScope()

  LaunchedEffect(Unit) {
    coroutineScope.launch {
      try {
        val fetchedTodos = ApiClient.apiService.getTodos()
        todos = fetchedTodos
        uiState = UiState(isLoading = false, success = "Todos loaded")
      } catch (e: Exception) {
        uiState = UiState(isLoading = false, error = e.message ?: "Error occurred")
      }
    }
  }

  when {
    uiState.isLoading -> LoadingView()
    uiState.error?.isNotEmpty() == true -> ErrorView(uiState.error.orEmpty())
    else -> TodoList(todos = todos)
  }
}

@Composable
fun LoadingView() {
  Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
    CircularProgressIndicator()
  }
}

@Composable
fun ErrorView(message: String) {
  Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
    Text(text = message, color = Color.Red)
  }
}

@Composable
fun TodoList(todos: List<Todo>) {
  Column {
    TopAppBar(title = { Text("My Todo List") })

    LazyColumn(modifier = Modifier.padding(8.dp)) {
      items(todos) { todo ->
        TodoItem(todo = todo)
      }
    }
  }
}
@Composable
fun TodoItem(todo: Todo) {
  Card(
    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
    shape = RoundedCornerShape(8.dp),
    elevation = CardDefaults.cardElevation()
  ) {
    Row(
      modifier = Modifier
        .background(Color(0xFFD6D6D6))
        .padding(16.dp),
      verticalAlignment = Alignment.CenterVertically
    ) {
      Checkbox(
        checked = todo.completed,
        onCheckedChange = null
      )
      Spacer(modifier = Modifier.width(8.dp))
      Text(text = todo.title)
    }
  }
}

