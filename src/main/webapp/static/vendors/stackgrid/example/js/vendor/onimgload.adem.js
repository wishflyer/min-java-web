(function() { !
    function() {
        return this.onimgload = function(t, i) {
            var n, h;
            return n = new Image,
            h = {
                url: t,
                height: n.height,
                width: n.width,
                ratio: n.width / n.height
            },
            n.onload = function() {
                return i(h)
            },
            n.src = t,
            h
        }
    } ()
}).call(this);

