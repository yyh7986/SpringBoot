function go_wrt(){
    location.href = "productWriteForm";
}

function cals(){
    let value1 = document.productWriteFrm.price1.value;
    let value2 = document.productWriteFrm.price2.value

    if( value1==="" || value2===""){
        return;
    }else{
        document.productWriteFrm.price3.value = value2 - value1;
    }
}