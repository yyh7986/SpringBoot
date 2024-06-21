function go_cart() {
    if (document.form.quantity.value == "") {
        alert("수량을 입력하세요");
        document.form.quantity.focus();
    } else {
        document.form.action = 'cartInsert';
        document.form.submit();
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
        document.form.action = "orderInsertOne";
        document.form.submit();
    }
}
