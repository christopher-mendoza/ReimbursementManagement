const url = 'http://localhost:8080/ReimbursementManagement';

let logoutbtn = document.getElementById('backbtn');
logoutbtn.addEventListener('click', logOut);

let addbtn = document.getElementById('addbtn');
addbtn.addEventListener('click', addReimbursements);

let uploadbtn = document.getElementById('uploadbtn');
uploadbtn.addEventListener('click', uploadData);

let userPage = document.getElementById('user');

const getReimbursements = () => {
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = receiveData;

    xhttp.open("GET", url + "/usermain", true);
    xhttp.send();
    
    function receiveData() {
        if(xhttp.readyState == 4 && xhttp.status == 200) {
            console.log(xhttp.response);
            let res = xhttp.response;
            user = JSON.parse(res);
            // get name
            let greet = document.createElement("h3");
            greet.innerHTML = "Hello " + user.user.name + "!";
            console.log(user.user.name);
            userPage.append(greet);
            var count = 1;

            // get reimbursement amt
            let amount = document.createElement("h3");
            amount.innerHTML = "Projected Reimbursement Total: $" + user.user.reAmount;
            userPage.append(amount);

            // get reimbursements
            let rTable = document.createElement('table');
            let rTableRow = document.createElement('tr');
            let rTableHeaders = [ 
                '#',
                'Event Date',
                'Location',
                'Cost',
                'Event Type',
                'Missed Work',
//                'Work Justification',
                'Grade Format',
                'Benefits Coordinator Approved',
                'Department Head Approved',
                'Direct Supervisor Approved',
//                'Grade Upload'
            ]
            // Headers
            for(h of rTableHeaders) {
                let th = document.createElement('th');
                th.innerHTML = h;
                rTableRow.appendChild(th);
            }
            rTable.append(rTableRow);
           
            // Reimbursement Rows
            for(r of user.list) {
                rTableRow = document.createElement('tr');

                let number = document.createElement('td');
                number.innerHTML = count;
                count++;
                rTableRow.appendChild(number);

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

                // Grade Format
                let gradeFormat = document.createElement('td');
                gradeFormat.innerHTML = r.gradingFormat.gFormatName;
                rTableRow.appendChild(gradeFormat);

                // Benefits Coordinator Approval
                let bcApprove = document.createElement('td');
                bcApprove.innerHTML = r.bcApproval.approve;
                rTableRow.appendChild(bcApprove);

                // Department Head Approval
                let dhApprove = document.createElement('td');
                dhApprove.innerHTML = r.dhApproval.approve;
                rTableRow.appendChild(dhApprove);

                // Direct Supervisor Approval
                let dsApprove = document.createElement('td');
                dsApprove.innerHTML = r.dsApproval.approve;
                rTableRow.appendChild(dsApprove);

                rTable.append(rTableRow);
            }
            userPage.append(rTable);
            console.log(user);
        } 
    }
}

window.onload = getReimbursements();

function addReimbursements() {
    let xhttp = new XMLHttpRequest();
    xhttp.open('POST', url + '/addreimbursements');
    xhttp.send();
    xhttp.onreadystatechange = receiveData;
    
    function receiveData() {
        console.log('Switching to addReimbursements.html');
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

function uploadData() {
    let xhttp = new XMLHttpRequest();
    xhttp.open('POST', url + '/userupload');
    xhttp.send();
    xhttp.onreadystatechange = receiveData;

    function receiveData() {
        window.location.href = xhttp.responseText;
    }
}