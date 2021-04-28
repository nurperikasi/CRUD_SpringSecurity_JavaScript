/**
 *
 */

$( document ).ready(function() {

    $('.table #deleteButton').on('click', function (event) {

        event.preventDefault();

        var href = $(this).attr('href');

        $.get(href, function (user, roles) {
            $('#deleteId').val(user.id)
            $('#deleteName').val(user.name)
            $('#deleteLastName').val(user.lastName)
            $('#deletePassword').val(user.password)

        });

        $('#deleteModalPage').modal();

    });
});