function fillScore(r) {
    if (!(r === null)) {
        document.getElementById("first-team-score").innerText = sanitize3(r.firstTeamPoints);
        document.getElementById("second-team-score").innerText = sanitize3(r.secondTeamPoints);
    }
}

function incrementScore(teamNumber) {
    let num;
    if (teamNumber === 1) {
        num = Number(document.getElementById('first-team-score').innerText);
        num++;
        document.getElementById('first-team-score').innerText = String(num);
    } else {
        num = Number(document.getElementById('second-team-score').innerText);
        num++;
        document.getElementById('second-team-score').innerText = String(num);
    }

    let link = '/scores/' + String(document.getElementById('current-score-id').value);
    if (teamNumber === 1) {
        link += '/first_team/increment';
    } else {
        link += '/second_team/increment';
    }

    fetch(link, {
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
        fillScore(r);
    })

}

function decrementScore(teamNumber) {
    let num;
    if (teamNumber === 1) {
        num = Number(document.getElementById('first-team-score').innerText);
        num--;
        if (num >= 0) {
            document.getElementById('first-team-score').innerText = String(num);
        }
    } else {
        num = Number(document.getElementById('second-team-score').innerText);
        num--;
        if (num >= 0) {
            document.getElementById('second-team-score').innerText = String(num);
        }
    }

    let link = '/scores/' + String(document.getElementById('current-score-id').value);
    if (teamNumber === 1) {
        link += '/first_team/decrement';
    } else {
        link += '/second_team/decrement';
    }

    fetch(link, {
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
        fillScore(r);
    })

}

function getNewScore() {
    let link = '/games/' + document.getElementById("game-id").value + '/new_score';

    fetch(link, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + localStorage.getItem('access'),
        },
    }).then(re => {
        if (200 <= re.status < 300) {
            return re.json();
        } else {
            return null;
        }
    }).then(r => {
        let scores = r.scores;
        document.getElementById('current-score-id').value = sanitize3(scores[scores.length - 1].id);
    })
}

function storeScore() {
    let link = '/scores/' + document.getElementById("current-score-id").value + '/store';

    fetch(link, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + localStorage.getItem('access'),
        },
    }).then(re => {
        if (200 <= re.status < 300) {
            return re.json();
        } else {
            return null;
        }
    })
}

function newScore() {
    document.getElementById('first-team-score').innerText = String(0);
    document.getElementById('second-team-score').innerText = String(0);

    storeScore();
    getNewScore();
}

const sanitize3 = (unsafe) => {
    return String(unsafe).replaceAll('&', '&amp;')
        .replaceAll('<', '&lt;')
        .replaceAll('>', '&gt;')
        .replaceAll('"', '&quot;')
        .replaceAll("'", '&#039;');
}