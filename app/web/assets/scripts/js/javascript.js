/*
 * Created by Jonas Gossiaux.
 * */

var AllCards = ["garden", "smithy", "village", "festival", "market", "laboratory", "moat", "woodcutter", "chancellor", "adventurer", "council Room", "witch", "throne room", "chapel", "moneylender", "cellar", "workshop", "feast", "remodel", "library", "mine", "spy", "thief", "militia", "bureaucrat"];


function createStandardShop(shopCards) {

    for (var i = 0; i < 10; i++) {

        $(".shopCards").eq(i).css("background-image", 'url("assets/media/images/Cards/' + shopCards[i] + '.jpg")');

    }


}
function addInput() {

    if ($(this).val() == "three") {

        $(".extraPlayer").show();
        $('#player3').prop('required', true);


    }
    else {
        $(".extraPlayer").hide();
        $('#player3').empty().prop('required', false);


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
            operation: "init",
            player1: player1,
            player2: player2,
            player3: player3


        }


    });
    response.done(function (data) {


    });


}


function beginSetup() {

    var response = $.ajax({
        dataType: "text",
        url: "/DominionServlet",
        data: {
            operation: "beginSetup"


        }


    });
    response.done(function (data) {
        console.log(data);
        var globalObject = JSON.parse(data);
        var shopCards = globalObject.shopCards;
        createStandardShop(shopCards);
        startTurn();


    });


}


function sendArray() {
    var cardArray = makeArrayFromForm();


    var response = $.ajax({
        dataType: "json",
        url: "/DominionServlet",
        type: 'GET',
        data: {
            operation: "startingCards",
            json: JSON.stringify(cardArray)
        },
        contentType: 'application/json',
        mimeType: 'application/json'


    });

    response.done(function (data) {


    });


}

function startTurn() {

    var response = $.ajax({
        dataType: "text",
        url: "/DominionServlet",
        data: {
            operation: "startTurn"


        }


    });
    response.done(function (data) {
        var playerObject = JSON.parse(data);
        $('.hand ul').empty();
        $('.playmat ul').empty();
        for (var i = 0; i < playerObject.hand.length; i++) {
            addToHand(playerObject.hand[i]);

        }


        changeCurrentName(playerObject.playerName);
        setValues(playerObject.actions, playerObject.buys, 0);
        if (playerObject.actionsInHand == 0) {
            setPhase("buy");
        }


    });


}


function setPhase(phase) {
    $(".phase span").empty().append(phase);
}


function setValues(actions, buys, coins) {
    $(".actions span").empty().append(actions);
    $(".buys span").empty().append(buys);
    $(".coins span").empty().append(coins);


}

function makeArrayFromForm() {
    var cardArray = [];

    var k = 0;

    for (var i = 0; i <= 24; i++) {
        if ($("label").eq(i).next().is(":checked")) {

            for (var j = 0; j <= 24; j++) {
                if ($("label").eq(i).next().val() == AllCards[j]) {
                    cardArray[k] = j;
                    k++;

                }

            }


        }
    }

    return (cardArray);


}


function selectDeck() {
    for (var i = 0; i < 10; i++) {

        $("input[type='checkbox']").eq(i).prop('checked', true).prev().css("color", "orange");
        $(".cardsLeft span").empty().append(0);

    }


}

