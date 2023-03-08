import ShowAdminMessage from "@/mixs/ShowAdminMessage";
import showAdminMessage from "@/mixs/ShowAdminMessage";
export const crud_C = {
}
export const crud_D = {
    removeData(this_, arrIDs, ApiUrl) {
        this_.$confirm('此操作将永远删除条记录, 是否继续?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning',
            center: true
        }).then(() => {
            this_.$http.post(ApiUrl, arrIDs).then(resp => {
                if (resp.data.code === ShowAdminMessage.data().DELETE_OK) {
                    showAdminMessage.methods.deleteOk(this_);
                    this_.selectAll()
                }
                else {
                    ShowAdminMessage.methods.deleteErr(this_);
                }
            })
        }).catch(() => {
           ShowAdminMessage.methods.deleteCancel(this_);
        });
    }
}
export const crud_R = {
    selectPage(this_, data, ApiUrl) {
        var _this = this_;
        this_.$http.post(ApiUrl, data).then((resp) => {

            _this.tableData = resp.data.data.pageDataList;
            _this.totalCount = resp.data.data.total;
        })
    },
    selectAll(this_, ApiUrl) {
        var _this = this_;
        this_.$http.get(ApiUrl).then(( resp) => {

            _this.tableData = resp.data.data;
        })
    },
    selectAllBySearchData(this_, data,ApiUrl) {
        var _this = this_;
        this_.$http.post(ApiUrl,data).then(( resp) => {

            _this.tableData = resp.data.data;
        })
    }
}

export const crud_U = {
    updateData(this_, data, ApiUrl){
        let _this = this_;
        this_.$http.post(ApiUrl, data).then((resp) => {

            if (resp.data.code === ShowAdminMessage.data().UPDATE_OK) {
                showAdminMessage.methods.updateOk(this_);
                _this.selectAll()
            }
            else {
                ShowAdminMessage.methods.updateErr(this_);
            }
        })

    }
}
