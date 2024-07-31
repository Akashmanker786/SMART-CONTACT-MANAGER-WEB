console.log("java script is activated")


// change theme  code starts //

let currentTheme = getTheme();
console.log(currentTheme);
changeTheme();



// function to change the theme

function changeTheme(){

     //  set class to web page  
      document.querySelector("html").classList.add(currentTheme);        

    //   setting the listner for changing the theme from button 
     const changeThemeButton =  document.querySelector('#theme_button');

    // changing the text in the themebutton 
    changeThemeButton.querySelector("span").textContent =  currentTheme == "light" ? "Dark" : "Light";

    changeThemeButton.addEventListener("click" , () => {
    console.log("theme button clicked");
    
    // storing the current theme  as oldTheme so that we can remove it in future
       const oldTheme = currentTheme; 
      
    if(currentTheme==="dark"){

        currentTheme = "light";
    }
    // theme to dark
    else{
        currentTheme = "dark";
    }

    // changing the theme in localStorage
     setTheme(currentTheme);
     document.querySelector("html").classList.remove(oldTheme);   // removing the old theme 
     document.querySelector("html").classList.add(currentTheme);      // adding the new theme
     
      // changing the text in the themebutton 
    changeThemeButton.querySelector("span").textContent =  currentTheme == "light" ? "Dark" : "Light";

    })


}


// function to set the theme in localStorage
function setTheme(theme){
 localStorage.setItem("theme", theme);
}

// function to get the theme from local Storage 
function getTheme(){
    
    const theme = localStorage.getItem("theme");
    if(theme)  return theme;
    else  return "light";
}


// change theme code ends //
