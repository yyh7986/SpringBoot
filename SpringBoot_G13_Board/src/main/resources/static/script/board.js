function idCheck(){
    const userId = document.join.userid;
    if(userid.value==""){
        alert('아이디를 입력하세요');
        userid.focus();
        return;
    }
    let k = userid.value;
    let opt = "toolbar=no,menubar=no,resizable=no,width=450,height=200";
    window.open("idcheck?userid=" + k, "id check", opt);
}