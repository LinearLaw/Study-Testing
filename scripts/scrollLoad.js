/**
 * @desc 上滑加载插件
 * @author qiuyu
 * @param cfg {object} 参数
 * @param cfg.container {string} 滑动的容器
 * @param cfg.upLength {number} 触发上滑加载的距离
 * @param cfg.downLength {number} 触发下拉加载的距离
 * @param cfg.funcRef {boolean} 是否有下拉刷新功能
 * @param cfg.onLoadMore {function} 触发上滑加载的回调函数
 * @param cfg.onRefresh {function} 触发下拉加载的回调函数
 */
var scrollLoad = function (cfg) {
    var self = this;

    self.container = cfg.container;
    self.upLength = cfg.upLength || 50;
    self.downLength = cfg.downLength || 50;
    self.funcRef = cfg.funcRef;
    self.canLoad = true;

    self.onLoadMore = cfg.onLoadMore || function () {
        };
    self.onRefresh = cfg.onRefresh || function () {
        };

    self.con = $(self.container).find(".scrollLoadCon");

    self.init();
};

(function () {
    "use strict";

    scrollLoad.prototype.init = function () {
        var self = this;

        self.con.css({
            "padding-bottom": "40px"
        });

        self.con.append('<div class="loadTip1">上滑加载更多</div>');
        self.con.append('<div class="loadTip2"><span class="loadT1"></span><span class="loadT2"></span><span class="loadT3"></span></div>');

        //配置IScroll
        self.scroll = new IScroll(self.container, {
            probeType: 3,
            mouseWheel: true,
            click: true,
            preventDefault: false
        });

        self.max = self.scroll.maxScrollY;

        self.scroll.on("scroll", function () {
            self.onMove();
        });
    };

    /**
     * @desc 内容滑动时触发
     * @func scrollLoad.onMove()
     */
    scrollLoad.prototype.onMove = function () {
        var self = this;

        //判断是否可以上滑加载
        if (self.canLoad) {
            if (self.scroll.y < self.max - self.upLength) {
                self.canLoad = false;

                //显示提示
                self.con.find(".loadTip2").show();
                self.con.find(".loadTip1").hide();

                self.onLoadMore();
            }
        }


    };

    /**
     * @desc 刷新插件，获取滚动高度
     * @func scrollLoad.refresh()
     */
    scrollLoad.prototype.refresh = function () {
        var self = this;

        self.con.find(".loadTip1").show();
        self.con.find(".loadTip2").hide();

        self.scroll.refresh();
        self.max = self.scroll.maxScrollY;
    }
})();
