layui.use(['form', 'jquery'], function () {
    var $ = layui.jquery, form = layui.form, layer = layui.layer;
    //初始化验证码
    getVarCode();
    if (top.location != self.location) top.location = self.location;
    $('.bind-password').on('click', function () {
        if ($(this).hasClass('icon-5')) {
            $(this).removeClass('icon-5');
            $("input[name='password']").attr('type', 'password');
        } else {
            $(this).addClass('icon-5');
            $("input[name='password']").attr('type', 'text');
        }
    });

    $('.icon-nocheck').on('click', function () {
        if ($(this).hasClass('icon-check')) {
            $(this).removeClass('icon-check');
        } else {
            $(this).addClass('icon-check');
        }
    });

    // 进行登录操作
    form.on('submit(login)', function (data) {
        data = data.field;
        if (data.username.trim().length < 4 || data.username.trim().length >20) {
            layer.msg('用户名必须4~20位数字或字母');
            return false;
        }
        if (data.password.trim().length < 6 || data.password.trim().length >12) {
            layer.msg('密码必须6~12位数字或字母');
            return false;
        }
        if (data.varCode.trim().length < 4 || data.varCode.trim().lenght > 4) {
            layer.msg('验证码错误');
            return false;
        }
        layer.msg('登录成功', function () {
            window.location = 'index';
        });
        return false;
    });

    $("#varCodeImage").on('click', function () {
        getVarCode();
    });

    function getVarCode() {
        $.ajax({
            type: "GET",
            url: '/admin/getVarCode',
            contentType: "application/x-www-form-urlecoded",
            dataType: "json",
            success: function (res) {
                layer.close();
                if (res.code === 100000) {
                    // index.clearTabCache();
                    $("#varCodeImage").attr("src",res.data.varCode)
                    return false;
                } else {
                    layer.msg(res.msg, {icon: 2, anim: 6});
                }
            },
            error: function () {
                layer.msg("验证码异常！");
            }
        });
    }
});