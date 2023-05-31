const btn = document.querySelector("#login-btn");
btn.addEventListener("click", login, true);

function login() {
    const data = new URLSearchParams();

    let login = document.getElementById('login').value;
    let password = document.getElementById('password').value;

    data.append("login", login);
    data.append("password", password);

    fetch("/auth/token", {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: data,
    })
        .then(r => {
            if (r.status === 401) {
                document.getElementById("auth-info").innerText = "Неверный логин или пароль";
                return null;
            } else {
                return r.json();
            }
        })
        .then(r => {
            localStorage.setItem('access', r.accessToken);
            localStorage.setItem('refresh', r.refreshToken);
            window.location.href = "/control/judge";
            //setCookie(r);
        })
    console.log("Tokens saved");
}