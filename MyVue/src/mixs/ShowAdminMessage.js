import Operate from "@/mixs/Operate";

const adminMessage = {
    data() {
        return {
            //成功的
            SAVE_OK: 20001,
            DELETE_OK: 20002,
            UPDATE_OK: 20003,
            GET_OK: 20004,
            PASSWORD_OK: 20005,
            PASSWORD_OK_ADMIN: 20006,
            //用户存在
            USER_OK: 20007,
            //退出登陆
            Cancel_OK: 20008,
            REGISTER_OK: 20009,
            PAGE_OK: 20004,
            //失败的
            SAVE_ERR: 10001,
            DELETE_ERR: 10002,
            UPDATE_ERR: 10003,
            GET_ERR: 10004,
            PASSWORD_ERR: 10005,
            //用户不存
            USER_ERR: 10006,
            //登录凭证失效
            LOGIN_LAPSE: 10017,
            //异常处理
            SYSTEM_ERR: 10001,
            SYSTEM_TIMEOUT_ERR: 10002,
            SYSTEM_UNKNOW_ERR: 19999,
            BUSINESS_ERR: 11002,

            //上传成功
            UPLOAD_OK: 2005,
            UPLOAD_ERR: 10005,
        }
    },
    methods: {

        cancelLoginOk(this_) {
            this_.$message({
                type:"success",
                message:"退出登陆成功！"
            })
        },
        userErr(this_) {
            this_.$message({
                type: 'error',
                message: '用户名不存在！'
            });
        },
        loginOk(this_, ts) {
            this_.$message({
                type: "success",
                message: `登陆成功！欢迎${ts}大神！`
            })
        },
        passwordErr1(this_) {
            this_.$message({
                type: "error",
                message: "密码错误，请大神再试试喵~"
            })
        },
        passwordErr2(this_) {
            this_.$message({
                type: 'error',
                message: ' 两次密码不一致！'
            });
        },
        formDataErr(this_) {
            this_.$message({
                type: 'error',
                message: ' 请填写合法的数据！'
            });
        },

        registerErr(this_) {
            this_.$message({
                type: "error",
                message: `注册失败，用户名已存在`
            })
        },
        registerOK(this_, ms) {
            this_.$message({
                type: "success",
                message: `注册成功！欢迎${ms}大神！,点击登陆即可完成操作！`
            })
        },
        notNull(_this, mes) {
            _this.$message({
                type: 'error',
                message: mes + '不能为空！'
            });
        },
        internetErr(_this) {
            _this.$message({
                type: 'error',
                message: '网站不合规范，请重新输入！'
            });
        },
        ipErr(_this) {
            _this.$message({
                type: 'error',
                message: 'ip地址不合规范，请重新输入！'
            });
        },
        emailErr(_this) {
            _this.$message({
                type: 'error',
                message: '邮箱不合规范，请重新输入！'
            });
        },
        IllegalSearch(this_) {
            this_.$message({
                type: 'warning',
                message: '非法操作，请输入数据'
            });
        },
        IllegalDelete(this_) {
            this_.$message({
                type: 'warning',
                message: '非法操作，请选择数据'
            });
        },
        AddCommentOk(this_) {
            this_.$message({
                type: "success",
                message: "评论成功！"
            })
        }, AddCommentErr(this_) {
            this_.$message({
                type: "error",
                message: "评论失败！"
            })
        },
        AddOk(this_) {
            this_.$message({
                type: "success",
                message: "新增成功！"
            })
        }, AddErr(this_, errms) {
            this_.$message({
                type: "error",
                message: "新增失败！" + errms
            })
        }, updateOk(this_) {
            this_.$message({
                type: "success",
                message: "更新成功！"
            })
        },
        updateErr(this_) {
            this_.$message({
                type: "error",
                message: "更新失败！"
            })
        },
        deleteCancel(this_) {
            this_.$message({
                type: 'info',
                message: '已取消删除'
            });
        },
        deleteOk(this_) {
            this_.$message({
                type: 'success',
                message: '删除成功'
            });
        },
        deleteErr(this_) {
            this_.$message({
                type: 'error',
                message: '删除失败'
            });
        },
        deleteMs(resp,) {
            if (resp.data.code === this.DELETE_OK) {

                this.$message({
                    type: 'success',
                    message: '删除成功'
                });

            } else {
                this.$message({
                    type: 'error',
                    message: '删除失败'
                });
            }
        },
        showStatusMessage(resp, Op) {
            const code = Operate.data()
            switch (Op) {
                case code.ADD:
                    break
                case code.DELETE:
                    this.deleteMs(resp, Op)
                    break
                case code.UPDATE:
                    break
                case code.SEARCH:
                    break
            }
        },


    }
}
export default adminMessage
