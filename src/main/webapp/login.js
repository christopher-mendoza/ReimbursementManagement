const url = 'http://localhost:8080/ReimbursementManagement';

let loginbtn = document.getElementById('loginbtn');
let radiobtn = document.getElementsByName('type');
loginbtn.addEventListener('click', loginFunction);

function loginFunction() {
    var usernameValue = document.getElementById('username').value;
    var passwordValue = document.getElementById('password').value;
    let loginAttempt = {
        un: usernameValue,
        pw: passwordValue
    };

    var type = null;
    for(i = 0; i < radiobtn.length; i++) {
        if(radiobtn[i].checked) {
            type = radiobtn[i].value;
        }
    }
    if(usernameValue == '') {
        alert('Need a username!');
    }
    else if(passwordValue == '') {
        alert('Need a password!');
    }
    else{
        let adminJson = JSON.stringify(loginAttempt);
        let xhttp = new XMLHttpRequest();
        xhttp.open("POST", url + "/login");
        xhttp.send(adminJson);
        xhttp.onreadystatechange = receiveData;

        function receiveData() {
            if(xhttp.readyState == 4) {
                if(xhttp.status == 200) {
                    window.location.href = xhttp.responseText;
                }
            }
        }
    }

}