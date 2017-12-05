function logoutButton() {
    var loginButton = document.querySelector('#login');
    login.innerHTML = 'Logouts';
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
createCookie();
var idInput = document.querySelector('#userId');
idInput.value = getCookie();


