function uploadJqueryForm() {
    $('#result').html('');
    $("#form2").ajaxForm({
        success: function (data) {
            data = JSON.parse(data);
            $('#recordTable > tbody:last-child').append('<tr><td style="">'+data.id+'</td><td style="">'+data.field+'</td></tr>');
            console.log(data);
        }
        , dataType: "text"
    }).submit();
    $("#form1").ajaxForm({
        success: function (data) {
            console.log(data);
        }
        , dataType: "text"
    }).submit();
    $( '#form2' ).each(function(){
        this.reset();
    });
    $( '#form1' ).each(function(){
        this.reset();
    });
}