color = ['green', 'yellow', 'red'];
index = 0;

function changeColor(color) {
    document.getElementById('btn-color').style.background = color;
}

function carousel() {
    if (index == 3)
        index = 0;
    document.getElementById('btn-color').style.background = color[index];
    setTimeout(function () {
        index++;
        carousel();
    }, 500);
}
