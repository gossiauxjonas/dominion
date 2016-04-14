function definePlayerArray() {
    var playerArray = [$("#player1").val(), $("#player2").val()]
    console.log(playerArray);
    $('#playerForm').hide();
}

$(document).ready(function () {
    $('#playerForm').on('submit', definePlayerArray);
});