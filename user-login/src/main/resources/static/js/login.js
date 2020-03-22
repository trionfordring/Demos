function freshTime() {
    var date = new Date();
    document.getElementById('date').innerHTML = date.getFullYear() + '-' + date.getMonth() + '-' + date.getDate();
    document.getElementById('time').innerHTML = date.getHours() + ':' + date.getMinutes() + ':' + date.getSeconds();
}

function startFresh() {
    freshTime();
    setInterval("freshTime()", 1000);
}

function turnToRegister() {
    document.getElementById('loginForm').style.display = 'none';
    document.getElementById('registerForm').style.display = 'inline'
}

function turnToLogin() {
    document.getElementById('registerForm').style.display = 'none';
    document.getElementById('loginForm').style.display = 'inline'
}