 // Model
 let todos = [
  {
    title: "Milk",
    category: "Dairy foods",
    id: "id1",
  },
  {
    title: "Apple",
    category: "Fruits",
    id: "id2",
  },
  {
    title: "Tea",
    category: "Drinks",
    id: "id3",
  },
];

// Creates a todo
function createTodo(title,category) {
  const id = "" + new Date().getTime();

  todos.push({
    title: title,
    category:category,
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

  
  const category = document.getElementById("pr_category");
  const dueCategory= category.options[category.selectedIndex].text; 
  createTodo(title, dueCategory);
 // alert (dueCategory); 
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
    element.innerText = todo.title + "----- " + todo.category;

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


function readJson(){
  let request = new XMLHttpRequest();
  request.open('GET', './JSON/productlist.json', true);
    request.onload = function () {
      let products = JSON.parse(this.response);
      let output = '';
      for (let i = 0; i < products.length; i++) {
        console.log(products[i].name+" " +products[i].category)
      }
      
 request.send();
}
}

function addRow() {
  
      let table1 = document.getElementById("table_vegetables");
      let table2 = document.getElementById("table_fruits");
      let table3 = document.getElementById("table_nuts");
      let table4 = document.getElementById("table_seefood");
      let table5 = document.getElementById("table_dairy");
      let table6 = document.getElementById("table_meat");
      let table7 = document.getElementById("table_chemicals");
      let table8 = document.getElementById("table_household");
      let table9 = document.getElementById("table_sweets");
      let table10 = document.getElementById("table_drinks");
      let table11 = document.getElementById("table_cosmetics");
      let table12 = document.getElementById("table_other");

      // Create a row using the inserRow() method and
      // specify the index where you want to add the row
      let row1 = table1.insertRow(-1);
      let row2 = table2.insertRow(-1);
      let row3 = table3.insertRow(-1); // We are adding at the end
      let row4= table4.insertRow(-1);
      let row5 = table5.insertRow(-1);
      let row6 = table6.insertRow(-1);
      let row7 = table7.insertRow(-1);
      let row8 = table8.insertRow(-1);
      let row9 = table9.insertRow(-1);
      let row10 = table10.insertRow(-1);
      let row11 = table11.insertRow(-1);
      let row12 = table12.insertRow(-1);
       
      const textbox = document.getElementById("todo-title");
      const title = textbox.value;
          
      const category = document.getElementById("pr_category");
      const dueCategory= category.options[category.selectedIndex].text; 
     // console.log(category.options[category.selectedIndex].text);
   
     //alert(dueCategory);
      let c1_1=row1.insertCell(0);
      let c1 = row1.insertCell(1);
      let c2 = row2.insertCell(0);
      let c2_1 = row2.insertCell(0);
      let c3 = row3.insertCell(0);
      let c3_1 = row3.insertCell(0);
      let c4 = row4.insertCell(0);
      let c4_1 = row4.insertCell(0);
      let c5 = row5.insertCell(0);
      let c5_1 = row5.insertCell(0);
      let c6 = row6.insertCell(0);
      let c6_1 = row6.insertCell(0);
      let c7 = row7.insertCell(0);
      let c7_1 = row7.insertCell(0);
      let c8 = row8.insertCell(0);
      let c8_1 = row8.insertCell(0);
      let c9 = row9.insertCell(0);
      let c9_1 = row9.insertCell(0);
      let c10 = row10.insertCell(0);
      let c10_1= row10.insertCell(0);
      let c11 = row11.insertCell(0);
      let c11_1 = row11.insertCell(0);
      let c12 = row12.insertCell(0);
      let c12_1 = row12.insertCell(0);

      const checkbox = document.createElement("input");
      checkbox.type = "checkbox";
      checkbox.onchange = checkTodo;

      if(category.options[category.selectedIndex].text=="Vegetables"){
                 
        c1_1.prepend(checkbox);
      
        c1.innerText =title;
        console.log(title);
      }
      if(category.options[category.selectedIndex].text=="Fruits"){
       
        c2_1.prepend(checkbox);
        c2.innerText =title;
      }
      if(category.options[category.selectedIndex].text=="Grain,nuts,seeds"){
        c3_1.prepend(checkbox);
        c3.innerText =title;
      }
      if(category.options[category.selectedIndex].text=="Fish and seefood"){
       
        c4_1.prepend(checkbox);
        c4.innerText =title;
      }
      if(category.options[category.selectedIndex].text=="Dairy foods"){
        
        c5_1.prepend(checkbox);
        c5.innerText =title;
      }
      if(category.options[category.selectedIndex].text=="Meat"){
        
        c6_1.prepend(checkbox);
        c6.innerText =title;
      }
      if(category.options[category.selectedIndex].text=="Household chemicals"){
       
        c7_1.prepend(checkbox);
        c7.innerText =title;
      }
      if(category.options[category.selectedIndex].text=="Household products"){
            
        c8_1.prepend(checkbox);
        c8.innerText =title;
      }
      if(category.options[category.selectedIndex].text=="Sweets"){
        c9_1.prepend(checkbox);
        c9.innerText =title;
      }
      if(category.options[category.selectedIndex].text=="Drinks"){
        c10_1.prepend(checkbox);
        c10.innerText =title;
      }
      if(category.options[category.selectedIndex].text=="Cosmetics"){
        c11_1.prepend(checkbox);
        c11.innerText =title;
      }
      if(category.options[category.selectedIndex].text=="Other"){
        c12_1.prepend(checkbox);
        c12.innerText =title;
      }
     
    }

render();


   
 