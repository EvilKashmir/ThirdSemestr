speed = 20;
xBorder = field.offsetWidth - 20;
yBorder = field.offsetHeight - 20;
xStart = 0;
yStart = 0;
xRotate = 1;
yRotate = 1;

setInterval(move, 100);

function move() {
    x = dvd.offsetWidth;
    y = dvd.offsetHeight;
    if (xStart + dvd.offsetWidth > xBorder || xStart < 0) {
        xRotate *= -1;
    }

    if (yStart + dvd.offsetHeight > yBorder || yStart < 0) {
        yRotate *= -1;
    }
    xStart += speed * xRotate;
    yStart += speed * yRotate;
    dvd.style.left = xStart + 'px';
    dvd.style.top = yStart + 'px';
}