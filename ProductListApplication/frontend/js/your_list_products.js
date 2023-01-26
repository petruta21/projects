 // Model
 let todos = [
  {
    title: "Milk",
    amount: "6",
    id: "id1",
  },
  {
    title: "Butter",
    amount: "2",
    id: "id2",
  },
  {
    title: "Eggs",
    amount: "20",
    id: "id3",
  },
];

// Creates a todo
function createTodo(title, amount) {
  const id = "" + new Date().getTime();

  todos.push({
    title: title,
    amount: amount,
    id: id,
  });
}

// Deletes a todo
function removeTodo(idToDelete) {
  todos = todos.filter(function (todo) {
    // If the id of this todo matches idToDelete, return false
    // For everything else, return true
    if (todo.id === idToDelete) {
      return false;
    } else {
      return true;
    }
  });
}

function toggleTodo(todoId, checked) {
  todos.forEach(function (todo) {
    if (todo.id === todoId) {
      todo.isDone = checked;
    }
  });
}

// Controller
function addTodo() {
  const textbox = document.getElementById("todo-title");
  const title = textbox.value;

  const amount = document.getElementById("amount-picker");
  const dueAmount = amount.value;

  createTodo(title, dueAmount);
  render();
}

function deleteTodo(event) {
  const deleteButton = event.target;
  const idToDelete = deleteButton.id;

  removeTodo(idToDelete);
  render();
}

function checkTodo(event) {
  const checkbox = event.target;

  const todoId = checkbox.dataset.todoId;
  const checked = checkbox.checked;

  toggleTodo(todoId, checked);
  render();
}

// View
function render() {
  // reset our list
  document.getElementById("todo-list").innerHTML = "";

  todos.forEach(function (todo) {
    const element = document.createElement("div");
    element.innerText = todo.title + " " + todo.amount;

    const checkbox = document.createElement("input");
    checkbox.type = "checkbox";
    checkbox.onchange = checkTodo;
    checkbox.dataset.todoId = todo.id;
    if (todo.isDone === true) {
      checkbox.checked = true;
    } else {
      checkbox.checked = false;
    }
    element.prepend(checkbox);

    const deleteButton = document.createElement("button");
    deleteButton.innerText = "Delete";
    deleteButton.style = "margin-left: 12px";
    deleteButton.onclick = deleteTodo;
    deleteButton.id = todo.id;
    element.appendChild(deleteButton);

    const todoList = document.getElementById("todo-list");
    todoList.appendChild(element);
  });
}

render();