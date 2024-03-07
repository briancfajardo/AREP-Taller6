document.getElementById('log-form').addEventListener('submit', function(event) {
    event.preventDefault();
    loadGetMsg();
});

function loadGetMsg() {
    let nameVar = document.getElementById("msg").value;
    const xhttp = new XMLHttpRequest();
    xhttp.onload = function() {
        document.getElementById("getrespmsg").innerHTML = this.responseText;
    };
    xhttp.open("GET", "/log?msh="+nameVar);
    xhttp.send();
}