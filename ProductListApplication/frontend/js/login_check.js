

function validateform(){  
  let username=document.myform.username.value;  
  let password=document.myform.password.value;  
    
  if (username==null || username==""){  
    alert("Name can't be blank");  
    return false;  
  }else if(password.length<6){  
    alert("Password must be at least 6 characters long.");  
    return false;  
    }  
  
  }


  function validateemail() {

    let validRegex = /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;

    let emailcheck1 = document.emailcheck.email.value;
  
    if (emailcheck1.match(validRegex)) {
  
         return true;
  
    } else {
  
      alert("Invalid email address!");
       
      return false;
  
    }
  
  } 

  function matchpass(){  
    let firstpassword=document.f1.password.value;  
    let secondpassword=document.f1.password2.value;  
      
    if(firstpassword==secondpassword){  
    return true;  
    }  
    else{  
    alert("password must be same!");  
    return false;  
    }  
    }  

