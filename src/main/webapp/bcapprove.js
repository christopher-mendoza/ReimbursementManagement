const url = 'http://localhost:8080/ReimbursementManagement';

let backbtn = document.getElementById('backbtn');
backbtn.addEventListener('click', goBack);

let submitbtn = document.getElementById('submitbtn');
submitbtn.addEventListener('click', submitJudgement);

let dropdownDiv = document.getElementById('dropdownDiv');

let dropDown = document.getElementById('dropDown');

let adminformDiv = document.getElementById('adminformDiv');

let judgement = document.getElementById('judgement');

let reasonDiv = document.getElementById('reasonDiv');

const getReimbursements = () => {
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = receiveData;
    xhttp.open('GET', url + '/bclist');
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
            'Name',
            'Event Date',
            'Location',
            'Cost',
            'Event Type',
            'Missed Work',
            'Work Justification',
            'Grade Format',
            'Grade Upload',
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
        rTableRow = document.createElement('tr');

         // Number
         let number = document.createElement('td');
         number.innerHTML = dropDown.selectedIndex;
         rTableRow.appendChild(number);
 
         // Name
         let name = document.createElement('td');
         name.innerHTML = list.name;
         rTableRow.appendChild(name);
 
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
         
         // Event Type
         let eventType = document.createElement('td');
         eventType.innerHTML = list.event.eventType.type;
         rTableRow.appendChild(eventType);
         
         // Missed Work
         let missedWork = document.createElement('td');
         missedWork.innerHTML = list.missedWork + ' hrs';
         rTableRow.appendChild(missedWork);
         
         // Work Justification
         let workJust = document.createElement('td');
         workJust.innerHTML = list.workJust;
         rTableRow.appendChild(workJust);
 
         // Grade Format
         let gradeFormat = document.createElement('td');
         gradeFormat.innerHTML = list.gradingFormat.gFormatName;
         rTableRow.appendChild(gradeFormat);
 
         // Grade Upload
         let gradeUpload = document.createElement('td');
         gradeUpload.innerHTML = list.gUp.gradeUp;
         rTableRow.appendChild(gradeUpload);

         // Proposed Reimbursement
         let propReim = document.createElement('td');
         propReim.innerHTML = list.event.eventCost * list.event.eventType.coverage;
         rTableRow.appendChild(propReim);

         // User Reimbursement
         let userReim = document.createElement('td');
         userReim.innerHTML = jsonObject.user.reAmount;
         rTableRow.appendChild(userReim);
 
         rTable.append(rTableRow);
         tableDiv.append(rTable);

         // Create admin form
         adminformDiv.hidden = false;
    }
    else {
        submitbtn.hidden = true;
        adminformDiv.hidden = true;
        reasonDiv.hidden = true;
        changeDiv.hidden = true;
    }
}

function showReason() {
    reasonDiv.hidden = false;
    if(judgement.selectedIndex == 0) {
        changeDiv.hidden = false;
    }
    else {
        changeDiv.hidden = true;
    }
}

function submitJudgement() {
    console.log("submit");
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