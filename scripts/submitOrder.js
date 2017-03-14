/**
 * Created by yangh on 2016/09/18.
 */
var main = {};
var myScroll;
(function ($) {
	main.init = function () {
		main.initEvent();
	};

	main.initEvent = function() {
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

		$(".radio:first").on("click", function () {
			$(".coupon").show().next().show();
		})

		$(".mask").on("click", function () {
			$(".coupon").hide().next().hide();
		})
	};

})(jQuery);

main.init();
