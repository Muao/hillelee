$(document).ready(function(){
    var i=1;
    $("#add_row").click(function(){
        $('#addr'+i).html("<td>"+ (i+1) +"</td><td><input name='answer"+i+"' type='text' placeholder='Answer' class='form-control input-md' required='required' />"+
            "</td><td><div class='custom-controls-stacked d-block my-3'>"+
                "<label class='custom-control material-switch'>"+
                "<span class='material-switch-control-description'><spring:message code=\"quest.false\"/></span>"+
                "<input type='checkbox' name='check"+i+"' id='checkbox"+i+"' class='material-switch-control-input'>"+
                "<span class='material-switch-control-indicator'></span>"+
                "<span class='material-switch-control-description'><spring:message code=\"quest.true\"/></span>"+
                "</label>"+
                "</div></td>");

        $('#tab_logic').append('<tr id="addr'+(i+1)+'"></tr>');
        i++;
    });
    $("#delete_row").click(function(){
        if(i>1){
            $("#addr"+(i-1)).html('');
            i--;
        }
    });

});