/* global fetch */


    var searchInput;
    searchInput = getCookie("tag");
            
            function loadImgSearch() {
    var mySearch = document.querySelector("#search-list");
    var myRequest = new Request('http://10.114.32.35:8080/Travelog/webresources/Fetch/PostByTag/' + searchInput);
    
    
    fetch(myRequest)
      .then(function(response) { return response.json(); })
      .then(function(data) {
        for (var i = 0; i < data.length; i++) {
            if (data[i].tag === searchInput) {
                var listItem = document.createElement('li');
          listItem.innerHTML = '<a id="'+data[i].id+'" onclick = "getId(this.id)" href="" target="_blank"><img class="myImg"  src="' + data[i].path + '" alt="'+ data[i].description +'"></a><div class="info-hidden" style="display:none"><div id=""></div></div>';                                                                                            
          mySearch.appendChild(listItem);
            }
        }
      });
      
}

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

function logoutButton() {
    var loginButton = document.querySelector('#login');
    login.innerHTML = 'Logout';
    var href = loginButton.getAttribute('href');
    loginButton.href = "index.html";
    
    loginButton.setAttribute('onclick','logOut()');
}
function logOut() {
    var cookieName = 'travelogId=';
    document.cookie = cookieName + '=; expires=Thu, 01 Jan 1970 00:00:00 UTC;';
}

function createCookie() {
    var id;
    
    if (document.cookie.indexOf('travelogId') > -1) {
        id = getCookie("travelogId");
    } else if (window.location.href.indexOf('id') > -1) {
        id = window.location.href.split('?')[1].replace('id=','');
        document.cookie = 'travelogId=' + id + ';expires=Thu, 18 Dec 2020 12:00:00 UTC';
    }
    if (id){
    logoutButton();
    }
}  
function hideItem() {
    var userId = getCookie("travelogId");
    var hiddenElm=document.getElementsByClassName('hidden');

    if (userId === "") {
      for (var i=0; i<hiddenElm.length; i++){
          hiddenElm[i].style.display='none';
      }
    } 
}
createCookie();
hideItem();
       loadImgSearch(); 