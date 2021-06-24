const url = 'http://localhost:8080/ReimbursementManagement';

let backbtn = document.getElementById('backbtn');
backbtn.addEventListener('click', goBack);

let dropdownDiv = document.getElementById('dropdownDiv');

let dropDown = document.getElementById('dropDown');



const getReimbursements = () => {
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = receiveData;
    xhttp.open('GET', url + '/dslist');
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
        let rTable = document.createElement('table');
        let rTableRow = document.createElement('tr');
        let rTableHeaders = [ 
            '#',
            'Event Date',
            'Location',
            'Cost',
            'Event Type',
            'Missed Work',
            'Work Justification',
            'Grade Format',
            'Grade Upload'
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

        rTable.append(rTableRow);
        tableDiv.append(rTable);

        // Create admin form
        
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