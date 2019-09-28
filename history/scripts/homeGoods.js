/**
 * Created by yangh on 2016/09/03.
 */
var main = {};
var myScroll;
(function ($) {
    main.init = function () {
        document.addEventListener('touchmove', function (e) {
            e.preventDefault();
        }, false);
    };

    /**
     * @func move()
     * @desc 监听滚动条的位置，异步加载列表
     */
    function move() {
        if (this.y <= main.max) {
            $("#tip").hide();
            $("#loading").show();

            var html = "";
            for (var i = 0; i < 10; i++) {
                html +='<li>\
                    <a href="javascript:void(0)">\
                    <div class="goods-pic">\
                    <img src="../../images/18.png">\
                    </div>\
                    <h3>海底捞</h3>\
                    <div class="score">\
                    <span class="star fa fa-star" ></span>\
                    <span class="star fa fa-star" ></span>\
                    <span class="star fa fa-star" ></span>\
                    <span class="star fa fa-star-half-o" ></span>\
                    <span class="star fa fa-star-o"></span>\
                    <span class="num">3.5</span>\
                    </div>\
                    <div class="price">￥88元</div>\
                    </a>\
                    </li>';
            }
            $(".goods").append(html);
            this.refresh();
            main.max = this.maxScrollY;
            console.log(main.max);

            $("#loading").hide();
            $("#tip").show();
        }
    }

    /**
     * @func main.scroll()
     * @desc 配置iscroll插件
     */
    main.scroll = function () {
        myScroll = new IScroll(".detail", {probeType: 3, mouseWheel: true, click: true, preventDefault: false});

        main.max = myScroll.maxScrollY;
        /*console.log(main.max);*/

        myScroll.on("scroll", move);
    }
})(jQuery);

main.init();

window.onload = function () {
    main.scroll();
};