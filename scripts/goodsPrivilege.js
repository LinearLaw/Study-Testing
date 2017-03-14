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
        $(".coupon-check").on("click", function()
        {
            $(".coupon-check").removeClass("checked");
            $(this).addClass("checked");
        })
    };

    /**
     * @func main.scroll()
     * @desc 配置iscroll插件
     */
    main.scroll = function () {
        myScroll = new IScroll(".order", {probeType: 3, mouseWheel: true, click: true, preventDefault: false});

        main.max = myScroll.maxScrollY;
        /*console.log(main.max);*/

    }
})(jQuery);

main.init();

window.onload = function () {
    main.scroll();
};