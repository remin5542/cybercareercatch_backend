document.addEventListener("DOMContentLoaded", function () {
    const jobRadios = document.querySelectorAll("input[name='jobNumber']");
    const saveButton = document.querySelector(".job-checkdetail-save");

    if (!saveButton) {
        return;
    }

    saveButton.addEventListener("click", function (e) {
        let isChecked = false;

        jobRadios.forEach(function (radio) {
            if (radio.checked) {
                isChecked = true;
            }
        });

        if (!isChecked) {
            alert("직군을 선택하세요.");
            e.preventDefault();
            return;
        }

        alert("직군 판정이 저장됩니다.");
    });
});