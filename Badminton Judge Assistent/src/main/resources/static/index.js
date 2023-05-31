

setInterval(function () {
    fetch('/auth/token', {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + localStorage.getItem('refresh'),
        },
    }).then(r => {
        return r.json();
    }).then(r => {
        localStorage.setItem('access', r.accessToken);
        localStorage.setItem('refresh', r.refreshToken);
        setCookie(r);
    })
}, 60 * 1000 * 1.5);

function setCookie(r) {
    let token = "jwt=JWT " + r.accessToken;
    document.cookie = token + "; path=/; expires=0";
}


// function searchPlayers(surname) {
//     return fetch('/players?surname=' + surname)
//         .then((response) => {
//             return response.json()
//         }).then((users) => {
//             fillTable(users)
//         })
// }







