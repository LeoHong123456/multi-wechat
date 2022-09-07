function getFileItem() {
    $.ajax({
        url: "http://127.0.0.1:8500/getFileList",
        data: "",
        dataType: "json",
        type: "POST",
        timeout: 2000,
        headers: {"Content-type": "appliction/x-www-form-urlencoded"},
        success: function (data, code, xhr) {
            data.data.forEach((v, i) =>{
                console.log(v);
                console.log(i);
                createItem();
            });
        },
        error: function () {
            window.alert();
        },
    });
}

function createItem(){
    $(".wechat-item").append(
        `<li>
            <a href="javascript:;"><img className="wechat-logo" src="/web/images/wechat_logo.png" alt=""></a>
            <p className="price"></p>
            <p className="promotion_price"></p>
        </li>`
    );
}
