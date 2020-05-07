$(document).ready(function() {
    $('#a').blur(function(event) {
        add();
    });
    $('#b').blur(function(event) {
        add();
    });

    function add() {
        var val1 = $('#a').val();
        var val2 = $('#b').val();
        $.ajax({
            url : '/add',
            data : {
                value1 : val1,
                value2 : val2
            },
            success : function(responseText) {
                $('#ajaxAddServletResponse').text(responseText);
            }
        });
    }
    setInterval(function () {
        $.ajax({
            url: '/date',
            success: function (responseText) {
                $('#date').text(responseText);
            }
        });
    }, 1000)
});
