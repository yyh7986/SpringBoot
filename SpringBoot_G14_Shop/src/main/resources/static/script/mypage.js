function go_cart() {
    if (document.document.frm.quantity.value == "") {
        alert("수량을 입력하세요");
        document.document.frm.quantity.focus();
    } else {
        document.document.frm.action = 'cartInsert';
        document.document.frm.submit();
    }
}

function go_cart_delete() {
    let count = 0;
    if (document.cartFrm.cseq.length == undefined) {
        if (document.cartFrm.cseq.checked) {
            count++;
        }
    } else {
        for (let i = 0; i < document.cartFrm.cseq.length; i++) {
            if (document.cartFrm.cseq[i].checked) {
                count++;
            }
        }
    }
    if (count == 0) {
        alert("삭제할 항목을 선택하세요");
    } else {
        let ans = confirm("삭제할?");
        if (ans) {
            document.cartFrm.action = "cartDelete";
            document.cartFrm.submit();
        }
    }
}

function go_order_insert() {
    let count = 0;
    if (document.cartFrm.cseq.length === undefined) {
        if (document.cartFrm.cseq.checked) {
            count++;
        }
    } else {
        for (let i = 0; i < document.cartFrm.cseq.length; i++) {
            if (document.cartFrm.cseq[i].checked) {
                count++;
            }
        }
    }
    if (count == 0) {
        alert("삭제할 항목을 선택하세요");
    } else {
        let ans = confirm("선택한 항목을 주문할까요?");
        if (ans) {
            document.cartFrm.action = "orderInsert";
            document.cartFrm.submit();
        }
    }

}

function go_order() {
    let ans = confirm("현재 상품을 주문할까요?");
    if (ans) {
        document.document.frm.action = "orderInsertOne";
        document.document.frm.submit();
    }
}

function qnaViewWithPass(pass, qseq) {
    let inputPass = prompt("비밀번호를 입력하세요.")
    inputPass === pass ?
        location.href = `qnaView?qseq=${qseq}` :
        alert("비밀번호가 일치하지 않습니다.")
}

function qnaView(qseq) {
    location.href=`qnaView?qseq=${qseq}`
}

function enable(){
    if(document.frm.secret.checked){
        document.frm.pass.disabled = false;
    }else{
        document.frm.pass.disabled = true;
        document.frm.pass.value = "";
    }
}