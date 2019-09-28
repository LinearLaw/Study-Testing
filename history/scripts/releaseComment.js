/**
 * Created by yangh on 2016/09/21.
 */
var main = {};

(function ($) {
	main.init = function () {

		main.initEvent();
	}

	main.initEvent = function () {
		$(".comment i").on("click", function () {
			var stars = $(this).parents(".comment").find("i");
			var index = stars.index(this);
			stars.removeClass("fa-star").addClass("fa-star-o");
			for( var i = 0; i <= index; i++)
			{
				stars.eq(i).removeClass("fa-star-o").addClass("fa-star");
			}
		});

		$(".content").on("keyup", function () {
			var num = $(this).siblings().find(".contentNum");
			if($(this).val().length < 15)
			{
				num.text( 15 - $(this).val().length ).parent().css("visibility", "visible");
			}
			else
			{
				num.parent().css("visibility", "hidden");
			}
		});

		$(".submitBtn").on("click", function () {
			var evaluationNum = $(this).parents(".evaluation").find(".fa-star").length;
			var evaluationContent = $(this).parents(".evaluation").find(".content");
			if(!evaluationNum)
			{
				common.alert({
					content:"请评分！"
				});
			}
			else if(evaluationContent.val().length < 15)
			{
				common.alert({
					content:"评论内容字数不够！",
					ok		:	function () {
						evaluationContent.focus();
					}
				});
			}
			else
			{
				//提交动作
			}
		})
	}
})(jQuery);