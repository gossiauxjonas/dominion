function definePlayerArray() {
    var playerArray = [$("#player1").val(), $("#player2").val()];

    if ($("#player3").val() != null) {
        playerArray.push($("#player3").val())
    }
    $('#playerForm').hide();

}

var counter = 10;


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
function changecolorAndNumber() {


    if( $('input[type="checkbox"]:checked' && (makeCounter() > 0))){
        this.style.color = "orange";

    }

    else if( $("input:checkbox:not(:checked)")){
        this.style.color("white");

    }



    }








function changeNumber() {

    var choiceLeft = makeCounter();
    if((choiceLeft) >=0) {
        $('.cardsLeft span').empty().append(choiceLeft);
    }

}
function makeCounter(){


    var counter = $('input[type="checkbox"]:checked').length;
    var choiceLeft = 10 - counter;



    return choiceLeft;




}


$(document).ready(function () {

    $('#playerForm').on('submit', definePlayerArray);
    $("input[name='amount']").on("change", addInput);
    $('#playerForm').on('submit', sendInit);
    $('label').on("click", changecolorAndNumber);
    $("input[type='checkbox']").on('change', changeNumber);





    

});