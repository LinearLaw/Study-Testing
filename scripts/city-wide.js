/**
 * Created by yangh on 2016/09/14.
 */
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
				html +='<li class="evaluation">\
					<div class="img"><img src="../../images/生活圈/2_06.png" alt=""></div>\
					<div class="nickname">黑色曼陀罗</div>\
					<div class="date">2015年6月11日 11：35</div>\
				<p class="content">老师讲的很仔细，学到不少，非常不错！</p>\
				</li>';
			}
			$(".evaluationList").append(html);
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
		myScroll = new IScroll("#wrapper", {probeType: 3, mouseWheel: true, click: true, preventDefault: false});

		main.max = myScroll.maxScrollY;
		/*console.log(main.max);*/

		myScroll.on("scroll", move);
	}
})(jQuery);

main.init();

window.onload = function () {
	main.scroll();
};