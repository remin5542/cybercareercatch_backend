document.addEventListener("DOMContentLoaded", function () {
    const params = new URLSearchParams(window.location.search);
    const result = params.get("result");

    if (result === "approve") {
        alert("승인 완료하셨습니다");
    } else if (result === "reject") {
        alert("반려 완료했습니다");
    }

    if (result) {
        params.delete("result");

        const newQuery = params.toString();
        const newUrl = window.location.pathname + (newQuery ? "?" + newQuery : "");

        window.history.replaceState({}, document.title, newUrl);
    }

    const buttons = document.querySelectorAll(".company-checkdetail-btn-wrap button[type='submit']");

    buttons.forEach(function (button) {
        button.addEventListener("click", function (event) {
            const message = button.dataset.confirmMessage || "처리하시겠습니까?";

            if (!confirm(message)) {
                event.preventDefault();
            }
        });
    });
});