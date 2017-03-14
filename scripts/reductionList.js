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
                html += '<li><a href="javascript:void(0);"><div class="imgBox"><img src="../../images/a_5.png" alt=""></div><h3>BLICSS SKEE</h3><p>本店购满500立减50</p><button>立即购买</button></a></li>';
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