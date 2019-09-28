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

    /**
     * @func move()
     * @desc 监听滚动条的位置，异步加载列表
     */
    function move() {
        // if (!canLoad) {
        //     return;
        // }

        if (myScroll.y < main.max - 50) {
            // canLoad = false;

            var html = "";
            for (var i = 0; i < 10; i++) {
                html += '<li>'+
                    '<h4>火把瘾火锅米线</h4>'+
                   '<a class="goods" href="javascript:;">'+
                    '<div class="goodsImg"><img src="../../images/1_03.png" style="height: 80px"></div>'+
                    '<h3>BLISS CAKE幸福西饼</h3>'+
                '<p class="address">[价值148元]芒果拿破仑1个，约8寸，方形，下</p>'+
                '<p  class="num">￥148.00元</p>'+
               '<p class="num1">￥128.00元(以满148减20)</p>'+
                '</a>'+
                '</li>';
            }
            $(".details").append(html);
            myScroll.refresh();
            main.max = myScroll.maxScrollY;
            // canLoad = true;
            console.log(main.max);
        }
    }

    /**
     * @func main.scroll()
     * @desc 配置iscroll插件
     */
    main.scroll = function () {
        myScroll = new IScroll("#list", {
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