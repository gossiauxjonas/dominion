
var AllCards = ["cellar","chapel","moat","chancellor","village","woodcutter","workshop","bureaucrat","feast","gardens","militia","moneylender","remodel","smithy","spy","thief","throneRoom","councilRoom","festival","laboratory","library","market","mine","witch","adventurer"];

function definePlayerArray() {
    var playerArray = [$("#player1").val(), $("#player2").val()];

    if ($("#player3").val() != null) {
        playerArray.push($("#player3").val())
    }
    $('#playerForm').hide();

}

function createStandardShop(){

    for(var i = 0; i<9 ;i++){

        $(".shopCards").eq(i).css("background-image",'url("assets/media/images/Cards/' + AllCards[i]+'.jpg")');

        console.log(AllCards[i]);
    }


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


    var response = $.ajax({
        dataType: "text",
        url: "/DominionServlet",
        data: {
            operation: "init"


        }


    }).done(function (data) {
        
        
        
    });
   
console.log('sendinit')
}

function makeArrayFromForm() {
    var cardArray = [];
    console.log(cardArray);
    for (var i = 0; i < 24; i++) {
        if ($("label").eq(i).next().is(":checked")) {
            cardArray[i] = $("label").eq(i).next().val();
            console.log(i);

        }
    }
    return cardArray;


}
function changecolorAndNumber() {


    if (!$(this).next().is(":checked") && (makeCounter() > 0)) {
        this.style.color = "orange";


    }
    else {
        this.style.color = "white";
        console.log("white");
    }


}

function sendArray() {
    var cardArray = makeArrayFromForm();


    var response = $.ajax({
        dataType: "json",
        url: "/DominionServlet",
        type: 'GET',
        data: {"json": JSON.stringify(cardArray)},
        contentType: 'application/json',
        mimeType: 'application/json'


    });
    
    response.done(function () {


    })

}


function changeNumber() {

    var choiceLeft = makeCounter();
    if ((choiceLeft) >= 0) {

        $('.cardsLeft span').empty().append(choiceLeft);

    }

}
function makeCounter() {


    var counter = $('input[type="checkbox"]:checked').length;
    var choiceLeft = 10 - counter;
    return choiceLeft;


}




function startLoop() {

    var response = $.ajax({
        dataType: "text",
        url: "/DominionServlet",
        data: {
            operation: "loop"


        }


    }).done(function (data) {



    });

}
    




$(document).ready(function () {
    var limit = 10;

    $('input.single-checkbox').on('change', function (evt) {
        if ($(this).siblings(':checked').length >= limit) {
            this.checked = false;
        }
    });


    createStandardShop();



    $('#playerForm').on('submit', definePlayerArray);
    $("input[name='amount']").on("change", addInput);
    $('#playerForm').on('submit', sendInit);
    $('.cardChoice label').on("click", changecolorAndNumber);
    $("input[type='checkbox']").on('change', changeNumber);
    $('.deckSubmit').on('click', sendArray);
    $('.playfield').on('load', startLoop);

});