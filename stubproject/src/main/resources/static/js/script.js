function uploadJqueryForm() {
    $('#result').html('');
    $("#form2").ajaxForm({
        success: function (data) {
            $('#result').html(data);
        }
        , dataType: "text"
    }).submit();
}