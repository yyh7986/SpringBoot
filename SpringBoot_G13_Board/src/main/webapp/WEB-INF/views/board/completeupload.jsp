<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Title</title>
    <meta charset="UTF-8">
</head>
<body>

<script>
    opener.document.insertBoard.savefilename.value = '${savefilename}';   // 전송될 히든태그에 이름 삽입
    opener.document.insertBoard.image.value = '${image}';
    opener.document.getElementById('image').innerHTML = '${image}'; //파일이름 확인을 위한 표시
    opener.document.getElementById('savefilename').innerHTML = '${savefilename}';
    opener.document.getElementById('previewimg').setAttribute('src', '/upload/' + '${savefilename}');
    opener.document.getElementById('previewimg').style.display = 'inline';  // 이미지 미리보기
    self.close();
</script>

</body>
</html>
