var versionTableOps = function () {
    var httpclient = new httpClient();
    var baseUrl = _path +"/tms";
    var urls = {
        delete: baseUrl + "/delete"
    };

    var composeDeleteUrl = (id)=> {
        return urls.delete + "?id=" + id;
    };

    function addTableDeleteRecordListener() {
        var table = document.getElementById("file-list-table");
        var tbody = table.getElementsByTagName("tbody")[0];
        var tRows = tbody.getElementsByTagName("tr");
        if (tRows != null && tRows.length > 0) {
            for (var i = 0; i < tRows.length; i++) {
                let row = tRows[i];
                let fileId = row.getAttribute("data-fileId");
                let links = row.getElementsByTagName("a");
                let deleteLink = links[links.length - 1];
                console.log("add listener to anchor " + fileId);
                deleteLink.addEventListener("click", function (e) {
                    e.preventDefault();
                    console.log(composeDeleteUrl(fileId));
                    httpclient.delete(composeDeleteUrl(fileId), (status, body)=> {
                        console.log("delete file " + fileId + " " + status + " " + body);
                        location.reload();
                    });
                });
            }
        }
    }

    function addUploadBtnListener() {
        var btn = document.getElementById("file-upload-modal-btn");
        btn.addEventListener("click", ()=> {
            console.log("open btn clicked");
            fileUploadOps.cleanup();
        });
    }

    function init() {
        addUploadBtnListener();
        addTableDeleteRecordListener();
    }

    return {
        init: init
    };
}();

(function () {
    fileUploadOps.init();
    versionTableOps.init();
})();