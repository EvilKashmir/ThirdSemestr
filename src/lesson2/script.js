var color = ['green', 'yellow', 'red'];
var cursor = 0;

function change_color(color) {
    document.getElementById('btn-color').style.background = color;
}

function carousel() {
    if (cursor == 3)
        cursor = 0;
    document.getElementById('btn-color').style.background = color[cursor];
    setTimeout(function() {
        cursor++;
        carousel();
    }, 500);
}
