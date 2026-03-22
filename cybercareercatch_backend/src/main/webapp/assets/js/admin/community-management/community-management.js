document.addEventListener("DOMContentLoaded", function () {
    const deleteForm = document.getElementById("freePostDeleteForm");

    if (!deleteForm) {
        return;
    }

    deleteForm.addEventListener("submit", function (event) {
        const checkedList = deleteForm.querySelectorAll("input[name='postNumber']:checked");

        if (checkedList.length === 0) {
            alert("삭제할 게시글을 선택하세요.");
            event.preventDefault();
            return;
        }

        const isConfirmed = confirm("선택한 자유 게시판 글을 삭제하시겠습니까?");

        if (!isConfirmed) {
            event.preventDefault();
        }
    });
});