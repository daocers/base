
function sub() {
    var ok = $.formValidator.pageIsValid('1');
    if (ok !== true) {
        return;
    }       
    document.forms["addForm"].submit();
}


function sub_update() {
    var ok = $.formValidator.pageIsValid('1');
    if (ok !== true) {
        return;
    }       
    document.forms["updateForm"].submit();
}