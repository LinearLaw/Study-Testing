var uploadImg = {
    src:{}
};

(function ($) {
    /**
     * @func plupload
     * @desc 图片上传
     * @param cfg.container {string} 图片上传的容器
     * @param cfg.url {string} 服务器端的上传页面地址
     * @param cfg.max {number} 限制图片数量
     * @param cfg.callback {function} 回调函数，参数为服务器返回的图片地址
     */
    uploadImg.plupload = function (cfg) {
        //初始化上传组件html结构
        var con='<ul id="fileList"><li id="addImg"><div id="addImgBtn"></div></li></ul>';
        $(cfg.container).append(con);

        uploadImg.width=$("#addImg").width();
        $("#addImg").height(uploadImg.width);

        var uploader = new plupload.Uploader({
            browse_button: "addImgBtn", //触发文件选择对话框的按钮，为那个元素id
            container: "addImg",
            url: cfg.url, //服务器端的上传页面地址
            filters: {
                mime_types: [{title: "图片文件", extensions: "jpg,gif,png,JPG,jpeg,JPEG"}],
                prevent_duplicates: true //不允许选取重复文件
                //max_file_size : '2000kb'
            }
            /*resize: {
                width: 800,
                height: 800
            }*/
        });

        uploader.init();

        /**
         * @desc 绑定文件添加进队列事件
         *       添加文件时显示预览图
         */
        uploader.bind('FilesAdded', function (uploader, files) {
            plupload.each(files, function (file) {
                //超过数量时删除添加按钮
                if (uploader.files.length >= cfg.max){
                    $("#addImg").addClass("addImgHide");
                }

                //限制数量
                if (uploader.files.length > cfg.max) {
                    alert("超过数量");
                    uploader.removeFile(file);
                } else {
                    var html = '' +
                        '<li style="height:'+uploadImg.width+'px" id=' + file.id + '>' +
                        '<div class="delImg"></div>' +
                        '<button class="imgPercent">上传中</button>' +
                        '</li>';
                    $('#fileList').prepend(html);
                    previewImage(file, function (imgsrc) {
                        $('#' + file.id).css('background-image', 'url(' + imgsrc + ')');
                    });

                    //上传图片
                    uploader.start();
                }
            });
        });

        /**
         * @func previewImage
         * @desc 图片预览
         *       有关mOxie的介绍和说明请看：https://github.com/moxiecode/moxie/wiki/API
         * @param file plupload事件监听函数参数中的file对象,callback为预览图片准备完成的回调函数
         * @param callback callback传入的参数为预览图片的url
         */
        function previewImage(file, callback) {
            //确保文件是图片
            if (!file || !/image\//.test(file.type)) return;

            //gif使用FileReader进行预览,因为mOxie.Image只支持jpg和png
            if (file.type == 'image/gif') {
                var fr = new mOxie.FileReader();
                fr.onload = function () {
                    callback(fr.result);
                    fr.destroy();
                    fr = null;
                };
                fr.readAsDataURL(file.getSource());
            } else {
                var preloader = new mOxie.Image();
                preloader.onload = function () {
                    //先压缩一下要预览的图片,宽200，高200
                    //preloader.downsize(200, 200);
                    var imgsrc = preloader.type == 'image/jpeg' ? preloader.getAsDataURL('image/jpeg', 80) : preloader.getAsDataURL(); //得到图片src,实质为一个base64编码的数据
                    callback && callback(imgsrc);
                    preloader.destroy();
                    preloader = null;
                };
                preloader.load(file.getSource());
            }
        }

        /**
         * 删除按钮的显示或隐藏
         */
        $(document).on("click","#fileList>li",function(){
            $(this).find(".delImg").toggle();
        });

        /**
         * @desc 删除图片
         */
        $(document).on("click",".delImg",function(){
            var file=$(this).parent();
            for(var i=0;i<uploader.files.length;i++){
                if(uploader.files[i].id==file.attr("id")){
                    uploader.removeFile(uploader.files[i]);
                }
            }
            delete uploadImg.src[file.attr("id")];
            file.remove();
            $("#addImg").removeClass("addImgHide");
        });

        /**
         * @desc 上传过程，显示进度
         */
        uploader.bind('UploadProgress', function (uploader, file) {
            $("#" + file.id).find(".imgPercent").html(file.percent + "%");
        });

        /**
         * @desc 有图片上传完成时获取返回的信息
         */
        uploader.bind('FileUploaded', function (uploader, file, data) {
            if(data.status==200){
                $("#"+file.id).find(".imgPercent").hide();
                uploadImg.src[file.id]=data.response;
            }
        });

        /**
         * @desc 上传发生错误
         */
        uploader.bind('Error', function (uploader, errObject){
            $("#"+errObject.file.id).find(".imgPercent").html("失败!");
        });

        /**
         * @desc 所有图片上传完成时返回地址
         */
        uploader.bind('UploadComplete', function (){
            cfg.callback(uploadImg.src);
        });
    };
})(jQuery);