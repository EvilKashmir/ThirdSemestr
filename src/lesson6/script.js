document.onmousemove = function (event) {
    let x = event.x - 50;
    let y = event.y - 50;
    document.querySelector('#first').style.transform = 'rotate(' + angle(x, y) + 'deg)';
    document.querySelector('#second').style.transform = 'rotate(' + angle(x - 120, y) + 'deg)';

    function angle(x, y) {
        if (x >= 0) {
            return 180 / Math.PI * Math.atan(y / x);
        } else {
            return 180 / Math.PI * (Math.atan(y / x) + Math.PI);
        }
    }
}