const oneBtn = document.getElementById('one-btn');
const twoBtn = document.getElementById('two-btn');
const searchBtn = document.getElementById('search-btn');

oneBtn.addEventListener('onclick', expandFirst);
twoBtn.addEventListener('onclick', expandSecond);
searchBtn.addEventListener('onclick', expandThird);

let first;
let second;
let third;

function init() {
    first = document.getElementById('one-row');
    second = document.getElementById('second-row');
    third = document.getElementById('third-row');
}

function expandFirst() {
    init();
    first.style.display = 'flex';
    second.style.display = 'none';
    third.style.display = 'none';
}

function expandSecond() {
    init();
    first.style.display = "none";
    second.style.display = "flex";
    third.style.display = "none";
}

function expandThird() {
    init();
    first.style.display = "none";
    second.style.display = "none";
    third.style.display = "flex";
}

