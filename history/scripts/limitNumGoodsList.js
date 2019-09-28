/**
 * Created by yangh on 2016/09/07.
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
        if (this.y < main.max - 80) {
            $("#tip").hide();
            $("#loading").show();

            var html = "";
            for (var i = 0; i < 10; i++) {
                html +='<li>\
                    <a href="javascript:void (0)" class="goods">\
                    <div class="goodsImg">\
                    <img src="../../images/1_03.png" alt="">\
                    </div>\
                    <h4>BLISS CAKE幸福西饼</h4>\
                <p class="describe">价值148元大烧饼，便宜卖便宜卖</p>\
                <span class="integral">89积分<span class="left">剩余15减</span> </span>\
                    </a>\
                    </li>';
            }
            $(".goodsList").append(html);
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
        myScroll = new IScroll("#wrapper", {probeType: 3, mouseWheel: true, click: true, preventDefault: false});

        main.max = myScroll.maxScrollY;
        /*console.log(main.max);*/

        myScroll.on("scroll", move);
    }
})(jQuery);

main.init();

window.onload = function () {
    main.scroll();
};