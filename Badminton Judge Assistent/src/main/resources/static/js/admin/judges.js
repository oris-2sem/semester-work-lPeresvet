function createJudges() {
    let quantity = document.getElementById("num-of-generate-judges").value;
    fetch("/judges/group?quantity=" + quantity, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + localStorage.getItem('access'),
        },
    }).then(r => {
        if (r.status === 201) {
            document.getElementById("judge-generate-status").innerText = "Сгенерировано";
            document.getElementById("num-of-generate-judges").value = '';
            return r.json();
        } else {
            document.getElementById("judge-generate-status").innerText = 'Повторите попытку';
            return null;
        }
    }).then(content => {
        let holder = document.getElementById("judges-holder");
        let inner = '';
        for (let i = 0; i < content.judges.length; i++) {
            inner +=
                "<p>" +
                    sanitize(content.judges[i].login) + " " + sanitize(content.judges[i].password) +
                "</p>"
        }
        holder.innerHTML = inner;
    });
}

function getJudge() {
    let login = document.getElementById("judge-login").value;
    fetch("/judges/login/" + login, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + localStorage.getItem('access'),
        },
    }).then(r => {
        if (r.status === 200) {
            document.getElementById("judge-get").disabled = true;
            document.getElementById("judge-update").disabled = false;
            document.getElementById("judge-new-password").disabled = false;
            document.getElementById("judge-delete").disabled = false;
            return r.json();
        } else {
            document.getElementById("judge-generate-status").innerText = 'Повторите попытку';
            return null;
        }
    }).then(content => {
        console.log(content);
        document.getElementById("judge-id").value = sanitize(content.id);
    });
}

function updateJudge() {
    let login = document.getElementById("judge-login").value;
    let id = document.getElementById("judge-id").value;
    let body = {
        login: login,
    };
    fetch("/judges/" + id, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + localStorage.getItem('access'),
        },
        body: JSON.stringify(body),
    }).then(r => {
        if (r.status === 202) {
            document.getElementById("judge-status").innerText = "Обновлен";
            document.getElementById("judge-status").className = 'text-success';

            return r.json();
        } else {
            document.getElementById("judge-status").innerText = "Обновить не получилось";
            document.getElementById("judge-status").className = 'text-danger';
            return null;
        }
    });
}

function updatePassword() {
    let id = document.getElementById("judge-id").value;

    fetch("/judges/" + id + "/password", {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + localStorage.getItem('access'),
        },
    }).then(r => {
        if (r.status === 202) {
            document.getElementById("judge-status").className = 'text-success';

            return r.json();
        } else {
            document.getElementById("judge-status").innerText = "Обновить не получилось";
            document.getElementById("judge-status").className = 'text-danger';
            return null;
        }
    }).then(content => {
        if (content != null) {
            document.getElementById("judge-status").innerText =
                "Новый пароль: " + sanitize(content.password);
        }
    });
}

function deleteJudge() {
    let id = document.getElementById("judge-id").value;

    fetch("/judges/" + id, {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + localStorage.getItem('access'),
        },
    }).then(r => {
        if (r.status === 202) {
            document.getElementById("judge-status").innerText = "Пользователь удален";
            document.getElementById("judge-status").className = 'text-success';

            clearChosenJudge();

            return r.json();
        } else {
            document.getElementById("judge-status").innerText = "Обновить не получилось";
            document.getElementById("judge-status").className = 'text-danger';
            return null;
        }
    });
}

function clearChosenJudge() {
    document.getElementById("judge-id").value = "";
    document.getElementById("judge-login").value = "";

    document.getElementById("judge-get").disabled = false;
    document.getElementById("judge-update").disabled = true;
    document.getElementById("judge-new-password").disabled = true;
    document.getElementById("judge-delete").disabled = true;
}

const sanitize = (unsafe) => {
    return String(unsafe).replaceAll('&', '&amp;')
        .replaceAll('<', '&lt;')
        .replaceAll('>', '&gt;')
        .replaceAll('"', '&quot;')
        .replaceAll("'", '&#039;');
}
