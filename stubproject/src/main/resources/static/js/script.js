function uploadJqueryForm() {
    $('#result').html('');
    $("#form2").ajaxForm({
        success: function (data) {
            data = JSON.parse(data);
            $('#recordTable > tbody:last-child').append("<tr><td>'+data.id+'</td><td>'+data.field+'</td></tr><td><form id='form"+data.id+"' method='get' action='/image' target='_blank'  class='form-inline'><input type='hidden' value='"+data.id+"' name='item'/><button value='Get image' class='btn btn-primary'>Get image</button></form></td>");
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