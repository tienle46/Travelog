/* global fetch */

function logoutButton() {
    var loginButton = document.querySelector('#login');
    login.innerHTML = 'Logout';
    var href = loginButton.getAttribute('href');
    loginButton.href = "index.html";
    
    loginButton.setAttribute('onclick','logOut()');
}


function getCookie() {
    var cookieName = 'travelogId=';
    var ca = document.cookie.split(';');
    
    for (var i =0; i < ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0) == ' ') {
            c =c.substring(1);
        }
        if (c.indexOf(cookieName) == 0) {
            return c.substring(cookieName.length, c.length);
        }
    }
    return "";
}

function logOut() {
    var cookieName = 'travelogId=';
    document.cookie = cookieName + '=; expires=Thu, 01 Jan 1970 00:00:00 UTC;';
}

function createCookie() {
    var id;
    
    if (document.cookie.indexOf('travelogId') > -1) {
        id = getCookie();
    } else if (window.location.href.indexOf('id') > -1) {
        id = window.location.href.split('?')[1].replace('id=','');
        document.cookie = 'travelogId=' + id + ';expires=Thu, 18 Dec 2020 12:00:00 UTC';
    }
    if (id){
    logoutButton();
    }
}  

function loadImg() {
    var myList = document.querySelector('.img-list');

    var myRequest = new Request('http://10.114.32.35:8080/Travelog-final3324008235048993204/webresources/Fetch/AllPost');

    fetch(myRequest)
      .then(function(response) { return response.json(); })
      .then(function(data) {
        for (var i = 0; i < data.length; i++) {
          var listItem = document.createElement('li');
          listItem.innerHTML = '<a id="'+data[i].id+'" onclick = "getId(this.id)" href="" target="_blank"><img class="myImg"  src="' + data[i].path + '" alt="'+ data[i].description +'"></a><div class="info-hidden" style="display:none"><div id=""></div></div>';                                                                                            
          myList.appendChild(listItem);
          
        }
        
      });
}

function displayImg() {
    var postId1 = document.getElementById("imgId").rel;
    for (var j=1; j <10; j++) {
        if (j === postId1) {
            document.getElementById("img-popup").src = document.getElementsByClassName("myImg").src;
            document.getElementById("caption").innerHTML = document.getElementsByClassName("myImg").alt;
            
        }
    }
}

function getId(clicked_id) {
              document.cookie = 'postId=' + clicked_id + ';expires=Thu, 18 Dec 2020 12:00:00 UTC';
              document.getElementById(clicked_id).href='view.html';
          }
/*
var imgId = document.querySelector('#img-id');
var imgValue = imgId.value;

function displayImg() {
    var imgTitle = document.querySelector("#img-title");
    var imgOwner = document.querySelector("#img-owner");
    var imgDate = document.querySelector("#img-date");
    var imgTag = document.querySelector("#img-tag");
    var myRequest = new Request('http://10.114.32.35:8080/Travelog-final3324008235048993204/webresources/Fetch/AllPost');
    
    
    fetch(myRequest)
      .then(function(response) { return response.json(); })
      .then(function(data) {
        for (var i = 0; i < data.length; i++) {
          var id = data[i].id;
          var title = data[i].title;
          var owner = data[i].owner;
          var tag = data[i].tag;
          var date = data[i].date;
          if (id === imgValue) {
              imgTitle.innerHTML = title;
              imgOwner.innerHTML = owner;
              imgDate.innerHTML = date;
          }
        }
      });
}
*/
/*
window.onload = function() {
        var imgClick = document.getElementById("myImg1");
        var insertImg = document.getElementById("image-popup");
        var imgCaption = document.getElementById("caption");
        
        imgClick.onclick = function() {
            imgModal.style.display = "block";
            insertImg.src = this.src;
            imgCaption.innerHTML = this.alt;    
        };
        };
        
        var imgModal = document.getElementById("image-modal");
      
         window.onclick = function(event) {
            if (event.target === imgModal) {
                imgModal.style.display = "none";
            }
        };
*/
createCookie();
loadImg();
//displayImg();
var idInput = document.querySelector('#userId');
idInput.value = getCookie();