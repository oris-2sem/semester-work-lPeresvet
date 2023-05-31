function searchPlayer(number) {
    let surname = document.getElementById(number + "-surname").value;

    fetch("/players?surname=" + surname, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + localStorage.getItem('access'),
        },
    }).then(r => {
        if (r.status === 200) {
            return r.json();
        } else {
            return null;
        }
    }).then(content => {
        let inner = "";
        for (let i = 0; i < content.playerNum; i++) {
            let id = sanitizeTeam(content.players[i].id);
            inner += '<a id="p-' + id +
                '" ' +
                'class="list-group-item list-group-item-action" onclick="choosePlayer(' + number + ', ' +
                id +
                ')">' +
                sanitizeTeam(content.players[i].surname) + " " + sanitizeTeam(content.players[i].name) +
                '</a>';
        }
        document.getElementById(number + "-surname-list").innerHTML = inner;
    });
}

function choosePlayer(number, id) {
    let inp = document.getElementById(number + "-surname");
    inp.value = document.getElementById("p-" + id).innerText;
    document.getElementById(number + "-surname-list").innerHTML = "";
    document.getElementById(number + "-player-id").value = id;
}

function createTeam() {
    let id1 = document.getElementById("1-player-id").value;
    let id2 = document.getElementById("2-player-id").value;
    let status = document.getElementById("team-create-status");
    let body;
    if (id1 < 0 && id2 < 0) {
        status.innerText = "Введите игрока для создания команды";
        return;
    } else if (id1 < 0) {
        body = {
            players: [id2]
        }
    } else if (id2 < 0) {
        body = {
            players: [id1]
        }
    } else {
        body = {
            players: [id1, id2]
        }
    }
    fetch("/teams", {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + localStorage.getItem('access'),
        },
        body: JSON.stringify(body),
    }).then(r => {
        if (r.status === 201) {
            status.innerText = 'Сохранено';
            document.getElementById("1-player-id").value = "-1";
            document.getElementById("2-player-id").value = "-1";
            document.getElementById("1-surname").value = "";
            document.getElementById("2-surname").value = "";
            return r.json();
        } else {
            status.innerText  = 'Повторите попытку';
            return null;
        }
    });
}

const sanitizeTeam = (unsafe) => {
    return String(unsafe).replaceAll('&', '&amp;')
        .replaceAll('<', '&lt;')
        .replaceAll('>', '&gt;')
        .replaceAll('"', '&quot;')
        .replaceAll("'", '&#039;');
}