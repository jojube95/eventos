function toggleLoadingSpinner(element){
    if (element.find(".spinner-border").length === 0){
        element.prop("disabled", true);
        element.append(" <span class=\"spinner-border spinner-border-sm\" role=\"status\" aria-hidden=\"true\"></span>");
    }
    else{
        element.prop("disabled", false);
        element.find(".spinner-border").remove();
    }
}

function waitForElm(selector) {
    return new Promise(resolve => {
        if (document.querySelector(selector)) {
            return resolve(document.querySelector(selector));
        }

        const observer = new MutationObserver(() => {
            if (document.querySelector(selector)) {
                resolve(document.querySelector(selector));
                observer.disconnect();
            }
        });

        observer.observe(document.body, {
            childList: true,
            subtree: true
        });
    });
}