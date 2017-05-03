var httpClient = function() {
  function request(method, url, data, callback) {
    var httpReq = new XMLHttpRequest();
    httpReq.onreadystatechange = function() {
      if (httpReq.readyState == XMLHttpRequest.DONE ) {
        callback(httpReq.status, httpReq.responseText);
      }
    }
    httpReq.open(method, url, true);
    httpReq.send(data);
  };

  function get(url, callback) {
    request("GET", url, null, callback);
  }

  function del(url, callback){
    request("DELETE", url, null, callback);
  }

  function post(url, body, callback) {
    request("POST", url, body, callback);
  }

  return {
    get: get,
    post: post,
    delete: del
  };
};
