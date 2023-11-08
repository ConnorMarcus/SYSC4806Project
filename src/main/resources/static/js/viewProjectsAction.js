$(document).ready(function() {
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");

    $(document).ajaxSend(function(e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });

    function getProjectID(elem) {
        return $(elem).parent().attr('project-id')
    }

   $('#delete').on('click', function () {
       var url = window.location.origin + "/deleteProject/" + getProjectID(this);

       $.ajax({
           type: 'DELETE',
           url: url
       })
           .done(function () {
               window.location.reload()
           })
   });
});