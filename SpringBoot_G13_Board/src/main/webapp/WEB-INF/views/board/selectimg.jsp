<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Title</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="css/board.css">
    <script>
        function selectedimage() {
            document.frm.submit();
        }
    </script>
</head>
<body>

<div id="wrap" align="center" style="width:100%">

    <form name="frm" action="fileupload" method="post" enctype="multipart/form-data">
        <h1>파일 선택</h1>
        <input type="file" name="image" onchange="selectedimage();">
    </form>

</div>

</body>
</html>