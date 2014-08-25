/**
 * Created by swingc on 8/1/2014.
 */
/**/
(function(){
    moveOption();
    $("#group-by").click(function(){
        $.getJSON("group.json",{name:$(".sorting_1").val()},function(result){
            if(result.group == "1"){

            }
        })
    });
    function moveOption(){
        $("#attendant").dblclick(function(){
            $("#attendant option:selected").prependTo("#others");
        })
        $("#others").dblclick(function(){
            $("#others option:selected").prependTo("#attendant");
        })
        $("#btn-right").click(function (){
            $("#attendant option:selected").prependTo("#others");
        });
        $("#btn-all-right").click(function (){
            $("#attendant option").prependTo("#others");
        });
        $("#btn-left").click(function (){
            $("#others option:selected").prependTo("#attendant");
        });
        $("#btn-all-left").click(function (){
            $("#others option").prependTo("#attendant");
        });
    }

})(jQuery);