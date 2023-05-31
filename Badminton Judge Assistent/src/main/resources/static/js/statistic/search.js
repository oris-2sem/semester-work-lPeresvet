const surnameSearch = document.getElementById("statistic-player-surname");

const button = document.getElementById("statistic-btn");
button.addEventListener("onclick", buildStatistic, true);

function search() {
    let surname = surnameSearch.value;

    if (surname === "") {
        document.getElementById("statistic-search-list").innerHTML = "";
        return;
    }

    fetch("/players?surname=" + surname, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
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
            let id = sanitize5(content.players[i].id);
            inner += '<a class="list-group-item list-group-item-action" ' +
                'id="'+ id +'-player"' +
                ' onclick="choose(' + id + ')">' +
                sanitize5(content.players[i].surname) +
                " " + sanitize5(content.players[i].name) +
                '</a>';
        }
        document.getElementById("statistic-search-list").innerHTML = inner;
    });
}

function choose(id) {
    let input = document.getElementById("statistic-player-surname");
    input.value = document.getElementById(id + "-player").innerText;
    document.getElementById("search-player-id").value = id;
    document.getElementById("statistic-search-list").innerHTML = "";
}

function buildStatistic() {
    let playerId = document.getElementById("search-player-id").value;
    fetch("/players/" + playerId + "/games", {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
        },
    }).then(r => {
        if (r.status === 200) {
            return r.json();
        } else {
            return null;
        }
    }).then(content => {
        let inner = '<h4>Сыгранные мачти</h4>';
        for(let i = 0; i < content.gamesNum; i++) {
            let row1 = "<tr>";
            let row2 = "<tr>";

            row1 += "<th>";
            for(let j = 0; j < content.games[i].teams[0].players.length; j++) {
                row1 += "<p>" +
                    sanitize5(content.games[i].teams[0].players[j].surname) +
                    " " +
                    sanitize5(content.games[i].teams[0].players[j].name) +
                    "</p>";
            }
            row1 += "</th>";

            row2 += "<th>";
            for(let j = 0; j < content.games[i].teams[1].players.length; j++) {
                row2 += "<p>" +
                    sanitize5(content.games[i].teams[1].players[j].surname) +
                    " " +
                    sanitize5(content.games[i].teams[1].players[j].name) +
                    "</p>";
            }
            row2 += "</th>";

            for (let j = 0; j < content.games[i].scores.length; j++) {
                row1 += "<td class='text-center'>" +
                    sanitize5(content.games[i].scores[j].firstTeamPoints) +
                    "</td>";
                row2 += "<td class='text-center'>" +
                    sanitize5(content.games[i].scores[j].secondTeamPoints) +
                    "</td>";
            }
            row1 += "</tr>";
            row2 += "</tr>";
            inner += row1 + row2;
        }

        document.getElementById("statistic").innerHTML = inner;
    });
}

function findTeammates() {
    let playerId = document.getElementById("search-player-id").value;
    fetch("/players/" + playerId + "/teammates", {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
        },
    }).then(r => {
        if (r.status === 200) {
            return r.json();
        } else {
            return null;
        }
    }).then(content => {
        let inner = '<h4>Сокомандники</h4>';

        for(let i = 0; i < content.playerNum; i++) {
            let player = content.players[i];
            inner += '<p>' +
                sanitize5(player.surname) + ' ' +
                sanitize5(player.name) +
                '</p>';
        }

        document.getElementById("teammates").innerHTML = inner;
    });
}

const sanitize5 = (unsafe) => {
    return String(unsafe).replaceAll('&', '&amp;')
        .replaceAll('<', '&lt;')
        .replaceAll('>', '&gt;')
        .replaceAll('"', '&quot;')
        .replaceAll("'", '&#039;');
}
