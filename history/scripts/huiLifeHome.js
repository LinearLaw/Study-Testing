/**
 * Created by yangh on 2016/09/04.
 */
var main = {};
var myScroll;
(function ($) {
    var canLoad = true;
    main.init = function () {

        main.initEvent();
    };

    main.initEvent = function() {
        $(".pay-select > div").on("click", function()
        {
            $(".pay-select > div").removeClass("checked");
            $(this).addClass("checked");
        })
    };

    // /**
    //  * @func move()
    //  * @desc 监听滚动条的位置，异步加载列表
    //  */
    function move() {
        if (!canLoad) {
            return;
        }

        if (myScroll.y < main.max - 50) {
            canLoad = false;

            var html = "";
            for (var i = 0; i < 3; i++) {
                html += '<li><a href="javascript:void(0);"><div class="fImg"><img src="../../images/food.png" alt=""></div><h3>美食名称</h3><p>店铺地址店铺地址店铺地址</p><p><span>￥252.00</span><span class="count">已售567678</span></p></a></li>';
            }
            $("#foodList").append(html);
            myScroll.refresh();
            main.max = myScroll.maxScrollY;
            canLoad = true;
            console.log(main.max);
        }
    }

    /**
     * @func main.scroll()
     * @desc 配置iscroll插件
     */
    main.scroll = function () {
        myScroll = new IScroll(".wrapper", {
            probeType: 3,
            mouseWheel: true,
            click: true,
            preventDefault: false
        });

        main.max = myScroll.maxScrollY;
        console.log(main.max);

        myScroll.on("scroll", move);
    };
})(jQuery);

main.init();

window.onload = function () {
    main.scroll();
};