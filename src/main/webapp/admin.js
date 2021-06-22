const url = 'http://localhost:8080/ReimbursementManagement';

let logoutbtn = document.getElementById('backbtn');
logoutbtn.addEventListener('click', logOut);


function logOut() {
    console.log('clicked button');
    let xhttp = new XMLHttpRequest();
    xhttp.open("POST", url + "/logout");
    xhttp.send();
    xhttp.onreadystatechange = receiveData;

    function receiveData() {
        console.log('Switching to login.html');
        window.location.href = xhttp.responseText;
    }

}