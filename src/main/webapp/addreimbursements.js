const url = 'http://localhost:8080/ReimbursementManagement';

let submitbtn = document.getElementById('submitbtn');
submitbtn.addEventListener('click', submitFunction);

let backbtn = document.getElementById('backbtn');
backbtn.addEventListener('click', goBack);

function submitFunction() {
    var eventDateValue = document.getElementById('eventDate').value;
    var eventLocValue = document.getElementById('eventLocation').value;
    var eventDescValue = document.getElementById('eventDesc').value;
    var eventCostValue = document.getElementById('eventCost').value;
    var missedWorkValue = document.getElementById('missedWork').value;
    var gradeFormatValue = document.getElementById('gradeFormat').value;
    var eventTypeValue = document.getElementById('eventType').value;
    var workJustValue = document.getElementById('workJust').value;

    // Change Date to mm-dd-yyyy
    function formatDate(date) {
        var d = new Date(date),
            month = '' + (d.getMonth() + 1),
            day = '' + d.getDate(),
            year = d.getFullYear();
    
        if (month.length < 2) 
            month = '0' + month;
        if (day.length < 2) 
            day = '0' + day;
    
        return [month, day, year].join('-');
    };
    

    let reimbursementApplication = {
        date: formatDate(eventDateValue),
        location: eventLocValue,
        description: eventDescValue,
        cost: eventCostValue,
        missedWork: missedWorkValue,
        gradeFormat: gradeFormatValue,
        eventType: eventTypeValue,
        workJust: workJustValue
    };

    if(eventDateValue == '') {
        alert('Need an event date!');
    }
    else if(eventLocValue == '') {
        alert('Need an event location!');
    }
    else if(eventDescValue == '') {
        alert('Need an event description!');
    }
    else if(eventCostValue <= 0) {
        alert('Need an actual event cost!');
    }
    else if(missedWorkValue <= 0) {
        alert('Need missed work > 0!');
    }
    else if(gradeFormatValue == 'noSel') {
        alert('Need a grading format!');
    }
    else if(eventTypeValue == 'noSel') {
        alert('Need an event type!');
    }
    else if(workJustValue == '') {
        alert('Need a work justification!');
    }
    else {
        let reimbursementJson = JSON.stringify(reimbursementApplication);
        let xhttp = new XMLHttpRequest();
        xhttp.open("POST", url + "/submitreimbursement");
        xhttp.send(reimbursementJson);
        xhttp.onreadystatechange = receiveData;

        function receiveData() {
            if(xhttp.readyState == 4) {
                if(xhttp.status == 200) {
                    console.log(xhttp.responseText);
                    window.location.href = xhttp.responseText;
                }
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