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
					<div class="userInfo">\
					<div class="portrait">\
					<img src="../../images/nonentity.png" alt="">\
					</div>\
					<span class="username">g***9</span>\
					<span class="vip">\
					<img src="../../images/2_5.png" alt="">\
					</span><br>\
					<span class="score">\
					<span class="fa fa-star"></span>\
					<span class="fa fa-star"></span>\
					<span class="fa fa-star"></span>\
					<span class="fa fa-star"></span>\
					<span class="fa fa-star-o"></span>\
					</span>\
					<span class="date">2016-08-11</span>\
					</div>\
					<div class="detail">\
					<p class="content">广东风味的火锅，广东风味的火锅，广东风味的火锅，广东风味的火锅，广东风味的火锅，广东风味的火锅，广东风味的火锅，广东风味的火锅，广东风味的火锅，广东风味的火锅，广东风味的火锅，广东风味的火锅，广东风味的火锅，广东风味的火锅</p>\
				<div class="pics">\
					<div>\
					<img src="../../images/2_7.png" alt="">\
					</div>\
					<div>\
					<img src="../../images/2_7.png" alt="">\
					</div>\
					<div>\
					<img src="../../images/2_7.png" alt="">\
					</div>\
					</div>\
					</div>\
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