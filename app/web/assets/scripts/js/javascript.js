
var AllCards = ["cellar","chapel","moat","chancellor","village","woodcutter","workshop","bureaucrat","feast","gardens","militia","moneylender","remodel","smithy","spy","thief","throneRoom","councilRoom","festival","laboratory","library","market","mine","witch","adventurer"];



function createStandardShop(){

    for(var i = 0; i<10 ;i++){

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
    var player1 = $("#player1").val();
    var player2 = $("#player2").val();
    var player3 = $("#player3").val();
    
    var response = $.ajax({
        dataType: "text",
        url: "/DominionServlet",
        data: {
            operation:"init",
            player1:player1,
            player2:player2,
            player3:player3


        }


    });response.done(function (data) {

        console.log(data.toString);
        console.log("data dinges uitgevoerd van loop")
        
    });

   
   
console.log('sendinit')

}



function startTurn() {

    var response = $.ajax({
        dataType: "text",
        url: "/DominionServlet",
        data: {
            operation:"loop"


        }


    });response.done(function (data) {
        console.log(data);
        console.log("data dinges uitgevoerd van loop");



    });

    console.log('loop started in javascript')

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

function cardToField() {
    var image =  $(this).css("background-image");

   $(".playmat ul").append("<li></li>");
    $(".playmat ul li:last-of-type").css("background-image", image);
    console.log( $(this).css("background-image") )


}

function shopToHand() {

    var image =  $(this).css("background-image");

    $(".hand ul ").append("<li></li>");
    $(".hand ul li:last-of-type").css("background-image", image);

    console.log( $(this).css("background-image") )


    
}



$(document).ready(function () {
    var limit = 10;

    $('input.single-checkbox').on('change', function (evt) {
        if ($(this).siblings(':checked').length >= limit) {
            this.checked = false;
        }
    });


    createStandardShop();




    $("input[name='amount']").on("change", addInput);
    $('#playerForm').on('submit', sendInit);
    $('.cardChoice label').on("click", changecolorAndNumber);
    $("input[type='checkbox']").on('change', changeNumber);
    $('.deckSubmit').on('click', sendArray);
    
    $('.hand li').on("click", cardToField);
    $('.shopCards').on('click', shopToHand);

});