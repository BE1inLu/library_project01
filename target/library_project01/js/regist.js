function checkusername() {
    var name = document.getElementById("name");
    name = String(name);

    if (name==null) {
        document.getElementById("errortype1").innerHTML = "ERROR1";
    }else{
        document.getElementById("errortype1").innerHTML = "";
    }
}

// function checkpasswd1() {
//     var passwd = document.getElementById("passwd");
//     var passwd1=passwd.String();
//     if (passwd1.length < 6) {
//         document.getElementById("errortype2").innerHTML = "ERROR2";
//     }else{
//         document.getElementById("errortype2").innerHTML = " ";
//     }
// }

// function checkpasswd2() {
//     var passwd1 = document.getElementById("passwd");
//     var passwd2 = document.getElementById("passwd2");
//     if (passwd1!=passwd2) {
//         document.getElementById("errortype3").innerHTML = "ERROR3";
//     }else{
//         document.getElementById("errortype3").innerHTML = " ";
//     }
// }

