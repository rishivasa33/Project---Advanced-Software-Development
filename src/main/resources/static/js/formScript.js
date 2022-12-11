
 var courseContentAddRequest;
window.onload = function() {

    $.when( initialize() ).done(function() {
        courseContentAddRequest.getAllContent();
});

//   $(function () {
//     let token = $("meta[name='_csrf']").attr("content");
//     let header = $("meta[name='_csrf_header']").attr("content");
//     console.log(token);
//     console.log(header);
//     let text = "textContent" in document.body ? "textContent" : "innerText";
//       let courseIdElem = document.getElementById('courseId');
//       let courseId = courseIdElem[text];
//       let courseModuleIdElem = document.getElementById('courseModuleId');
//       let courseModuleId = courseModuleIdElem[text];
//       console.log(courseId);
//       console.log(courseModuleId);
//       courseContentAddRequest = new CreateCourseContentAddRequest(header,token, courseId, courseModuleId);
// }).then(courseContentAddRequest.getAllContent());
};

 function initialize(){
      //$(function () {
    let token = $("meta[name='_csrf']").attr("content");
    let header = $("meta[name='_csrf_header']").attr("content");
    console.log(token);
    console.log(header);
    let text = "textContent" in document.body ? "textContent" : "innerText";
      let courseIdElem = document.getElementById('courseId');
      let courseId = courseIdElem[text];
      let courseModuleIdElem = document.getElementById('courseModuleId');
      let courseModuleId = courseModuleIdElem[text];
      console.log(courseId);
      console.log(courseModuleId);
      courseContentAddRequest = new CreateCourseContentAddRequest(header,token, courseId, courseModuleId);
//});
}



class CreateCourseContentAddRequest {
  constructor(header, token, courseId, courseModuleId) {
    this.courseId = courseId;
    this.courseModuleId = courseModuleId;
    this.token = token;
    this.header = header;
    this.courseContentList = new Array();
  }

  addContent(content){
    console.log(content);
    if(content["success"]){
        this.courseContentList.push(content);
        let contentHeading = content["courseModuleContentHeading"];
        let contentText = content["courseModuleContentText"];
        let contentId = content["courseModuleContentId"];
        $("#contentList").append(`<div id = \"${contentId}\"><h3>${contentHeading}</h3><p>${contentText}</p></div>`);
    }
    else{
        alert("adding course content failed");
    }
  }


  getAllContent(){

    let url = "courseModuleDetails/getAllContents";
    let data=JSON.stringify({"courseId":this.courseId, "courseModuleId":this.courseModuleId});
    let qrReq=new XMLHttpRequest;

        XMLHttpRequest.responseType="json";
    
    qrReq.open("post", url, true);
    qrReq.setRequestHeader(this.header, this.token);
    qrReq.setRequestHeader("Content-Type", "application/json");
     qrReq.onload = function(){
        console.log(qrReq.responseText);
        let responseText = JSON.parse(qrReq.responseText);
        if(responseText["success"]){
           let contentArray = responseText["contentList"] 
           for (var i = 0; i < contentArray.length; i++){
                var courseContent = contentArray[i];
                courseContentAddRequest.addContent(courseContent);
            }
          
        }

      };
      console.log("data is " + data);
      console.log(this.courseId, this.courseModuleId, "tesst")
    qrReq.send(data);

    console.log("request send");


  }

  fetchContent(){

    let url = document.getElementById('create_content_form').action;
    let courseContentHeading = $("#course_content_heading").val(); 
    let courseContentText = $("#course_content_text").val(); 
    var data=JSON.stringify({"courseId":this.courseId, "courseModuleId":this.courseModuleId, 
        "courseContentHeading":courseContentHeading, "courseContentText": courseContentText});
    // $.ajaxSetup({
    // headers: { 'X-CSRF-TOKEN': this.token,"Content-Type" : "application/json"}
    // });

    // $.ajaxSetup({
    // beforeSend: function(xhr) {
    //     xhr.setRequestHeader(this.header, this.token);
    //     xhr.setRequestHeader("Content-Type", "application/json");
    //     }
    // });



 //     $.ajax(
 //    {
 //        type:"POST",
 //        url: url,
 //        dataType : 'json',
 //        data:data, 
 //        success: function( response ) 
 //        {
 //              addContent(response)
        
 //        }

 //     });
 // }
    //courseDetails/courseModuleDetails/getAllContents
    let qrReq=new XMLHttpRequest;

        XMLHttpRequest.responseType="json";
    
    qrReq.open("post", url, true);
    qrReq.setRequestHeader(this.header, this.token);
    qrReq.setRequestHeader("Content-Type", "application/json");
     qrReq.onload = function(){

          courseContentAddRequest.addContent(JSON.parse(qrReq.responseText));

      }
    qrReq.send(data);

    console.log("request send");
    

    }
};



  var courseContentAddRequest = new CreateCourseContentAddRequest("default", 0);

  function test(){
    courseContentAddRequest.fetchContent();
  }




