var fileUploadOps = function () {
    var httpclient = httpClient();
    var baseUrl = _path +"/tms";
    var urls = {
        listDirs: baseUrl + "/listdir",
        uploadFile: baseUrl + "/upload"
    };
    var viewBaseUrl = "http://120.27.233.57:8001";

    var round2 = num => Math.round(num * 100) / 100;
    var showElement = elem=>elem.style.visibility = "visible";
    var hideElement = elem=>elem.style.visibility = "hidden";
    var appendAttrVal = (elem, attrName, val)=> {
        let oldVal = elem.getAttribute(attrName);
        elem.setAttribute(attrName, oldVal + " " + val);
    };
    var removeAttrVal = (elem, attrName, val)=> {
        let oldVal = elem.getAttribute(attrName);
        let newVal;
        oldVal.split(/\S+/g).forEach(v=>
            newVal = newVal + (String.valueOf(v) == String.valueOf(val)) ? "" : val + " "
        ); // todo filter collect
        elem.setAttribute(attrName, newVal);
    };
    var newChildTo = function (parent, childTag, childVal, childText) {
        var child = document.createElement(childTag);
        child.value = childVal;
        child.textContent = childText;
        parent.appendChild(child);
    };

    var hideShowElement = function (element, toDisplay) {
        element.style.display = toDisplay;
    }

    var selectFileOps = (function () {
        var selectedFile = null;
        var units = ["B", "KB", "MB"];
        var fileInfoElem = document.getElementById("local-file-info");
        var pathElem = document.getElementById("local-file-path");
        var sizeElem = document.getElementById("local-file-size");
        var fileInput = document.getElementById("local-file-input");

        function init() {
            fileInput.addEventListener("change", function () {
                uploadOps.cleanup();
                var file = fileInput.files[0];
                select(file);
            });
        }

        function select(file) {
            selectedFile = file;
            let size = file.size;
            for (var i = 0; i < 2; i++) {
                let s = round2(size / 1024);
                if (s < 1) {
                    break;
                }
                size = s;
            }

            let unit = units[i];
            pathElem.textContent = file.name;
            sizeElem.textContent = size + unit;
            showElement(fileInfoElem);
        }

        function cleanup() {
            // console.log("file select cleanup");
            hideElement(fileInfoElem);
            selectedFile = null;
            pathElem.textContent = "";
            sizeElem.textContent = "";
        }

        var getFile = ()=> {
            return selectedFile;
        };

        return {
            init: init,
            getFile: getFile,
            cleanup: cleanup
        }
    })();

    var selectDirOps = (function () {
        var inputDir, selectDir, defaultDir;
        var infoArea = document.getElementById("server-dir-feedback");
        var info = document.getElementById("server-dir-info");
        var viewLink = document.getElementById("server-dir-link");
        var dirSelector = document.getElementById("server-dir-sel");
        var newDirInputs = document.getElementById("new-server-dir");
        var newDir1 = newDirInputs.getElementsByTagName("input")[0];
        var newDir2 = newDirInputs.getElementsByTagName("input")[1];
        var newDir3 = newDirInputs.getElementsByTagName("input")[2];
        var newDirRegex = /^[a-zA-Z0-9-_]{3,20}$/i;

        var getSelectDir = ()=> dirSelector.options[dirSelector.selectedIndex].text;

        function bindSelectDirListener() {
            dirSelector.addEventListener("change", function () {
                uploadOps.cleanup();
                selectDir = getSelectDir();
                updateDir(selectDir);
            });
        }

        function bindNewDirInputListener(input) {
            input.addEventListener("keyup", event=> {
                uploadOps.cleanup();
                inputDir = getInputDir();
                updateDir(inputDir);
            }, false);
        }

        // todo
        function getInputDir() {
            let newDirStr = "/";
            if (newDirRegex.test(newDir1.value)) {
                newDirStr = newDirStr + newDir1.value;
            } else {
                return newDirStr;
            }
            if (newDirRegex.test(newDir2.value)) {
                newDirStr = newDirStr + "/" + newDir2.value;
            } else {
                return newDirStr;
            }
            if (newDirRegex.test(newDir3.value)) {
                newDirStr = newDirStr + "/" + newDir3.value;
            }
            return newDirStr;
        }

        function init() {
            // init
            bindSelectDirListener();
            bindNewDirInputListener(newDir1);
            bindNewDirInputListener(newDir2);
            bindNewDirInputListener(newDir3);

            httpclient.get(urls.listDirs, function (status, body) {
                console.log(status);
                if (status == 200) {
                    console.log(body);
                    let dirs = JSON.parse(body);
                    if (dirs.length > 0) {
                        dirs.forEach((item, id) =>
                            newChildTo(dirSelector, "option", id, item));
                        defaultDir = dirs[0];
                        // updateDir();
                    }
                }
            });
        };

        var checkDir = (dir)=>!(dir == null || dir.trim().length == 0);
        var updateDir = (dir)=> {
            if (checkDir(dir)) {
                info.textContent = dir;
                updateDirData(dir);
                viewLink.setAttribute("href", viewBaseUrl + dir);
                showElement(infoArea);
            } else {
                console.log("updateDir invalid: " + dir);
            }
        }
        var cleanup = ()=> {
            // console.log("dir select cleanup");
            hideElement(infoArea);
            newDir1.value = "";
            newDir2.value = "";
            newDir3.value = "";
            inputDir = null;
            selectDir = null;
            dirSelector.selectedIndex = -1;
            info.textContent = "";
            viewLink.removeAttribute("href");
            removeDirData();
        }
        var getDirData = ()=> {
            return info.getAttribute("data-dir");
        };
        var updateDirData = dir=> {
            info.setAttribute("data-dir", dir);
        };
        var removeDirData = () => {
            info.removeAttribute("data-dir");
        };

        return {
            init: init,
            getDir: getDirData,
            cleanup: cleanup
        };
    })();

    //  var remarkOps = function () {
    //    var remarkRegex = /^[0-9a-zA-Z\u4e00-\u9eff]{4,50}$/i;
    //    var remarkArea = document.getElementById("file-remark");
    //   var info = document.getElementById("file-remark-info");
    // }();

    var uploadOps = function () {
        var uploadInfo = document.getElementById("upload-info");
        var uploadBtn = document.getElementById("upload-btn");
        var cancelBtn = document.getElementById("cancel-upload-btn");
        var uploadState = document.getElementById("upload-state");

        function init() {
            uploadBtn.addEventListener("click", uploadBtnClicked);
            cancelBtn.addEventListener("click", ()=> {
                // console.log(location);
                location.reload();
            });
        }

        function uploadBtnClicked() {
            var file = selectFileOps.getFile();
            console.log(file);
            var serverDir = selectDirOps.getDir();
            if (file == null) {
                showErrInfo("请选择文件");
            } else {
                if (serverDir == null || serverDir.trim().length == 0) {
                    showErrInfo("请选择文件夹");
                } else {
                    uploadFile(file, serverDir);
                }
            }
        }

        function uploadFile(file, serverDir) {
            var msg = "上传文件 " + file.name + " 至 " + serverDir + " ...";
            showInfo(msg);
            var fd = new FormData();
            fd.append("dir", serverDir);
            fd.append('file', file);
            $.ajax({
                url: urls.uploadFile,
                type: 'POST',
                data: fd,
                processData: false,
                contentType: false
            }).done(function (data) {
                showSucc("完成!");
            })
                .fail(function (xhr, status, err) {
                    showErr("失败: " + status + " " + xhr.status + " " + xhr.responseText);
                })
                .always(function (xhr, status, err) {
                    console.log(msg + " ajax closed");
                });
        }

        var levels = {
            info: "NONE",
            succ: "OK",
            err: "ERROR"
        }
        var clearMsg = ()=> {
            // console.log("upload cleanup");
            uploadInfo.textContent = "";
            uploadState.textContent = "";
        };
        var showInfo = (msg)=> {
            showMsg(uploadInfo, msg, levels.info)
        };
        var showErrInfo = (msg)=> {
            showMsg(uploadInfo, msg, levels.err)
        };
        var showSucc = (msg)=> {
            showMsg(uploadState, msg, levels.succ)
        };
        var showErr = (msg)=> {
            showMsg(uploadState, msg, levels.err)
        };
        var showMsg = (element, msg, level)=> {
            element.textContent = msg;
            element.setAttribute("data-level", level);
        }

        return {
            init: init,
            cleanup: clearMsg
        }
    }();

    var init = function () {
        console.log("init");
        var form = document.getElementById("file-upload-form");
        form.addEventListener("submit", event=>event.preventDefault());

        selectFileOps.init();
        selectDirOps.init();
        uploadOps.init();
    };

    var cleanup = function () {
        console.log("fileUploadOps cleanup");
        selectFileOps.cleanup();
        selectDirOps.cleanup();
        uploadOps.cleanup();
    };

    return {
        init: init,
        cleanup: cleanup
    };
}();


