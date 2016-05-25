/*
 * Created by Jonas Gossiaux.
 * */

var AllCards = ["garden", "smithy", "village", "festival", "market", "laboratory", "moat", "woodcutter", "chancellor", "adventurer", "council Room", "witch", "throne room", "chapel", "moneylender", "cellar", "workshop", "feast", "remodel", "library", "mine", "spy", "thief", "militia", "bureaucrat"];

var standardCards = [1, 8, 18, 19, 21, 13, 4, 6, 9, 5];

var boolean = true;

var globalCard;


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
        if (playerObject.actionsInHand == "false") {
            setPhase("buy");
        }
        else if (playerObject.actionsInHand == "true") {
            setPhase("action")
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

        $("input[type='checkbox']").eq(standardCards[i]).prop('checked', true).prev().css("color", "orange");
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

    setPhase("buy");
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
            coins: $(".coins span").html()


        }


    });
    response.done(function (data) {
        var buyInfo = JSON.parse(data);

        if (buyInfo.bought == "true") {

            if (!buyInfo.buysLeft > 0) {
                endTurn();
            }
            else {
                setValues(0, buyInfo.buysLeft, buyInfo.treasureLeft);
            }
        }
        else {
            alert("you can't buy this card, either you don't have enough coins or the stack is empty");
        }


    });

}

function selectCardToBuy() {
    checkIfOpenShop();

    if ($(".phase span").html() == "buy" && boolean) {

        var cardUrl = $(this).css("background-image");
        var cardName = makeCardFromUrl(cardUrl);
        buyCard(cardName);
    }


}
function makeCardFromUrl(cardUrl) {
    var cardName = cardUrl.split("/");
    cardName = cardName[cardName.length - 1].split(".");
    cardName = cardName[0];
    return cardName;
}


function cardToField(image) {

    if (typeof (image) != "string") {
        var image = $(this).css("background-image");
    }

    if (!$(this).hasClass("victory") && !$(this).hasClass("action")) {

        $(".playmat ul").append("<li></li>");
        $(".playmat li:last-of-type").css("background-image", image);
        removeFromHand(image);
    }

    if (($(this).hasClass("action")) && ($('.phase span').html() == "action") && parseInt($('.actions span').html()) > 0) {
        $(".playmat ul").append("<li></li>");
        $(".playmat li:last-of-type").css("background-image", image);
        removeFromHand(image);
        playAction(makeCardFromUrl(image));

    }
    if ($(".playmat li").length > 6) {
        var newLength = 900 / $('.playmat li').length;
        $(".playmat li").css("width", newLength);

    }


}


function playAction(cardName) {

    askPlayerSomething(cardName);


    var response = $.ajax({
        dataType: "text",
        url: "/DominionServlet",
        data: {
            operation: "actionCard",
            cardName: cardName
        }


    });
    response.done(function (data) {


        var afterAction = JSON.parse(data);

        setValues(afterAction.actions, afterAction.buys, afterAction.coinsLeft);

        newHand(afterAction.newHand);

        if (afterAction.actionsInHand != true) {

            setPhase("buy")
        }

    });


}

function chooseThisCard() {
    var card = makeCardFromUrl($(this).css("background-image"));
    oneTimeBuy(card);

}

function oneTimeBuy(card) {
    var maxPrice = 4;

    if (globalCard == "feast") {
        maxPrice = 5;
    }

    var response = $.ajax({
        dataType: "text",
        url: "/DominionServlet",
        data: {
            operation: "oneTimeBuy",
            card: card,
            maxPrice: maxPrice
        }


    });
    response.done(function (data) {

        var data = JSON.parse(data);
        var valid = data.bought;

        if (valid) {
            $(".oneTimeBuy").hide();
            $(".oneTimeBuy span").empty();
        }
        else {
            $(".oneTimeBuy span").empty().append("that card is to expensive! Try again.");

        }


    });

}

