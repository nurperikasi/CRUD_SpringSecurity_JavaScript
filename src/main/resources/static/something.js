/**
 *
 */

$( document ).ready(function() {

    $('.table #editButton').on('click',function(event){

        event.preventDefault();

        var href=$(this).attr('href');

        $.get(href,function(user, roles){
            $('#editId').val(user.id)
            $('#editName').val(user.name)
            $('#editLastName').val(user.lastName)
            $('#editPassword').val(user.password)

            $('form' ).submit(function() {
                let roles = [];

                $('input:checked[name=roles[]]').each(function () {
                    roles.put($(this).val());
                });
            });
            $('#editRoles').val(roles)
        });

        $('#editModalPage').modal();

    });

});