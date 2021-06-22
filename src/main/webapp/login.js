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

    var type;
    for(i = 0; i < radiobtn.length; i++) {
        if(radiobtn[i].checked) {
            type = radiobtn[i].value;
        }
    }
    if(type == 'Administration') {
        let adminJson = JSON.stringify(loginAttempt);
        let xhttp = new XMLHttpRequest();
        xhttp.open("POST", url + "/adminlogin");
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
    if(type == 'User') {

    }
        // let userJson = JSON.stringify(loginAttempt);
        // let xhttp = new XMLHttpRequest();
        // xhttp.onreadystatechange = receiveData;
        // xhttp.open('POST', url);
        // xhttp.send(userJson);

        // function receiveData() {
        //     if(xhttp.readyState == 4 && xhttp.status == 200) {
        //         console.log("success");
        //         console.log(xhttp.responseText);
        //     }
        // }    
}

// function validateForm() {
//     let un = document.forms['loginForm']['username'].value;
//     let pw = document.forms['loginForm']['password'].value;
//     if(un == '') {
//         alert("Username required!");
//         return false;
//     }
//     if(pw == '') {
//         alert("Password required!");
//         return false;
//     }
// }