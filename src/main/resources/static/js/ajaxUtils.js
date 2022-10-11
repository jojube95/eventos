$(document).ready(function() {
    ajaxAddCsrf();
});

function ajaxCall(type, url, params, body, success) {
    let endpointUrl = addParamsToUrl(url, params);

    $.ajax({
        type: type,
        contentType: "application/json; charset=utf-8",
        url: endpointUrl,
        ...(type === 'POST') && {data: body},
        ...(type === 'POST') && {dataType: 'json'},
        success: success,
        error: function (err) {
            alert(err);
        }
    });
}

function addParamsToUrl(url, params) {
    let endpointUrl = url;

    Object.entries(params).forEach((element, index) => {
        if (index === 0) {
            endpointUrl = endpointUrl.concat("?")
        }
        else{
            endpointUrl = endpointUrl.concat("&")
        }

        endpointUrl = endpointUrl.concat(element[0] + "=" + element[1])
    });

    return endpointUrl;
}

function ajaxAddCsrf() {
    let token = $("meta[name='_csrf']").attr("content");
    let header = $("meta[name='_csrf_header']").attr("content");
    $(document).ajaxSend(function (e, xhr) {
        xhr.setRequestHeader(header, token);
    });
}
