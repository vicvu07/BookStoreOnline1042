$(document).ready(function() {

    $(".change-book-amount").change(function () {
        dataCartBook = {};
        dataCartBook.id = $(this).data("id");
        dataCartBook.amount = $(this).val();


        NProgress.start();

        var linkPost = "/api/cart-book/update";

        axios.post(linkPost, dataCartBook).then(function(res){
            NProgress.done();
            if(res.data.success) {
                location.reload();
            } else {
                swal(
                    'Fail',
                    res.data.message,
                    'error'
                ).then(function() {
                    location.reload();
                });
            }
        }, function(err){
            NProgress.done();
            swal(
                'Error',
                'Fail',
                'error'
            );
        });
    });
    $(".delete-cart-book").on("click",function(){
        var pdInfo = $(this).data("id");

        NProgress.start();
        var linkGet = "/api/cart-book/delete/"+pdInfo;
        axios.get(linkGet).then(function(res){
            NProgress.done();
            if(res.data.success) {
                swal(
                    'Success',
                    res.data.message,
                    'success'
                ).then(function() {
                    location.reload();
                });
            } else {
                swal(
                    'Fail',
                    res.data.message,
                    'error'
                );
            }
        }, function(err){
            NProgress.done();
            swal(
                'Error',
                'Fail',
                'error'
            );
        });
    })


});