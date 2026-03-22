document.addEventListener("DOMContentLoaded", function () {
    const rows = document.querySelectorAll(".job-check-row:not(.job-check-head)");
    const pageButtons = document.querySelectorAll(".job-check-page");

    rows.forEach(function (row) {
        row.addEventListener("mouseenter", function () {
            row.classList.add("is-hover");
        });

        row.addEventListener("mouseleave", function () {
            row.classList.remove("is-hover");
        });
    });

    pageButtons.forEach(function (button) {
        button.addEventListener("click", function () {
            pageButtons.forEach(function (item) {
                item.classList.remove("job-check-page-active");
            });

            button.classList.add("job-check-page-active");
        });
    });
});