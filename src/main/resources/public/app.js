document.getElementById('log-form').addEventListener('submit', function(event) {
    event.preventDefault();
    loadGetMsg();
});

let loadGetMsg = () => {
    let nameVar = document.getElementById("msg").value;
    const xhttp = new XMLHttpRequest();
    xhttp.onload = function() {
        const data = JSON.parse(this.responseText);
        document.getElementById("getrespmsg").setAttribute("style", "display: block");
        let container = document.getElementById("table")
        buildLogTable(container, data);

        //document.getElementById("getrespmsg").innerHTML = this.responseText;
    };
    xhttp.open("GET", "/log?msg="+nameVar);
    xhttp.send();
}

let buildLogTable = (container, logs) => {
    container.innerHTML = ""

    for (let i = 0; i < logs.length; i++) {
        let row = document.createElement("tr");
        let logData = document.createElement("td");
        logData.innerText = logs[i]["message"];
        row.appendChild(logData);
        let createdData = document.createElement("td");
        createdData.innerText = logs[i]["date"];
        row.appendChild(createdData);
        container.appendChild(row);
    }
};