function definePlayerArray() {
    var playerArray = [$("#player1").val(), $("#player2").val()];
    
    if($("#player3").val() == !null){
        playerArray.push($("#player3").val())
    }
    $('#playerForm').hide();

    console.log(playerArray);
}


function addInput() {

    if ($(this).val() == "three") {

        $(".extraPlayer").show();
        console.log("three");
    }
    else {
        $(".extraPlayer").hide();
        console.log("twoo")
    }
    console.log("somehting happend ");
}

$(document).ready(function () {
    $('#playerForm').on('submit', definePlayerArray);
    $("input[name='amount']").on("change", addInput);

});