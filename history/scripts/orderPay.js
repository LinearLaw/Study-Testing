/**
 * Created by yangh on 2016/09/03.
 */
var main = {};
var myScroll;
(function ($) {
    main.init = function () {

        main.initEvent();
    };

    main.initEvent = function() {

        $(".getCoupon").click(function(){
            $(".coupon"). css("display","block");
        })
        $(".confirm").click(function(){
            $(".coupon"). css("display","none");
        })

        $(".plus").on("click", function () {
            var minus = $(this).siblings(".minus");
            var input = $(this).siblings("input");
            if ($(this).hasClass("enable")) {
                input.val(Number(input.val()) + 1);
                minus.addClass("enable");
            }
        });
        $(".minus").on("click", function () {
            var input = $(this).siblings("input");
            if ($(this).hasClass("enable")) {
                input.val(Number(input.val()) - 1);
                if (input.val() == 1)
                    $(this).removeClass("enable");
            }
        });

        $(".radio:first").on("click", function () {
            $(".coupon").show().next().show();
        })
        $(".mask").on("click", function () {
            $(".coupon").hide().next().hide();
        });
        $(".use-coupon").on("click", function () {
            var couponNum = $(this).siblings(".amount").find(".num").html();
            var prevCouponNum = $(".couponInfo").html().replace(/[^\d]/g,"");
            var originalPrice = Number($(".orderPrice").html().replace(/[^\d]/g,"")) + Number(prevCouponNum);
            $(".orderPrice").html("￥" + (originalPrice - couponNum));
            $(".couponInfo").html("（使用优惠券抵消" + couponNum + "元）");
            $(".mask").trigger("click");
        })

        $(".bind").on("click", function()
        {
            window.verificationCode	= function (DOM) {
                $(DOM).prop("disabled", true).css("backgroundColor", "#ccc").html("59s重新获取");
                clearInterval(verificationCodeTimer);
                var verificationCodeTimer = setInterval(function () {
                    if(Number($(DOM).html().replace(/[^\d]/g,"") > 0))
                        $(DOM).html((Number($(DOM).html().replace(/[^\d]/g,"")) - 1) + "s重新获取");
                    else
                    {
                        clearInterval(verificationCodeTimer);
                        $(DOM).prop("disabled", false).css("backgroundColor", "#0195ff").html("获取验证码");


                        //验证码回调


                    }
                },1000);
            }
            common.alert({
                width	:	"85%",
                title	:	"绑定新手机号",
                content	:	"<input type='text' maxlength='11' style='border-bottom: 1px solid #ccc' placeholder='输入新手机号' /> <button class='pullRight' style='background-color: #0195ff;color: white;	height: 20px;line-height: 20px;	padding: 0 3px; min-width: 82px' onclick='verificationCode(this);'>获取验证码</button><br/> <input type='text' maxlength='11' style='border-bottom: 1px solid #ccc; margin-left: -176px;margin-top: 10px;width: 60px;' placeholder='验证码' /> ",
                ok		:	function () {
                    //ok按钮回掉


                },
                dialog	:	true
            })
        });
    };

})(jQuery);

main.init();

window.onload = function () {
};