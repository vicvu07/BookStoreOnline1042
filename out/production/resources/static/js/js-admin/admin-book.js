$(document).ready(function() {





    var dataBook = {};


    function readURL(input) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();
            reader.onload = function (e) {
                $('.book-main-image').attr('src', e.target.result);
            }
            reader.readAsDataURL(input.files[0]);
        }
    }
    $("#change-book-mainImage").change(function () {
        readURL(this);
    });

    $("#myForm").submit(function (e) {
        e.preventDefault();
        var formData = new FormData();
        NProgress.start();
        formData.append('file', $("change-book-mainImage")[0].files[0]);
        axios.post("/api/upload/upload-image", formData).then(function (res) {
            NProgress.done();
            if (res.data.success) {
                $("#book-main-image").val(res.data.link);
            }
            $("#myForm")[0].submit();

        }, function (err) {
            NProgress.done();
            $('#myForm')[0].submit();

        });

    });


    /*$("#change-book-mainImage").change(function() {
        readURL(this);
        var formData = new FormData();
        NProgress.start();
        formData.append('file', $("#change-book-mainImage")[0].files[0]);
        axios.post("/api/upload/upload-image", formData).then(function(res){
            NProgress.done();
            if(res.data.success) {
                $('.book-main-image').attr('src', res.data.link);
            }
        }, function(err){
            NProgress.done();
        });
    });*/



    $("#new-book").on("click", function () {
        dataBook = {};
        $('#input-book-name').val("");
        $('#input-book-desc').val("");
        $("#input-book-category").val("");
        $("#input-book-price").val("");
        $("#input-book-discount").val("");
        $("#input-book-publishedYear").val("");
        $('.book-main-image').attr('src', 'https://www.vietnamprintpack.com/images/default.jpg');

    });


    $(".edit-book").on("click", function () {
        var pdInfo = $(this).data("book");
        console.log(pdInfo);
        NProgress.start();
        axios.get("/api/book/detail/" + pdInfo).then(function(res){
            NProgress.done();
            if(res.data.success) {
                dataBook.id = res.data.data.id;
                $("#input-book-name").val(res.data.data.name);
                $("#input-book-desc").val(res.data.data.shortDesc);
                $("#input-book-category").val(res.data.data.categoryId);
                $("#input-book-price").val(res.data.data.price);
                $("#input-book-discount").val(res.data.data.discount);
                $("#input-book-publishedYear").val(res.data.data.publishedYear);
                if(res.data.data.mainImage != null) {
                    $('.book-main-image').attr('src', res.data.data.mainImage);
                }
            }else {
                console.log("ahihi");
            }
        }, function(err){
            NProgress.done();
        })
    });



    $(".btn-save-book").on("click", function () {
        if($("#input-book-name").val() === "" || $("#input-book-desc").val() === "" || $("#input-book-price").val()==="" || $("#input-book-discount").val()==="") {
            swal(
                'Lỗi',
                'Bạn cần phải điền tất cả chỗ trống',
                'error'
            );
            return;
        }


        dataBook.name = $('#input-book-name').val();
        dataBook.shortDesc = $('#input-book-desc').val();
        dataBook.categoryId = $("#input-book-category").val();
        dataBook.mainImage = $('.book-main-image').attr('src');
        dataBook.price = $("#input-book-price").val();
        dataBook.discount = $("#input-book-discount").val();
        dataBook.publishedYear = $("#input-book-publishedYear").val();
        NProgress.start();
        console.log(dataBook.id);
        var linkPost = "/api/book/create";
        if(dataBook.id) {
            linkPost = "/api/book/update/" + dataBook.id;
        }

        axios.post(linkPost, dataBook).then(function(res){
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
                'Some error when saving book',
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