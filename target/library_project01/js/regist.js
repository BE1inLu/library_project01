function checkusername() {
    var name = document.getElementById("name");
    name = String(name);

    if (name==null) {
        document.getElementById("errortype1").innerHTML = "ERROR1";
    }else{
        document.getElementById("errortype1").innerHTML = "";
    }
}

