function definePlayerArray() {
    var playerArray = [$("#player1").val(), $("#player2").val()];

    if ($("#player3").val() != null) {
        playerArray.push($("#player3").val())
    }
    $('#playerForm').hide();

}


function addInput() {

    if ($(this).val() == "three") {

        $(".extraPlayer").show();

    }
    else {
        $(".extraPlayer").hide();

    }

}


function sendInit() {
    var operation = "init";

    var response = $.ajax({
        dataType: "text",
        url: "/DominionServlet",
        data: {
            operation: "init"


        }



    });
    response.done(function () {
        
    })

}
function changecolor() {
    if(this.style.color ==  "white"){
        this.style.color = "orange";

    }
    else{
        this.style.color = "white"
    }


}


$(document).ready(function () {
    $('#playerForm').on('submit', definePlayerArray);
    $("input[name='amount']").on("change", addInput);
    $('#playerForm').on('submit', sendInit);
    $('label ').on("click" , changecolor)
    

});