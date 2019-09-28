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
                html += '<li><a href="javascript:void(0)"><div class="detailsImg"><img src="../../images/1_40.png"></div><h3>分级基金A的"黄金坑" 低风险却未必低收益。</h3><p>截止2月16日，成交较活跃的永续A基金中，隐收益率位居前列的基金有</p><div class="num"><span class="eye fa fa-eye"><span class="price">1250</span></span></div></a></li>'
            }
            $(".details").append(html);
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
        myScroll = new IScroll(".wrapper", {probeType: 3, mouseWheel: true, click: true, preventDefault: false});

        main.max = myScroll.maxScrollY;
        console.log(main.max);

        myScroll.on("scroll", move);
    }
})(jQuery);

main.init();

window.onload = function () {
    main.scroll();
};