function askPlayerSomething(cardName) {


    switch (cardName) {

        case "chapel":

            $(".askScreen ul ").append($(".hand li"));
            $(".askScreen").show();
            $(".askScreen").on("click", "input", sendCardsToTrash);


            break;

        case "workshop":
            globalCard = "workshop";
            $(".oneTimeBuy strong").empty().append("take a card costing 4 or less");
            $('.oneTimeBuy').show();
            for (var i = 0; i < 10; i++) {
                $('.cardList div').eq(i).css("background-image", $(".shopCards").eq(i).css("background-image"));
            }
            $(".cardList").on("click", "div", chooseThisCard);


            break;

        case "feast":
            globalCard = "feast";
            $(".oneTimeBuy strong").empty().append("take a card costing 5 or less");
            $('.oneTimeBuy').show();
            for (var i = 0; i < 10; i++) {
                $('.cardList div').eq(i).css("background-image", $(".shopCards").eq(i).css("background-image"));
            }
            $(".cardList").on("click", "div", chooseThisCard);


            break;


        default:
            globalCard = "";
            break;


    }


}


function selectThisCard() {

    if ($(this).hasClass("selected")) {

        $(this).css("border", "").removeClass("selected");

    }
    else if ($(".askScreen .selected").length < 4) {
        $(this).css("border", "solid").css("border-color", "white").addClass("selected");
    }


}


function sendCardsToTrash() {


    var cardsToDiscard = [];
    var urlArray = [];

    for (var i = 0; i < $(".askScreen ul li").length; i++) {

        if ($(".askScreen ul li").eq(i).hasClass("selected")) {
            cardsToDiscard.push(makeCardFromUrl($(".askScreen ul li").eq(i).css("background-image")));
            urlArray.push($(".askScreen ul li").eq(i).css("background-image"));
        }

    }
    sendCardsToServlet(cardsToDiscard);
    $('.askScreen ul ').empty();

    $(".askScreen").hide();

    for (var i = 0; i < cardsToDiscard.length; i++) {

        removeFromHand(urlArray[i]);
    }

}

function sendCardsToServlet(cardsToDiscard) {

    var response = $.ajax({
        dataType: "text",
        url: "/DominionServlet",
        data: {
            operation: "discard",
            cards: JSON.stringify(cardsToDiscard)
        }


    });
    response.done(function (data) {


    });


}


function checkIfOpenShop() {

    var response = $.ajax({
        dataType: "text",
        url: "/DominionServlet",
        data: {
            operation: "shopIsopen"

        }


    });
    response.done(function (data) {

        var shopCheck = JSON.parse(data);
        boolean = shopCheck.shopIsopen;


    });

    if (!boolean) {
        endGame();
    }


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

    else if (card == "estate" | card == "duchy" | card == "province") {
        $(".hand ul ").append("<li class='victory'></li>");
    }
    else {
        $(".hand ul ").append("<li class='action'></li>");
    }

    $(".hand ul li:last-of-type").css("background-image", image);

    if ($(".hand li").length > 6) {
        var newLength = 900 / $('.hand li').length;
        $(".hand li").css("width", newLength);

    }

}


function newHand(cardArray) {
    $(".hand ul").empty();
    for (var i = 0; i < cardArray.length; i++) {
        addToHand(cardArray[i]);
    }


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

}


function endGame() {


    var response = $.ajax({
        dataType: "text",
        url: "/DominionServlet",
        data: {
            operation: "endGame"

        }


    });
    response.done(function (data) {
        var winnersAndLosers = JSON.parse(data);
        var html = "THE GAME HAS ENDED: </br> </br>";
        html += "the winner is: " + winnersAndLosers.winner + " with " + winnersAndLosers.winnerPoints + " points! </br>";
        html += winnersAndLosers.loser1 + " had " + winnersAndLosers.loser1points + " points! </br>";

        if (typeof (winnersAndLosers.loser2 ) != "undefined") {
            html += winnersAndLosers.loser2 + " had " + winnersAndLosers.loser2points + " points! ";
        }

        $(".endScreen").append(html).show();
        $(".playfield").hide();


    });


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
    $(".table div div, .valuables div div").on("click", selectCardToBuy);
    $(".endTurn").on("click", endTurn);
    $(".playTreasure").on("click", playTreasure);
    $(".askScreen ").on("click", 'li', selectThisCard);
    $(".askScreen").hide();
    $(".oneTimeBuy").hide();
});