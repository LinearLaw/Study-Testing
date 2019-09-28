$(function () {
    $("#title").keyup(function () {
        var area = $(this);
        var max = parseInt(area.attr("maxlength"), 10);
        if (max > 0) {
            if (area.val().length > max) {
                area.val(area.val().substr(0, max));
            }

        }
    });
    $("#title").blur(function () {
        var area = $(this);
        var max = parseInt(area.attr("maxlength"), 10);
        if (max > 0) {
            if (area.val().length > max) {
                area.val(area.val().substr(0, max));
            }

        }
    });
    $("#content").keyup(function () {
        var area = $(this);
        var max = parseInt(area.attr("maxlength"), 10);
        if (max > 0) {
            if (area.val().length > max) {
                area.val(area.val().substr(0, max));
            }
            else{

            }
        }
    });
    $("#content").blur(function () {
        var area = $(this);
        var max = parseInt(area.attr("maxlength"), 10);
        if (max > 0) {
            if (area.val().length > max) {
                area.val(area.val().substr(0, max));
            }
            else{

            }
        }
    });

});
