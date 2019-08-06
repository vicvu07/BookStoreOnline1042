$(document).ready(function() {





    var dataCategory = {};


    $("#new-category").on("click", function () {
        dataCategory = {};
        $('#input-category-name').val("");
        $('#input-category-desc').val("");

    });


    $(".edit-category").on("click", function () {
        var pdInfo = $(this).data("category");
        console.log(pdInfo);
        NProgress.start();
        axios.get("/api/category/detail/" + pdInfo).then(function(res){
            NProgress.done();
            if(res.data.success) {
                dataCategory.id = res.data.data.id;
                $("#input-category-name").val(res.data.data.name);
                $("#input-category-desc").val(res.data.data.shortDesc);

            }else {
                console.log("ahihi");
            }
        }, function(err){
            NProgress.done();
        })
    });



    $(".btn-save-category").on("click", function () {
        if($("#input-category-name").val() === "" || $("#input-category-desc").val() === "" ) {
            swal(
                'Lỗi',
                'Bạn cần phải điền tất cả chỗ trống',
                'error'
            );
            return;
        }


        dataCategory.name = $('#input-category-name').val();
        dataCategory.shortDesc = $('#input-category-desc').val();
        NProgress.start();
        console.log(dataCategory.id);
        var linkPost = "/api/category/create";
        if(dataCategory.id) {
            linkPost = "/api/category/update/" + dataCategory.id;
        }

        axios.post(linkPost, dataCategory).then(function(res){
            NProgress.done();
            if(res.data.success) {
                swal(
                    'Good job!',
                    res.data.message,
                    'success'
                ).then(function() {
                    location.reload();
                });
            } else {
                swal(
                    'Error',
                    res.data.message,
                    'error'
                );
            }
        }, function(err){
            NProgress.done();
            swal(
                'Error',
                'Some error when saving category',
                'error'
            );
        })
    });


    /*_______________The beginning of Show less& Show more for Text Area___________________*/
    var showChar = 50;  // How many characters are shown by default
    var ellipsestext = "...";
    var moretext = "Xem thêm >";
    var lesstext = "Thu gọn";


    $('.more').each(function() {
        var content = $(this).html();

        if(content.length > showChar) {

            var c = content.substr(0, showChar);
            var h = content.substr(showChar, content.length - showChar);

            var html = c + '<span class="moreellipses">' + ellipsestext+ '&nbsp;</span><span class="morecontent"><span>' + h + '</span>&nbsp;&nbsp;<a href="" class="morelink">' + moretext + '</a></span>';

            $(this).html(html);
        }

    });

    $(".morelink").click(function(){
        if($(this).hasClass("less")) {
            $(this).removeClass("less");
            $(this).html(moretext);
        } else {
            $(this).addClass("less");
            $(this).html(lesstext);
        }
        $(this).parent().prev().toggle();
        $(this).prev().toggle();
        return false;
    });
    /*_______________End show less& Show more for Text Area___________________*/



});