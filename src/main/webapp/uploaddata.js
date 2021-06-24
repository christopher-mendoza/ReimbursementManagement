const url = 'http://localhost:8080/ReimbursementManagement';

let backbtn = document.getElementById('backbtn');
backbtn.addEventListener('click', goBack);

function goBack() {
    let xhttp = new XMLHttpRequest();
    xhttp.open("POST", url + "/cancel");
    xhttp.send();
    xhttp.onreadystatechange = receiveData;

    function receiveData() {
        window.location.href = xhttp.responseText;
    }
}