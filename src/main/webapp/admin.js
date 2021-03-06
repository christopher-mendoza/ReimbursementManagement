const url = 'http://localhost:8080/ReimbursementManagement';

let logoutbtn = document.getElementById('backbtn');
logoutbtn.addEventListener('click', logOut);

let bcbtn = document.getElementById('bcbtn');
bcbtn.addEventListener('click', bcApprove);

let dhbtn = document.getElementById('dhbtn');
dhbtn.addEventListener('click', dhApprove);

let dsbtn = document.getElementById('dsbtn');
dsbtn.addEventListener('click', dsApprove);

let adminPage = document.getElementById('admin');


const getReimbursements = () => {
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = receiveData;

    xhttp.open("GET", url + "/adminmain", true);
    xhttp.send();
    
    function receiveData() {
        if(xhttp.readyState == 4 && xhttp.status == 200) {
            let res = xhttp.response.replace("login.html", "");
            admin = JSON.parse(res);

            // get name
            let greet = document.createElement("h3");
            greet.innerHTML = "Hello " + admin.user.name + "!";
            console.log(admin.user.name);
            adminPage.append(greet);
            var count = 1;
            // get reimbursements
            let rTable = document.createElement('table');
            rTable.classList.add("table");
            rTable.classList.add("table-bordered");
            let rTableRow = document.createElement('tr');
            rTableRow.classList.add("table");
            rTableRow.classList.add("table-bordered");
            let rTableHeaders = [ 
                '#',
                'Name',
                'Event Date',
                'Location',
                'Cost',
                'Event Type',
                'Missed Work',
                'Proposed Reimbursement',
                'User Reimbursement'
            ]
            // Headers
            for(h of rTableHeaders) {
                let th = document.createElement('th');
                th.innerHTML = h;
                rTableRow.appendChild(th);
            }
            rTable.append(rTableRow);

            // Reimbursement Rows
            for(r of admin.list) {
                rTableRow = document.createElement('tr');

                // Number
                let number = document.createElement('td');
                number.innerHTML = count;
                count++;
                rTableRow.appendChild(number);

                // Name
                let name = document.createElement('td');
                name.innerHTML = r.name;
                rTableRow.appendChild(name);

                //Event Date
                let eventDate = document.createElement('td');
                eventDate.innerHTML = r.event.eventDate;
                rTableRow.appendChild(eventDate);

                // Event Location
                let eventLocation = document.createElement('td');
                eventLocation.innerHTML = r.event.eventLocation;
                rTableRow.appendChild(eventLocation);

                // Event Cost
                let eventCost = document.createElement('td');
                eventCost.innerHTML = '$' + r.event.eventCost;
                rTableRow.appendChild(eventCost);

                // Event Type
                let eventType = document.createElement('td');
                eventType.innerHTML = r.event.eventType.type;
                rTableRow.appendChild(eventType);

                // Missed Work
                let missedWork = document.createElement('td');
                missedWork.innerHTML = r.missedWork + ' hrs';
                rTableRow.appendChild(missedWork);

                // Proposed Reimbursement
                let propReim = document.createElement('td');
                propReim.innerHTML = '$' + (r.event.eventCost * r.event.eventType.coverage);
                rTableRow.appendChild(propReim);

                // User Reimbursement
                let userReim = document.createElement('td');
                userReim.innerHTML = '$' + admin.user.reAmount;
                rTableRow.appendChild(userReim);

                rTable.append(rTableRow);
            }
            adminPage.append(rTable);
            console.log(admin);

            if(admin.user.bcAdmin == true) {
                bcbtn.hidden = false;
            }
            else if(admin.user.dhAdmin == true) {
                dhbtn.hidden = false;
            }
            else if(admin.user.dsAdmin == true) {
                dsbtn.hidden = false;
            }
        } 
    }
}


window.onload = getReimbursements();

function getUserReimbursements() {
    console.log("getting supervisor reimbursement");
    let xhttp = new XMLHttpRequest();
    xhttp.open('GET', url + '/getsupervisor');
}

function bcApprove() {
    console.log("bc");
    let xhttp = new XMLHttpRequest();
    xhttp.open('POST', url + '/bcapprove');
    xhttp.send();
    xhttp.onreadystatechange = receiveData;

    function receiveData() {
        window.location.href = xhttp.responseText;
    }
}

function dhApprove() {
    console.log("dh");
    let xhttp = new XMLHttpRequest();
    xhttp.open('POST', url + '/dhapprove');
    xhttp.send();
    xhttp.onreadystatechange = receiveData;

    function receiveData() {
        window.location.href = xhttp.responseText;
    }
}

function dsApprove() {
    console.log("ds");
    let xhttp = new XMLHttpRequest();
    xhttp.open('POST', url + '/dsapprove');
    xhttp.send();
    xhttp.onreadystatechange = receiveData;

    function receiveData() {
        window.location.href = xhttp.responseText;
    }
}

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