
const change_userimput = document.getElementById("change_userimput");

change_userinput.onchange = function () {
    const exchange1 = document.getElementsByName("change");
    
    for (let i = 0; i < exchange1.length; i++) {
        if (exchange1[i].checked) {
            if (i!=0) {
                document.getElementById("search-text").disabled = true;
                document.getElementById("userid").disabled = false;
                document.getElementById("username").disabled = false;
                document.getElementById("userpassword").disabled = false;
                document.getElementById("useremail").disabled = false;
                document.getElementById("usertel").disabled = false;
                document.getElementById("sex1").disabled = false;
                document.getElementById("sex2").disabled = false;
                document.getElementById("superuser1").disabled=false;
                document.getElementById("superuser2").disabled=false;
            } else {
                document.getElementById("search-text").disabled = false;
                document.getElementById("userid").disabled = true;
                document.getElementById("username").disabled = true;
                document.getElementById("userpassword").disabled = true;
                document.getElementById("useremail").disabled = true;
                document.getElementById("usertel").disabled = true;
                document.getElementById("sex1").disabled = true;
                document.getElementById("sex2").disabled = true;
                document.getElementById("superuser1").disabled=true;
                document.getElementById("superuser2").disabled=true;
            }
        }
    }
}