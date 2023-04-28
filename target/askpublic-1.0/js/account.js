

const regPart = document.getElementById("register");
const logPart = document.getElementById("login");
const logNav = document.getElementById("logBtn");
const regNav = document.getElementById("regBtn");

regPart.style.display = "none";
logPart.style.display = "block";
logNav.style.backgroundColor = "teal";
logNav.style.color = "white";

logNav.addEventListener("click", (e)=> {
    e.preventDefault();
    if(regPart.style.display === "none"){
        return false;
    }else {
        regPart.style.display = "none";
        logPart.style.display = "block";
        logNav.style.backgroundColor = "teal";
        logNav.style.color = "white";
        regNav.style.backgroundColor = "transparent";
        regNav.style.color = "white";
    }
})

regNav.addEventListener("click", (e)=> {
    e.preventDefault();
    if(regPart.style.display === "none"){
        regPart.style.display = "block";
        logPart.style.display = "none";
        regNav.style.backgroundColor = "teal";
        regNav.style.color = "white";
        logNav.style.backgroundColor = "transparent";
        logNav.style.color = "white";
    }else {
        return false;
    }
})
