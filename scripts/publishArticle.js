/**
 * Created by qiu on 2016/4/12.
 */
var main={};

(function($){
    main.init=function(){
        var self=this;

        self.initPlupload();
    };

    main.initEvent=function(){

    };

    main.initPlupload=function(){
        uploadImg.plupload({
            container: "#uploadImg",
            url: "a",
            max: 4,
            callback:function(data){
                console.log(data);
            }
        });
    }

})(jQuery);

main.init();
