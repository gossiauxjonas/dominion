var AllCards = ["gardens", "smithy", "village", "festival", "market", "laboratory", "moat", "woodcutter", "chancellor", "adventurer", "councilroom", "witch", "throneroom", "chapel", "moneylender", "cellar", "workshop", "feast", "remodel", "library", "mine", "spy", "thief", "militia", "bureaucrat"];


function createStandardShop() {

    for (var i = 0; i < 10; i++) {

        $(".shopCards").eq(i).css("background-image", 'url("assets/media/images/Cards/' + AllCards[i] + '.jpg")');

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
            operation: "init",
            player1: player1,
            player2: player2,
            player3: player3


        }


    });
    response.done(function (data) {


    });


}


function startTurn() {

    var response = $.ajax({
        dataType: "text",
        url: "/DominionServlet",
        data: {
            operation: "loop"


        }


    });
    response.done(function (data) {
        console.log("de loop ajax call");
        console.log(JSON.parse(data));


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
            json: JSON.stringify(cardArray)},
        contentType: 'application/json',
        mimeType: 'application/json'


    });

    response.done(function (data) {


    });
    console.log("sendArray worked");

}


function makeArrayFromForm() {
    var cardArray = [];

    var k = 0;
    
    for (var i = 0; i < 24; i++)
    {
        if ($("label").eq(i).next().is(":checked")) 
        {

            for (var j = 0; j < 24; j++)
            {
                if ($("label").eq(i).next().val() == AllCards[j])
                {
                    cardArray[k] = j;
                    k++;

                }

            }




        }
    }
    console.log(cardArray);
    return(cardArray);


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
    var image = $(this).css("background-image");

    $(".playmat ul").append("<li></li>");
    $(".playmat li:last-of-type").css("background-image", image);


}

function shopToHand() {

    var image = $(this).css("background-image");

    $(".hand ul ").append("<li></li>");
    $(".hand ul li:last-of-type").css("background-image", image);


}


$(document).ready(function () {
    var limit = 11;

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
    $('.hand ul').on("click", "li", cardToField);
    $('.shopCards').on('click', shopToHand);

});