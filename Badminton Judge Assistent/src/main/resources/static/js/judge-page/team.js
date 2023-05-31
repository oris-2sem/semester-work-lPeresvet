let locked1 = false;
let locked2 = false;

function createTeamByOnePlayer(teamNumber, ...players) {
    let allData = [];

    let length = players.length
    for (let i = 0; i < length; i++) {
        let player = players[i].split(" ");
        if (player.length < 2 || player[0] === "" || player[1] === "") {
            document.getElementById("warn" + teamNumber).innerHTML = 'Проверьте правильность ввода';
            return;
        }
        let data = {
            surname: player[0],
            name: player[1],
        }
        allData.push(data);
    }
    createPlayers(allData, teamNumber);

}

function createPlayers(data, teamNumber) {
    let players = {
        players: data
    }
    fetch("/teams/players", {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + localStorage.getItem('access'),
        },
        body: JSON.stringify(players),
    }).then(r => {
        if (r.status === 201) {
            document.getElementById("warn" + teamNumber).innerHTML = 'Сохранено';
            // alert(r);
            return r.json();
        } else {
            document.getElementById("warn" + teamNumber).innerHTML = 'Повторите попытку';
            return null;
        }
    }).then(re => {
        if (!(re === null)) {
            document.getElementById(teamNumber + "-team-id").value = sanitize4(re.id);
            document.getElementById("create-team-button-" + teamNumber).disabled = true;
            document.getElementById("create-team-two-button-" + teamNumber).disabled = true;
        }
    });
}

const sanitize4 = (unsafe) => {
    return String(unsafe).replaceAll('&', '&amp;')
        .replaceAll('<', '&lt;')
        .replaceAll('>', '&gt;')
        .replaceAll('"', '&quot;')
        .replaceAll("'", '&#039;');
}
