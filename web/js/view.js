/* global fetch */
var postId = getCookie("postId");
var userIdInput = document.querySelector("#userId");
userIdInput.value = getCookie("travelogId");
var postIdInput = document.querySelector("#postId");
postIdInput.value = getCookie("postId");
var userIdInput = document.querySelector("#userId1");
userIdInput.value = getCookie("travelogId");
var postIdInput = document.querySelector("#postId1");
postIdInput.value = getCookie("postId");

function getCookie(cname) {
    var name = cname + "=";
    var decodedCookie = decodeURIComponent(document.cookie);
    var ca = decodedCookie.split(';');
    for(var i = 0; i <ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0) === ' ') {
            c = c.substring(1);
        }
        if (c.indexOf(name) === 0) {
            return c.substring(name.length, c.length);
        }
    }
    return "";
}
function deleteCookie() {
    var cookieName = 'postId=';
    document.cookie = cookieName + '=; expires=Thu, 01 Jan 1970 00:00:00 UTC;';
}

function loadImgView() {
    var myRequest = new Request('http://10.114.32.35:8080/Travelog-final3324008235048993204/webresources/Fetch/AllPost');
    
    
    fetch(myRequest)
      .then(function(response) { return response.json(); })
      .then(function(data) {
        for (var i = 0; i < data.length; i++) {
            if (data[i].id.toString() === postId) {
                var path = data[i].path;
                var des = data[i].description;  
                var title = data[i].title;
                var owner = data[i].owner;
                var date = data[i].date;
                var tag = data[i].tag;
            }
        }
        document.getElementById("img-popup").src = path;
        document.getElementById("caption").innerHTML = des;
        document.getElementById("img-title").innerHTML = title;
        document.getElementById("img-owner").innerHTML = owner;
        document.getElementById("img-date").innerHTML = date;
        document.getElementById("img-tag").innerHTML = tag;
      });
      
}

function loadComment() {
    var myRequest = new Request('http://10.114.32.35:8080/Travelog-final3324008235048993204/webresources/Fetch/Comment/'+postId);
    var cmtList = document.querySelector('.cmt-list');
    fetch(myRequest)
            .then(function(response) {return response.json();  })
            .then(function(data){
                for (var i = 0; i < data.length; i++) {
                    var listItem = document.createElement('li');
                    listItem.innerHTML = '<p>'+data[i].user+' said: '+data[i].comment+'</p>';
                    cmtList.appendChild(listItem);
                }
            });
}

function loadLike() {
    var myRequest = new Request('http://10.114.32.35:8080/Travelog-final3324008235048993204/webresources/Fetch/Like/'+postId);
    
    fetch(myRequest)
            .then(function(response) {return response.json();  })
            .then(function(data){
                for (var i = 0; i < data.length; i++) {
                document.getElementById("like-number").innerHTML = data[i].likenumber ; 
                }
            });
}
loadImgView();
loadComment();
loadLike();

