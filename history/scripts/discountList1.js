/**
 * Created by yangh on 2016/09/04.
 */
var main = {};
var myScroll;
(function ($) {
    main.init = function () {
        document.addEventListener('touchmove', function (e) {
            e.preventDefault();
        }, false);

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
        // if (!canLoad) {
        //     return;
        // }

        if (myScroll.y < main.max - 50) {
            //canLoad = false;

            var html = "";
            for (var i = 0; i < 10; i++) {
                html += '<li><a href="javascript:void(0);"><div class="imgBox"><img src="../../images/a_3.png" alt=""></div><div class="intro">仅需66元飞科剃须刀土豪金爆款充电剃须刀</div><p><span class="presentPrice">￥55</span ><span class="originPrice">￥199</span></p><p>已售55件</p></a><a class="btn" href="javascript:void(0);">马上买</a></li>';
            }
            $("#produceList").append(html);
            myScroll.refresh();
            main.max = myScroll.maxScrollY;
            //canLoad = true;
            console.log(main.max);
        }
    }

    /**
     * @func main.scroll()
     * @desc 配置iscroll插件
     */
    main.scroll = function () {
        myScroll = new IScroll(".content", {
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