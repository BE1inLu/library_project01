
const change_bookinput = document.getElementById("change_bookinput");

change_bookinput.onchange = function () {
    const exchange1 = document.getElementsByName("change");
    const exchange2=document.getElementsByName("bookchange");

    for (let i = 0; i < exchange1.length; i++) {
        if (exchange1[i].checked) {
            if (i != 0) {
                document.getElementById("search-text").disabled = true;

                document.getElementById("bookid").disabled = false;
                document.getElementById("bookname").disabled = false;
                document.getElementById("bookborrow_num").disabled = false;
                document.getElementById("bookreceive_num").disabled = false;

                document.getElementById("depot1").disabled = false;
                document.getElementById("depot2").disabled = false;

                document.getElementById("exchangebook1").disabled = false;
                document.getElementById("exchangebook2").disabled = false;

            } else {
                document.getElementById("search-text").disabled = false;

                document.getElementById("bookid").disabled = true;
                document.getElementById("bookname").disabled = true;
                document.getElementById("bookborrow_num").disabled = true;
                document.getElementById("bookreceive_num").disabled = true;

                document.getElementById("depot1").disabled = true;
                document.getElementById("depot2").disabled = true;

                document.getElementById("exchangebook1").disabled = true;
                document.getElementById("exchangebook2").disabled = true;
            }
        }
    }

    for(let i=0;i<exchange2.length;i++){
        if(exchange2[i].checked){
            if(i!=0){
                document.getElementById("bookid").disabled=true;
            }else{
                document.getElementById("bookid").disabled=false;
            }
        }
    }

}