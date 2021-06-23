const url = 'http://localhost:8080/ReimbursementManagement';

let logoutbtn = document.getElementById('backbtn');
logoutbtn.addEventListener('click', logOut);

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

            // get reimbursements
            let rTable = document.createElement('table');
            let rTableRow = document.createElement('tr');
            let rTableHeaders = [ 
                'Name',
                'Event Date',
                'Location',
                'Cost',
                'Event Type',
                'Missed Work',
                'Work Justification',
                'Grade Format',
                'Benefits Coordinator Approved',
                'Department Head Approved',
                'Direct Supervisor Approved',
                'Grade Upload'
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

                rTable.append(rTableRow);
            }
            adminPage.append(rTable);
            console.log(admin);
        } 
    }
}


window.onload = getReimbursements();

function getUserReimbursements() {
    console.log("getting supervisor reimbursement");
    let xhttp = new XMLHttpRequest();
    xhttp.open('GET', url + '/getsupervisor');
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