function changecolorAndNumber() {


    if (!$(this).next().is(":checked") && (makeCounter() > 0)) {
        this.style.color = "orange";


    }
    else {
        this.style.color = "white";

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

function removeFromHand(image) {

    for (var i = 0; i < $(".hand li").length && !badPractice; i++) {
        if ($(".hand li").eq(i).css("background-image") == image) {
            $(".hand li").eq(i).remove();
            var badPractice = true;
        }
    }

}


function playTreasure(treasure) {

    getTreasure();
    var handLength = $(".hand li").length;
    var i = 0;
    while (i < handLength) {// geen for loop want cardToField neemt altijd de eerste kaart van het type dat je op klikte en niet de kaart zelf

        if ($(".hand li").eq(i).hasClass("treasure")) {

            cardToField($(".hand li").eq(i).css("background-image"));

        }
        else {
            i++;
        }

    }


}
function getTreasure() {

    var response = $.ajax({
        dataType: "text",
        url: "/DominionServlet",
        data: {
            operation: "playTreasure"


        }


    });
    response.done(function (data) {
        var treasureObject = JSON.parse(data);
        $(".coins span").empty().append(treasureObject.coins);


    });

}
function endTurn() {

    var response = $.ajax({
        dataType: "text",
        url: "/DominionServlet",
        data: {
            operation: "endTurn"


        }


    });
    response.done(function (data) {
        startTurn()

    });

}

function buyCard(cardName) {

    var response = $.ajax({
        dataType: "text",
        url: "/DominionServlet",
        data: {
            operation: "buyCard",
            cardPlace: cardName,
            coins:$(".coins span").html()
            


        }


    });
    response.done(function (data) {
        var buyInfo = JSON.parse(data);
        
        if(buyInfo.bought == "true"){
            console.log("you bought a "+buyInfo.cardBought);
            if(!buyInfo.buysLeft > 0 ){
                endTurn();
            }
            else{
                setValues(0,buyInfo.buysLeft,buyInfo.treasureLeft);
            }
        }
        else{
            console.log("you don't have enough money to buy that card!")
        }
        
        
       
        
    });

}

function selectCardToBuy() {

    if ($(".phase span").html() == "buy") {

        var cardUrl = $(this).css("background-image");
        var cardName = cardUrl.split("/");
        cardName = cardName[cardName.length - 1].split(".");
        cardName = cardName[0];
        buyCard(cardName);
    }




}


function cardToField(image) {
    console.log(image);
    if (typeof (image) != "string") {
        var image = $(this).css("background-image");
    }

    $(".playmat ul").append("<li></li>");
    $(".playmat li:last-of-type").css("background-image", image);
    removeFromHand(image);


}

function shopToHand() {

    var image = $(this).css("background-image");

    $(".hand ul ").append("<li></li>");
    $(".hand ul li:last-of-type").css("background-image", image);


}


function cardPlace(cardName) {
    for (var i = 0; i < AllCards.length; i++) {
        if (AllCards[i] == cardName) {
            return i;
        }
    }
}
function addToHand(card) {

    var image = 'url("assets/media/images/Cards/' + card + '.jpg")';
    if (card == "copper" | card == "silver" | card == "gold") {
        $(".hand ul ").append("<li class='treasure'></li>");
    }
    else {
        $(".hand ul ").append("<li></li>");
    }

    $(".hand ul li:last-of-type").css("background-image", image);


}

function showCardPreview() {
    $(".cardPreview").show();
    $(".cardPreview").css("background-image", 'url("assets/media/images/Cards/' + $(this).next().val() + '.jpg")');



}
function emptyCardPreview() {
    $(".cardPreview").hide();
}

function changeCurrentName(currentName) {
    $(".activePlayer span").empty().append(currentName);
    console.log(currentName);
}

$(document).ready(function () {
    var limit = 11;


    $('input.single-checkbox').on('change', function (evt) {
        if ($(this).siblings(':checked').length >= limit) {
            this.checked = false;
        }
    });


    $("input[name='amount']").on("change", addInput);
    $('#playerForm').on('submit', sendInit);
    $('.cardChoice label').on("click", changecolorAndNumber);
    $('.cardChoice label').hover(showCardPreview, emptyCardPreview);
    $("input[type='checkbox']").on('change', changeNumber);
    $('.deckSubmit').on('click', sendArray);
    $(".standardDeckSelect").on("click", selectDeck);
    $('.hand ul').on("click", 'li', cardToField);
    $(".table div div, .valuables div div").on("click",selectCardToBuy);
     $(".endTurn").on("click", endTurn);
    $(".playTreasure").on("click", playTreasure);


});