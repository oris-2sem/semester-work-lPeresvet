function createStartedGame(teamId1, teamId2, cortId, turn) {
    let status = document.getElementById("game-status");
    if (turn > 2 || turn < 1) {
        status.innerHTML = "Введите корректный номер команды";
        return;
    }

    if (teamId1 < 0 || teamId2 < 0) {
        status.innerText = "Выберите 2 команды";
        return;
    }

    if (cortId == null) {
        status.innerText = "Введите номер корта";
        return;
    }
    let data = {
        cortId: cortId,
        team1Id: teamId1,
        team2Id: teamId2,
        status: 'started',
        turn: turn,
        judgeId: 1
    }

    fetch("/games", {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + localStorage.getItem('access'),
        },
        body: JSON.stringify(data),
    }).then(re => {
        if (re.status === 201) {
            return re.json();
        } else {
            return null;
        }
    }).then(r => {
        if (!(r === null)) {
            document.getElementById("game-status").innerHTML = 'Игра началась!';
            document.getElementById("game-id").value = r.id;
            document.getElementById('current-score-id').value = sanitize2(r.scores[0].id);
            document.getElementById('start-button').disabled = true;
        }
    })
}

function finishGame() {
    let gameId = document.getElementById("game-id").value;
    let scoreId = document.getElementById("current-score-id").value;
    fetch("/games/" + gameId + "/finished", {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + localStorage.getItem('access'),
        },
    }).then(re => {
        if (re.status === 202) {
            return re.json();
        } else {
            return null;
        }
    }).then(r => {
        if (!(r === null)) {
            document.getElementById("game-status").innerHTML = 'Игра закончилась!';
            document.getElementById("game-id").value = -1;
            document.getElementById('current-score-id').value = -1;
            document.getElementById('start-button').disabled = false;
            document.getElementById("create-team-button-1").disabled = false;
            document.getElementById("create-team-button-2").disabled = false;
            document.getElementById("create-team-two-button-1").disabled = false;
            document.getElementById("create-team-two-button-2").disabled = false;
        }
    });

    fetch("/scores/" + scoreId + "/store", {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + localStorage.getItem('access'),
        },
    }).then(re => {
        if (re.status === 202) {
            return re.json();
        } else {
            return null;
        }
    }).then(conten => {
        document.getElementById('second-team-score').innerText = '0';
        document.getElementById('first-team-score').innerText = '0';
    });
}

const sanitize2 = (unsafe) => {
    return String(unsafe).replaceAll('&', '&amp;')
        .replaceAll('<', '&lt;')
        .replaceAll('>', '&gt;')
        .replaceAll('"', '&quot;')
        .replaceAll("'", '&#039;');
}