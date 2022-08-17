$(function(){


    function sendAjax(){
        $.ajax({
            url: BASE_URL + "/admin/getVarCode",
            method: "get",
            dataType: 'json',
            success: function(response){
                if(response.code === 100000){
                    $('.varcode-img').attr('src', response.data.varCode);
                    $('.varCodeId').val(response.data.varCodeId);
                }else{
                    layer.msg(response.message,{icon:5})
                }
            },
            error: function(response){
                layer.msg(response.message);
                layer.msg(data.field.toString())
            }
        });
    }
})