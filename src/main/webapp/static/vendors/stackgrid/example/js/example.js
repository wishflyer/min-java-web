(function() {
    var n, t, i, e, o, u, r, c, l, d, s;
    for (s = new Stackgrid, window.onload = function() {
        s.config.columnWidth = 220,
        s.config.gutter = 20,
        s.config.isFluid = !1,
        s.config.layout = "optimized",
        o.update(),
        s.config.moveItem = function(n, t, i, e) {
            return jQuery.Velocity(n, {
                left: t,
                top: i,
                complete: e,
                duration: 200,
                queue: !1
            })
        },
        s.config.scaleContainer = function(n, t, i, e) {
            return jQuery.Velocity(n, "stop"),
            jQuery.Velocity(n, {
                height: i,
                width: t,
                complete: e,
                duration: 200,
                queue: !1
            })
        },
        s.initialize(".satckGridWall-grid-container", ".satckGridWall-grid-item")
    },
    o = {
        $container: void 0,
        $items: void 0,
        columnWidth: 220,
        gutter: 20,
        isFluid: !1,
        layout: "optimized"
    },
    o.update = function() {
        var n, t, i, e;
        for (this.$container = document.querySelector(".satckGridWall-grid-container"),
         this.$items = document.querySelectorAll(".satckGridWall-grid-item"),
          e = this.$items, t = 0, i = e.length; i > t; t++) n = e[t],
            $(n).click(this.removeItem)
        /*n.addEventListener("click", this.removeItem)*/
    },
    window.uu = o.update,
    o.removeItem = function() {
        jQuery.Velocity(this, {
            scale: 0
        },
        300,
        function(n) {
            return function() {
                return o.$container.removeChild(n),
                s.reset(),
                s.restack()
            }
        } (this))
    },
    o.append = function(n) {
        var t;
        t = document.createElement("div"),
        t.setAttribute("class", "satckGridWall-grid-item"),
        t.innerHTML = '<img src="' + n + '" alt="">',

        onimgload(n,
            
            function(n) {
                return function() {

                    return n.$container.appendChild(t),
                        
                    n.update(),
                        
                    jQuery.Velocity(t, {
                        scale: 0
                    },
                    1,
                    function() {
                        return s.append(t)
                    }),
                    jQuery.Velocity(t, {
                        scale: 1
                    },
                    200)
                }
            } (this)

        )
    },


    

    t = $(".control-button"), r = 0, l = t.length; l > r; r++) n = t[r],

    
    $(n).click( function(n) {
        return n.preventDefault()
    });

    /*n.addEventListener("click",
    function(n) {
        return n.preventDefault()
    });*/
    for (e = ["one", "two", "three", "four", "five", "six", "seven", "eight", "nine"], i = {},
    c = 0, d = e.length; d > c; c++) u = e[c],
    //i[u] = $(".control-button.-" + u);
    i[u] = document.querySelector(".control-button.-" + u);

    i.one.onclick = function() {
        return o.append(window.dd.vendorsPath+"stackgrid/example/"+"img/short.jpg")
    },
    i.two.onclick = function() {
        return o.append(window.dd.vendorsPath+"stackgrid/example/"+"img/medium.jpg")
    },
    i.three.onclick = function() {
        return o.append(window.dd.vendorsPath+"stackgrid/example/"+"img/tall.jpg")
    },
    i.four.onclick = function() {
        20 === o.gutter ? (this.innerHTML = "Gutter - 40", o.gutter = 40, s.config.gutter = 40) : 40 === o.gutter ? (this.innerHTML = "Gutter - 0", o.gutter = 0, s.config.gutter = 0) : (this.innerHTML = "Gutter - 20", o.gutter = 20, s.config.gutter = 20),
        s.restack()
    },
    i.five.onclick = function() {
        "ordinal" === o.layout ? (this.innerHTML = "Layout - optimized", o.layout = "optimized", s.config.layout = "optimized") : (this.innerHTML = "Layout - ordinal", o.layout = "ordinal", s.config.layout = "ordinal"),
        s.restack()
    },
    i.six.onclick = function() {
        o.isFluid ? (this.innerHTML = "isFluid - false", o.isFluid = !1, s.config.isFluid = !1) : (this.innerHTML = "isFluid - true", o.isFluid = !0, s.config.isFluid = !0),
        s.restack()
    },
    i.seven.onclick = function() {
        3 === o.numberOfColumns ? (this.innerHTML = "numberOfColumns - 4", o.numberOfColumns = 4, s.config.numberOfColumns = 4) : (this.innerHTML = "numberOfColumns - 3", o.numberOfColumns = 3, s.config.numberOfColumns = 3),
        s.restack()
    },
    i.eight.onclick = function() {
        320 === o.columnWidth ? (this.innerHTML = "columnWidth - 220", o.columnWidth = 220, s.config.columnWidth = 220) : 220 === o.columnWidth ? (this.innerHTML = "columnWidth - 120", o.columnWidth = 120, s.config.columnWidth = 120) : (this.innerHTML = "columnWidth - 320", o.columnWidth = 320, s.config.columnWidth = 320),
        s.reset()
    },
    i.nine.onclick = function() {
        var n, t, i, e;
        for (e = o.$items, t = 0, i = e.length; i > t; t++) n = e[t],
        o.$container.removeChild(n);
        s.reset()
    }
}).call(this);