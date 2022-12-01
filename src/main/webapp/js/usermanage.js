

const change_userimput = document.getElementById("change_userimput");

// var textitem="search";

function e1(i) {

    var j = (i == "search") ? false : true;

    document.getElementById("userid").disabled = j;
    document.getElementById("username").disabled = j;
    document.getElementById("userpassword").disabled = j;
    document.getElementById("useremail").disabled = j;
    document.getElementById("usertel").disabled = j;
    document.getElementsByName("sex").disabled = j;
}

change_userinput.onchange = function () {
    const exchange1 = document.getElementsByName("change");
    for (let i = 0; i < exchange1.length; i++) {
        if (exchange1[i].checked) {
            textitem = exchange1[i].value;
            console.log(textitem);
            if (textitem == "exchange") {
                document.getElementById("search-text").disabled = true;
                document.getElementById("userid").disabled = false;
                document.getElementById("username").disabled = false;
                document.getElementById("userpassword").disabled = false;
                document.getElementById("useremail").disabled = false;
                document.getElementById("usertel").disabled = false;
                document.getElementsByName("sex").disabled = false;

            } else {
                document.getElementById("search-text").disabled = false;
                document.getElementById("userid").disabled = true;
                document.getElementById("username").disabled = true;
                document.getElementById("userpassword").disabled = true;
                document.getElementById("useremail").disabled = true;
                document.getElementById("usertel").disabled = true;
                document.getElementsByName("sex").disabled = true;
            }
        }
    }
}