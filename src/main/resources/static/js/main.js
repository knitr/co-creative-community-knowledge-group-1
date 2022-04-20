$(document).ready(function () {
    $('#sidebarCollapse').on('click', function () {
        $('#sidebar').toggleClass('active');
    });

    $('#communityList').on('click', function () {
        $('#communitySubmenu').collapse('toggle');
    });

    $('#resourcesList').on('click', function () {
        $('#resourcesSubMenu').collapse('toggle');
    });

    $('#trainingList').on('click', function () {
        $('#trainingSubMenu').collapse('toggle');
    });

});

$(document).ready(function () {
    $("#createPost").focus(function () {
        console.log("Focus")
        $(".postbody").show(); //show when user moves focus in the textarea
    });

});

function showCommentSection(id) {
    var x = document.getElementById("commentSection" + id);
    if (x.style.display === "none") {
        x.style.display = "block";
    } else {
        x.style.display = "none";
    }
}


document.addEventListener("DOMContentLoaded", function () {
    window.addEventListener('scroll', function () {
        if (window.scrollY > 0) {
            document.getElementById('navbar').classList.add('sticky');
            // add padding top to show content behind navbar
            let navbar_height = document.querySelector('.navbar').offsetHeight;
            document.body.style.paddingTop = navbar_height + 'px';
        } else {
            document.getElementById('navbar').classList.remove('sticky');
            // remove padding top from body
            document.body.style.paddingTop = '0';
        }
    });
});

