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

        for (var i = 0; i < playerObject.hand.length; i++) {
            addToHand(playerObject.hand[i])
        }


        changeCurrentName(playerObject.playerName);
        setValues(playerObject.actions, playerObject.buys, 0)


    });


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
    console.log(cardArray);
    return (cardArray);


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

function removeFromHand(image) {

    for (var i = 0; i < $(".hand li").length && !badPractice; i++) {
        if ($(".hand li").eq(i).css("background-image") == image) {
            $(".hand li").eq(i).remove();
            var badPractice = true;
        }
    }

}

function checkForActionCards() {

    var response = $.ajax({
        dataType: "text",
        url: "/DominionServlet",
        data: {
            operation: "actions"


        }


    });
    response.done(function (data) {
        var actionsInHand = JSON.stringify(data.actions);
        if (actionsInHand == "none") {
            buyPhase();
        }
        else {

        }

        buyPhase();
    });


}

function buyPhase() {


}

function playTreasure() {
    var handLength = $(".hand li").length;

    for (var i = 0; i <= handLength; i++) {

        if ($(".hand li").eq(i).hasClass("treasure")) {

            cardToField($(".hand li").eq(i).css("background-image"));
            console.log("treasure founbd")
        }
        else{
            console.log("treasure not founbd")
        }
    }


}
function endTurn() {
    $(".endTurn").addClass("selected");
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

function changeCurrentName(currentName) {
    $(".activePlayer span").empty().append(currentName);
    console.log(currentName);
}

$(document).ready(function () {
    var limit = 11;
    buyPhase();

    $('input.single-checkbox').on('change', function (evt) {
        if ($(this).siblings(':checked').length >= limit) {
            this.checked = false;
        }
    });


    $("input[name='amount']").on("change", addInput);
    $('#playerForm').on('submit', sendInit);
    $('.cardChoice label').on("click", changecolorAndNumber);
    $("input[type='checkbox']").on('change', changeNumber);
    $('.deckSubmit').on('click', sendArray);
    $('.hand ul').on("click", 'li', cardToField);
    $('.shopCards').on('click', shopToHand);

    $(".endTurn").on("click", endTurn);
    $(".playTreasure").on("click", playTreasure);


});