const surnameSearchInput1 = document.getElementById("first-player-surname");
const surnameSearchInput2 = document.getElementById("second-player-surname");

surnameSearchInput1.addEventListener("onkeyup", search1);
surnameSearchInput2.addEventListener("onkeyup", search2);

function search2() {
    let surname = surnameSearchInput2.value;

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
            inner += '<a class="list-group-item list-group-item-action" onclick="setTeam(2, ' +
                sanitize1(content.players[i].id) +
                ')">' +
                sanitize1(content.players[i].surname) + " " + sanitize1(content.players[i].name) +
                '</a>';
        }
        document.getElementById("2-search-list").innerHTML = inner;
    });
}

function search1() {
    let surname = surnameSearchInput1.value;

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
            inner += '<a class="list-group-item list-group-item-action" onclick="setTeam(1, ' +
                sanitize1(content.players[i].id) +
                ')">' +
                sanitize1(content.players[i].surname) + " " + sanitize1(content.players[i].name) +
                '</a>';
        }
        document.getElementById("1-search-list").innerHTML = inner;
    });
}

function setTeam(teamN, playerId) {

    fetch("/players/" + playerId +"/teams", {
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
        for (let i = 0; i < content.teamsNum; i++) {
            console.log(content.teams[i]);
            let text = sanitize1(content.teams[i].players[0].surname) + " " + sanitize1(content.teams[i].players[0].name);
            if (content.teams[i].players.length === 2) {
                text += " " + sanitize1(content.teams[i].players[1].surname) +
                    " " + sanitize1(content.teams[i].players[1].name);
            }
            inner += '<a id="' + sanitize1(content.teams[i].id) + '" class="list-group-item list-group-item-action" onclick="chooseTeam('
                + teamN + ', ' + sanitize1(content.teams[i].id) + ')">' +
                text +
                '</a>';
        }
        document.getElementById(teamN + "-search-list").innerHTML = inner;
    });
}

function chooseTeam(teamNum, teamId) {
    document.getElementById(teamNum + "-team-id").value = teamId;
    let inner = document.getElementById(teamId).innerText;
    if (teamNum === 1) {
        surnameSearchInput1.value = inner;
    } else {
        surnameSearchInput2.value = inner;
    }
    document.getElementById(teamNum + "-search-list").innerText = "";
}

function createNewPlayer() {
    if (document.getElementById("sex").value === "default") {
        document.getElementById("player-create-status").innerHTML = 'Выберите пол';
    } else {
        let player = {
            name: document.getElementById("name").value,
            surname: document.getElementById("surname").value,
            score: document.getElementById("score").value,
            sex: document.getElementById("sex").value
        }
        fetch("/players", {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': 'Bearer ' + localStorage.getItem('access'),
            },
            body: JSON.stringify(player),
        }).then(r => {
            if (r.status === 201) {
                document.getElementById("player-create-status").innerHTML = 'Сохранено';
                document.getElementById("name").value = '';
                document.getElementById("surname").value = '';
                document.getElementById("score").value = '0';
                //todo: сброс селекта выбора пола
                return r.json();
            } else {
                document.getElementById("player-create-status").innerHTML = 'Повторите попытку';
                return null;
            }
        });
    }
}

const sanitize1 = (unsafe) => {
    return String(unsafe).replaceAll('&', '&amp;')
        .replaceAll('<', '&lt;')
        .replaceAll('>', '&gt;')
        .replaceAll('"', '&quot;')
        .replaceAll("'", '&#039;');
}