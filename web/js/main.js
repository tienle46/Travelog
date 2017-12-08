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
    var output = '';
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
/*    
function displayContent() {
    var imgList = document.querySelector('.img-list');
    var imgRequest = new Request('http://10.114.32.133:8080/Travelog/resources/Fetch/postData');
    
    fetch(imgRequest)
            .then(function(response) {
                return response.json();
    })
    
            .then(function(json) {
                for (var i=0; i<json.length; i++) {
                    var listItem = document.createElement('li');
                    listItem.innerHTML = '<img src="' + json[i].path + '">';
                    imgList.appendChild(listItem);
                    
                }
    });
}
*/
/*
var loadContent = function(json) {

    var output = '';
    var id;

    //If cookie exists, get value with getId(). Else if location contains id parameter, we create new cookie.
    if (document.cookie.indexOf('travelogId') > -1) {
        id = getId();
    } else if (window.location.href.indexOf('id') > -1) {

        id = window.location.href.split('?')[1].replace('id=', '');
        document.cookie = 'travelogId=' + id + '; expires=Thu, 18 Dec 2020 12:00:00 UTC';
    }

    //Insert to main-content.
    for (i = 0; i < json.length; i++) {
        output += '<img src="' + json[i].path + '">';
            }

    document.querySelector('.main-content').innerHTML = output;
}

function getJson() {

    var mainContent = document.querySelector('.main-content')

    if (mainContent) {

        fetch('http://10.114.32.35:8080/Travelog-final3324008235048993204/webresources/Fetch/test').then(function(response) {
            var contentType = response.headers.get("content-type");

            if (contentType && contentType.indexOf("application/json") !== -1) {

                return response.json().then(function(json) {
                    loadContent(json);
                });

            }

        });

    }
}*/

function loadImg() {
    var myList = document.querySelector('.img-list');

    var myRequest = new Request('http://10.114.32.35:8080/Travelog-final3324008235048993204/webresources/Fetch/AllPost');

    fetch(myRequest)
      .then(function(response) { return response.json(); })
      .then(function(data) {
        for (var i = 0; i < data.length; i++) {
          var listItem = document.createElement('li');
          listItem.innerHTML = '<img src="' + data[i].path + '">';
          myList.appendChild(listItem);
        }
      });
}

createCookie();
loadImg();
//getJson();
//displayContent();
var idInput = document.querySelector('#userId');
idInput.value = getCookie();


