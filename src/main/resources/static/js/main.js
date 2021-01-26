$(document).ready(function () {
    console.log("init")

    $("#searchButton").click(function () {
        const query = $("#searchBox").val();
        window.location.href = "/main?query=" + query
    });

    $("#searchBox").keydown(function(key) {
        if (key.keyCode === 13) {
            const query = $("#searchBox").val();
            window.location.href = "/main?query=" + query
        }
    });
});