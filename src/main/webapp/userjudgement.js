const url = 'http://localhost:8080/ReimbursementManagement';

let backbtn = document.getElementById('backbtn');
backbtn.addEventListener('click', goBack);

let submitbtn = document.getElementById('submitbtn');
submitbtn.addEventListener('click', submitJudgement);

let dropdownDiv = document.getElementById('dropdownDiv');

let dropDown = document.getElementById('dropDown');

let adminformDiv = document.getElementById('adminformDiv');

let judgement = document.getElementById('judgement');


const getReimbursements = () => {
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = receiveData;
    xhttp.open('GET', url + '/userlist');
    xhttp.send();

    function receiveData() {
        if(xhttp.readyState == 4 && xhttp.status == 200) {
            let res = xhttp.response;
            jsonObject = JSON.parse(res);

            // Create Dropdown
            var counter = 1;
            for(r of jsonObject.list) {
                var option = document.createElement('option');
                option.value = jsonObject.list.id;
                option.text = counter;
                dropDown.appendChild(option);
                counter++;
            }
            console.log(jsonObject);
            dropdownDiv.appendChild(dropDown);
            
        }
    }
}

window.onload = getReimbursements();

function showReimbursement() {
    let tableDiv = document.getElementById('tableDiv');
    let list = jsonObject.list[dropDown.selectedIndex - 1];
    tableDiv.innerHTML = '';
    if(dropDown.selectedIndex != 0) {
        submitbtn.hidden = false;
        let rTable = document.createElement('table');
        let rTableRow = document.createElement('tr');
        let rTableHeaders = [ 
            '#',
            'Event Date',
            'Location',
            'Cost',
            'Proposed Reimbursement',
            'Benefits Coordinator Set Reimbursement',
            'Benefits Coordinator Approved',
            'Reason',
            'Department Head Approved',
            'Reason',
            'Direct Supervisor Approved',
            'Reason'
        ]
        // Headers
        for(h of rTableHeaders) {
            let th = document.createElement('th');
            th.innerHTML = h;
            rTableRow.appendChild(th);
        }
        rTable.append(rTableRow);
        rTableRow = document.createElement('tr');

        // Number
        let number = document.createElement('td');
        number.innerHTML = dropDown.selectedIndex;
        rTableRow.appendChild(number);

        // Event Date
        let eventDate = document.createElement('td');
        eventDate.innerHTML = list.event.eventDate;
        rTableRow.appendChild(eventDate);

        // Event Location
        let eventLocation = document.createElement('td');
        eventLocation.innerHTML = list.event.eventLocation;
        rTableRow.appendChild(eventLocation);
        
        // Event Cost
        let eventCost = document.createElement('td');
        eventCost.innerHTML = '$' + list.event.eventCost;
        rTableRow.appendChild(eventCost);
        
        // Proposed Reimbursement
        let propReim = document.createElement('td');
        propReim.innerHTML = '$' + (list.event.eventCost * list.event.eventType.coverage);
        rTableRow.appendChild(propReim);

        // Benefits Coordinator Set Reimbursement
        let bcReim = document.createElement('td');
        bcReim.innerHTML = '$' + list.event.eventProposed;
        rTableRow.appendChild(bcReim);

        // Benefits Coordinator Approval
        let bcApprove = document.createElement('td');
        bcApprove.innerHTML = list.bcApproval.approve;
        rTableRow.appendChild(bcApprove);

        // BC Reason
        let bcReason = document.createElement('td');
        bcReason.innerHTML = list.bcApproval.reason;
        rTableRow.appendChild(bcReason);
 
        // Department Head Approval
        let dhApprove = document.createElement('td');
        dhApprove.innerHTML = list.dhApproval.approve;
        rTableRow.appendChild(dhApprove);

        // DH Reason
        let dhReason = document.createElement('td');
        dhReason.innerHTML = list.dhApproval.reason;
        rTableRow.appendChild(dhReason);
 
        // Direct Supervisor Approval
        let dsApprove = document.createElement('td');
        dsApprove.innerHTML = list.dsApproval.approve;
        rTableRow.appendChild(dsApprove);

        // DS Reason
        let dsReason = document.createElement('td');
        dsReason.innerHTML = list.dsApproval.reason;
        rTableRow.appendChild(dsReason);

        rTable.append(rTableRow);
        tableDiv.append(rTable);

        // Create admin form
        adminformDiv.hidden = false;
        
    }
    else {
        submitbtn.hidden = true;
        adminformDiv.hidden = true;
        reasonDiv.hidden = true;
    }
}

function submitJudgement() {
    console.log("submit");
    
    let userJudgement = {
        judgement: judgement.selectedIndex,
        id: jsonObject.list[dropDown.selectedIndex - 1].id
    }

    let userJson = JSON.stringify(userJudgement);
    let xhttp = new XMLHttpRequest();
    xhttp.open('POST', url + '/userverdict');
    xhttp.send(userJson);
    xhttp.onreadystatechange = receiveData;

    function receiveData() {
        if(xhttp.readyState == 4) {
            if(xhttp.status == 200) {
                window.location.href = xhttp.responseText;
            }
        }
    }
}

function goBack() {
    let xhttp = new XMLHttpRequest();
    xhttp.open("POST", url + "/cancel");
    xhttp.send();
    xhttp.onreadystatechange = receiveData;

    function receiveData() {
        window.location.href = xhttp.responseText;
    }
}