function post() {
    var questionId = $("#question_id").val();
    var content = $("#comment-content").val();
    if (!content) {
        alert("不能回复空内容~~~");
        return;
    }

    $.ajax({
       type: "POST",
       url: "/comment",
       contentType: "application/json",
       data: JSON.stringify({
           "parentId": questionId,
           "content": content,
           "type": 1
       }),
       dataType: "json",
       success: function (response) {
           if (response.code == 200) {
               window.location.reload();
           } else {
               if (response.code == 2003) {
                   var isAccepted = confirm(response.message);
                   if (isAccepted) {
                       window.open("http://localhost:800/callback?code=84ce80502faa9ea7a212&state=1");
                       window.localStorage.setItem("closable", "true");
                   }
               }
               alert(response.message);
           }
           console.log(response);
       }
    });
}