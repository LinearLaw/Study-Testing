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
				html +='<li>\
					<!--头像-->\
					<div class="log">\
					<div class="detailsMes">\
					<div class="detailsImg">\
					<img src="../../images/生活圈/2_03.png">\
					</div>\
					<div class="detailsCon">\
					<h3>苏群/sunqun</h3>\
					<p class="data">2016-3-23 15:21</p>\
				</div>\
				<a href="javascript:;" class="detailsRight ">\
					<span class=" fa fa-angle-right"></span>\
					</a>\
					</div>\
					<!--详情-->\
					<div class="details">\
					<h3>【官方公告】生意参谋针对统计周期内新老客户，新老买家</h3>\
				<p class="textDesc">亲爱的商友们：</p>\
				<p class="textDesc">大家好！为了给大家提供更精确的数据，生意参谋将于7月6日周计期内的新老访客，新老买家做了算法调整和优化！关于新老客户算法</p>\
				</div>\
				</div>\
				<div class="detailsMes1">\
					<ul class="imgs">\
					<li>\
					<a href="javascript:;">\
					<img src="../../images/生活圈/2_07.png">\
					</a>\
					</li>\
					<li>\
					<a href="javascript:;">\
					<img src="../../images/生活圈/2_07.png">\
					</a>\
					</li>\
					<li>\
					<a href="javascript:;">\
					<img src="../../images/生活圈/2_07.png">\
					</a>\
					</li>\
					</ul>\
					<div class="button">\
					<a href="javascript:;">\
					<span class="fa fa-commenting-o"></span>评论\
					</a>\
					<a href="javascript:;">\
					<span class="fa fa-thumbs-o-up"></span>点赞\
					</a>\
					</div>\
					</div>\
					</li>';
			}
			$(".conList").append(html);
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