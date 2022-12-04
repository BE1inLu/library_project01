
const change_loginput = document.getElementById("change_loginput");

change_loginput.onchange=function(){
    const exchaneg1=document.getElementsByName("change");
    const exchange2=document.getElementsByName("logchange");

    for (let i = 0; i < exchange1.length; i++) {
        if (exchange1[i].checked) {
            if (i != 0) {
                document.getElementById("search-text").disabled = true;

                document.getElementById("logid").disabled = false;
                document.getElementById("bookid").disabled = false;
                document.getElementById("userid").disabled = false;

                document.getElementById("borrowDate").disabled = false;
                document.getElementById("receiveDate").disabled = false;

                document.getElementById("depot1").disabled = false;
                document.getElementById("depot2").disabled = false;

                document.getElementById("undepot1").disabled = false;
                document.getElementById("undepot2").disabled = false;

                document.getElementById("nullitem1").disabled = false;
                document.getElementById("nullitem2").disabled = false;

            } else {
                document.getElementById("search-text").disabled = false;

                document.getElementById("logid").disabled = true;
                document.getElementById("bookid").disabled = true;
                document.getElementById("userid").disabled = true;

                document.getElementById("borrowDate").disabled = true;
                document.getElementById("receiveDate").disabled = true;

                document.getElementById("depot1").disabled = true;
                document.getElementById("depot2").disabled = true;

                document.getElementById("undepot1").disabled = true;
                document.getElementById("undepot2").disabled = true;

                document.getElementById("nullitem1").disabled = true;
                document.getElementById("nullitem2").disabled = true;
            }
        }
    }

    for(let i=0;i<exchange2.length;i++){
        if(exchange2[i].checked){
            if(i!=0){
                document.getElementById("logid").disabled=true;
            }else{
                document.getElementById("logid").disabled=false;
            }
        }
    }

}
