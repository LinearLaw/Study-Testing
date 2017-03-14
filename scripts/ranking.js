/**
 * Created by yangh on 2016/10/25.
 */
var main = {};
var myScroll;
(function ($) {
    main.init = function () {
        document.addEventListener('touchmove', function (e) {
            e.preventDefault();
        }, false);

        main.initPage();
    };

    /**
     * @func initPage()
     * @desc 页面初始化加载
     */
    main.initPage = function () {
        var wrapperW = $(".wrapper").offsetWidth
    }
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
                html +='<ul>\
                    <li>NO.1</li>\
                <li><div class="img"><img src="../images/2_8.png" alt=""></div></li>\
                    <li>蓝瘦香菇</li>\
                    <li>12354353</li>\
                    </ul>';
            }
            $(".listWrap .list").append(html);
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
        myScroll = new IScroll(".listWrap", {probeType: 3, mouseWheel: true, click: true, preventDefault: false});

        main.max = myScroll.maxScrollY;
        /*console.log(main.max);*/

        myScroll.on("scroll", move);
    }
})(jQuery);

main.init();

window.onload = function () {
    main.scroll();
};